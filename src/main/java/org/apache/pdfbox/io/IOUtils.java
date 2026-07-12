package org.apache.pdfbox.io;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.io.RandomAccessStreamCache;

/* loaded from: target.jar:org/apache/pdfbox/io/IOUtils.class */
public final class IOUtils {
    private static final RandomAccessStreamCache.StreamCacheCreateFunction streamCache;
    private static final Optional<Consumer<ByteBuffer>> UNMAPPER;
    private static final Log LOG;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !IOUtils.class.desiredAssertionStatus();
        streamCache = RandomAccessStreamCacheImpl::new;
        UNMAPPER = Optional.ofNullable(AccessController.doPrivileged(IOUtils::unmapper));
        LOG = LogFactory.getLog(IOUtils.class);
    }

    private IOUtils() {
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        copy(in, baout);
        return baout.toByteArray();
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0;
        while (true) {
            int n = input.read(buffer);
            if (-1 != n) {
                output.write(buffer, 0, n);
                count += n;
            } else {
                return count;
            }
        }
    }

    public static long populateBuffer(InputStream in, byte[] buffer) throws IOException {
        int remaining;
        int length = buffer.length;
        while (true) {
            remaining = length;
            if (remaining <= 0) {
                break;
            }
            int bufferWritePos = buffer.length - remaining;
            int bytesRead = in.read(buffer, bufferWritePos, remaining);
            if (bytesRead < 0) {
                break;
            }
            length = remaining - bytesRead;
        }
        return buffer.length - remaining;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ioe) {
                LOG.debug("An exception occurred while trying to close - ignoring", ioe);
            }
        }
    }

    public static IOException closeAndLogException(Closeable closeable, Log logger, String resourceName, IOException initialException) {
        try {
            closeable.close();
        } catch (IOException ioe) {
            logger.warn("Error closing " + resourceName, ioe);
            if (initialException == null) {
                return ioe;
            }
        }
        return initialException;
    }

    public static void unmap(ByteBuffer buf) {
        if (buf != null) {
            try {
                UNMAPPER.ifPresent(u -> {
                    u.accept(buf);
                });
            } catch (Exception e) {
                LOG.error("Unable to unmap ByteBuffer.", e);
            }
        }
    }

    private static Consumer<ByteBuffer> unmapper() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            try {
                try {
                    Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                    MethodHandle unmapper = lookup.findVirtual(unsafeClass, "invokeCleaner", MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                    Field f = unsafeClass.getDeclaredField("theUnsafe");
                    f.setAccessible(true);
                    Object theUnsafe = f.get(null);
                    return newBufferCleaner(ByteBuffer.class, unmapper.bindTo(theUnsafe));
                } catch (SecurityException se) {
                    LOG.error("Unmapping is not supported because of missing permissions. Please grant at least the following permissions: RuntimePermission(\"accessClassInPackage.sun.misc\")  and ReflectPermission(\"suppressAccessChecks\")", se);
                    return null;
                }
            } catch (ReflectiveOperationException | RuntimeException e) {
                Class<?> directBufferClass = Class.forName("java.nio.DirectByteBuffer");
                Method m = directBufferClass.getMethod("cleaner", new Class[0]);
                m.setAccessible(true);
                MethodHandle directBufferCleanerMethod = lookup.unreflect(m);
                Class<?> cleanerClass = directBufferCleanerMethod.type().returnType();
                MethodHandle cleanMethod = lookup.findVirtual(cleanerClass, "clean", MethodType.methodType(Void.TYPE));
                MethodHandle nonNullTest = lookup.findStatic(Objects.class, "nonNull", MethodType.methodType((Class<?>) Boolean.TYPE, (Class<?>) Object.class)).asType(MethodType.methodType((Class<?>) Boolean.TYPE, cleanerClass));
                MethodHandle noop = MethodHandles.dropArguments(MethodHandles.constant(Void.class, null).asType(MethodType.methodType(Void.TYPE)), 0, (Class<?>[]) new Class[]{cleanerClass});
                MethodHandle unmapper2 = MethodHandles.filterReturnValue(directBufferCleanerMethod, MethodHandles.guardWithTest(nonNullTest, cleanMethod, noop)).asType(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class));
                return newBufferCleaner(directBufferClass, unmapper2);
            } catch (SecurityException se2) {
                throw se2;
            }
        } catch (ReflectiveOperationException | RuntimeException e2) {
            LOG.error("Unmapping is not supported.", e2);
            return null;
        }
    }

    private static Consumer<ByteBuffer> newBufferCleaner(Class<?> unmappableBufferClass, MethodHandle unmapper) {
        if ($assertionsDisabled || Objects.equals(MethodType.methodType((Class<?>) Void.TYPE, (Class<?>) ByteBuffer.class), unmapper.type())) {
            return buffer -> {
                if (!buffer.isDirect()) {
                    throw new IllegalArgumentException("unmapping only works with direct buffers");
                }
                if (!unmappableBufferClass.isInstance(buffer)) {
                    throw new IllegalArgumentException("buffer is not an instance of " + unmappableBufferClass.getName());
                }
                Throwable e = (Throwable) AccessController.doPrivileged(() -> {
                    try {
                        (void) unmapper.invokeExact(buffer);
                        return null;
                    } catch (Throwable t) {
                        return t;
                    }
                });
                if (Objects.nonNull(e)) {
                    LOG.error("Unable to unmap the mapped buffer", e);
                }
            };
        }
        throw new AssertionError();
    }

    public static RandomAccessStreamCache.StreamCacheCreateFunction createMemoryOnlyStreamCache() {
        return streamCache;
    }

    public static RandomAccessStreamCache.StreamCacheCreateFunction createTempFileOnlyStreamCache() {
        return MemoryUsageSetting.setupTempFileOnly().streamCache;
    }
}

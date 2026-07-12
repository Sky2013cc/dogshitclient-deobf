package okhttp3;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import jdk.internal.dynalink.CallSiteDescriptor;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.tools.java.RuntimeConstants;

/* compiled from: Route.kt */
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\u0018��2\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\u000eJ\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\tR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\nR\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u000b¨\u0006\u0017"}, d2 = {"Lokhttp3/Route;", "", "address", "Lokhttp3/Address;", "proxy", "Ljava/net/Proxy;", "socketAddress", "Ljava/net/InetSocketAddress;", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "()Lokhttp3/Address;", "()Ljava/net/Proxy;", "()Ljava/net/InetSocketAddress;", "-deprecated_address", "equals", "", "other", "hashCode", "", "-deprecated_proxy", "requiresTunnel", "-deprecated_socketAddress", "toString", "", "okhttp"})
/* loaded from: target.jar:okhttp3/Route.class */
public final class Route {

    @NotNull
    private final Address address;

    @NotNull
    private final Proxy proxy;

    @NotNull
    private final InetSocketAddress socketAddress;

    public Route(@NotNull Address address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        this.address = address;
        this.proxy = proxy;
        this.socketAddress = socketAddress;
    }

    @JvmName(name = "address")
    @NotNull
    public final Address address() {
        return this.address;
    }

    @JvmName(name = "proxy")
    @NotNull
    public final Proxy proxy() {
        return this.proxy;
    }

    @JvmName(name = "socketAddress")
    @NotNull
    public final InetSocketAddress socketAddress() {
        return this.socketAddress;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "address", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_address")
    @NotNull
    /* renamed from: -deprecated_address, reason: not valid java name */
    public final Address m3724deprecated_address() {
        return this.address;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_proxy")
    @NotNull
    /* renamed from: -deprecated_proxy, reason: not valid java name */
    public final Proxy m3725deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "socketAddress", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_socketAddress")
    @NotNull
    /* renamed from: -deprecated_socketAddress, reason: not valid java name */
    public final InetSocketAddress m3726deprecated_socketAddress() {
        return this.socketAddress;
    }

    public final boolean requiresTunnel() {
        if (this.proxy.type() != Proxy.Type.HTTP) {
            return false;
        }
        return this.address.sslSocketFactory() != null || this.address.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE);
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof Route) && Intrinsics.areEqual(((Route) other).address, this.address) && Intrinsics.areEqual(((Route) other).proxy, this.proxy) && Intrinsics.areEqual(((Route) other).socketAddress, this.socketAddress);
    }

    public int hashCode() {
        int result = (31 * 17) + this.address.hashCode();
        return (31 * ((31 * result) + this.proxy.hashCode())) + this.socketAddress.hashCode();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004e  */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String toString() {
        String str;
        String socketHostname;
        StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
        String addressHostname = this.address.url().host();
        InetAddress address = this.socketAddress.getAddress();
        if (address != null) {
            String hostAddress = address.getHostAddress();
            if (hostAddress != null) {
                Intrinsics.checkNotNull(hostAddress);
                str = _HostnamesCommonKt.toCanonicalHost(hostAddress);
                socketHostname = str;
                if (StringsKt.contains$default((CharSequence) addressHostname, ':', false, 2, (Object) null)) {
                    $this$toString_u24lambda_u240.append(addressHostname);
                } else {
                    $this$toString_u24lambda_u240.append(RuntimeConstants.SIG_ARRAY).append(addressHostname).append("]");
                }
                if (this.address.url().port() == this.socketAddress.getPort() || Intrinsics.areEqual(addressHostname, socketHostname)) {
                    $this$toString_u24lambda_u240.append(CallSiteDescriptor.TOKEN_DELIMITER);
                    $this$toString_u24lambda_u240.append(this.address.url().port());
                }
                if (!Intrinsics.areEqual(addressHostname, socketHostname)) {
                    if (Intrinsics.areEqual(this.proxy, Proxy.NO_PROXY)) {
                        $this$toString_u24lambda_u240.append(" at ");
                    } else {
                        $this$toString_u24lambda_u240.append(" via proxy ");
                    }
                    if (socketHostname == null) {
                        $this$toString_u24lambda_u240.append("<unresolved>");
                    } else if (StringsKt.contains$default((CharSequence) socketHostname, ':', false, 2, (Object) null)) {
                        $this$toString_u24lambda_u240.append(RuntimeConstants.SIG_ARRAY).append(socketHostname).append("]");
                    } else {
                        $this$toString_u24lambda_u240.append(socketHostname);
                    }
                    $this$toString_u24lambda_u240.append(CallSiteDescriptor.TOKEN_DELIMITER);
                    $this$toString_u24lambda_u240.append(this.socketAddress.getPort());
                }
                String sb = $this$toString_u24lambda_u240.toString();
                Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
                return sb;
            }
        }
        str = null;
        socketHostname = str;
        if (StringsKt.contains$default((CharSequence) addressHostname, ':', false, 2, (Object) null)) {
        }
        if (this.address.url().port() == this.socketAddress.getPort()) {
        }
        $this$toString_u24lambda_u240.append(CallSiteDescriptor.TOKEN_DELIMITER);
        $this$toString_u24lambda_u240.append(this.address.url().port());
        if (!Intrinsics.areEqual(addressHostname, socketHostname)) {
        }
        String sb2 = $this$toString_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}

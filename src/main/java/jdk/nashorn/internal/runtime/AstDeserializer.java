package jdk.nashorn.internal.runtime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.InflaterInputStream;
import jdk.nashorn.internal.ir.FunctionNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/nashorn/internal/runtime/AstDeserializer.class */
public final class AstDeserializer {
    AstDeserializer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FunctionNode deserialize(byte[] serializedAst) {
        try {
            return (FunctionNode) new ObjectInputStream(new InflaterInputStream(new ByteArrayInputStream(serializedAst))).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError("Unexpected exception deserializing function", e);
        }
    }
}

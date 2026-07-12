package jdk.nashorn.internal.runtime.options;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/options/Option.class */
public class Option<T> {
    protected T value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Option(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public String toString() {
        return getValue() + " [" + getValue().getClass() + "]";
    }
}

package net.ccbluex.liquidbounce.ui.client.hud.element;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: Element.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018�� \u00102\u00020\u0001:\u0003\u0010\u0011\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "", "horizontal", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;", "vertical", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;", Constants.CTOR, "(Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;)V", "getHorizontal", "()Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;", "setHorizontal", "(Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;)V", "getVertical", "()Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;", "setVertical", "(Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;)V", "Companion", "Horizontal", "Vertical", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side.class */
public final class Side {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private Horizontal horizontal;

    @NotNull
    private Vertical vertical;

    public Side(@NotNull Horizontal horizontal, @NotNull Vertical vertical) {
        Intrinsics.checkNotNullParameter(horizontal, "horizontal");
        Intrinsics.checkNotNullParameter(vertical, "vertical");
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @NotNull
    public final Horizontal getHorizontal() {
        return this.horizontal;
    }

    public final void setHorizontal(@NotNull Horizontal horizontal) {
        Intrinsics.checkNotNullParameter(horizontal, "<set-?>");
        this.horizontal = horizontal;
    }

    @NotNull
    public final Vertical getVertical() {
        return this.vertical;
    }

    public final void setVertical(@NotNull Vertical vertical) {
        Intrinsics.checkNotNullParameter(vertical, "<set-?>");
        this.vertical = vertical;
    }

    /* compiled from: Element.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Companion;", "", Constants.CTOR, "()V", "default", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        /* renamed from: default, reason: not valid java name */
        public final Side m3436default() {
            return new Side(Horizontal.LEFT, Vertical.UP);
        }
    }

    /* compiled from: Element.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018�� \u000b2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;", "", "sideName", "", Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getSideName", "()Ljava/lang/String;", "LEFT", "MIDDLE", "RIGHT", "Companion", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal.class */
    public enum Horizontal {
        LEFT("Left"),
        MIDDLE(PDLayoutAttributeObject.BLOCK_ALIGN_MIDDLE),
        RIGHT("Right");


        @NotNull
        private final String sideName;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static final Companion Companion = new Companion(null);

        Horizontal(String sideName) {
            this.sideName = sideName;
        }

        @NotNull
        public final String getSideName() {
            return this.sideName;
        }

        /* compiled from: Element.kt */
        @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal$Companion;", "", Constants.CTOR, "()V", "getByName", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal;", "name", "", "haze"})
        @SourceDebugExtension({"SMAP\nElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Element.kt\nnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,202:1\n1#2:203\n*E\n"})
        /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side$Horizontal$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @Nullable
            public final Horizontal getByName(@NotNull String name) {
                Object obj;
                Intrinsics.checkNotNullParameter(name, "name");
                Iterator<E> it = Horizontal.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    Object next = it.next();
                    Horizontal it2 = (Horizontal) next;
                    if (Intrinsics.areEqual(it2.getSideName(), name)) {
                        obj = next;
                        break;
                    }
                }
                return (Horizontal) obj;
            }
        }

        @NotNull
        public static EnumEntries<Horizontal> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: Element.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018�� \u000b2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;", "", "sideName", "", Constants.CTOR, "(Ljava/lang/String;ILjava/lang/String;)V", "getSideName", "()Ljava/lang/String;", "UP", "MIDDLE", "DOWN", "Companion", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical.class */
    public enum Vertical {
        UP("Up"),
        MIDDLE(PDLayoutAttributeObject.BLOCK_ALIGN_MIDDLE),
        DOWN("Down");


        @NotNull
        private final String sideName;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static final Companion Companion = new Companion(null);

        Vertical(String sideName) {
            this.sideName = sideName;
        }

        @NotNull
        public final String getSideName() {
            return this.sideName;
        }

        /* compiled from: Element.kt */
        @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical$Companion;", "", Constants.CTOR, "()V", "getByName", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical;", "name", "", "haze"})
        @SourceDebugExtension({"SMAP\nElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Element.kt\nnet/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,202:1\n1#2:203\n*E\n"})
        /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/hud/element/Side$Vertical$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @Nullable
            public final Vertical getByName(@NotNull String name) {
                Object obj;
                Intrinsics.checkNotNullParameter(name, "name");
                Iterator<E> it = Vertical.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    Object next = it.next();
                    Vertical it2 = (Vertical) next;
                    if (Intrinsics.areEqual(it2.getSideName(), name)) {
                        obj = next;
                        break;
                    }
                }
                return (Vertical) obj;
            }
        }

        @NotNull
        public static EnumEntries<Vertical> getEntries() {
            return $ENTRIES;
        }
    }
}

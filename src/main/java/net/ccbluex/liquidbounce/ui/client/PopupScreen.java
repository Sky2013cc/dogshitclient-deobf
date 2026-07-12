package net.ccbluex.liquidbounce.ui.client;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.util.Constants;

/* compiled from: PopupScreen.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018��2\u00020\u0001:\u0002)*B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\rH\u0002J&\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rJ(\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rH\u0002J(\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\rH\u0002J\u001e\u0010%\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010&\u001a\u00020\rJ\u000e\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0017\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��¨\u0006+"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/PopupScreen;", "", "title", "", "message", "buttons", "", "Lnet/ccbluex/liquidbounce/ui/client/ButtonData;", "onClose", "Ljava/lang/Runnable;", Constants.CTOR, "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Runnable;)V", "buttonWidth", "", "buttonHeight", "popupWidth", "popupHeight", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "scrollOffset", "buttonRects", "", "Lnet/ccbluex/liquidbounce/ui/client/PopupScreen$Rect;", "hoveredButton", "wrapText", "text", "maxWidth", "drawScreen", "", "screenWidth", "screenHeight", "mouseX", "mouseY", "drawButtons", "setupScissorBox", "width", "height", "mouseClicked", "mouseButton", "handleMouseWheel", "amount", "Builder", "Rect", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/PopupScreen.class */
public final class PopupScreen {

    @NotNull
    private final String title;

    @NotNull
    private final String message;

    @NotNull
    private final List<ButtonData> buttons;

    @NotNull
    private final Runnable onClose;
    private int buttonWidth;
    private int buttonHeight;
    private int popupWidth;
    private int popupHeight;
    private int x;
    private int y;
    private int scrollOffset;

    @NotNull
    private final List<Rect> buttonRects;
    private int hoveredButton;

    public PopupScreen(@NotNull String title, @NotNull String message, @NotNull List<ButtonData> buttons, @NotNull Runnable onClose) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.title = title;
        this.message = message;
        this.buttons = buttons;
        this.onClose = onClose;
        this.buttonWidth = 200;
        this.buttonHeight = 20;
        this.popupWidth = 320;
        this.popupHeight = 200;
        this.buttonRects = new ArrayList();
        this.hoveredButton = -1;
    }

    /* compiled from: PopupScreen.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020��2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020��2\u0006\u0010\u0006\u001a\u00020\u0005J\u0018\u0010\f\u001a\u00020��2\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\n\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\u0010R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/PopupScreen$Builder;", "", Constants.CTOR, "()V", "title", "", "message", "buttons", "", "Lnet/ccbluex/liquidbounce/ui/client/ButtonData;", "onClose", "Ljava/lang/Runnable;", "button", "text", "action", "build", "Lnet/ccbluex/liquidbounce/ui/client/PopupScreen;", "haze"})
    @SourceDebugExtension({"SMAP\nPopupScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PopupScreen.kt\nnet/ccbluex/liquidbounce/ui/client/PopupScreen$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,199:1\n1#2:200\n*E\n"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/PopupScreen$Builder.class */
    public static final class Builder {

        @Nullable
        private String title;

        @Nullable
        private String message;

        @NotNull
        private final List<ButtonData> buttons = new ArrayList();

        @NotNull
        private Runnable onClose = Builder::onClose$lambda$0;

        private static final void onClose$lambda$0() {
        }

        @NotNull
        public final Builder title(@NotNull String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            Builder $this$title_u24lambda_u241 = this;
            $this$title_u24lambda_u241.title = title;
            return this;
        }

        @NotNull
        public final Builder message(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Builder $this$message_u24lambda_u242 = this;
            $this$message_u24lambda_u242.message = message;
            return this;
        }

        @NotNull
        public final Builder button(@NotNull String text, @NotNull Runnable action) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(action, "action");
            Builder $this$button_u24lambda_u244 = this;
            $this$button_u24lambda_u244.buttons.add(new ButtonData(text, action));
            return this;
        }

        public static /* synthetic */ Builder button$default(Builder builder, String str, Runnable runnable, int i, Object obj) {
            if ((i & 2) != 0) {
                runnable = Builder::button$lambda$3;
            }
            return builder.button(str, runnable);
        }

        private static final void button$lambda$3() {
        }

        @NotNull
        public final Builder onClose(@NotNull Runnable onClose) {
            Intrinsics.checkNotNullParameter(onClose, "onClose");
            Builder $this$onClose_u24lambda_u245 = this;
            $this$onClose_u24lambda_u245.onClose = onClose;
            return this;
        }

        @NotNull
        public final PopupScreen build() {
            if (this.title == null) {
                throw new IllegalArgumentException("title should be not null".toString());
            }
            if (this.message == null) {
                throw new IllegalArgumentException("message should be not null".toString());
            }
            String str = this.title;
            Intrinsics.checkNotNull(str);
            String str2 = this.message;
            Intrinsics.checkNotNull(str2);
            return new PopupScreen(str, str2, this.buttons, this.onClose);
        }
    }

    private final List<String> wrapText(String text, int maxWidth) {
        List lines = new ArrayList();
        Sequence paragraphs = StringsKt.lineSequence(text);
        for (String paragraph : paragraphs) {
            if (StringsKt.isBlank(paragraph)) {
                lines.add("");
            } else {
                List<String> words = StringsKt.split$default((CharSequence) paragraph, new String[]{" "}, false, 0, 6, (Object) null);
                String currentLine = "";
                for (String word : words) {
                    String tempLine = currentLine.length() == 0 ? word : currentLine + ' ' + word;
                    int width = MinecraftInstance.mc.field_71466_p.func_78256_a(tempLine);
                    if (width > maxWidth) {
                        if (currentLine.length() > 0) {
                            lines.add(currentLine);
                            currentLine = word;
                        }
                    }
                    currentLine = tempLine;
                }
                if (currentLine.length() > 0) {
                    lines.add(currentLine);
                }
            }
        }
        return lines;
    }

    public final void drawScreen(int screenWidth, int screenHeight, int mouseX, int mouseY) {
        this.x = (screenWidth - this.popupWidth) / 2;
        this.y = (screenHeight - this.popupHeight) / 2;
        Gui.func_73734_a(0, 0, screenWidth, screenHeight, new Color(0, 0, 0, 200).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + this.popupWidth, this.y + this.popupHeight, new Color(48, 48, 48, 255).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + this.popupWidth, this.y + 2, new Color(80, 80, 80, 255).getRGB());
        Gui.func_73734_a(this.x, (this.y + this.popupHeight) - 2, this.x + this.popupWidth, this.y + this.popupHeight, new Color(80, 80, 80, 255).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + 2, this.y + this.popupHeight, new Color(80, 80, 80, 255).getRGB());
        Gui.func_73734_a((this.x + this.popupWidth) - 2, this.y, this.x + this.popupWidth, this.y + this.popupHeight, new Color(80, 80, 80, 255).getRGB());
        MinecraftInstance.mc.field_71466_p.func_175063_a(this.title, (screenWidth / 2) - (MinecraftInstance.mc.field_71466_p.func_78256_a(this.title) / 2), this.y + 15, 16777215);
        List messageLines = wrapText(this.message, this.popupWidth - 20);
        int messageY = this.y + 35 + this.scrollOffset;
        GL11.glEnable(3089);
        setupScissorBox(this.x + 10, this.y + 30, this.popupWidth - 20, this.popupHeight - 70);
        for (String line : messageLines) {
            MinecraftInstance.mc.field_71466_p.func_175063_a(line, this.x + 10, messageY, 13421772);
            messageY += MinecraftInstance.mc.field_71466_p.field_78288_b;
        }
        GL11.glDisable(3089);
        drawButtons(screenWidth, screenHeight, mouseX, mouseY);
    }

    private final void drawButtons(int screenWidth, int screenHeight, int mouseX, int mouseY) {
        int buttonY = ((this.y + this.popupHeight) - this.buttonHeight) - 15;
        int currentButtonX = (screenWidth / 2) - (((this.buttons.size() * this.buttonWidth) + ((this.buttons.size() - 1) * 10)) / 2);
        this.buttonRects.clear();
        this.hoveredButton = -1;
        int size = this.buttons.size();
        for (int i = 0; i < size; i++) {
            ButtonData button = this.buttons.get(i);
            Rect rect = new Rect(currentButtonX, buttonY, this.buttonWidth, this.buttonHeight);
            this.buttonRects.add(rect);
            boolean isHovered = mouseX >= rect.getX() && mouseX <= rect.getX() + rect.getWidth() && mouseY >= rect.getY() && mouseY <= rect.getY() + rect.getHeight();
            if (isHovered) {
                this.hoveredButton = i;
            }
            int buttonColor = isHovered ? new Color(80, 80, 80, 255).getRGB() : new Color(60, 60, 60, 255).getRGB();
            Gui.func_73734_a(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), buttonColor);
            Gui.func_73734_a(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + 1, new Color(100, 100, 100, 255).getRGB());
            Gui.func_73734_a(rect.getX(), (rect.getY() + rect.getHeight()) - 1, rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), new Color(40, 40, 40, 255).getRGB());
            Gui.func_73734_a(rect.getX(), rect.getY(), rect.getX() + 1, rect.getY() + rect.getHeight(), new Color(100, 100, 100, 255).getRGB());
            Gui.func_73734_a((rect.getX() + rect.getWidth()) - 1, rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight(), new Color(40, 40, 40, 255).getRGB());
            int textY = rect.getY() + ((rect.getHeight() - MinecraftInstance.mc.field_71466_p.field_78288_b) / 2);
            MinecraftInstance.mc.field_71466_p.func_175063_a(button.getText(), (rect.getX() + (rect.getWidth() / 2)) - (MinecraftInstance.mc.field_71466_p.func_78256_a(button.getText()) / 2), textY, isHovered ? 16776960 : 16777215);
            currentButtonX += this.buttonWidth + 10;
        }
    }

    private final void setupScissorBox(int x, int y, int width, int height) {
        int scaleFactor = new ScaledResolution(MinecraftInstance.mc).func_78325_e();
        GL11.glScissor(x * scaleFactor, MinecraftInstance.mc.field_71440_d - ((y + height) * scaleFactor), width * scaleFactor, height * scaleFactor);
    }

    public final void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int size = this.buttons.size();
        for (int i = 0; i < size; i++) {
            Rect rect = this.buttonRects.get(i);
            if (mouseX >= rect.getX() && mouseX <= rect.getX() + rect.getWidth() && mouseY >= rect.getY() && mouseY <= rect.getY() + rect.getHeight()) {
                this.buttons.get(i).getAction().run();
                this.onClose.run();
                return;
            }
        }
    }

    public final void handleMouseWheel(int amount) {
        List messageLines = wrapText(this.message, this.popupWidth - 20);
        int totalMessageHeight = messageLines.size() * MinecraftInstance.mc.field_71466_p.field_78288_b;
        int availableSpace = this.popupHeight - 70;
        if (totalMessageHeight > availableSpace) {
            this.scrollOffset += amount / 8;
            this.scrollOffset = RangesKt.coerceIn(this.scrollOffset, -(totalMessageHeight - availableSpace), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PopupScreen.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0082\b\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lnet/ccbluex/liquidbounce/ui/client/PopupScreen$Rect;", "", SimpleTaglet.EXCLUDED, "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "width", "height", Constants.CTOR, "(IIII)V", "getX", "()I", "getY", "getWidth", "getHeight", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/ui/client/PopupScreen$Rect.class */
    public static final class Rect {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        @NotNull
        public final Rect copy(int x, int y, int width, int height) {
            return new Rect(x, y, width, height);
        }

        public static /* synthetic */ Rect copy$default(Rect rect, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = rect.x;
            }
            if ((i5 & 2) != 0) {
                i2 = rect.y;
            }
            if ((i5 & 4) != 0) {
                i3 = rect.width;
            }
            if ((i5 & 8) != 0) {
                i4 = rect.height;
            }
            return rect.copy(i, i2, i3, i4);
        }

        @NotNull
        public String toString() {
            return "Rect(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ')';
        }

        public int hashCode() {
            int result = Integer.hashCode(this.x);
            return (((((result * 31) + Integer.hashCode(this.y)) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Rect)) {
                return false;
            }
            Rect rect = (Rect) other;
            return this.x == rect.x && this.y == rect.y && this.width == rect.width && this.height == rect.height;
        }

        public Rect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }
    }
}

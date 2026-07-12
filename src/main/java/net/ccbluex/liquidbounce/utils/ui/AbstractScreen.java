package net.ccbluex.liquidbounce.utils.ui;

import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: GuiExtensions.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b&\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\n\u001a\u0002H\u000b\"\b\b��\u0010\u000b*\u00020\u0006*\u0002H\u000bH\u0084\u0002¢\u0006\u0002\u0010\fJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0014J\u001c\u0010\n\u001a\u0002H\u000b\"\b\b��\u0010\u000b*\u00020\u0013*\u0002H\u000bH\u0084\u0002¢\u0006\u0002\u0010\u0014JW\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0019\b\u0002\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u001e¢\u0006\u0002\b\u001fH\u0084\bø\u0001��R$\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0084\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"Lnet/ccbluex/liquidbounce/utils/ui/AbstractScreen;", "Lnet/minecraft/client/gui/GuiScreen;", Constants.CTOR, "()V", "textFields", "Ljava/util/ArrayList;", "Lnet/minecraft/client/gui/GuiTextField;", "Lkotlin/collections/ArrayList;", "getTextFields", "()Ljava/util/ArrayList;", "unaryPlus", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "(Lnet/minecraft/client/gui/GuiTextField;)Lnet/minecraft/client/gui/GuiTextField;", "mouseClicked", "", "mouseX", "", "mouseY", "mouseButton", "Lnet/minecraft/client/gui/GuiButton;", "(Lnet/minecraft/client/gui/GuiButton;)Lnet/minecraft/client/gui/GuiButton;", "textField", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_ID, "fontRenderer", "Lnet/minecraft/client/gui/FontRenderer;", SimpleTaglet.EXCLUDED, OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "width", "height", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_BLOCK, "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "haze"})
@SourceDebugExtension({"SMAP\nGuiExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GuiExtensions.kt\nnet/ccbluex/liquidbounce/utils/ui/AbstractScreen\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,210:1\n1863#2,2:211\n*S KotlinDebug\n*F\n+ 1 GuiExtensions.kt\nnet/ccbluex/liquidbounce/utils/ui/AbstractScreen\n*L\n26#1:211,2\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/utils/ui/AbstractScreen.class */
public abstract class AbstractScreen extends GuiScreen {

    @NotNull
    private final ArrayList<GuiTextField> textFields = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final ArrayList<GuiTextField> getTextFields() {
        return this.textFields;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final <T extends GuiTextField> T unaryPlus(@NotNull T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        this.textFields.add(t);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        Iterable $this$forEach$iv = this.textFields;
        for (Object element$iv : $this$forEach$iv) {
            GuiTextField it = (GuiTextField) element$iv;
            it.func_146192_a(mouseX, mouseY, mouseButton);
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final <T extends GuiButton> T unaryPlus(@NotNull T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        this.field_146292_n.add(t);
        return t;
    }

    public static /* synthetic */ GuiTextField textField$default(AbstractScreen $this, int id, FontRenderer fontRenderer, int x, int y, int width, int height, Function1 block, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: textField");
        }
        if ((i & 64) != 0) {
            block = new Function1<GuiTextField, Unit>() { // from class: net.ccbluex.liquidbounce.utils.ui.AbstractScreen$textField$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GuiTextField guiTextField) {
                    Intrinsics.checkNotNullParameter(guiTextField, "<this>");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(GuiTextField guiTextField) {
                    invoke2(guiTextField);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(fontRenderer, "fontRenderer");
        Intrinsics.checkNotNullParameter(block, "block");
        GuiTextField guiTextField = new GuiTextField(id, fontRenderer, x, y, width, height);
        block.invoke(guiTextField);
        return $this.unaryPlus((AbstractScreen) guiTextField);
    }

    @NotNull
    protected final GuiTextField textField(int id, @NotNull FontRenderer fontRenderer, int x, int y, int width, int height, @NotNull Function1<? super GuiTextField, Unit> block) {
        Intrinsics.checkNotNullParameter(fontRenderer, "fontRenderer");
        Intrinsics.checkNotNullParameter(block, "block");
        GuiTextField guiTextField = new GuiTextField(id, fontRenderer, x, y, width, height);
        block.invoke(guiTextField);
        return unaryPlus((AbstractScreen) guiTextField);
    }
}

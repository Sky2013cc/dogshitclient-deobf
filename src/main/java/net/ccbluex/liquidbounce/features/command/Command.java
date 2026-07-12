package net.ccbluex.liquidbounce.features.command;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.utils.client.ClientUtilsKt;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.client.MinecraftInstanceKt;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Command.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\t\b&\u0018��2\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H&¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0016¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0003H\u0004J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0003H\u0004J\u001b\u0010\u0016\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0004¢\u0006\u0002\u0010\u0010J\b\u0010\u0019\u001a\u00020\u000eH\u0004J\b\u0010\u001a\u001a\u00020\u000eH\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/Command;", "Lnet/ccbluex/liquidbounce/utils/client/MinecraftInstance;", "command", "", "alias", "", Constants.CTOR, "(Ljava/lang/String;[Ljava/lang/String;)V", "getCommand", "()Ljava/lang/String;", "getAlias", "()[Ljava/lang/String;", "[Ljava/lang/String;", "execute", "", "args", "([Ljava/lang/String;)V", "tabComplete", "", "([Ljava/lang/String;)Ljava/util/List;", "chat", "msg", "chatSyntax", "syntax", "syntaxes", "chatSyntaxError", "playEdit", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/Command.class */
public abstract class Command implements MinecraftInstance {

    @NotNull
    private final String command;

    @NotNull
    private final String[] alias;

    public abstract void execute(@NotNull String[] strArr);

    public Command(@NotNull String command, @NotNull String... alias) {
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.command = command;
        this.alias = alias;
    }

    @NotNull
    public final String getCommand() {
        return this.command;
    }

    @NotNull
    public final String[] getAlias() {
        return this.alias;
    }

    @Override // net.ccbluex.liquidbounce.utils.client.MinecraftInstance
    @NotNull
    public Minecraft getMc() {
        return MinecraftInstance.DefaultImpls.getMc(this);
    }

    @NotNull
    public List<String> tabComplete(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void chat(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        ClientUtilsKt.chat("§3" + msg);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void chatSyntax(@NotNull String syntax) {
        Intrinsics.checkNotNullParameter(syntax, "syntax");
        chat("§3Syntax: §7" + LiquidBounce.INSTANCE.getCommandManager().getPrefix() + syntax);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void chatSyntax(@NotNull String[] syntaxes) {
        Intrinsics.checkNotNullParameter(syntaxes, "syntaxes");
        chat("§3Syntax:");
        for (String syntax : syntaxes) {
            StringBuilder append = new StringBuilder().append("§8> §7").append(LiquidBounce.INSTANCE.getCommandManager().getPrefix()).append(this.command).append(' ');
            String lowerCase = syntax.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            chat(append.append(lowerCase).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void chatSyntaxError() {
        chat("§3Syntax error");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void playEdit() {
        MinecraftInstanceKt.playSound$default(getMc(), MinecraftInstanceKt.asResourceLocation("random.anvil_use"), 0.0f, 2, null);
    }
}

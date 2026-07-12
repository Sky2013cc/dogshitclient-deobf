package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.utils.kotlin.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: SayCommand.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/commands/SayCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", Constants.CTOR, "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/commands/SayCommand.class */
public final class SayCommand extends Command {

    @NotNull
    public static final SayCommand INSTANCE = new SayCommand();

    private SayCommand() {
        super("say", new String[0]);
    }

    @Override // net.ccbluex.liquidbounce.features.command.Command
    public void execute(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        if (args.length > 1) {
            getMc().field_71439_g.func_71165_d(StringUtils.INSTANCE.toCompleteString(args, 1));
            chat("Message was sent to the chat.");
        } else {
            chatSyntax("say <message...>");
        }
    }
}

package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.features.command.Command;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: PingCommand.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/commands/PingCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", Constants.CTOR, "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/commands/PingCommand.class */
public final class PingCommand extends Command {

    @NotNull
    public static final PingCommand INSTANCE = new PingCommand();

    private PingCommand() {
        super("ping", new String[0]);
    }

    @Override // net.ccbluex.liquidbounce.features.command.Command
    public void execute(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        chat("§3Your ping is §a" + getMc().func_147114_u().func_175102_a(getMc().field_71439_g.func_110124_au()).func_178853_c() + "ms§3.");
    }
}

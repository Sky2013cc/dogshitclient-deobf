package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.features.command.Command;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: VClipCommand.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/commands/VClipCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", Constants.CTOR, "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/commands/VClipCommand.class */
public final class VClipCommand extends Command {

    @NotNull
    public static final VClipCommand INSTANCE = new VClipCommand();

    private VClipCommand() {
        super("vclip", new String[0]);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x000c
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:275)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:68)
        */
    @Override // net.ccbluex.liquidbounce.features.command.Command
    public void execute(@org.jetbrains.annotations.NotNull java.lang.String[] r9) {
        /*
            r8 = this;
            r0 = r9
            java.lang.String r1 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r9
            int r0 = r0.length
            r1 = 1
            if (r0 <= r1) goto L5f
        Ld:
            r0 = r9
            r1 = 1
            r0 = r0[r1]     // Catch: java.lang.NumberFormatException -> L59
            double r0 = java.lang.Double.parseDouble(r0)     // Catch: java.lang.NumberFormatException -> L59
            r10 = r0
            r0 = r8
            net.minecraft.client.Minecraft r0 = r0.getMc()     // Catch: java.lang.NumberFormatException -> L59
            net.minecraft.client.entity.EntityPlayerSP r0 = r0.field_71439_g     // Catch: java.lang.NumberFormatException -> L59
            r1 = r0
            if (r1 != 0) goto L21
        L20:
            return
        L21:
            r12 = r0
            r0 = r12
            boolean r0 = r0.func_70115_ae()     // Catch: java.lang.NumberFormatException -> L59
            if (r0 == 0) goto L33
            r0 = r12
            net.minecraft.entity.Entity r0 = r0.field_70154_o     // Catch: java.lang.NumberFormatException -> L59
            goto L38
        L33:
            r0 = r12
            net.minecraft.entity.Entity r0 = (net.minecraft.entity.Entity) r0     // Catch: java.lang.NumberFormatException -> L59
        L38:
            r13 = r0
            r0 = r13
            r1 = r13
            double r1 = r1.field_70165_t     // Catch: java.lang.NumberFormatException -> L59
            r2 = r13
            double r2 = r2.field_70163_u     // Catch: java.lang.NumberFormatException -> L59
            r3 = r10
            double r2 = r2 + r3
            r3 = r13
            double r3 = r3.field_70161_v     // Catch: java.lang.NumberFormatException -> L59
            r0.func_70107_b(r1, r2, r3)     // Catch: java.lang.NumberFormatException -> L59
            r0 = r8
            java.lang.String r1 = "You were teleported."
            r0.chat(r1)     // Catch: java.lang.NumberFormatException -> L59
            goto L5e
        L59:
            r10 = move-exception
            r0 = r8
            r0.chatSyntaxError()
        L5e:
            return
        L5f:
            r0 = r8
            java.lang.String r1 = "vclip <value>"
            r0.chatSyntax(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.ccbluex.liquidbounce.features.command.commands.VClipCommand.execute(java.lang.String[]):void");
    }
}

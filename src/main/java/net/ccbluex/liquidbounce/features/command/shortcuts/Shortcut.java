package net.ccbluex.liquidbounce.features.command.shortcuts;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.ccbluex.liquidbounce.features.command.Command;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.util.Constants;

/* compiled from: Shortcut.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0016¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR)\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lnet/ccbluex/liquidbounce/features/command/shortcuts/Shortcut;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "name", "", "script", "", "Lkotlin/Pair;", "", Constants.CTOR, "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getScript", "()Ljava/util/List;", "execute", "", "args", "([Ljava/lang/String;)V", "haze"})
@SourceDebugExtension({"SMAP\nShortcut.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Shortcut.kt\nnet/ccbluex/liquidbounce/features/command/shortcuts/Shortcut\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,16:1\n1863#2,2:17\n*S KotlinDebug\n*F\n+ 1 Shortcut.kt\nnet/ccbluex/liquidbounce/features/command/shortcuts/Shortcut\n*L\n14#1:17,2\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/features/command/shortcuts/Shortcut.class */
public final class Shortcut extends Command {

    @NotNull
    private final String name;

    @NotNull
    private final List<Pair<Command, String[]>> script;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Shortcut(@NotNull String name, @NotNull List<? extends Pair<? extends Command, String[]>> script) {
        super(name, new String[0]);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(script, "script");
        this.name = name;
        this.script = script;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final List<Pair<Command, String[]>> getScript() {
        return this.script;
    }

    @Override // net.ccbluex.liquidbounce.features.command.Command
    public void execute(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Iterable $this$forEach$iv = this.script;
        for (Object element$iv : $this$forEach$iv) {
            Pair it = (Pair) element$iv;
            ((Command) it.getFirst()).execute((String[]) it.getSecond());
        }
    }
}

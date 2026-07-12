package net.ccbluex.liquidbounce.chat;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.chat.packet.packets.Packet;
import org.jetbrains.annotations.NotNull;

/* compiled from: ClientListener.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n��\bf\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lnet/ccbluex/liquidbounce/chat/ClientListener;", "", "onConnect", "", "onConnected", "onHandshake", "success", "", "onDisconnect", "onLogon", "onPacket", "packet", "Lnet/ccbluex/liquidbounce/chat/packet/packets/Packet;", "onError", "cause", "", "haze"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/chat/ClientListener.class */
public interface ClientListener {
    void onConnect();

    void onConnected();

    void onHandshake(boolean z);

    void onDisconnect();

    void onLogon();

    void onPacket(@NotNull Packet packet);

    void onError(@NotNull Throwable th);
}

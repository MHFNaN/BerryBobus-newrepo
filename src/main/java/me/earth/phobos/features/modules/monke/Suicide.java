package me.earth.phobos.features.modules.monke;

import me.earth.phobos.features.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

/**
 * @author ligmaballz
 */
public class Suicide
        extends Module {
    private static Suicide suicide;

    public Suicide() {
        super("suicide", "die without the /kill or /suicide", Category.MONKE, true, false, false);
    }

    @Override
    public void onEnable() {
        if (Suicide.fullNullCheck()) {
            return;
        }
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(Suicide.mc.player.posX, Suicide.mc.player.posY, Suicide.mc.player.posZ, false));
        Suicide.mc.player.setLocationAndAngles(Suicide.mc.player.posX, Suicide.mc.player.posY, Suicide.mc.player.posZ, Suicide.mc.player.rotationYaw, Suicide.mc.player.rotationPitch);
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(Suicide.mc.player.posX, Suicide.mc.player.posY - 1339.2, Suicide.mc.player.posZ, true));
        this.disable();
    }
}

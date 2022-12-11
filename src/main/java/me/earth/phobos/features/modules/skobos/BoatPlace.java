package me.earth.phobos.features.modules.skobos;

import me.earth.phobos.features.modules.*;
import net.minecraft.item.*;
import org.lwjgl.input.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class BoatPlace extends Module
{
    public BoatPlace() {
        super("BoatPlace", "0b0t boat placement bypass for boatspeed", Module.Category.SKOBOS, true, false, false);
    }
    
    @Override
    public void onUpdate() {
        if (BoatPlace.mc.player.getHeldItemMainhand().getItem() instanceof ItemBoat && Mouse.isButtonDown(1)) {
            BoatPlace.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            BoatPlace.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(BoatPlace.mc.objectMouseOver.getBlockPos(), EnumFacing.SOUTH, EnumHand.MAIN_HAND, 1.0f, 1.0f, 1.0f));
        }
    }
}

package me.earth.phobos.mixin.mixins;

import java.util.List;
import javax.annotation.Nullable;
import me.earth.phobos.features.modules.movement.Flight;
import me.earth.phobos.features.modules.movement.Phase;
import me.earth.phobos.features.modules.player.Freecam;
import me.earth.phobos.features.modules.player.Jesus;
import me.earth.phobos.features.modules.render.XRay;
import me.earth.phobos.util.EntityUtil;
import me.earth.phobos.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Block.class})
public abstract class MixinBlock {
    @Shadow
    @Deprecated
    public abstract float getBlockHardness(IBlockState var1, World var2, BlockPos var3);

    @Inject(method={"addCollisionBoxToList(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;Z)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void addCollisionBoxToListHook(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState, CallbackInfo info) {
        if (entityIn != null && Util.mc.player != null && (entityIn.equals((Object)Util.mc.player) || Util.mc.player.getRidingEntity() != null && entityIn.equals((Object)Util.mc.player.getRidingEntity())) && (Flight.getInstance().isOn() && (Flight.getInstance().mode.getValue() == Flight.Mode.PACKET && Flight.getInstance().better.getValue() != false && Flight.getInstance().phase.getValue() != false || Flight.getInstance().mode.getValue() == Flight.Mode.DAMAGE && Flight.getInstance().noClip.getValue() != false) || Phase.getInstance().isOn() && Phase.getInstance().mode.getValue() == Phase.Mode.PACKETFLY && Phase.getInstance().type.getValue() == Phase.PacketFlyMode.SETBACK && Phase.getInstance().boundingBox.getValue().booleanValue())) {
            info.cancel();
        }

    @Inject(method={"isFullCube"}, at={@At(value="HEAD")}, cancellable=true)
    public void isFullCubeHook(IBlockState blockState, CallbackInfoReturnable<Boolean> info) {
        try {
            if (XRay.getInstance().isOn()) {
                info.setReturnValue(XRay.getInstance().shouldRender((Block)Block.class.cast(this)));
                info.cancel();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}


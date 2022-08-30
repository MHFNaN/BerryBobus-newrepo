package phobos.features.modules.combat;

import phobos.event.events.PacketEvent.Send;
import phobos.features.modules.Module;
import phobos.features.modules.Module.Category;
import phobos.features.setting.Setting;
import phobos.util.CombatUtil;
import phobos.util.EntityUtil;
import phobos.util.InventoryUtil;
import phobos.util.InventoryUtil.Hand;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XP extends Module {
   public Setting<Boolean> safeOnly = this.register(new Setting("SafeOnly", true));
   public Setting<Float> enemyRange = this.register(new Setting("EnemyRange", 2.0F, 0.1F, 8.0F));

   public XP() {
      super("XPDown", Category.COMBAT, "Looks down when ur using xp");
   }

   @SubscribeEvent
   public void onPacketSend(Send e) {
      if (e.getPacket() instanceof CPacketPlayer && this.isEnabled() && !fullNullCheck()) {
         if ((Boolean)this.safeOnly.getValue() && !EntityUtil.isSafe(mc.field_71439_g)) {
            return;
         }

         if (!InventoryUtil.heldItem(Items.field_151062_by, Hand.Both)) {
            return;
         }

         if (!mc.field_71474_y.field_74313_G.func_151470_d()) {
            return;
         }

         try {
            if (mc.field_71439_g.func_70032_d(CombatUtil.getTarget(12.0F)) > (Float)this.enemyRange.getValue()) {
               return;
            }
         } catch (Exception var3) {
            var3.printStackTrace();
         }

         ((CPacketPlayer)e.getPacket()).field_149473_f = 90.0F;
      }

   }
}

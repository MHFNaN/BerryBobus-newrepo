package me.earth.phobos.features.modules.monke;

import java.util.Iterator;
import me.earth.phobos.event.events.Render3DEvent;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.setting.Setting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

public class PenisESP extends Module {
   private final Setting<Float> penisSize = this.register(new Setting("PenisSize", 1.5F, 0.1F, 5.0F));

   public PenisESP() {
      super("PenisESP", "Draws a pp", Category.MONKE, false, false, false);
   }

   public void onRender3D(Render3DEvent event) {
      Iterator var2 = mc.field_71441_e.field_72996_f.iterator();

      while(var2.hasNext()) {
         Object o = var2.next();
         if (o instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)o;
            double n = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)mc.field_71428_T.field_194147_b;
            mc.func_175598_ae();
            double x = n - mc.func_175598_ae().field_78725_b;
            double n2 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)mc.field_71428_T.field_194147_b;
            mc.func_175598_ae();
            double y = n2 - mc.func_175598_ae().field_78726_c;
            double n3 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)mc.field_71428_T.field_194147_b;
            mc.func_175598_ae();
            double z = n3 - mc.func_175598_ae().field_78723_d;
            GL11.glPushMatrix();
            RenderHelper.func_74518_a();
            this.esp(player, x, y, z);
            RenderHelper.func_74519_b();
            GL11.glPopMatrix();
         }
      }

   }

   public void esp(EntityPlayer player, double x, double y, double z) {
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glDepthMask(true);
      GL11.glLineWidth(1.0F);
      GL11.glTranslated(x, y, z);
      GL11.glRotatef(-player.field_70177_z, 0.0F, player.field_70131_O, 0.0F);
      GL11.glTranslated(-x, -y, -z);
      GL11.glTranslated(x, y + (double)(player.field_70131_O / 2.0F) - 0.22499999403953552D, z);
      GL11.glColor4f(1.38F, 0.55F, 2.38F, 1.0F);
      GL11.glRotated((double)(player.func_70093_af() ? 35 : 0), 1.0D, 0.0D, 0.0D);
      GL11.glTranslated(0.0D, 0.0D, 0.07500000298023224D);
      Cylinder shaft = new Cylinder();
      shaft.setDrawStyle(100013);
      shaft.draw(0.1F * (Float)this.penisSize.getValue(), 0.11F, 0.4F, 25, 20);
      GL11.glTranslated(0.0D, 0.0D, -0.12500000298023223D);
      GL11.glTranslated(-0.09000000074505805D, 0.0D, 0.0D);
      Sphere right = new Sphere();
      right.setDrawStyle(100013);
      right.draw(0.14F * (Float)this.penisSize.getValue(), 10, 20);
      GL11.glTranslated(0.16000000149011612D, 0.0D, 0.0D);
      Sphere left = new Sphere();
      left.setDrawStyle(100013);
      left.draw(0.14F * (Float)this.penisSize.getValue(), 10, 20);
      GL11.glColor4f(1.35F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslated(-0.07000000074505806D, 0.0D, 0.589999952316284D);
      Sphere tip = new Sphere();
      tip.setDrawStyle(100013);
      tip.draw(0.13F * (Float)this.penisSize.getValue(), 15, 20);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
   }
}

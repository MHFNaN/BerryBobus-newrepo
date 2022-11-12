package me.earth.phobos.features.modules.skobos;

import java.util.Iterator;
import me.earth.phobos.event.events.Render3DEvent;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

public class PenisESP extends Module {
   private float spin = 0.0F;

   public PenisESP() {
      super("PenisEsp", "Draws something you don't have.", Category.SKOBOS, true, false, false);
   }

   public void onRender3D(Render3DEvent render3DEvent) {
      this.spin += 5.0F;
      Iterator var2 = mc.field_71441_e.field_72996_f.iterator();

      while(var2.hasNext()) {
         Object e = var2.next();
         if (e instanceof EntityPlayer) {
            RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
            EntityPlayer entityPlayer = (EntityPlayer)e;
            double d = entityPlayer.field_70142_S + (entityPlayer.field_70165_t - entityPlayer.field_70142_S) * (double)mc.field_71428_T.field_194147_b;
            double d2 = d - renderManager.field_78725_b;
            double d3 = entityPlayer.field_70137_T + (entityPlayer.field_70163_u - entityPlayer.field_70137_T) * (double)mc.field_71428_T.field_194147_b;
            double d4 = d3 - renderManager.field_78726_c;
            double d5 = entityPlayer.field_70136_U + (entityPlayer.field_70161_v - entityPlayer.field_70136_U) * (double)mc.field_71428_T.field_194147_b;
            double d6 = d5 - renderManager.field_78723_d;
            GL11.glPushMatrix();
            RenderHelper.func_74518_a();
            this.esp(entityPlayer, d2, d4, d6);
            RenderHelper.func_74519_b();
            GL11.glPopMatrix();
         }
      }

      if (this.spin >= 360.0F) {
         this.spin = 0.0F;
      }

   }

   public void esp(EntityPlayer entityPlayer, double d, double d2, double d3) {
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glDepthMask(true);
      GL11.glLineWidth(1.0F);
      GL11.glTranslated(d, d2, d3);
      GL11.glRotatef(-entityPlayer.field_70177_z + this.spin, 0.0F, entityPlayer.field_70131_O, 0.0F);
      GL11.glTranslated(-d, -d2, -d3);
      GL11.glTranslated(d, d2 + (double)(entityPlayer.field_70131_O / 2.0F) - 0.22499999403953552D, d3);
      GL11.glColor4f((float)ColorUtil.rainbow(50).getRed() / 255.0F, (float)ColorUtil.rainbow(50).getGreen() / 255.0F, (float)ColorUtil.rainbow(50).getBlue() / 255.0F, 1.0F);
      GL11.glRotated((double)(entityPlayer.func_70093_af() ? 35 : 0), 1.0D, 0.0D, 0.0D);
      GL11.glTranslated(0.0D, 0.0D, 0.07500000298023224D);
      Cylinder cylinder = new Cylinder();
      cylinder.setDrawStyle(100013);
      cylinder.draw(0.1F, 0.11F, 1.0F, 25, 20);
      GL11.glColor4f((float)ColorUtil.rainbow(50).getRed() / 255.0F, (float)ColorUtil.rainbow(50).getGreen() / 255.0F, (float)ColorUtil.rainbow(50).getBlue() / 255.0F, 1.0F);
      GL11.glTranslated(0.0D, 0.0D, -0.12500000298023223D);
      GL11.glTranslated(-0.09000000074505805D, 0.0D, 0.0D);
      Sphere sphere = new Sphere();
      sphere.setDrawStyle(100013);
      sphere.draw(0.14F, 10, 20);
      GL11.glTranslated(0.16000000149011612D, 0.0D, 0.0D);
      Sphere sphere2 = new Sphere();
      sphere2.setDrawStyle(100013);
      sphere2.draw(0.14F, 10, 20);
      GL11.glColor4f((float)ColorUtil.rainbow(50).getRed() / 255.0F, (float)ColorUtil.rainbow(50).getGreen() / 255.0F, (float)ColorUtil.rainbow(50).getBlue() / 255.0F, 1.0F);
      GL11.glTranslated(-0.07000000074505806D, 0.0D, 1.089999952316284D);
      Sphere sphere3 = new Sphere();
      sphere3.setDrawStyle(100013);
      sphere3.draw(0.13F, 15, 20);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
   }
}

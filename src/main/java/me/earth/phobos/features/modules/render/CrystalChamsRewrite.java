package me.earth.phobos.features.modules.render;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.earth.phobos.event.events.RenderEntityModelEvent;
import me.earth.phobos.event.events.PacketEvent.Receive;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.modules.client.ClickGui;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.EntityUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class CrystalChamsRewrite extends Module {
   public Setting<Boolean> animateScale = this.register(new Setting("AnimateScale", false));
   public Setting<Boolean> chams = this.register(new Setting("Chams", false));
   public Setting<Boolean> throughWalls = this.register(new Setting("ThroughWalls", true));
   public Setting<Boolean> wireframeThroughWalls = this.register(new Setting("WireThroughWalls", true));
   public Setting<Boolean> glint = this.register(new Setting("Glint", false, (v) -> {
      return (Boolean)this.chams.getValue();
   }));
   public Setting<Boolean> wireframe = this.register(new Setting("Wireframe", false));
   public Setting<Float> scale = this.register(new Setting("Scale", 1.0F, 0.1F, 10.0F));
   public Setting<Float> lineWidth = this.register(new Setting("LineWidth", 1.0F, 0.1F, 3.0F));
   public Setting<Boolean> colorSync = this.register(new Setting("Sync", false));
   public Setting<Boolean> rainbow = this.register(new Setting("Rainbow", false));
   public Setting<Integer> saturation = this.register(new Setting("Saturation", 50, 0, 100, (v) -> {
      return (Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> brightness = this.register(new Setting("Brightness", 100, 0, 100, (v) -> {
      return (Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> speed = this.register(new Setting("Speed", 40, 1, 100, (v) -> {
      return (Boolean)this.rainbow.getValue();
   }));
   public Setting<Boolean> xqz = this.register(new Setting("XQZ", false, (v) -> {
      return !(Boolean)this.rainbow.getValue() && (Boolean)this.throughWalls.getValue();
   }));
   public Setting<Integer> red = this.register(new Setting("Red", 0, 0, 255, (v) -> {
      return !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> green = this.register(new Setting("Green", 255, 0, 255, (v) -> {
      return !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> blue = this.register(new Setting("Blue", 0, 0, 255, (v) -> {
      return !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> alpha = this.register(new Setting("Alpha", 255, 0, 255));
   public Setting<Integer> hiddenRed = this.register(new Setting("Hidden Red", 255, 0, 255, (v) -> {
      return (Boolean)this.xqz.getValue() && !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> hiddenGreen = this.register(new Setting("Hidden Green", 0, 0, 255, (v) -> {
      return (Boolean)this.xqz.getValue() && !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> hiddenBlue = this.register(new Setting("Hidden Blue", 255, 0, 255, (v) -> {
      return (Boolean)this.xqz.getValue() && !(Boolean)this.rainbow.getValue();
   }));
   public Setting<Integer> hiddenAlpha = this.register(new Setting("Hidden Alpha", 255, 0, 255, (v) -> {
      return (Boolean)this.xqz.getValue() && !(Boolean)this.rainbow.getValue();
   }));
   public Map<EntityEnderCrystal, Float> scaleMap = new ConcurrentHashMap();
   public static CrystalChamsRewrite INSTANCE;

   public CrystalChamsRewrite() {
      super("CCReerite", "Crystal Chams Rewrite", Category.RENDER, true, false, false);
      INSTANCE = this;
   }

   public void onUpdate() {
      Iterator var1 = mc.field_71441_e.field_72996_f.iterator();

      while(var1.hasNext()) {
         Entity crystal = (Entity)var1.next();
         if (crystal instanceof EntityEnderCrystal) {
            if (!this.scaleMap.containsKey(crystal)) {
               this.scaleMap.put((EntityEnderCrystal)crystal, 3.125E-4F);
            } else {
               this.scaleMap.put((EntityEnderCrystal)crystal, (Float)this.scaleMap.get(crystal) + 3.125E-4F);
            }

            if ((Float)this.scaleMap.get(crystal) >= 0.0625F * (Float)this.scale.getValue()) {
               this.scaleMap.remove(crystal);
            }
         }
      }

   }

   @SubscribeEvent
   public void onReceivePacket(Receive event) {
      if (event.getPacket() instanceof SPacketDestroyEntities) {
         SPacketDestroyEntities packet = (SPacketDestroyEntities)event.getPacket();
         int[] var3 = packet.func_149098_c();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            int id = var3[var5];
            Entity entity = mc.field_71441_e.func_73045_a(id);
            if (entity instanceof EntityEnderCrystal) {
               this.scaleMap.remove(entity);
            }
         }
      }

   }

   public void onRenderModel(RenderEntityModelEvent event) {
      if (event.getStage() == 0 && event.entity instanceof EntityEnderCrystal && (Boolean)this.wireframe.getValue()) {
         Color color = (Boolean)this.colorSync.getValue() ? ClickGui.getInstance().getCurrentColor() : EntityUtil.getColor(event.entity, (Integer)this.red.getValue(), (Integer)this.green.getValue(), (Integer)this.blue.getValue(), (Integer)this.alpha.getValue(), false);
         boolean fancyGraphics = mc.field_71474_y.field_74347_j;
         mc.field_71474_y.field_74347_j = false;
         float gamma = mc.field_71474_y.field_74333_Y;
         mc.field_71474_y.field_74333_Y = 10000.0F;
         GL11.glPushMatrix();
         GL11.glPushAttrib(1048575);
         GL11.glPolygonMode(1032, 6913);
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         if ((Boolean)this.wireframeThroughWalls.getValue()) {
            GL11.glDisable(2929);
         }

         GL11.glEnable(2848);
         GL11.glEnable(3042);
         GlStateManager.func_179112_b(770, 771);
         GlStateManager.func_179131_c((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
         GlStateManager.func_187441_d((Float)this.lineWidth.getValue());
         event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }
   }
}

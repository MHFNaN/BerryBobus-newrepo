package me.earth.phobos.features.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.earth.phobos.event.events.Render3DEvent;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.EntityUtil;
import me.earth.phobos.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class CityEsp extends Module {
   public Setting<Boolean> end_crystal = this.register(new Setting("EndCrystal", true));
   public Setting<Integer> range = this.register(new Setting("Range", 6, 0, 12));
   public Setting<Boolean> render = this.register(new Setting("Render", true));
   public Setting<Boolean> colorSync = this.register(new Setting("ColorSync", true, (v) -> {
      return (Boolean)this.render.getValue();
   }));
   public Setting<Integer> red = this.register(new Setting("Red", 255, 1, 255, (v) -> {
      return (Boolean)this.render.getValue();
   }));
   public Setting<Integer> green = this.register(new Setting("Green", 255, 1, 255, (v) -> {
      return (Boolean)this.render.getValue();
   }));
   public Setting<Integer> blue = this.register(new Setting("Blue", 255, 1, 255, (v) -> {
      return (Boolean)this.render.getValue();
   }));
   public Setting<Integer> alpha = this.register(new Setting("Alpha", 125, 1, 255, (v) -> {
      return (Boolean)this.render.getValue();
   }));
   List<BlockPos> blocks = new ArrayList();

   public CityEsp() {
      super("CityEsp", "citi e es pee", Category.RENDER, true, false, false);
   }

   public void onEnable() {
      this.blocks.clear();
   }

   public void onUpdate() {
      this.blocks.clear();
      Iterator var1 = mc.field_71441_e.field_73010_i.iterator();

      while(var1.hasNext()) {
         EntityPlayer player = (EntityPlayer)var1.next();
         if (mc.field_71439_g.func_70032_d(player) <= (float)(Integer)this.range.getValue() && mc.field_71439_g != player) {
            BlockPos p = EntityUtil.is_cityable(player, (Boolean)this.end_crystal.getValue());
            if (p != null) {
               this.blocks.add(p);
            }
         }
      }

   }

   public void onRender3D(Render3DEvent event) {
      Iterator var2 = this.blocks.iterator();

      while(var2.hasNext()) {
         BlockPos pos = (BlockPos)var2.next();
         RenderUtil.drawBox(pos, new Color((Integer)this.red.getValue(), (Integer)this.green.getValue(), (Integer)this.blue.getValue(), (Integer)this.alpha.getValue()));
      }

   }

   public void onDisable() {
      this.blocks.clear();
   }
}

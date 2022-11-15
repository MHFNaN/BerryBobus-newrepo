package me.earth.phobos.features.modules.client;

import me.earth.phobos.Phobos;
import me.earth.phobos.event.events.Render2DEvent;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.ColorUtil;
import me.earth.phobos.util.RenderUtil;
import me.earth.phobos.util.Timer;

public class CSGOWatermark extends Module {
   Timer delayTimer = new Timer();
   public Setting<Integer> X = this.register(new Setting("WatermarkX", 0, 0, 300));
   public Setting<Integer> Y = this.register(new Setting("WatermarkY", 0, 0, 300));
   public Setting<Integer> delay = this.register(new Setting("Delay", 240, 0, 600));
   public Setting<Integer> saturation = this.register(new Setting("Saturation", 127, 1, 255));
   public Setting<Integer> brightness = this.register(new Setting("Brightness", 100, 0, 255));
   public float hue;
   public int red = 1;
   public int green = 1;
   public int blue = 1;

   public CSGOWatermark() {
      super("Watermark", "Skobos Watermark 100% not skidded", Category.CLIENT, true, false, false);
   }

   public void onRender2D(Render2DEvent event) {
      this.drawCsgoWatermark();
   }

   public void drawCsgoWatermark() {
      int padding = true;
      String message = "Skobos V1.3.7 | " + mc.field_71439_g.func_70005_c_() + " | " + Phobos.serverManager.getPing() + "ms";
      int textWidth = Phobos.textManager.getStringWidth(message);
      int textHeight = mc.field_71466_p.field_78288_b;
      RenderUtil.drawRectangleCorrectly((Integer)this.X.getValue() - 4, (Integer)this.Y.getValue() - 4, textWidth + 16, textHeight + 12, ColorUtil.toRGBA(22, 22, 22, 255));
      RenderUtil.drawRectangleCorrectly((Integer)this.X.getValue(), (Integer)this.Y.getValue(), textWidth + 4, textHeight + 4, ColorUtil.toRGBA(0, 0, 0, 255));
      RenderUtil.drawRectangleCorrectly((Integer)this.X.getValue(), (Integer)this.Y.getValue(), textWidth + 8, textHeight + 4, ColorUtil.toRGBA(0, 0, 0, 255));
      RenderUtil.drawRectangleCorrectly((Integer)this.X.getValue(), (Integer)this.Y.getValue(), textWidth + 8, 1, ColorUtil.rainbow((Integer)this.delay.getValue()).hashCode());
      Phobos.textManager.drawString(message, (float)((Integer)this.X.getValue() + 3), (float)((Integer)this.Y.getValue() + 3), ColorUtil.toRGBA(255, 255, 255, 255), false);
   }
}

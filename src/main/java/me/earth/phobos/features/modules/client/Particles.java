package me.earth.phobos.features.modules.client;

import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.setting.Setting;

public class Particles extends Module {
   private static Particles INSTANCE = new Particles();
   public Setting<Integer> particleLength = this.register(new Setting("ParticleLength", 80, 0, 300));
   public Setting<Integer> particlered = this.register(new Setting("ParticleRed", 0, 0, 255));
   public Setting<Integer> particlegreen = this.register(new Setting("ParticleGreen", 255, 0, 255));
   public Setting<Integer> particleblue = this.register(new Setting("ParticleBlue", 255, 0, 255));

   public Particles() {
      super("Particles", "Draws Particles on screen ig", Category.CLIENT, true, false, false);
      this.setInstance();
   }

   public static Particles getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new Particles();
      }

      return INSTANCE;
   }

   private void setInstance() {
      INSTANCE = this;
   }
}

package me.earth.phobos.features.modules.client;

import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.setting.Setting;

public class ModuleTools extends Module {
   private static ModuleTools INSTANCE;
   public Setting<ModuleTools.Notifier> notifier;
   public Setting<ModuleTools.PopNotifier> popNotifier;

   public ModuleTools() {
      super("ModuleTools", "Change settings", Category.CLIENT, true, false, false);
      this.notifier = this.register(new Setting("ModuleNotifier", ModuleTools.Notifier.PHOBOS));
      this.popNotifier = this.register(new Setting("PopNotifier", ModuleTools.PopNotifier.PHOBOS));
      INSTANCE = this;
   }

   public static ModuleTools getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new ModuleTools();
      }

      return INSTANCE;
   }

   public static enum PopNotifier {
      PHOBOS,
      FUTURE,
      DOTGOD,
      NONE;
   }

   public static enum Notifier {
      TROLLGOD,
      PHOBOS,
      FUTURE,
      DOTGOD;
   }
}

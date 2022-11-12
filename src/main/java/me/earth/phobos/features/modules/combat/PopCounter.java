package me.cum.fusion.features.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import me.earth.phobos.features.command.Command;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.Module.Category;
import me.earth.phobos.features.modules.client.HUD;
import me.earth.phobos.features.modules.client.ModuleTools;
import me.earth.phobos.features.modules.client.ModuleTools.PopNotifier;
import net.minecraft.entity.player.EntityPlayer;

public class PopCounter extends Module {
   public static HashMap<String, Integer> TotemPopContainer = new HashMap();
   private static PopCounter INSTANCE = new PopCounter();

   public PopCounter() {
      super("PopCounter", "Counts other players totem pops.", Category.COMBAT, true, false, false);
      this.setInstance();
   }

   public static PopCounter getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new PopCounter();
      }

      return INSTANCE;
   }

   private void setInstance() {
      INSTANCE = this;
   }

   public void onEnable() {
      TotemPopContainer.clear();
   }

   public String death1(EntityPlayer player) {
      int l_Count = (Integer)TotemPopContainer.get(player.func_70005_c_());
      TotemPopContainer.remove(player.func_70005_c_());
      if (l_Count == 1) {
         if (!ModuleTools.getInstance().isEnabled()) {
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totem!";
         }

         switch((PopNotifier)ModuleTools.getInstance().popNotifier.getValue()) {
         case FUTURE:
            return ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.func_70005_c_() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.GRAY + " totem.";
         case PHOBOS:
            return ChatFormatting.GOLD + player.func_70005_c_() + ChatFormatting.RED + " died after popping " + ChatFormatting.GOLD + l_Count + ChatFormatting.RED + " totem.";
         case DOTGOD:
            return ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.LIGHT_PURPLE + " time!";
         case NONE:
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totem!";
         }
      } else {
         if (!ModuleTools.getInstance().isEnabled()) {
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totems!";
         }

         switch((PopNotifier)ModuleTools.getInstance().popNotifier.getValue()) {
         case FUTURE:
            return ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.func_70005_c_() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.GRAY + " totems.";
         case PHOBOS:
            return ChatFormatting.GOLD + player.func_70005_c_() + ChatFormatting.RED + " died after popping " + ChatFormatting.GOLD + l_Count + ChatFormatting.RED + " totems.";
         case DOTGOD:
            return ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.LIGHT_PURPLE + " times!";
         case NONE:
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " died after popping " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totems!";
         }
      }

      return null;
   }

   public void onDeath(EntityPlayer player) {
      if (!fullNullCheck()) {
         if (!getInstance().isDisabled()) {
            if (!mc.field_71439_g.equals(player)) {
               if (TotemPopContainer.containsKey(player.func_70005_c_())) {
                  Command.sendSilentMessage(this.death1(player));
               }

            }
         }
      }
   }

   public String pop(EntityPlayer player) {
      int l_Count = 1;
      if (TotemPopContainer.containsKey(player.func_70005_c_())) {
         l_Count = (Integer)TotemPopContainer.get(player.func_70005_c_());
         HashMap var10000 = TotemPopContainer;
         String var10001 = player.func_70005_c_();
         ++l_Count;
         var10000.put(var10001, l_Count);
      } else {
         TotemPopContainer.put(player.func_70005_c_(), l_Count);
      }

      if (l_Count == 1) {
         if (!ModuleTools.getInstance().isEnabled()) {
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totem.";
         }

         switch((PopNotifier)ModuleTools.getInstance().popNotifier.getValue()) {
         case FUTURE:
            return ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.func_70005_c_() + ChatFormatting.GRAY + " just popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.GRAY + " totem.";
         case PHOBOS:
            return ChatFormatting.GOLD + player.func_70005_c_() + ChatFormatting.RED + " popped " + ChatFormatting.GOLD + l_Count + ChatFormatting.RED + " totem.";
         case DOTGOD:
            return ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.func_70005_c_() + " has popped " + ChatFormatting.RED + l_Count + ChatFormatting.LIGHT_PURPLE + " time in total!";
         case NONE:
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totem.";
         }
      } else {
         if (!ModuleTools.getInstance().isEnabled()) {
            return HUD.getInstance().getCommandMessage() + ChatFormatting.WHITE + player.func_70005_c_() + " popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totems.";
         }

         switch((PopNotifier)ModuleTools.getInstance().popNotifier.getValue()) {
         case FUTURE:
            return ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.func_70005_c_() + ChatFormatting.GRAY + " just popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.GRAY + " totems.";
         case PHOBOS:
            return ChatFormatting.GOLD + player.func_70005_c_() + ChatFormatting.RED + " popped " + ChatFormatting.GOLD + l_Count + ChatFormatting.RED + " totems.";
         case DOTGOD:
            return ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.func_70005_c_() + " has popped " + ChatFormatting.RED + l_Count + ChatFormatting.LIGHT_PURPLE + " times in total!";
         case NONE:
            return ChatFormatting.WHITE + player.func_70005_c_() + " popped " + ChatFormatting.GREEN + l_Count + ChatFormatting.WHITE + " Totems.";
         }
      }

      return "";
   }

   public void onTotemPop(EntityPlayer player) {
      if (!fullNullCheck()) {
         if (!getInstance().isDisabled()) {
            if (!mc.field_71439_g.equals(player)) {
               Command.sendSilentMessage(this.pop(player));
            }
         }
      }
   }
}

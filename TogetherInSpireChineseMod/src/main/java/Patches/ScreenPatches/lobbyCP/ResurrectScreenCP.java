package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.lobby.settings.ResurrectScreen;
import spireTogether.ui.elements.settings.IntegerArrowUISetting;
import spireTogether.ui.elements.settings.ToggleUISetting;

@SpirePatch(clz = spireTogether.screens.lobby.settings.ResurrectScreen.class, method = "init", requiredModId = "spireTogether")
public class ResurrectScreenCP
{
  public static void Postfix(ResurrectScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      Reflection.setFieldValue("name", __instance, "复活设置");
      String[] stringArray = new String[4];
      stringArray[0] = "复活队友血量";
      stringArray[1] = "是否为百分比";
      stringArray[2] = "击杀boss后是否复活";
      stringArray[3] = "死亡后是否允许获得奖励";
      IntegerArrowUISetting temp0 = (IntegerArrowUISetting) __instance.iterables.get(0).other.get(0);
      temp0.label.text = stringArray[0];
      ToggleUISetting temp1 = (ToggleUISetting) __instance.iterables.get(1).middle;
      temp1.label.text = stringArray[1];
      for (int i = 3; i >= 2; i--)
      {
        ToggleUISetting temp = (ToggleUISetting) __instance.iterables.get(i).other.get(0);
        temp.label.text = stringArray[i];
      }
    }
  }
}
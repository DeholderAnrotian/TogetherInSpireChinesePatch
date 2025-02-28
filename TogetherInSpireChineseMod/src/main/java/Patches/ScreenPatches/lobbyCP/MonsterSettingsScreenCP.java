package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.Screen;
import spireTogether.screens.lobby.MPHostPresetsScreen;
import spireTogether.screens.lobby.settings.MonsterSettingsScreen;
import spireTogether.ui.elements.UIElement;
import spireTogether.ui.elements.settings.IntegerArrowUISetting;

@SpirePatch(clz = spireTogether.screens.lobby.settings.MonsterSettingsScreen.class, method = "init", requiredModId = "spireTogether")
public class MonsterSettingsScreenCP
{
  public static void Postfix(MonsterSettingsScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      Reflection.setFieldValue("name", __instance, "怪物");
      String[] stringArray = new String[4];
      stringArray[0] = "怪物基础血量倍率";
      stringArray[1] = "每名玩家使怪物血量上升值";
      stringArray[2] = "怪物基础伤害倍率";
      stringArray[3] = "每名玩家使怪物伤害上升值";
      int e = 3;
      for (int i = __instance.iterables.size() - 1; e >= 0; i--)
      {
        IntegerArrowUISetting temp = (IntegerArrowUISetting) __instance.iterables.get(i).other.get(__instance.iterables.get(i).other.size() - 1);
        temp.label.text = stringArray[e];
        e--;
      }
    }
  }
}
package Patches.ScreenPatches.lobbyCP;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.lobby.settings.GameSettingsScreen;
import spireTogether.ui.elements.settings.InputfieldUISetting;
import spireTogether.ui.elements.settings.IntegerArrowUISetting;

@SpirePatch(clz = spireTogether.screens.lobby.settings.GameSettingsScreen.class, method = "init", requiredModId = "spireTogether")
public class GameSettingsScreenCP
{
  public static void Postfix(GameSettingsScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      Reflection.setFieldValue("name", __instance, "游戏");
      String[] stringArray = new String[6];
      stringArray[0] = "游戏种子";
      stringArray[1] = "进阶等级";
      stringArray[2] = "初始遗物";
      stringArray[3] = "初始荒疫";
      stringArray[4] = "初始药水";
      stringArray[5] = "回合时间限制";
      int e = 5;
      seed.label.text = stringArray[0];
      for (int i = __instance.iterables.size() - 1; e > 0; i--)
      {
        IntegerArrowUISetting temp = (IntegerArrowUISetting) __instance.iterables.get(i).other.get(__instance.iterables.get(i).other.size() - 1);
        temp.label.text = stringArray[e];
        e--;
      }
    }
  }
}

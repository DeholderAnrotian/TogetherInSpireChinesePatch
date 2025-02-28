package Patches.ScreenPatches.lobbyCP;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.lobby.settings.GameSettingsScreen;
import spireTogether.ui.elements.settings.ButtonUISetting;
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
      InputfieldUISetting temp0 = (InputfieldUISetting) __instance.iterables.get(0).other.get(0);
      temp0.label.text = stringArray[0];
      IntegerArrowUISetting temp1 = (IntegerArrowUISetting) __instance.iterables.get(1).other.get(0);
      temp1.label.text = stringArray[1];
      ButtonUISetting temp2 = (ButtonUISetting) __instance.iterables.get(2).middle;
      temp2.label.text = stringArray[2];
      ButtonUISetting temp3 = (ButtonUISetting) __instance.iterables.get(3).middle;
      temp3.label.text = stringArray[3];
      ButtonUISetting temp4 = (ButtonUISetting) __instance.iterables.get(4).middle;
      temp4.label.text = stringArray[4];
      IntegerArrowUISetting temp5 = (IntegerArrowUISetting) __instance.iterables.get(5).other.get(0);
      temp5.label.text = stringArray[5];
    }
  }
}

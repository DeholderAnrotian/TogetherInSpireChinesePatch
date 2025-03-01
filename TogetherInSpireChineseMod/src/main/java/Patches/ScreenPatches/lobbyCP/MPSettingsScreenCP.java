package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.lobby.settings.MPSettingsScreen;
import spireTogether.ui.elements.settings.*;

@SpirePatch(clz = spireTogether.screens.lobby.settings.MPSettingsScreen.class, method = "init", requiredModId = "spireTogether")
public class MPSettingsScreenCP
{
  public static void Postfix(MPSettingsScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      Reflection.setFieldValue("name", __instance, "多人设置");
      String[] stringArray = new String[8];
      stringArray[0] = "每次交易最大卡片数量";
      stringArray[1] = "每次交易最大遗物数量";
      stringArray[2] = "每次交易最大药水数量";
      stringArray[3] = "每次交易最大金币数量";
      stringArray[4] = "禁用交易物品黑名单";
      stringArray[5] = "友方开火方式";
      stringArray[6] = "允许作弊";
      stringArray[7] = "游戏开始方式";
      for (int i = 0; i < 4; i++)
      {
        IntegerArrowUISetting temp = (IntegerArrowUISetting) __instance.iterables.get(i).other.get(0);
        temp.label.text = stringArray[i];
      }
      ToggleUISetting temp4 = (ToggleUISetting) __instance.iterables.get(4).other.get(0);
      temp4.label.text = stringArray[4];
      EnumUISetting temp5 = (EnumUISetting) __instance.iterables.get(5).middle;
      temp5.label.text = stringArray[5];
      ToggleUISetting temp6 = (ToggleUISetting) __instance.iterables.get(6).other.get(0);
      temp6.label.text = stringArray[6];
      EnumUISetting temp7 = (EnumUISetting) __instance.iterables.get(7).middle;
      temp7.label.text = stringArray[7];
    }
  }
}

package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.lobby.MPHostPresetsScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.useable.Label;

public class MPHostPresetsScreenCP
{
  @SpirePatch(clz = spireTogether.screens.lobby.MPHostPresetsScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(MPHostPresetsScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(0, new Label("选择游戏难度", 97, 954, 75));
        __instance.frontLayer.elements.set(1, new BoxedLabel("取消", 100, 78, 558, 152));
        __instance.frontLayer.elements.set(2, new BoxedLabel("确定", 1274, 78, 558, 152));
//        for(int i=0;i<3;i++)
//        {
//          HostPresetBox temp=(HostPresetBox)__instance.iterables.get(0).middle;
//          temp.descText.text=toChinese(Presets);
//        }
//
//        __instance.iterables.get(0).middle
//        __instance.iterables.get(1).middle.name = "地狱";
//        __instance.iterables.get(2).middle.name = "自定义";
      }
    }
  }
}

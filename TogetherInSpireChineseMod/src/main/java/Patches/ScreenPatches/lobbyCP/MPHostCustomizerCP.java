package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.lobby.MPHostCustomizer;
import spireTogether.ui.elements.mixed.BoxedLabel;


public class MPHostCustomizerCP
{
  @SpirePatch(clz = spireTogether.screens.lobby.MPHostCustomizer.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(MPHostCustomizer __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(0, new BoxedLabel("本体游戏特效", 1280, 24, 425, 110));
      }
    }
  }

  @SpirePatch(clz = spireTogether.screens.lobby.MPHostCustomizer.class, method = "init", requiredModId = "spireTogether")
  public static class initInsert
  {
    @SpireInsertPatch(rloc = 27)
    public static void Insert(MPHostCustomizer __instance)
    {
      __instance.screenLabel.text = "游戏设置";
    }
  }
}

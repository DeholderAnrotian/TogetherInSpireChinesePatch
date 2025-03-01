package Patches.TradingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.trading.TradingWaitingOnPlayerScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;

public class TradingWaitingOnPlayerScreenCP
{
  @SpirePatch(clz = spireTogether.screens.trading.TradingWaitingOnPlayerScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(TradingWaitingOnPlayerScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(0, new BoxedLabel("等待其他玩家确认......", 0, 0, 1920, 1080));
      }
    }
  }
}

package Patches.TradingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.trading.TradingScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;

public class TradingScreenCP
{
  @SpirePatch(clz = spireTogether.screens.trading.TradingScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(TradingScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(__instance.frontLayer.elements.size() - 1, new BoxedLabel("锁定", 650, 17, 620, 110)
        {
          public void update()
          {
            super.update();
            if (__instance.player.locked)
            {
              this.SetText("解锁");
            }
            else
            {
              this.SetText("锁定");
            }
          }
        });
      }
    }
  }
}

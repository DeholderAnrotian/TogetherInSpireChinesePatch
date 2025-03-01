package Patches.TradingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.trading.PreTradingScreen;

public class PreTradingScreenCP
{
  @SpirePatch(clz = spireTogether.screens.trading.PreTradingScreen.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class ConstructorPostfix
  {
    public static void Postfix(PreTradingScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        Reflection.setFieldValue("title", __instance, "选择一名玩家进行交易");
      }
    }
  }
}

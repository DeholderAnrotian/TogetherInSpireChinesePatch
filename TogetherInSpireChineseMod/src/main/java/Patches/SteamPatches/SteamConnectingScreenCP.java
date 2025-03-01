package Patches.SteamPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import spireTogether.screens.steam.SteamConnectingScreen;
import spireTogether.ui.elements.UIElement;
import spireTogether.ui.elements.mixed.BoxedLabel;

public class SteamConnectingScreenCP
{
  @SpirePatch(clz = spireTogether.screens.steam.SteamConnectingScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(SteamConnectingScreen __instance)
    {
      __instance.text = new BoxedLabel("正在连接......", UIElement.GetXFromMiddleWidth(1230), 700, 1230, 250, 0.0F, 0.0F, 100, 0, 100, true, false, false);
    }
  }
}
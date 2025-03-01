package Patches.SteamPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.steam.SteamLobbyListScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;

import static Helpers.EnumToChineseStringsHelper.toChinese;

public class SteamLobbyListScreenCP
{
  @SpirePatch(clz = spireTogether.screens.steam.SteamLobbyListScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(SteamLobbyListScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(4, new BoxedLabel(SteamLobbyListScreen.filter_preset == null ? "全部" : toChinese(SteamLobbyListScreen.filter_preset), 1565, 715, 265, 60)
        {
          public void update()
          {
            this.SetText(SteamLobbyListScreen.filter_preset == null ? "全部" : toChinese(SteamLobbyListScreen.filter_preset));
            super.update();
          }
        });
      }
    }
  }
}
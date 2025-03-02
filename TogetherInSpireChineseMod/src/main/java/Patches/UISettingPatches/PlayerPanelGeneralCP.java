package Patches.UISettingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import spireTogether.screens.Screen;
import spireTogether.ui.elements.Renderable;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.presets.PlayerPanelGeneral;

public class PlayerPanelGeneralCP
{
  @SpirePatch(clz = PlayerPanelGeneral.class, method = "SetEmpty", requiredModId = "spireTogether")
  public static class SetEmptyPostfix
  {
    public static PlayerPanelGeneral Postfix(PlayerPanelGeneral __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.conditionals.elements.clear();
        __instance.conditionals.Add((new BoxedLabel("<空>", __instance.xPos, __instance.yPos, (int) (768.0F * __instance.scale), (int) (150.0F * __instance.scale))).SetFont(FontHelper.bannerNameFont));
        __instance.baseElements.elements.clear();
        __instance.baseElements.Add(new Renderable(Screen.ui.button_large, __instance.xPos, __instance.yPos, (int) (768.0F * __instance.scale), (int) (150.0F * __instance.scale)));
        __instance.playerID = -1;
      }
      return __instance;
    }
  }
}

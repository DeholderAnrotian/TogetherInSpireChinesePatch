package Patches.UISettingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.network.objects.settings.GameSettings;
import spireTogether.ui.elements.presets.HostPresetBox;

import static Helpers.EnumToChineseStringsHelper.toChinese;

public class HostPresetBoxCP
{
  @SpirePatch(clz = HostPresetBox.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(HostPresetBox __instance, Integer xPos, Integer yPos, final GameSettings.Presets preset)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.descText = __instance.descButton.GenerateLabel(toChinese(preset));
      }
    }
  }
}

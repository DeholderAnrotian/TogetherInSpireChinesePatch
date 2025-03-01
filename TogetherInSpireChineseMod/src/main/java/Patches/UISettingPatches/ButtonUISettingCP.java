package Patches.UISettingPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.settings.ButtonUISetting;

public class ButtonUISettingCP
{
  @SpirePatch(clz = spireTogether.ui.elements.settings.ButtonUISetting.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class constructorPostfix
  {
    public static void Postfix(ButtonUISetting __instance, String text, Integer xPos, Integer yPos)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        Reflection.setFieldValue("buttonLabel", __instance, new BoxedLabel("选择", xPos + 1478, yPos, 275, 75));
      }
    }
  }
}

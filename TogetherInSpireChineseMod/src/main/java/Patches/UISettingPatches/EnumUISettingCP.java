package Patches.UISettingPatches;

import Helpers.EnumToChineseStringsHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.network.objects.settings.GameSettings;
import spireTogether.ui.elements.settings.EnumUISetting;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumUISettingCP
{
  @SpirePatch(clz = spireTogether.ui.elements.settings.EnumUISetting.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class constructorPostfix
  {
    public static <E extends Enum<E>> void Postfix(EnumUISetting __instance, String text, Integer xPos, Integer yPos, String startValue, Class<E> enumClass)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        if (enumClass.isEnum())
        {
          List<String> enumList = new ArrayList<>();
          for (E e : EnumSet.allOf(enumClass))
          {
            if (enumClass == GameSettings.Presets.class)
            {
              enumList.add(EnumToChineseStringsHelper.toChinese((GameSettings.Presets) e));
            }
            else if (enumClass == GameSettings.StartType.class)
            {
              enumList.add(EnumToChineseStringsHelper.toChinese((GameSettings.StartType) e));
            }
            else if (enumClass == GameSettings.FriendlyFireType.class)
            {
              enumList.add(EnumToChineseStringsHelper.toChinese((GameSettings.FriendlyFireType) e));
            }
            else
            {
              enumList.add(e.toString());
            }
          }
          Reflection.setFieldValue("enumList", __instance, enumList);
        }
      }
    }
  }
}

package Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.campfireOptions.ResurrectOption;
import spireTogether.campfireOptions.TradeOption;

public class CampfireCP
{
  @SpirePatch(clz = spireTogether.campfireOptions.ResurrectOption.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class ResurrectOptionPatch
  {
    public static void Postfix(ResurrectOption __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        Reflection.setFieldValue("label", __instance, "复活");
        Reflection.setFieldValue("description", __instance, "复活一名死亡的玩家");
      }
    }
  }

  @SpirePatch(clz = spireTogether.campfireOptions.ResurrectOption.class, method = "updateUsability", requiredModId = "spireTogether")
  public static class ResurrectOptionUpdatePatch
  {
    public static void Postfix(ResurrectOption __instance, boolean canUse)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        if (!canUse)
        {
          Reflection.setFieldValue("description", __instance, "这个选项已经用过了");
        }
      }
    }
  }

  @SpirePatch(clz = spireTogether.campfireOptions.TradeOption.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
  public static class TradeOptionPatch
  {
    public static void Postfix(TradeOption __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        Reflection.setFieldValue("label", __instance, "交易");
        Reflection.setFieldValue("description", __instance, "免费行动：和其他玩家交易");
      }
    }
  }

  @SpirePatch(clz = spireTogether.campfireOptions.TradeOption.class, method = "updateUsability", requiredModId = "spireTogether")
  public static class TradeOptionUpdatePatch
  {
    public static void Postfix(TradeOption __instance, boolean canUse)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        if (!canUse)
        {
          Reflection.setFieldValue("description", __instance, "这个选项已经用过了");
        }
      }
    }
  }
}

package Patches.ScreenPatches.resurrectScreenCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.screens.resurrecting.ResurrectScreen;

@SpirePatch(clz = spireTogether.screens.resurrecting.ResurrectScreen.class, method = SpirePatch.CONSTRUCTOR, requiredModId = "spireTogether")
public class ResurrectingScreenCP
{
  public static void Postfix(ResurrectScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      Reflection.setFieldValue("title", __instance, "选择一名玩家复活");
    }
  }
}

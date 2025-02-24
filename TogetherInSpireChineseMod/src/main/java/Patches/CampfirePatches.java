package Patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.SpireTogetherMod;
import spireTogether.campfireOptions.ResurrectOption;

public class CampfirePatches {
    @SpirePatch(
            clz= spireTogether.campfireOptions.ResurrectOption.class,
            method="SpirePatch.CONSTRUCTOR",
            requiredModId = "spireTogether"
    )
    public static class ResurrectOptionPatch
    {
        @SpirePostfixPatch
        public static void optionNamePatch(ResurrectOption __instance)
        {
            if(Settings.language== Settings.GameLanguage.ZHS)
            {
                Reflection.setFieldValue("label",__instance, "复活" );
                Reflection.setFieldValue("description",__instance, "复活一名死亡的玩家" );
            }
        }

    }
}

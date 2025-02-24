package Patches;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import spireTogether.SpireTogetherMod;

@SpirePatch(
        clz= SpireTogetherMod.class,
        method="receiveEditStrings",
        requiredModId = "spireTogether"
)
public class UIStringsPatches {

    public static void Postfix()
    {
        if (Settings.language == Settings.GameLanguage.ZHS)
            BaseMod.loadCustomStringsFile(UIStrings.class, "localization/zhs/UIStrings.json");
    }
}

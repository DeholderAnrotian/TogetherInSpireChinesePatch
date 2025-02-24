package Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import spireTogether.SpireTogetherMod;
import spireTogether.patches.ui.MainMenuButtonsPatch;
import spireTogether.util.Reflection;

@SpirePatch(
        clz= MainMenuButtonsPatch.LabelPatcher.class,
        method="Postfix",
        requiredModId = "spireTogether"
)
public class MainMenuPanelPatches {
    public static void Postfix(MenuButton __instance, String ___label)
    {
        if(Settings.language== Settings.GameLanguage.ZHS)
        {
            if (__instance.result == MainMenuButtonsPatch.Enums.PLAY_MULTIPLAYER) {
                Reflection.setFieldValue("label", __instance, "多人游戏");
            } else if (__instance.result == MenuButton.ClickResult.PLAY) {
                Reflection.setFieldValue("label", __instance, "单人游戏");
            } else if (__instance.result == MenuButton.ClickResult.RESUME_GAME) {
                Reflection.setFieldValue("label", __instance, "单人游戏 - 继续");
            } else if (__instance.result == MenuButton.ClickResult.ABANDON_RUN) {
                Reflection.setFieldValue("label", __instance, "单人游戏 - 放弃");
            }
        }
    }
}

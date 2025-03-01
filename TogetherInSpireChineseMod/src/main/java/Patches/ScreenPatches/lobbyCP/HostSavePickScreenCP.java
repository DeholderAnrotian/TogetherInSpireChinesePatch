package Patches.ScreenPatches.lobbyCP;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.screens.ScreenManager;
import spireTogether.screens.lobby.HostSavePickerScreen;
import spireTogether.screens.steam.CreateLobbyScreen;
import spireTogether.ui.elements.UIElement;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.useable.Clickable;
import spireTogether.ui.elements.useable.Label;
import spireTogether.util.SpireVariables;

import static spireTogether.screens.Screen.ui;

@SpirePatch(clz = spireTogether.screens.lobby.HostSavePickerScreen.class, method = "init", requiredModId = "spireTogether")
public class HostSavePickScreenCP
{
  public static void Postfix(HostSavePickerScreen __instance)
  {
    if (Settings.language == Settings.GameLanguage.ZHS)
    {
      __instance.frontLayer.elements.set(0, new Label("继续保存的游戏或者创建新游戏", 56, 975, 75));
      if (SpireVariables.compatibleSaves.size() > 5)
      {
        __instance.frontLayer.elements.set(4, new BoxedLabel("新游戏", UIElement.GetXForMirrorElement(412, 486, false), 32, 486, 120));
        __instance.frontLayer.elements.set(3, new BoxedLabel("返回", UIElement.GetXForMirrorElement(412, 486, true), 32, 486, 120));
      }
      else
      {
        __instance.frontLayer.elements.set(2, new BoxedLabel("新游戏", UIElement.GetXForMirrorElement(412, 486, false), 32, 486, 120));
        __instance.frontLayer.elements.set(1, new BoxedLabel("返回", UIElement.GetXForMirrorElement(412, 486, true), 32, 486, 120));
      }
      __instance.iterables.get(1).middle = new Clickable(ui.button_large, UIElement.GetXForMirrorElement(412, 486, true), 32, 486, 120)
      {
        public void OnLeftClick()
        {
          super.OnLeftClick();
          ScreenManager.Open(CreateLobbyScreen.class);
        }

        public String GetSelectedLine()
        {
          return "CANCEL";
        }

        public String GetInteractLine()
        {
          return null;
        }
      };
    }
  }
}

package Patches.SteamPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import spireTogether.network.steam.SteamManager;
import spireTogether.screens.steam.SteamPasswordScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.useable.Clickable;
import spireTogether.ui.elements.useable.Label;

import static spireTogether.screens.Screen.ui;

public class SteamPasswordScreenCP
{
  @SpirePatch(clz = spireTogether.screens.steam.SteamPasswordScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(SteamPasswordScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        __instance.frontLayer.elements.set(0, new Label("输入密码", 85, 564, 75));
        __instance.frontLayer.elements.set(1, new BoxedLabel("加入", 729, 108, 500, 100));
        __instance.iterables.get(2).middle = new Clickable(ui.button_large, 729, 108, 500, 100)
        {
          public void OnLeftClick()
          {
            super.OnLeftClick();
            if (SteamPasswordScreen.password.equals(SteamPasswordScreen.enteredPassword))
            {
              SteamManager.JoinLobby(SteamPasswordScreen.lobbyID);
            }
            else
            {
              SteamPasswordScreen.enteredPassword = "密码错误!";
              __instance.passInputfield.SetText(SteamPasswordScreen.enteredPassword);
//            if (ModManager.SayTheSpire_Running)
//            {
//             Output.text("Incorrect password", true);
//            }
            }
          }

          public String GetSelectedLine()
          {
            return "JOIN";
          }

          public String GetInteractLine()
          {
            return null;
          }
        };
      }
    }
  }
}

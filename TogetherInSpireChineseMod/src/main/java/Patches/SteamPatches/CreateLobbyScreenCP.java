package Patches.SteamPatches;

import com.codedisaster.steamworks.SteamMatchmaking;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import dLib.util.Reflection;
import spireTogether.network.steam.SteamManager;
import spireTogether.screens.steam.CreateLobbyScreen;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.mixed.InputField;
import spireTogether.ui.elements.useable.Clickable;

import static spireTogether.screens.Screen.ui;


public class CreateLobbyScreenCP
{
  @SpirePatch(clz = spireTogether.screens.steam.CreateLobbyScreen.class, method = "init", requiredModId = "spireTogether")
  public static class initPostfix
  {
    public static void Postfix(CreateLobbyScreen __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        Reflection.setFieldValue("lobbyName", __instance, SteamManager.friends.getPersonaName() + "的房间");
        Reflection.setFieldValue("lobbyTypeLabel", __instance, new BoxedLabel("公开", 80, 290, 390, 75));
        __instance.frontLayer.elements.set(3, __instance.lobbyTypeLabel);
        __instance.iterables.get(2).middle = (new InputField(ui.inputfield, 85, 660, 1766, 142)
        {
          public void OnTextChange(String newText)
          {
            super.OnTextChange(newText);
            CreateLobbyScreen.lobbyName = newText;
          }

          public String GetSelectedLine()
          {
            return CreateLobbyScreen.lobbyName.isEmpty() ? "Enter lobby name" : "Edit current lobby name of " + CreateLobbyScreen.lobbyName;
          }

          public String GetInteractLine()
          {
            return null;
          }

          public String GetOnTextChangedLine(String newText)
          {
            return "Entered lobby name: " + newText;
          }
        }).SetCharLimit(20).SetText(CreateLobbyScreen.lobbyName);
        __instance.iterables.get(4).middle = new Clickable(ui.button_large, 80, 290, 390, 75)
        {
          public void OnLeftClick()
          {
            super.OnLeftClick();
            switch (CreateLobbyScreen.lobbyType)
            {
              case Public:
                CreateLobbyScreen.lobbyType = SteamMatchmaking.LobbyType.Private;
                __instance.lobbyTypeLabel.SetText("仅邀请");
                break;
              case Private:
                CreateLobbyScreen.lobbyType = SteamMatchmaking.LobbyType.FriendsOnly;
                __instance.lobbyTypeLabel.SetText("好友及邀请");
                break;
              case FriendsOnly:
                CreateLobbyScreen.lobbyType = SteamMatchmaking.LobbyType.Public;
                __instance.lobbyTypeLabel.SetText("公开");
            }

          }

          public String GetSelectedLine()
          {
            return "CURRENT LOBBY TYPE: " + __instance.lobbyTypeLabel.text;
          }

          public String GetInteractLine()
          {
            return "CHANGED LOBBY TYPE TO: " + __instance.lobbyTypeLabel.text;
          }
        };
      }
    }
  }
}

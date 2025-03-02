package Patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import spireTogether.chat.ChatConsole;
import spireTogether.ui.fonts.FontManager;

import static spireTogether.chat.ChatConsole.*;

public class ChatConsoleCP
{
  @SpirePatch(clz = spireTogether.chat.ChatConsole.class, method = "receivePostInitialize", requiredModId = "spireTogether")
  public static class receivePostInitializePrefix
  {
    public static SpireReturn<Void> Prefix(ChatConsole __instance)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        consoleFont = FontManager.GenerateNonASCIIFont();
        consoleFont.setColor(Color.WHITE);
        consoleBackground = ImageMaster.loadImage("img/ConsoleBackground.png");
        if (messagePrompt == null)
        {
          messagePrompt = ParseText("输入: ", (int) (210.0F * Settings.scale), (int) (200.0F * Settings.scale));
        }

        if (openPrompt == null)
        {
          openPrompt = ParseText("按Tab键打开聊天框", (int) (210.0F * Settings.scale), (int) (200.0F * Settings.scale));
        }

      }
      else
      {
        __instance.receivePostInitialize();
      }
      return SpireReturn.Return();
    }
  }

  @SpirePatch(clz = spireTogether.chat.ChatConsole.class, method = "setText", requiredModId = "spireTogether")
  public static class setTextPrefix
  {
    public static SpireReturn<Void> Prefix(ChatConsole __instance, String updatedText)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        setCurrentText(updatedText);
        currentMessage = ParseText("输入: " + currentText, (int) (210.0F * Settings.scale), (int) (200.0F * Settings.scale));
        currentMessage.remove(0);

      }
      else
      {
        __instance.setText(updatedText);
      }
      return SpireReturn.Return();
    }
  }
}

package Patches.ScreenPatches.lobbyCP;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SeedHelper;
import com.megacrit.cardcrawl.helpers.TrialHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import com.megacrit.cardcrawl.trials.CustomTrial;
import dLib.modcompat.ModManager;
import skindex.registering.SkindexRegistry;
import skindex.skins.player.PlayerSkin;
import spireTogether.monsters.CharacterEntity;
import spireTogether.network.P2P.P2PManager;
import spireTogether.network.P2P.P2PMessageSender;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.settings.GameSettings;
import spireTogether.network.objects.settings.NetworkCustomMod;
import spireTogether.patches.RandomCharacterPatches;
import spireTogether.screens.Screen;
import spireTogether.screens.ScreenManager;
import spireTogether.screens.lobby.MPLobbyScreen;
import spireTogether.ui.elements.Renderable;
import spireTogether.ui.elements.mixed.BoxedLabel;
import spireTogether.ui.elements.useable.Clickable;
import spireTogether.util.DevConfig;
import spireTogether.util.Reflection;
import spireTogether.util.SpireLogger;
import spireTogether.util.UIElements;

import java.util.ArrayList;

import static spireTogether.screens.lobby.MPLobbyScreen.characterRender;
import static spireTogether.screens.lobby.MPLobbyScreen.startButtonManager;

public class MPLobbyScreenCP
{
  @SpirePatch(clz = MPLobbyScreen.class, method = "RegisterStartButton", requiredModId = "spireTogether")
  public static class RegisterStartButtonPostfix
  {
    public static void Postfix(MPLobbyScreen __instance, Texture buttonImg, Color textColor)
    {
      if (Settings.language == Settings.GameLanguage.ZHS)
      {
        startButtonManager.elements.clear();
        __instance.ClearNonBaseIterables();
        if (P2PManager.data.settings.startType != GameSettings.StartType.FREE_FOR_ALL && IsLobbyLocked())
        {
          if (P2PManager.data.settings.startType == GameSettings.StartType.ALL_READY)
          {
            if (P2PManager.GetSelf().startStatus != P2PPlayer.StartStatus.READY)
            {
              __instance.AddIterable(new Clickable(buttonImg, 1042, 38, 768, 150)
              {
                public void OnLeftClick()
                {
                  super.OnLeftClick();
                  P2PManager.GetSelf().startStatus = P2PPlayer.StartStatus.READY;
                  __instance.selfPanel.UpdateData();
                  P2PMessageSender.Send_ChangedPlayerStartStatus(P2PManager.GetSelf().startStatus);
                  MPLobbyScreen.resetStartButton = true;
                  if (MPLobbyScreen.AreAllPlayersReady())
                  {
                    Embark();
                  }
                }

                public String GetSelectedLine()
                {
                  return "Ready";
                }

                public String GetInteractLine()
                {
                  return null;
                }
              });
              startButtonManager.Add(new BoxedLabel("准备", 1042, 38, 768, 150));
            }
            else
            {
              __instance.AddIterable(new Clickable(buttonImg, 1042, 38, 768, 150)
              {
                public void OnLeftClick()
                {
                  super.OnLeftClick();
                  P2PManager.GetSelf().startStatus = P2PPlayer.StartStatus.IDLE;
                  __instance.selfPanel.UpdateData();
                  P2PMessageSender.Send_ChangedPlayerStartStatus(P2PManager.GetSelf().startStatus);
                  MPLobbyScreen.resetStartButton = true;
                  __instance.EmbarkIfAllPlayersReady();
                }

                public String GetSelectedLine()
                {
                  return "Unready";
                }

                public String GetInteractLine()
                {
                  return null;
                }
              });
              startButtonManager.Add(new BoxedLabel("取消准备", 1042, 38, 768, 150));
            }
          }
          else if (P2PManager.data.settings.startType == GameSettings.StartType.OWNER_UNLOCK)
          {
            if ((P2PManager.GetSelf().IsLobbyOwner() || DevConfig.isDeveloper) && IsLobbyLocked())
            {
              __instance.AddIterable(new Clickable(buttonImg, 1042, 38, 768, 150)
              {
                public void OnLeftClick()
                {
                  super.OnLeftClick();
                  P2PManager.data.settings.startType = GameSettings.StartType.FREE_FOR_ALL;
                  P2PMessageSender.Send_UnlockedRoom();
                  MPLobbyScreen.resetStartButton = true;
                }

                public String GetSelectedLine()
                {
                  return "Unlock";
                }

                public String GetInteractLine()
                {
                  return null;
                }
              });
              startButtonManager.Add(new BoxedLabel("解锁", 1042, 38, 768, 150));
            }
            else if (!P2PManager.GetSelf().IsLobbyOwner())
            {
              startButtonManager.Add(new Renderable(Screen.ui.button_large_outline_empty, 1042, 38, 768, 150));
              startButtonManager.Add((new BoxedLabel("等待房主中", 1042, 38, 768, 150, 0.07F, 0.3F, true, true)).SetColour(textColor));
            }
          }
        }
        else
        {
          __instance.AddIterable(new Clickable(buttonImg, 1042, 38, 768, 150)
          {
            public void OnLeftClick()
            {
              super.OnLeftClick();
              P2PManager.GetSelf().startStatus = P2PPlayer.StartStatus.EMBARKED;
              __instance.selfPanel.UpdateData();
              this.canClick = true;
              Embark();
            }

            public String GetSelectedLine()
            {
              return "Embark";
            }

            public String GetInteractLine()
            {
              return null;
            }
          });
          startButtonManager.Add(new BoxedLabel("出发", 1042, 38, 768, 150));
        }

        MPLobbyScreen.resetStartButton = false;
      }
    }

    static boolean IsLobbyLocked()
    {
      if (P2PManager.data.settings.startType == GameSettings.StartType.OWNER_UNLOCK)
      {
        P2PPlayer p = P2PManager.GetLobbyOwner();
        if (p != null)
        {
          return p.startStatus != P2PPlayer.StartStatus.READY && p.startStatus != P2PPlayer.StartStatus.EMBARKED;
        }
      }
      return true;
    }

    static void Embark()
    {
      if (ModManager.TogetherInSpireBoosterPackMod.isActive())
      {
        UIElements.Cursors.boomerang.Unlock();
      }

      if (characterRender.playerClass.equals(RandomCharacterPatches.Enums.MP_RANDOM))
      {
        AbstractPlayer randChar = CardCrawlGame.characterManager.getRandomCharacter(new Random());
        P2PManager.GetSelf().UpdatePlayerClass(randChar.chosenClass, true);
        characterRender = CharacterEntity.Get(randChar, false);
        PlayerSkin s = SkindexRegistry.getRandomSkin(characterRender.playerClass, true);
        if (s != null)
        {
          P2PManager.GetSelf().UpdateSkin(s, true);
        }

        P2PManager.GetSelf().HP = randChar.startingMaxHP;
      }

      AbstractDungeon.player = null;
      SpireLogger.LogClient("Embarking!");
      String setSeed = P2PManager.data.settings.setSeed;
      if (!setSeed.isEmpty())
      {
        boolean isTrialSeed = TrialHelper.isTrialSeed(setSeed);
        if (isTrialSeed)
        {
          Settings.specialSeed = SeedHelper.getLong(setSeed);
          Settings.isTrial = true;
          Settings.seed = P2PManager.data.gameSeed;
        }
        else
        {
          SeedHelper.setSeed(setSeed);
        }
      }
      else
      {
        Settings.seed = P2PManager.data.gameSeed;
      }

      CardCrawlGame.chosenCharacter = characterRender.playerClass;
      CardCrawlGame.mainMenuScreen.isFadingOut = true;
      CardCrawlGame.mainMenuScreen.fadeOutMusic();
      Settings.isDailyRun = false;
      Settings.isEndless = false;
      P2PManager.GetSelf().startStatus = P2PPlayer.StartStatus.EMBARKED;
      P2PMessageSender.Send_ChangedPlayerStartStatus(P2PPlayer.StartStatus.EMBARKED);
      AbstractDungeon.generateSeeds();
      long personalSeed = P2PManager.data.privateSeed;
      AbstractDungeon.merchantRng = new Random(personalSeed);
      AbstractDungeon.treasureRng = new Random(personalSeed);
      AbstractDungeon.relicRng = new Random(personalSeed);
      AbstractDungeon.potionRng = new Random(personalSeed);
      AbstractDungeon.shuffleRng = new Random(personalSeed);
      AbstractDungeon.cardRandomRng = new Random(personalSeed);
      AbstractDungeon.cardRng = new Random(personalSeed);
      Integer ascensionLevel = P2PManager.data.settings.ascensionLevel;
      AbstractDungeon.isAscensionMode = ascensionLevel > 0;
      AbstractDungeon.ascensionLevel = ascensionLevel;
      if (!P2PManager.data.settings.customMods.isEmpty())
      {
        Settings.isTrial = true;
        CustomTrial trial = new CustomTrial();
        ArrayList<CustomMod> modList = NetworkCustomMod.Convert(P2PManager.data.settings.customMods);
        trial.addDailyMods(GetActiveDailyModIds(modList));
        Reflection.InvokeMethod("addNonDailyMods", new CustomModeScreen(), trial, GetActiveNonDailyModIds(modList));
        Settings.isEndless = trial.dailyModIDs().contains("Endless");
        CardCrawlGame.trial = trial;
        AbstractPlayer.customMods = CardCrawlGame.trial.dailyModIDs();
        SpireLogger.LogClient("Running trial with following mods:");

        for (CustomMod m : modList)
        {
          SpireLogger.LogClient(m.ID);
        }
      }
      else
      {
        Settings.isTrial = false;
      }

      ScreenManager.Close();
      CardCrawlGame.fadeToBlack(0.0F);
    }

    static ArrayList<String> GetActiveDailyModIds(ArrayList<CustomMod> modList)
    {
      ArrayList<String> active = new ArrayList<>();

      for (CustomMod mod : modList)
      {
        if (mod.selected && mod.isDailyMod)
        {
          active.add(mod.ID);
        }
      }
      return active;
    }

    static ArrayList<String> GetActiveNonDailyModIds(ArrayList<CustomMod> modList)
    {
      ArrayList<String> active = new ArrayList<>();

      for (CustomMod mod : modList)
      {
        if (mod.selected && !mod.isDailyMod)
        {
          active.add(mod.ID);
        }
      }

      return active;
    }
  }
}

package Helpers;

import spireTogether.network.objects.settings.GameSettings;

public class EnumToChineseStringsHelper
{
  public static String toChinese(GameSettings.Presets input)
  {
    if (input == GameSettings.Presets.CUSTOM)
    {
      return "自定义模式";
    }
    else if (input == GameSettings.Presets.HELL)
    {
      return "地狱模式";
    }
    else if (input == GameSettings.Presets.MINIGAME)
    {
      return "迷你游戏";
    }
    else if (input == GameSettings.Presets.RECOMMENDED)
    {
      return "推荐模式";
    }
    else
    {
      return "未知字符串";
    }
  }

  public static String toChinese(GameSettings.StartType input)
  {
    if (input == GameSettings.StartType.FREE_FOR_ALL)
    {
      return "自由开始";
    }
    else if (input == GameSettings.StartType.OWNER_UNLOCK)
    {
      return "房主解锁后自由开始";
    }
    else if (input == GameSettings.StartType.ALL_READY)
    {
      return "全部准备后开始";
    }
    else
    {
      return "未知字符串";
    }
  }

  public static String toChinese(GameSettings.FriendlyFireType input)
  {
    if (input == GameSettings.FriendlyFireType.FULL)
    {
      return "全部";
    }
    else if (input == GameSettings.FriendlyFireType.NONE)
    {
      return "无友伤";
    }
    else if (input == GameSettings.FriendlyFireType.TARGETING)
    {
      return "指向";
    }
    else
    {
      return "未知字符串";
    }
  }
}

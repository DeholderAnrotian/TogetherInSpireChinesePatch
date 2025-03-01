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
}

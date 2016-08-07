package org.lxp.util;

import java.util.List;

public class TextUtil {
  public static String join(List<String> list) {
    String rtn;
    if (list.isEmpty()) {
      rtn = "";
    } else {
      StringBuilder sb = new StringBuilder();
      for (String string : list) {
        sb.append(string).append(",");
      }
      rtn = sb.substring(0, sb.length() - 1);
    }
    return rtn;
  }
}

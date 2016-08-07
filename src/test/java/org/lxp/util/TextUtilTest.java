package org.lxp.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TextUtilTest {
  @Test
  public void testJoin() throws Exception {
    List<String> list = Collections.emptyList();
    Assert.assertEquals("", TextUtil.join(list));

    list = Arrays.asList(new String[] { "1", "2" });
    Assert.assertEquals("1,2", TextUtil.join(list));
  }
}

package org.lxp.converter.vo;

import java.util.Collections;
import java.util.List;

public class ReturnVo {
  private List<FileVo> success;
  private List<String> fail;

  public ReturnVo(List<FileVo> success, List<String> fail) {
    this.success = success;
    this.fail = fail;
  }

  public static ReturnVo empty() {
    List<FileVo> success = Collections.emptyList();
    List<String> fail = Collections.emptyList();
    return new ReturnVo(success, fail);
  }

  public List<FileVo> getSuccess() {
    return success;
  }

  public List<String> getFail() {
    return fail;
  }
}

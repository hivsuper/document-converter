package org.lxp.converter.vo;

public class FileVo {
  private String input;
  private String output;

  public FileVo(String input, String output) {
    this.input = input;
    this.output = output;
  }

  public String getInput() {
    return input;
  }

  public String getOutput() {
    return output;
  }
}

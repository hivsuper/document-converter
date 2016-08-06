package org.lxp.converter.vo;

public class ConfigVo {
  private String pdf2swfPath;
  private String officePath;
  private String sourcePath;

  public ConfigVo(String pdf2swfPath, String officePath, String sourcePath) {
    this.pdf2swfPath = pdf2swfPath;
    this.officePath = officePath;
    this.sourcePath = sourcePath;
  }

  public String getPdf2swfPath() {
    return pdf2swfPath;
  }

  public String getOfficePath() {
    return officePath;
  }

  public String getSourcePath() {
    return sourcePath;
  }
}

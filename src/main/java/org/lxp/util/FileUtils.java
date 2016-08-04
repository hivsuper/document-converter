package org.lxp.util;

public class FileUtils {
  public static final String SEPARATOR = ".";
  public static final String PDF_SUFFIX = "pdf";
  public static final String PPT_SUFFIX = "ppt";
  public static final String PPTX_SUFFIX = "pptx";
  public static final String XLS_SUFFIX = "xls";
  public static final String XLSX_SUFFIX = "xlsx";
  public static final String TXT_SUFFIX = "txt";
  public static final String DOC_SUFFIX = "doc";
  public static final String DOCX_SUFFIX = "docx";
  public static final String SWF_SUFFIX = "swf";

  public static String getFilePrefix(String fileName) {
    return fileName.substring(0, fileName.lastIndexOf(SEPARATOR));
  }

  public static String getFileSufix(String fileName) {
    return fileName.substring(fileName.lastIndexOf(SEPARATOR) + 1);
  }
}

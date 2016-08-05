package org.lxp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
  private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
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

  public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
    File sFile = new File(inputFile);
    File tFile = new File(outputFile);
    FileInputStream fis = new FileInputStream(sFile);
    FileOutputStream fos = new FileOutputStream(tFile);
    int temp = 0;
    try {
      while ((temp = fis.read()) != -1) {
        fos.write(temp);
      }
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    } finally {
      try {
        fis.close();
        fos.close();
      } catch (IOException e) {
        LOG.error(e.getMessage(), e);
      }
    }
  }
}

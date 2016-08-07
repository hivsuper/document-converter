package org.lxp.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.lxp.converter.PDFConverter;
import org.lxp.converter.SWFConverter;
import org.lxp.converter.impl.Doc2SwfConverter;
import org.lxp.converter.impl.JacobPDFConverter;
import org.lxp.converter.impl.JodPDFconverter;
import org.lxp.converter.impl.SWFToolsSWFConverter;
import org.lxp.converter.vo.ConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://www.cnblogs.com/luckyxiaoxuan/archive/2012/06/15/2550303.html
 */
public class Test {
  private static final Logger LOG;
  static final ConfigVo WINDOWS_CONFIG = new ConfigVo("D:/Program Files/SWFTools/pdf2swf.exe",
      "C:/Program Files/LibreOffice 5", "C:/Users/Administrator/Desktop/test");
  static final ConfigVo LINUX_CONFIG = new ConfigVo("/usr/local/bin/pdf2swf", "/opt/libreoffice5.0", "/home/lxp/test");

  static {
    LOG = LoggerFactory.getLogger(Test.class);
    System.setProperty("logback.configurationFile", "logback.xml");
  }

  private static void convert(final String sourceFolder, String pdf2swfPath, PDFConverter pdfConverter)
      throws Exception {
    SWFConverter swfConverter = new SWFToolsSWFConverter(pdf2swfPath);
    Doc2SwfConverter doc2SwfConverter = new Doc2SwfConverter(pdfConverter, swfConverter);

    final String docTextDoc = String.format("%s/%s", sourceFolder, "docTest2003.doc");

    doc2SwfConverter.convert(docTextDoc);

    final String docTextDocx = String.format("%s/%s", sourceFolder, "docTest.docx");
    final String docTextPPT = String.format("%s/%s", sourceFolder, "pptTest2003.ppt");
    final String docTextPPTX = String.format("%s/%s", sourceFolder, "pptTest.pptx");
    List<String> list = Arrays.asList(new String[] { docTextDocx, docTextPPT, docTextPPTX });
    doc2SwfConverter.convert(list);

    final String docTextXlsxInput = String.format("%s/%s", sourceFolder, "xlsTest.xlsx");
    final String docTextXlsxOutput = String.format("%s/%s", sourceFolder, "xlsTest.swf");
    doc2SwfConverter.convert(docTextXlsxInput, docTextXlsxOutput);
  }

  private static boolean isWindows() {
    return System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0;
  }

  public static void main(String[] args) throws Exception {
    LOG.debug("start to convert");
    if (isWindows()) {
      boolean rtn = new Random().nextBoolean();
      if (rtn) {
        convert(WINDOWS_CONFIG.getSourcePath(), WINDOWS_CONFIG.getPdf2swfPath(),
            new JodPDFconverter(WINDOWS_CONFIG.getOfficePath()));
      } else {
        convert(WINDOWS_CONFIG.getSourcePath(), WINDOWS_CONFIG.getPdf2swfPath(), new JacobPDFConverter());
      }
    } else {
      convert(LINUX_CONFIG.getSourcePath(), LINUX_CONFIG.getPdf2swfPath(),
          new JodPDFconverter(LINUX_CONFIG.getOfficePath()));
    }
  }
}
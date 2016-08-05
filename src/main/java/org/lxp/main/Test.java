package org.lxp.main;

import java.util.Random;

import org.lxp.converter.PDFConverter;
import org.lxp.converter.SWFConverter;
import org.lxp.converter.impl.Doc2SwfConverter;
import org.lxp.converter.impl.JacobPDFConverter;
import org.lxp.converter.impl.JodPDFconverter;
import org.lxp.converter.impl.SWFToolsSWFConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://www.cnblogs.com/luckyxiaoxuan/archive/2012/06/15/2550303.html
 */
public class Test {
  private static final Logger LOG;

  static {
    LOG = LoggerFactory.getLogger(Test.class);
    System.setProperty("logback.configurationFile", "logback.xml");
  }

  private static void convert(final String folder, PDFConverter pdfConverter) throws Exception {
    SWFConverter swfConverter = new SWFToolsSWFConverter("D:\\Program Files\\SWFTools\\pdf2swf.exe");
    Doc2SwfConverter doc2SwfConverter = new Doc2SwfConverter(pdfConverter, swfConverter);
    doc2SwfConverter.convert(String.format("%s/%s", folder, "docTest.docx"));
    doc2SwfConverter.convert(String.format("%s/%s", folder, "pptTest.ppt"));
    doc2SwfConverter.convert(String.format("%s/%s", folder, "pptTest1.pptx"));
  }

  public static void main(String[] args) throws Exception {
    final String folder = "C:/Users/Administrator/Desktop/test";
    LOG.debug("start to convert");
    boolean rtn = new Random().nextBoolean();
    if (rtn) {
      convert(folder, new JodPDFconverter("C:/Program Files/LibreOffice 5"));
    } else {
      convert(folder, new JacobPDFConverter());
    }
  }
}

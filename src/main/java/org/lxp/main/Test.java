package org.lxp.main;

import org.lxp.converter.PDFConverter;
import org.lxp.converter.SWFConverter;
import org.lxp.converter.impl.Doc2SwfConverter;
import org.lxp.converter.impl.JacobPDFConverter;
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

  public static void main(String[] args) throws Exception {
    LOG.debug("start to convert");
    SWFConverter swfConverter = new SWFToolsSWFConverter("D:\\Program Files\\SWFTools\\pdf2swf.exe");
    PDFConverter pdfConverter = new JacobPDFConverter();
    Doc2SwfConverter doc2SwfConverter = new Doc2SwfConverter(pdfConverter, swfConverter);
    doc2SwfConverter.convert("C:\\Users\\Administrator\\Desktop\\test\\docTest.docx");
    doc2SwfConverter.convert("C:\\Users\\Administrator\\Desktop\\test\\pptTest.ppt");
    doc2SwfConverter.convert("C:\\Users\\Administrator\\Desktop\\test\\pptTest1.pptx");
  }
}

package org.lxp.converter.impl;

import java.io.File;
import java.io.FileNotFoundException;

import org.lxp.converter.PDFConverter;
import org.lxp.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class JacobPDFConverter extends PDFConverter {
  private static final Logger LOG = LoggerFactory.getLogger(JacobPDFConverter.class);
  private static final int WORD_VALUE = 17;
  private static final int EXCEL_VALUE = 0;
  private static final int PPT_VALUE = 32;

  @Override
  public String convert(String inputFile, String outputFile) throws Exception {
    String suffix = FileUtils.getFileSufix(inputFile).toLowerCase();
    File file = new File(inputFile);
    if (!file.exists()) {
      LOG.error("{} is not found", inputFile);
      throw new FileNotFoundException(inputFile);
    }
    if (suffix.equals(FileUtils.PDF_SUFFIX)) {
      LOG.debug("{} is PDF. not need to convert", inputFile);
    } else {
      if (suffix.equals(FileUtils.TXT_SUFFIX) || suffix.equals(FileUtils.DOCX_SUFFIX)
          || suffix.equals(FileUtils.TXT_SUFFIX)) {
        word2PDF(inputFile, outputFile);
      } else if (suffix.equals(FileUtils.PPT_SUFFIX) || suffix.equals(FileUtils.PPTX_SUFFIX)) {
        ppt2PDF(inputFile, outputFile);
      } else if (suffix.equals(FileUtils.XLS_SUFFIX) || suffix.equals(FileUtils.XLSX_SUFFIX)) {
        excel2PDF(inputFile, outputFile);
      } else {
        throw new Exception(String.format("%s file format not supported", file));
      }
    }
    return outputFile;
  }

  private static void word2PDF(String inputFile, String pdfFile) {
    // 打开word应用程序
    ActiveXComponent app = new ActiveXComponent("Word.Application");
    // 设置word不可见
    app.setProperty("Visible", false);
    // 获得word中所有打开的文档,返回Documents对象
    Dispatch docs = app.getProperty("Documents").toDispatch();
    // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
    Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
    // 调用Document对象的ExportAsFixedFormat方法，将文档保存为pdf格式
    Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, WORD_VALUE);// word保存为pdf格式宏，值为17
    // 关闭文档
    Dispatch.call(doc, "Close", false);
    // 关闭word应用程序
    app.invoke("Quit", 0);
  }

  private static void excel2PDF(String inputFile, String pdfFile) {
    ActiveXComponent app = new ActiveXComponent("Excel.Application");
    app.setProperty("Visible", false);
    Dispatch excels = app.getProperty("Workbooks").toDispatch();
    Dispatch excel = Dispatch.call(excels, "Open", inputFile, false, true).toDispatch();
    Dispatch.call(excel, "ExportAsFixedFormat", EXCEL_VALUE, pdfFile);
    Dispatch.call(excel, "Close", false);
    app.invoke("Quit");
  }

  private static void ppt2PDF(String inputFile, String pdfFile) {
    ActiveXComponent app = new ActiveXComponent("PowerPoint.Application");
    // app.setProperty("Visible", msofalse);
    Dispatch ppts = app.getProperty("Presentations").toDispatch();
    Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true, // ReadOnly
        true, // Untitled指定文件是否有标题
        false// WithWindow指定文件是否可见
    ).toDispatch();
    Dispatch.call(ppt, "SaveAs", pdfFile, PPT_VALUE);
    Dispatch.call(ppt, "Close");
    app.invoke("Quit");
  }
}

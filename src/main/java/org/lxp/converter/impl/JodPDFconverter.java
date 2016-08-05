package org.lxp.converter.impl;

import java.io.File;
import java.io.FileNotFoundException;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.lxp.converter.PDFConverter;
import org.lxp.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JodPDFconverter extends PDFConverter {
  private static final Logger LOG = LoggerFactory.getLogger(JodPDFconverter.class);
  private static OfficeManager officeManager;
  private static final String ODT_SUFFIX = "odt";
  private static String OFFICE_HOME;
  private static int port[] = { 8080 };

  public JodPDFconverter(String officeHome) {
    OFFICE_HOME = officeHome;
  }

  @Override
  public String convert(String inputFile, String outputFile) throws Exception {
    if (inputFile.endsWith(FileUtils.TXT_SUFFIX)) {
      String odtFile = String.format("%s%s%s", FileUtils.getFilePrefix(inputFile), FileUtils.SEPARATOR, ODT_SUFFIX);
      if (new File(odtFile).exists()) {
        LOG.error("There is already a {}", odtFile);
        inputFile = odtFile;
      } else {
        try {
          FileUtils.copyFile(inputFile, odtFile);
          inputFile = odtFile;
        } catch (FileNotFoundException e) {
          LOG.error(e.getMessage(), e);
        }
      }
    }
    startService();
    LOG.info("{}->{} converting start", inputFile, outputFile);
    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
    converter.convert(new File(inputFile), new File(outputFile));
    stopService();
    LOG.info("{} has been generated successfully", inputFile, outputFile);
    return outputFile;
  }

  public static void startService() {
    DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
    try {
      LOG.info("start service....");
      configuration.setOfficeHome(OFFICE_HOME);// 设置OpenOffice.org安装目录
      configuration.setPortNumbers(port); // 设置转换端口，默认为8100
      configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟
      configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时

      officeManager = configuration.buildOfficeManager();
      officeManager.start(); // 启动服务
      LOG.info("office converting service start successfully");
    } catch (Exception e) {
      LOG.error("fail to start converting service", e);
    }
  }

  public static void stopService() {
    LOG.info("close office converting service....");
    if (officeManager != null) {
      officeManager.stop();
    }
    LOG.info("fail to close office converting service");
  }
}

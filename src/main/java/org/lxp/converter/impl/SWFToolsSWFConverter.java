package org.lxp.converter.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lxp.converter.SWFConverter;
import org.lxp.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SWFToolsSWFConverter extends SWFConverter {
  private static final Logger LOG = LoggerFactory.getLogger(SWFToolsSWFConverter.class);
  private static String PDF2SWF_PATH;

  public SWFToolsSWFConverter(String pdf2swfPath) {
    PDF2SWF_PATH = pdf2swfPath;
  }

  @Override
  public String convert(String inputFile, String outputFile) throws Exception {
    String suffix = FileUtils.getFileSuffix(inputFile).toLowerCase();
    if (!suffix.equals(FileUtils.PDF_SUFFIX)) {
      throw new Exception(String.format("%s is not pdf", inputFile));
    }
    File pdfFile = new File(inputFile);
    if (!pdfFile.exists()) {
      throw new FileNotFoundException(inputFile);
    }
    File outFile = new File(outputFile);
    if (outFile.exists()) {
      outFile.delete();
    }
    String command = String.format("%s %s -o %s -T 9 -f", PDF2SWF_PATH, inputFile, outputFile);
    LOG.debug("{}", command);
    LOG.info("converting {} to {}", inputFile, outputFile);
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(command);
      process.waitFor();
      InputStream in = process.getInputStream();
      BufferedReader read = new BufferedReader(new InputStreamReader(in));
      String line = null;
      while ((line = read.readLine()) != null) {
        LOG.info(line);
      }
      LOG.info("{} is generated successfully", outputFile);
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    } finally {
      if (process != null) {
        InputStream in = process.getErrorStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = read.readLine()) != null) {
          LOG.info(line);
        }
        process.destroy();
      }
    }
    return outputFile;
  }
}

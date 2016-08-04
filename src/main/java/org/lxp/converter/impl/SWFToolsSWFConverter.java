package org.lxp.converter.impl;

import java.io.File;
import java.io.FileNotFoundException;

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
    String suffix = FileUtils.getFileSufix(inputFile).toLowerCase();
    if (!suffix.equals(FileUtils.PDF_SUFFIX)) {
      throw new Exception(String.format("%s is not pdf", inputFile));
    }
    File pdfFile = new File(inputFile);
    if (!pdfFile.exists()) {
      throw new FileNotFoundException(inputFile);
    }
    File outFile = new File(outputFile);
    if (outFile.exists()) {
      throw new Exception(String.format("There is already a %s", inputFile));
    }
    String command = PDF2SWF_PATH + " \"" + inputFile + "\" -o " + outputFile + " -T 9 -f";
    LOG.info("converting of {} start", inputFile);
    Runtime.getRuntime().exec(command);
    LOG.info("converting of {} is finished", inputFile);
    return outputFile;
  }
}

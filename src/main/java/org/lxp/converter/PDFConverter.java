package org.lxp.converter;

import org.lxp.util.FileUtils;

public abstract class PDFConverter implements Converter {
  @Override
  public String convert(String inputFile) throws Exception {
    return convert(inputFile,
        String.format("%s%s%s", FileUtils.getFilePrefix(inputFile), FileUtils.SEPARATOR, FileUtils.PDF_SUFFIX));
  }
}

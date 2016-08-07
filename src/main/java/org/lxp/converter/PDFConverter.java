package org.lxp.converter;

import java.util.List;

import org.lxp.converter.vo.ReturnVo;
import org.lxp.util.FileUtils;

public abstract class PDFConverter implements Converter {
  @Override
  public String convert(String inputFile) throws Exception {
    return convert(inputFile, getOutputFileName(inputFile));
  }

  public abstract ReturnVo convert(List<String> inputFiles);

  protected String getOutputFileName(String inputFile) {
    return String.format("%s%s%s", FileUtils.getFilePrefix(inputFile), FileUtils.SEPARATOR, FileUtils.PDF_SUFFIX);
  }
}

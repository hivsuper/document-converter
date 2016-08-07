package org.lxp.converter.impl;

import java.util.List;

import org.lxp.converter.Converter;
import org.lxp.converter.PDFConverter;
import org.lxp.converter.SWFConverter;
import org.lxp.converter.vo.FileVo;
import org.lxp.converter.vo.ReturnVo;
import org.lxp.util.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Doc2SwfConverter implements Converter {
  private static final Logger LOG = LoggerFactory.getLogger(Doc2SwfConverter.class);
  private PDFConverter pdfConverter;
  private SWFConverter swfConverter;

  public Doc2SwfConverter(PDFConverter pdfConverter, SWFConverter swfConverter) {
    this.pdfConverter = pdfConverter;
    this.swfConverter = swfConverter;
  }

  public void convert(List<String> inputDocFiles) {
    ReturnVo returnVo = this.pdfConverter.convert(inputDocFiles);
    List<FileVo> success = returnVo.getSuccess();
    List<String> fail = returnVo.getFail();
    for (FileVo fileVo : success) {
      try {
        this.swfConverter.convert(fileVo.getOutput());
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        fail.add(fileVo.getInput());
      }
    }
    if (!fail.isEmpty()) {
      LOG.error("fail list {}", TextUtil.join(fail));
    }
  }

  @Override
  public String convert(String inputFile, String outputFile) throws Exception {
    return this.swfConverter.convert(this.pdfConverter.convert(inputFile), outputFile);
  }

  @Override
  public String convert(String inputFile) throws Exception {
    return this.swfConverter.convert(this.pdfConverter.convert(inputFile));
  }
}

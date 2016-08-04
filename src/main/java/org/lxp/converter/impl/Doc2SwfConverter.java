package org.lxp.converter.impl;

import org.lxp.converter.Converter;
import org.lxp.converter.PDFConverter;
import org.lxp.converter.SWFConverter;

public class Doc2SwfConverter implements Converter {
  private PDFConverter pdfConverter;
  private SWFConverter swfConverter;

  public Doc2SwfConverter(PDFConverter pdfConverter, SWFConverter swfConverter) {
    this.pdfConverter = pdfConverter;
    this.swfConverter = swfConverter;
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

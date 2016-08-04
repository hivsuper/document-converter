package org.lxp.converter;

public interface Converter {
  public abstract String convert(String inputFile, String outputFile) throws Exception;

  public String convert(String inputFile) throws Exception;
}

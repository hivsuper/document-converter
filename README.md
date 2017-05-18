# converter
*This project is totally inspired by http://www.cnblogs.com/luckyxiaoxuan/archive/2012/06/15/2550303.html. Thanks so much to the author.*
+ What can it do?
1. Convert txt/word/ppt/excel to pdf base on Microsoft Office/LibreOffice.
2. Convert pdf to swf base on SWFTools.

### JacobPDFConverter(Only for windows)
Couple things below should be known if you run the test method via JacobPDFConverter.

1. Microsoft office should be installed on your computer.

2. SWFTools should be installed on your computer. You can modify the path to adapt to your environment in Test class.

3. `jacob-1.18-x86.dll` or `jacob-1.18-x64.dll` should be added to system path(*eg.*`C:\Windows\System32`).

### JodPDFconverter(Across windows/linux)
Also if you run the test method via JodPDFconverter, these things should be done at first:

1. LibreOffice should be installed on your computer.

2. Same as last chapter

3. If the converting is invoked on linux OS, X Window is needed. It was always failing unless I do `sudo apt-get install ubuntu-desktop`.
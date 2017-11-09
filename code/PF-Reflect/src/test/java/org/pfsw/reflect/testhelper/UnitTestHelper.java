/*
 * Created on 14.01.2012
 */
package org.pfsw.reflect.testhelper;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UnitTestHelper
{
  public static final String BASE_TESTDATA_DIR = System.getProperty("testdata.dir", "./src/test/testdata/");
  public static final String TESTDATA_DIR = BASE_TESTDATA_DIR + "pf-reflect";
  public static final String TESTDATA = TESTDATA_DIR + "/";
	
  public static Document readXmlFile(String fileName) throws SAXException, IOException, ParserConfigurationException
  {
    DocumentBuilderFactory builderFactory;
    Document document;
    File file;

    file = new File(fileName);
    builderFactory = DocumentBuilderFactory.newInstance();
    builderFactory.setNamespaceAware(true);
	  builderFactory.setValidating(false);
	  builderFactory.setExpandEntityReferences(false);
	  builderFactory.setCoalescing(true);
    document = builderFactory.newDocumentBuilder().parse(file);
    return document;
  }

}

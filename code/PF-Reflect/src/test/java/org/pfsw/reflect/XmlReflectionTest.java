// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 14/01/2012
// HISTORY  :
//  14/01/2012  mdu  CREATED
//
// Copyright (c) 2012, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect ;

// ===========================================================================
// IMPORTS
// ===========================================================================
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.pfsw.reflect.XmlReflection;
import org.pfsw.reflect.testhelper.PersonData;
import org.pfsw.reflect.testhelper.UnitTestHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class XmlReflectionTest extends TestCase
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
	private static final String PERSON_XML_FILE_1 = UnitTestHelper.TESTDATA + "person-1.xml";
	private static final String PERSON_XML_FILE_2 = UnitTestHelper.TESTDATA + "person-2.xml";
	private static final String PERSON_XML_FILE_3 = UnitTestHelper.TESTDATA + "person-3.xml";
	private static final String PERSON_LIST_XML_FILE_1 = UnitTestHelper.TESTDATA + "person-list-1.xml";

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public XmlReflectionTest( String name )
  {
    super( name ) ;
  } // XmlReflectionTest() 

  // =========================================================================
  // PUBLIC CLASS METHODS
  // =========================================================================
  public static Test suite()
  {
		return new TestSuite(XmlReflectionTest.class) ;
	} // suite() 

  // =========================================================================
  // TEST METHODS
  // =========================================================================

  public void test_hasValueAttrName_null()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue(null);
  	assertFalse(xr.hasValueAttrName());
  } // test_hasValueAttrName_null() 

  // -------------------------------------------------------------------------

  public void test_hasValueAttrName_digit()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("92");
  	assertFalse(xr.hasValueAttrName());
  } // test_hasValueAttrName_digit() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_empty()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("");
  	assertFalse(xr.hasValueAttrName());
  } // test_hasValueAttrName_empty() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_blank()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue(" ");
  	assertFalse(xr.hasValueAttrName());
  } // test_hasValueAttrName_blank() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_special_char()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("[");
  	assertFalse(xr.hasValueAttrName());
  	xr.setAttrNameForFieldValue("*");
  	assertFalse(xr.hasValueAttrName());
  	xr.setAttrNameForFieldValue("@");
  	assertFalse(xr.hasValueAttrName());
  	xr.setAttrNameForFieldValue("{test");
  	assertFalse(xr.hasValueAttrName());
  } // test_hasValueAttrName_special_char() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_A()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("AllValue");
  	assertTrue(xr.hasValueAttrName());
  } // test_hasValueAttrName_A() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_Z()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("zoneData");
  	assertTrue(xr.hasValueAttrName());
  } // test_hasValueAttrName_Z() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_a()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("attr");
  	assertTrue(xr.hasValueAttrName());
  } // test_hasValueAttrName_a() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_v()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("value");
  	assertTrue(xr.hasValueAttrName());
  } // test_hasValueAttrName_v() 
  
  // -------------------------------------------------------------------------
  
  public void test_hasValueAttrName_z()
  {
  	XmlReflection xr = new XmlReflection();
  	
  	xr.setAttrNameForFieldValue("zorro");
  	assertTrue(xr.hasValueAttrName());
  } // test_hasValueAttrName_z() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_String() 
	{
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals("Kermit", xr.convertToType("Kermit", String.class));
	} // test_convertToType_String() 
	
	// -------------------------------------------------------------------------
  
  public void test_convertToType_int() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Integer.valueOf(77), xr.convertToType("77", Integer.TYPE));
  } // test_convertToType_int() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Integer() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Integer.valueOf(257), xr.convertToType("257", Integer.class));
  } // test_convertToType_Integer() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_boolean() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Boolean.TRUE, xr.convertToType("true", Boolean.TYPE));
  	assertEquals(Boolean.TRUE, xr.convertToType("TRUE", Boolean.TYPE));
  	assertEquals(Boolean.FALSE, xr.convertToType("false", Boolean.TYPE));
  	assertEquals(Boolean.FALSE, xr.convertToType("0", Boolean.TYPE));
  } // test_convertToType_boolean() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Boolean() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Boolean.TRUE, xr.convertToType("true", Boolean.class));
  	assertEquals(Boolean.TRUE, xr.convertToType("TRUE", Boolean.class));
  	assertEquals(Boolean.FALSE, xr.convertToType("false", Boolean.class));
  	assertEquals(Boolean.FALSE, xr.convertToType("0", Boolean.class));
  } // test_convertToType_Boolean() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_char() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals('M', xr.convertToType("M", Character.TYPE));
  } // test_convertToType_char() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Character() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals('M', xr.convertToType("M", Character.class));
  } // test_convertToType_Character() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_long() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Long.valueOf(425698567536L), xr.convertToType("425698567536", Long.TYPE));
  } // test_convertToType_long() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Long() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Long.valueOf(9893283958383L), xr.convertToType("9893283958383", Long.class));
  } // test_convertToType_Long() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_byte() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Byte.valueOf((byte)42), xr.convertToType("42", Byte.TYPE));
  } // test_convertToType_byte() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Byte() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Byte.valueOf((byte)125), xr.convertToType("125", Byte.class));
  } // test_convertToType_Byte() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_short() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Short.valueOf((short)-12225), xr.convertToType("-12225", Short.TYPE));
  } // test_convertToType_short() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Short() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Short.valueOf((short)-125), xr.convertToType("-125", Short.class));
  } // test_convertToType_Short() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_float() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Float.valueOf(34266.012f), xr.convertToType("34266.012", Float.TYPE));
  } // test_convertToType_float() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Float() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Float.valueOf(342.12f), xr.convertToType("342.12", Float.class));
  } // test_convertToType_Float() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_double() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Double.valueOf(1135555542.12d), xr.convertToType("1135555542.12", Double.TYPE));
  } // test_convertToType_double() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Double() 
  {
  	XmlReflection xr = new XmlReflection();
  	
  	assertEquals(Double.valueOf(35555542.12d), xr.convertToType("35555542.12", Double.class));
  } // test_convertToType_Double() 
  
  // -------------------------------------------------------------------------
  
  public void test_convertToType_Date() 
  {
  	XmlReflection xr = new XmlReflection();
  	@SuppressWarnings("deprecation")
		Date date = new Date("Jan 15, 2010");
  	
  	assertEquals(date, xr.convertToType("Jan 15, 2010", Date.class));
  } // test_convertToType_Date() 
  
  // -------------------------------------------------------------------------
  
  public void test_createInstance_default_person_1() throws Exception 
	{
  	XmlReflection xr = new XmlReflection();
		Element element;
		Object object;
		
		element = this.createPersonDataElement1();
  	object = xr.createInstance(element);
  	assertTrue(object instanceof PersonData);
	} // test_createInstance_default_person_1() 
	
	// -------------------------------------------------------------------------
  
  public void test_createInstance_default_persondata_1() throws Exception 
  {
  	XmlReflection xr = new XmlReflection();
  	Element element;
  	PersonData object;
  	
  	element = this.createPersonDataElement1();
  	object = xr.createInstance(element, PersonData.class);
  	assertNull(object.getFirstName());
  } // test_createInstance_default_persondata_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_createInitializedInstance_default_person_2() throws Exception 
  {
  	XmlReflection xr = new XmlReflection();
  	Element element;
  	Object object;
  	PersonData person;
  	
  	element = this.createPersonDataElement2();
  	object = xr.createInitializedInstance(element);
  	assertTrue(object instanceof PersonData);
  	person = (PersonData)object;
  	assertEquals("Jennifer", person.getFirstName());
  	assertEquals("Lopez", person.getLastName());
  	assertEquals(2, person.getNumberOfChildren());
  	assertEquals(19333, person.getZipCode());
  	assertTrue(person.isFemale());
  } // test_createInitializedInstance_default_person_2() 
  
  // -------------------------------------------------------------------------
  
  public void test_createInitializedInstance_default_persondata_1() throws Exception 
  {
  	XmlReflection xr = new XmlReflection();
  	Element element;
  	PersonData object;
  	
  	element = this.createPersonDataElement1();
  	object = xr.createInitializedInstance(element, PersonData.class);
  	assertEquals("Richard", object.getFirstName());
  	assertEquals("Burton", object.getLastName());
  	assertEquals(3, object.getNumberOfChildren());
  	assertEquals(15666, object.getZipCode());
  	assertFalse(object.isFemale());
  } // test_createInitializedInstance_default_persondata_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_createInitializedInstance_persondata_3() throws Exception 
  {
  	XmlReflection xr = new XmlReflection();
  	Element element;
  	PersonData object;
  	
  	xr.setAttrNameForClass("className");
  	xr.setAttrNameForFieldName("id");
  	xr.setAttrNameForFieldValue(null);
  	xr.setTagNameForField("attribute");
  	
  	element = this.createPersonDataElement3();
  	object = xr.createInitializedInstance(element, PersonData.class);
  	assertEquals("Linda", object.getFirstName());
  	assertEquals("Hamilton", object.getLastName());
  	assertEquals(4, object.getNumberOfChildren());
  	assertEquals(12999, object.getZipCode());
  	assertTrue(object.isFemale());
  } // test_createInitializedInstance_persondata_3() 
  
  // -------------------------------------------------------------------------
  
  public void test_createInitializedInstances() throws Exception 
	{
  	XmlReflection xr = new XmlReflection();
  	List<Element> elements;
  	List<PersonData> persons;
	
  	elements = this.createPersonListElements1();
  	persons = xr.createInitializedInstances(elements, PersonData.class);
  	assertEquals(5, persons.size());
	} // test_createInitializedInstances() 
	
	// -------------------------------------------------------------------------
  
  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  @Override
  protected void setUp() throws Exception
  {
    super.setUp() ;
	} // setUp() 

  // -------------------------------------------------------------------------

  protected Element createPersonDataElement1() throws Exception 
	{
  	return this.createPersonDataElementFromFile(PERSON_XML_FILE_1);
	} // createPersonDataElement1() 
	
	// -------------------------------------------------------------------------
  
  protected Element createPersonDataElement2() throws Exception 
  {
  	return this.createPersonDataElementFromFile(PERSON_XML_FILE_2);
  } // createPersonDataElement2() 
  
  // -------------------------------------------------------------------------
  
  protected Element createPersonDataElement3() throws Exception 
  {
  	return this.createPersonDataElementFromFile(PERSON_XML_FILE_3);
  } // createPersonDataElement3() 
  
  // -------------------------------------------------------------------------
  
  protected List<Element> createPersonListElements1() throws Exception 
  {
  	return this.createPersonListFromFile(PERSON_LIST_XML_FILE_1, "Person");
  } // createPersonListElements1() 
  
  // -------------------------------------------------------------------------
  
  protected Element createPersonDataElementFromFile(String fileName) throws Exception 
  {
  	Document document;
  	
  	document = UnitTestHelper.readXmlFile(fileName);
  	return document.getDocumentElement();
  } // createPersonDataElementFromFile() 
  
  // -------------------------------------------------------------------------
  
  protected List<Element> createPersonListFromFile(String fileName, String tagName) throws Exception 
  {
  	Document document;
  	NodeList nodeList;
  	List<Element> result;
  	
  	document = UnitTestHelper.readXmlFile(fileName);
  	nodeList = document.getElementsByTagName(tagName);
  	result = new ArrayList<Element>();
  	for (int i = 0; i < nodeList.getLength(); i++)
		{
			result.add((Element)nodeList.item(i));
		}
  	return result;
  } // createPersonListFromFile() 
  
  // -------------------------------------------------------------------------
  
} // class XmlReflectionTest 

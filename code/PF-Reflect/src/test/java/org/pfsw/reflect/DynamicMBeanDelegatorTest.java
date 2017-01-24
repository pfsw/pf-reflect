// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 06/05/2012
// HISTORY  :
//  06/05/2012  mdu  CREATED
//
// Copyright (c) 2012, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect ;

// ===========================================================================
// IMPORTS
// ===========================================================================
import javax.management.Attribute;
import javax.management.AttributeList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.pfsw.reflect.DynamicMBeanDelegator;
import org.pfsw.reflect.test.PersonData;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class DynamicMBeanDelegatorTest extends TestCase
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public DynamicMBeanDelegatorTest( String name )
  {
    super( name ) ;
  } // DynamicMBeanDelegatorTest() 

  // =========================================================================
  // PUBLIC CLASS METHODS
  // =========================================================================
  public static Test suite()
  {
		return new TestSuite(DynamicMBeanDelegatorTest.class) ;
	} // suite() 

  // =========================================================================
  // TEST METHODS
  // =========================================================================

  public void test_getAttribute() throws Exception
  {
  	PersonData person = this.createSamplePerson1();
  	DynamicMBeanDelegator<PersonData> delegator = new DynamicMBeanDelegator<PersonData>(person);
  	
  	assertEquals("Jennifer", delegator.getAttribute("firstName"));
  	assertEquals("Rush", delegator.getAttribute("lastName"));
  	assertEquals((byte)0, delegator.getAttribute("numberOfChildren"));
  	assertEquals(888, delegator.getAttribute("zipCode"));
  	assertEquals(true, delegator.getAttribute("female"));
  } // test_getAttribute() 

  // -------------------------------------------------------------------------

  public void test_getAttributes() throws Exception
  {
  	PersonData person = this.createSamplePerson1();
  	DynamicMBeanDelegator<PersonData> delegator = new DynamicMBeanDelegator<PersonData>(person);
  	AttributeList attributes;
  	Attribute attr;
  	
  	attributes = delegator.getAttributes(new String[] {"zipCode", "lastName", "firstName"});
  	assertEquals(3, attributes.size());
  	attr = (Attribute)attributes.get(0);
  	assertEquals(888, attr.getValue());
  	attr = (Attribute)attributes.get(1);
  	assertEquals("Rush", attr.getValue());
  	attr = (Attribute)attributes.get(2);
  	assertEquals("Jennifer", attr.getValue());
  } // test_getAttributes() 
  
  // -------------------------------------------------------------------------
  
  public void test_setAttribute() throws Exception
  {
  	PersonData person = this.createSamplePerson1();
  	DynamicMBeanDelegator<PersonData> delegator = new DynamicMBeanDelegator<PersonData>(person);
  	
  	delegator.setAttribute(new Attribute("lastName", "Aniston"));
  	delegator.setAttribute(new Attribute("zipCode", 777));
  	
  	assertEquals("Jennifer", person.getFirstName());
  	assertEquals("Aniston", person.getLastName());
  	assertTrue(person.isFemale());
  	assertEquals((byte)0, person.getNumberOfChildren());
  	assertEquals(777, person.getZipCode());
  } // test_setAttribute() 
  
  // -------------------------------------------------------------------------
  
  public void test_invoke_no_params() throws Exception
  {
  	PersonData person = this.createSamplePerson1();
  	DynamicMBeanDelegator<PersonData> delegator = new DynamicMBeanDelegator<PersonData>(person);
  	
  	assertEquals("Jennifer", delegator.invoke("getFirstName", new Object[0], new String[0]));
  	assertEquals("Rush", delegator.invoke("getLastName", new Object[0], new String[0]));
  	assertEquals((byte)0, delegator.invoke("getNumberOfChildren", new Object[0], new String[0]));
  	assertEquals(888, delegator.invoke("getZipCode", new Object[0], new String[0]));
  	assertEquals(true, delegator.invoke("isFemale", new Object[0], new String[0]));
  } // test_invoke_no_params() 

  // -------------------------------------------------------------------------

  public void test_invoke_3_params() throws Exception
  {
  	PersonData person = this.createSamplePerson1();
  	DynamicMBeanDelegator<PersonData> delegator = new DynamicMBeanDelegator<PersonData>(person);
  	
  	delegator.invoke("changeNameAndGender", new Object[] {"Paul", "Newman", false}, new String[] {"java.lang.String", "java.lang.String", "boolean"});
  	assertEquals("Paul", person.getFirstName());
  	assertEquals("Newman", person.getLastName());
  	assertEquals(888, delegator.invoke("getZipCode", new Object[0], new String[0]));
  	assertFalse(person.isFemale());
  } // test_invoke_3_params() 
  
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
  
  protected PersonData createSamplePerson1() 
	{
  	PersonData person;
  	
  	person = new PersonData();
  	person.setFemale(true);
  	person.setFirstName("Jennifer");
  	person.setLastName("Rush");
  	person.setNumberOfChildren((byte)0);
  	person.setZipCode(888);
  	return person;
	} // createSamplePerson1() 
	
	// -------------------------------------------------------------------------

} // class DynamicMBeanDelegatorTest 

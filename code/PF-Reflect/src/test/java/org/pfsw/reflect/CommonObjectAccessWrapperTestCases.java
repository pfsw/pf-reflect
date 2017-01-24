// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 19/12/2008
// HISTORY  :
//  19/12/2008  mdu  CREATED
//
// Copyright (c) 2008, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect ;

// ===========================================================================
// IMPORTS
// ===========================================================================
import junit.framework.TestCase;

import org.pfsw.reflect.ObjectAccessWrapper;
import org.pfsw.reflect.test.OtherClass;
import org.pfsw.reflect.test.Subclass1;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 * @version 1.0
 */
abstract public class CommonObjectAccessWrapperTestCases extends TestCase
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
  public CommonObjectAccessWrapperTestCases( String name )
  {
    super( name ) ;
  } // CommonObjectAccessWrapperTestCases() 

  // =========================================================================
  // PUBLIC CLASS METHODS
  // =========================================================================

  // =========================================================================
  // TEST METHODS
  // =========================================================================

  public void test_getAttributeNames_1()
  {
  	ObjectAccessWrapper oaw ;
  	Subclass1 object = new Subclass1() ;
  	String[] names ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	names = oaw.getAttributeNames() ;
  	assertTrue( this.contains( names, "name" ) ) ;
  	assertTrue( this.contains( names, "flag1" ) ) ;
  	assertTrue( this.contains( names, "myClass" ) ) ;
  	assertTrue( this.contains( names, "ident" ) ) ;
  	assertTrue( this.contains( names, "var_1_1" ) ) ;
  	assertTrue( this.contains( names, "var_1_2" ) ) ;
  	assertTrue( this.contains( names, "var_1_3" ) ) ;
  	assertTrue( this.contains( names, "var_1_4" ) ) ;
  	assertFalse( this.contains( names, "var_9_9" ) ) ;
  } // test_getAttributeNames_1() 

  // -------------------------------------------------------------------------

  public void test_getAttributeNames_2()
  {
  	ObjectAccessWrapper oaw ;
  	Object object = new Object() ;
  	String[] names ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	names = oaw.getAttributeNames() ;
  	assertEquals( 0, names.length ) ;
  } // test_getAttributeNames_2() 
  
  // -------------------------------------------------------------------------
  
  public void test_getAttributeNames_3()
  {
  	ObjectAccessWrapper oaw ;
  	String[] names ;
  	
  	oaw = new ObjectAccessWrapper(null) ;
  	names = oaw.getAttributeNames() ;
  	assertEquals( 0, names.length ) ;
  } // test_getAttributeNames_3() 
  
  // -------------------------------------------------------------------------
  
  // This test gets red when Cobertura code coverage is used because it 
  // weaves 2 instance variables into each class
  public void EXCLUDED_test_getAttributeNames_4()
  {
  	ObjectAccessWrapper oaw ;
  	String[] names ;
  	
  	oaw = new ObjectAccessWrapper(new OtherClass()) ;
  	names = oaw.getAttributeNames() ;
  	assertEquals( 0, names.length ) ;
  } // test_getAttributeNames_4() 
  
  // -------------------------------------------------------------------------
  
  public void test_setAttributeValue_1() throws NoSuchFieldException
  {
  	ObjectAccessWrapper oaw ;
  	Subclass1 object = new Subclass1() ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	oaw.setAttributeValue( "ident", new Long(123L) ) ;
  	assertEquals( new Long(123L), oaw.getValueOfField( "ident" ) ) ;
  } // test_setAttributeValue_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_setAttributeValue_2()
  {
  	ObjectAccessWrapper oaw ;
  	Subclass1 object = new Subclass1() ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	try
		{
			oaw.setAttributeValue( "xxxxx", new Long(123L) ) ;
			fail( "Expected NoSuchFieldException" ) ;
		}
		catch ( NoSuchFieldException e )
		{
			// as expected
		}
  } // test_setAttributeValue_2() 
  
  // -------------------------------------------------------------------------
  
  public void test_set_get_1() 
  {
  	ObjectAccessWrapper oaw ;
  	Subclass1 object = new Subclass1() ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	oaw.set( "name", "Eragon" ) ;
  	oaw.set( "unknown", "Palmera" ) ;
  	assertEquals( "Eragon", oaw.get( "name" ) ) ;
  	assertNull( oaw.get( "unknown" ) ) ;
  } // test_set_get_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_setField_getField_1() 
  {
  	ObjectAccessWrapper oaw ;
  	Subclass1 object = new Subclass1() ;
  	
  	oaw = this.createAccessWrapper(object) ;
  	oaw.setValueOfField( "var_1_2", Integer.valueOf( "20" ) ) ;
  	oaw.setValueOfField( "unknown", "Palmera" ) ;
  	assertEquals( new Integer(20), oaw.getValueOfField( "var_1_2" ) ) ;
  	assertNull( oaw.getValueOfField( "unknown" ) ) ;
  } // test_setField_getField_1() 
  
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

  abstract protected ObjectAccessWrapper createAccessWrapper( Object object ); 
	
	// -------------------------------------------------------------------------
  
  protected boolean contains( String[] strings, String str ) 
	{
		for (int i = 0; i < strings.length; i++ )
		{
			if ( str.equals( strings[i] ) )
			{
				return true ;
			}
		}
		return false;
	} // contains() 
	
	// -------------------------------------------------------------------------
  
} // class CommonObjectAccessWrapperTestCases 

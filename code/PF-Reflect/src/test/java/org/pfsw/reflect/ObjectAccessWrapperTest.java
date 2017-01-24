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
import org.pfsw.reflect.ObjectAccessWrapper;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 * @version 1.0
 */
public class ObjectAccessWrapperTest extends CommonObjectAccessWrapperTestCases
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
  public ObjectAccessWrapperTest( String name )
  {
    super( name ) ;
  } // ObjectAccessWrapperTest() 

  // =========================================================================
  // PUBLIC CLASS METHODS
  // =========================================================================
  public static Test suite()
  {
		return new TestSuite( ObjectAccessWrapperTest.class ) ;
	} // suite() 

  // =========================================================================
  // TEST METHODS
  // =========================================================================

  
  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  @Override
  protected void setUp() throws Exception
  {
    super.setUp() ;
	} // setUp() 

  // -------------------------------------------------------------------------

  @Override
  protected ObjectAccessWrapper createAccessWrapper( Object object ) 
	{
		return new ObjectAccessWrapper(object) ;
	} // createAccessWrapper()
	
	// -------------------------------------------------------------------------
  
} // class ObjectAccessWrapperTest 

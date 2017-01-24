// ===========================================================================
// CONTENT  : CLASS Subclass2
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 05/10/2002
// HISTORY  :
//  05/10/2002  duma  CREATED
//
// Copyright (c) 2002, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect.test ;

import java.util.List;
import java.util.Vector;

// ===========================================================================
// IMPORTS
// ===========================================================================

/**
 *
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class Subclass2 extends Subclass1
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
	public static final String const_2_1 = "constant" ;

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
	private Subclass1 var_2_2 = new Subclass1() ;
	protected Object var_2_3 = null ;
	List var_2_4 = new Vector() ;

  // =========================================================================
  // CLASS METHODS
  // =========================================================================

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public Subclass2()
  {
    super() ;
  } // Subclass2()

  // -------------------------------------------------------------------------
  
  Subclass2( int start )
  {
  	this() ;
  } // Subclass2()
  
  // -------------------------------------------------------------------------
  
  protected Subclass2( String name )
  {
  	this() ;
  	if ( "bad".equals(name) )
		{
			throw new IllegalArgumentException( "name must not be bad" ) ;
		}
  } // Subclass2()
  
  // -------------------------------------------------------------------------
  
  @SuppressWarnings("unused")
  private Subclass2( String name, List data )
  {
  	this() ;
  } // Subclass2()
  
  // -------------------------------------------------------------------------
  
  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
	public String const_2_1() { return const_2_1 ; }
	public Subclass1 var_2_2() { return var_2_2 ; }
	public Object var_2_3() { return var_2_3 ; }
	public List var_2_4() { return var_2_4 ; }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================

  // -------------------------------------------------------------------------

} // class Subclass2
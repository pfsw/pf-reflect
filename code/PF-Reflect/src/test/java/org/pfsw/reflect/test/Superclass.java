// ===========================================================================
// CONTENT  : CLASS Superclass
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 28/09/2002
// HISTORY  :
//  28/09/2002  duma  CREATED
//
// Copyright (c) 2002, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect.test ;

// ===========================================================================
// IMPORTS
// ===========================================================================
import java.util.Date;

/**
 *
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class Superclass
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================


  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
	private String name 		= "Superclass" ;
	protected boolean flag1	= true ;
	public Class myClass		= this.getClass() ;
	Long ident							= new Long(27883783L) ;

  // =========================================================================
  // CLASS METHODS
  // =========================================================================

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public Superclass()
  {
    super() ;
    this.dummy3();
    this.dummy6(true) ;
  } // Superclass() 


	// -------------------------------------------------------------------------
	// Getter for fields
	// -------------------------------------------------------------------------
	public String name() { return name ; }
	public boolean flag1() { return flag1 ; }
	public Class myClass() { return myClass ; }
	public Long ident() { return ident ; }

	// -------------------------------------------------------------------------
	// Methods for test cases in ReflectUtilTest that use findMethod()
	// -------------------------------------------------------------------------

  protected void dummy1()
	{		
    // Just for testing
	} // dummy1() 

	// -------------------------------------------------------------------------
	
  void dummy2()
	{		
    // Just for testing
	} // dummy2() 

	// -------------------------------------------------------------------------
	
  private void dummy3()
	{		
    // Just for testing
	} // dummy3() 

	// -------------------------------------------------------------------------
	
  protected void dummy4( long x )
	{		
    // Just for testing
	} // dummy4() 

	// -------------------------------------------------------------------------
	
  void dummy5( String s, Date d)
	{		
    // Just for testing
	} // dummy5() 

	// -------------------------------------------------------------------------
	
  private void dummy6( boolean b )
	{		
    // Just for testing
	} // dummy6() 

	// -------------------------------------------------------------------------
	
  @SuppressWarnings("hiding")
  protected String dummy7( String name )
  {		
  	return "X:" + name ;
  } // dummy7() 


	public String getName()
	{
		return name;
	}


	public void setName( String name )
	{
		this.name = name;
	}
  
  // -------------------------------------------------------------------------
  
} // class Superclass 

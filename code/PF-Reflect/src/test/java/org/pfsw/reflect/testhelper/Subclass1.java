// ===========================================================================
// CONTENT  : CLASS Subclass1
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 28/09/2002
// HISTORY  :
//  28/09/2002  duma  CREATED
//
// Copyright (c) 2002, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect.testhelper;

import java.util.Date;

/**
 * @author Manfred Duchrow
 * @version 1.0
 */
public class Subclass1 extends Superclass
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  public transient String var_1_1 = "sub_1_v1";
  private Integer var_1_2 = new Integer(7);
  char var_1_4 = 'X';
  transient final String dummy1 = "x";

  // =========================================================================
  // CLASS VARIABLES
  // =========================================================================
  protected static Date var_1_3 = null;

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public Subclass1()
  {
    super();
  } // Subclass1()

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
  public String var_1_1()
  {
    return var_1_1;
  }
  public Integer var_1_2()
  {
    return var_1_2;
  }
  public Date var_1_3()
  {
    return var_1_3;
  }
  public char var_1_4()
  {
    return var_1_4;
  }

  @Override
  public String dummy7(String name)
  {
    return "AAA: " + name;
  } // dummy7() 

  // -------------------------------------------------------------------------

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
} // class Subclass1
// ===========================================================================
// CONTENT  : CLASS Superclass
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 28/09/2002
// HISTORY  :
//  28/09/2002  duma  CREATED
//
// Copyright (c) 2002, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect.testhelper;

import java.io.File;
import java.util.Date;

@SuppressWarnings("unused")
public class Superclass
{
  private final File folder = new File("/tmp");
  private String name = "Superclass";
  protected boolean flag1 = true;
  public Class<?> myClass = this.getClass();
  Long ident = new Long(27883783L);

  /**
   * Initialize the new instance with default values.
   */
  public Superclass()
  {
    super();
    this.dummy3();
    this.dummy6(true);
  }

  // Getter for fields

  public File getFolder()
  {
    return this.folder;
  }

  public String name()
  {
    return name;
  }
  public boolean flag1()
  {
    return flag1;
  }
  public Class<?> myClass()
  {
    return myClass;
  }
  public Long ident()
  {
    return ident;
  }

  // Methods for test cases in ReflectUtilTest that use findMethod()

  protected void dummy1()
  {
    // Just for testing
  }

  void dummy2()
  {
    // Just for testing
  }

  private void dummy3()
  {
    // Just for testing
  }

  protected void dummy4(long x)
  {
    // Just for testing
  }

  void dummy5(String s, Date d)
  {
    // Just for testing
  }

  private void dummy6(boolean b)
  {
    // Just for testing
  }

  @SuppressWarnings("hiding")
  protected String dummy7(String name)
  {
    return "X:" + name;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

}

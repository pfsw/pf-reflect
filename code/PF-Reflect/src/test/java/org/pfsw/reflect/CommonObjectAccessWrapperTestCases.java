// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 19/12/2008
// HISTORY  :
//  19/12/2008  mdu  CREATED
//
// Copyright (c) 2008, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pfsw.reflect.testhelper.OtherClass;
import org.pfsw.reflect.testhelper.Subclass1;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 * @version 1.0
 */
public abstract class CommonObjectAccessWrapperTestCases
{
  @Test
  public void test_getAttributeNames_1()
  {
    ObjectAccessWrapper oaw;
    Subclass1 object = new Subclass1();
    String[] names;

    oaw = createAccessWrapper(object);
    names = oaw.getAttributeNames();
    assertTrue(contains(names, "name"));
    assertTrue(contains(names, "flag1"));
    assertTrue(contains(names, "myClass"));
    assertTrue(contains(names, "ident"));
    assertTrue(contains(names, "var_1_1"));
    assertTrue(contains(names, "var_1_2"));
    assertTrue(contains(names, "var_1_3"));
    assertTrue(contains(names, "var_1_4"));
    assertFalse(contains(names, "var_9_9"));
  }

  @Test
  public void test_getAttributeNames_2()
  {
    ObjectAccessWrapper oaw;
    Object object = new Object();
    String[] names;

    oaw = createAccessWrapper(object);
    names = oaw.getAttributeNames();
    assertEquals(0, names.length);
  }

  @Test
  public void test_getAttributeNames_3()
  {
    ObjectAccessWrapper oaw;
    String[] names;

    oaw = new ObjectAccessWrapper(null);
    names = oaw.getAttributeNames();
    assertEquals(0, names.length);
  }

  // This test gets red when Cobertura code coverage is used because it 
  // weaves 2 instance variables into each class
  public void EXCLUDED_test_getAttributeNames_4()
  {
    ObjectAccessWrapper oaw;
    String[] names;

    oaw = new ObjectAccessWrapper(new OtherClass());
    names = oaw.getAttributeNames();
    assertEquals(0, names.length);
  }

  @Test
  public void test_setAttributeValue_1() throws UnknownFieldException
  {
    ObjectAccessWrapper oaw;
    Subclass1 object = new Subclass1();

    oaw = createAccessWrapper(object);
    oaw.setAttributeValue("ident", new Long(123L));
    assertEquals(new Long(123L), oaw.getValueOfField("ident"));
  }

  @Test
  public void test_setAttributeValue_2()
  {
    ObjectAccessWrapper oaw;
    Subclass1 object = new Subclass1();

    oaw = createAccessWrapper(object);
    try
    {
      oaw.setAttributeValue("xxxxx", new Long(123L));
      fail("Expected UnknownFieldException");
    }
    catch (Exception e)
    {
      assertTrue(e instanceof UnknownFieldException);
    }
  }

  @Test
  public void test_set_get_1()
  {
    ObjectAccessWrapper oaw;
    Subclass1 object = new Subclass1();

    oaw = createAccessWrapper(object);
    oaw.set("name", "Eragon");
    oaw.set("unknown", "Palmera");
    assertEquals("Eragon", oaw.get("name"));
    assertNull(oaw.get("unknown"));
  }

  @Test
  public void test_setField_getField_1()
  {
    ObjectAccessWrapper oaw;
    Subclass1 object = new Subclass1();

    oaw = createAccessWrapper(object);
    oaw.setValueOfField("var_1_2", Integer.valueOf("20"));
    oaw.setValueOfField("unknown", "Palmera");
    assertEquals(new Integer(20), oaw.getValueOfField("var_1_2"));
    assertNull(oaw.getValueOfField("unknown"));
  }

  // =========================================================================
  // HELPER METHODS
  // =========================================================================
  protected abstract ObjectAccessWrapper createAccessWrapper(Object object);

  protected boolean contains(String[] strings, String str)
  {
    for (int i = 0; i < strings.length; i++)
    {
      if (str.equals(strings[i]))
      {
        return true;
      }
    }
    return false;
  }
}

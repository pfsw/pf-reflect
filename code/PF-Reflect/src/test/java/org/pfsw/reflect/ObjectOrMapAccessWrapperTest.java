// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 13/11/2009
// HISTORY  :
//  13/11/2009  mdu  CREATED
//
// Copyright (c) 2009, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 * @version 2.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ObjectOrMapAccessWrapperTest extends CommonObjectAccessWrapperTestCases
{
  // =========================================================================
  // TEST METHODS
  // =========================================================================

  @Test
  public void test_getAttributeNamesFromMap_1()
  {
    ObjectAccessWrapper oaw;
    String[] names;

    oaw = this.createAccessWrapper(this.sampleMap1());
    names = oaw.getAttributeNames();
    assertTrue(this.contains(names, "alpha"));
    assertTrue(this.contains(names, "beta"));
    assertTrue(this.contains(names, "gamma"));
    assertTrue(this.contains(names, "delta"));
    assertTrue(this.contains(names, "pi"));
  }

  @Test
  public void test_get_1()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    assertEquals("centauri", oaw.get("alpha"));
    assertEquals("geuze", oaw.get("beta"));
    assertEquals("erich", oaw.get("gamma"));
    assertEquals(new Integer(8), oaw.get("delta"));
    assertEquals(new Float(3.1415f), oaw.get("pi"));
    assertNull(oaw.get("omega"));
  }

  @Test
  public void test_getAttributeValue_1() throws NoSuchFieldException
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    assertEquals("centauri", oaw.getAttributeValue("alpha"));
    assertEquals("geuze", oaw.getAttributeValue("beta"));
    assertEquals("erich", oaw.getAttributeValue("gamma"));
    assertEquals(new Integer(8), oaw.getAttributeValue("delta"));
    assertEquals(new Float(3.1415f), oaw.getAttributeValue("pi"));
  }

  @Test
  public void test_getAttributeValue_NoSuchFieldException()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    try
    {
      assertNull(oaw.getAttributeValue("omega"));
      fail("NoSuchFieldException expected!");
    }
    catch (NoSuchFieldException ex)
    {
      // Expected
    }
  }

  @Test
  public void test_getValueOfField_1()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    assertEquals("centauri", oaw.getValueOfField("alpha"));
    assertEquals("geuze", oaw.getValueOfField("beta"));
    assertEquals("erich", oaw.getValueOfField("gamma"));
    assertEquals(new Integer(8), oaw.getValueOfField("delta"));
    assertEquals(new Float(3.1415f), oaw.getValueOfField("pi"));
    assertNull(oaw.getValueOfField("omega"));
  }

  @Test
  public void test_set_1()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    oaw.set("alpha", "111");
    oaw.set("pi", "222");
    oaw.set("kappa", "333");
    assertEquals("111", oaw.get("alpha"));
    assertEquals("geuze", oaw.get("beta"));
    assertEquals("erich", oaw.get("gamma"));
    assertEquals(new Integer(8), oaw.get("delta"));
    assertEquals("222", oaw.get("pi"));
    assertEquals("333", oaw.get("kappa"));
    assertNull(oaw.get("omega"));
  }

  @Test
  public void test_setValueOfField_1()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    oaw.setValueOfField("alpha", "111");
    oaw.setValueOfField("pi", "222");
    oaw.setValueOfField("kappa", "333");
    assertEquals("111", oaw.get("alpha"));
    assertEquals("geuze", oaw.get("beta"));
    assertEquals("erich", oaw.get("gamma"));
    assertEquals(new Integer(8), oaw.get("delta"));
    assertEquals("222", oaw.get("pi"));
    assertEquals("333", oaw.get("kappa"));
    assertNull(oaw.get("omega"));
  }

  @Override
  @Test
  public void test_setAttributeValue_1()
  {
    ObjectAccessWrapper oaw;

    oaw = this.createAccessWrapper(this.sampleMap1());
    try
    {
      oaw.setAttributeValue("alpha", "111");
      oaw.setAttributeValue("pi", "222");
    }
    catch (NoSuchFieldException ex)
    {
      fail("No NoSuchFieldException expected! " + ex.getMessage());
    }
    try
    {
      oaw.setAttributeValue("kappa", "333");
      fail("NoSuchFieldException expected!");
    }
    catch (Exception ex)
    {
      assertTrue(ex instanceof NoSuchFieldException);
    }
    assertEquals("111", oaw.get("alpha"));
    assertEquals("geuze", oaw.get("beta"));
    assertEquals("erich", oaw.get("gamma"));
    assertEquals(new Integer(8), oaw.get("delta"));
    assertEquals("222", oaw.get("pi"));
  }

  // =========================================================================
  // HELPER METHODS
  // =========================================================================
  @Override
  protected ObjectAccessWrapper createAccessWrapper(Object object)
  {
    return new ObjectOrMapAccessWrapper(object);
  }

  protected Map sampleMap1()
  {
    Map map = new HashMap();

    map.put("alpha", "centauri");
    map.put("beta", "geuze");
    map.put("gamma", "erich");
    map.put("pi", new Float(3.1415f));
    map.put("delta", new Integer(8));
    return map;
  }

}

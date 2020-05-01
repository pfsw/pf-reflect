// ===========================================================================
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 13/01/2008
// HISTORY  :
//  13/01/2008  mdu  CREATED
//
// Copyright (c) 2008-2020, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import static org.pfsw.reflect.ReflectUtil.RU;
import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;
import org.pfsw.reflect.testhelper.BaseClass1;
import org.pfsw.reflect.testhelper.Subclass1;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 */
public class ModifiersTest
{
  @Test
  public void test_abstract_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isAbstract());

    modifiers.setAbstract();
    assertTrue(modifiers.isAbstract());

    modifiers = new Modifiers(Modifier.PUBLIC | Modifier.FINAL);
    assertTrue(modifiers.isPublic());
    assertTrue(modifiers.isFinal());
    assertFalse(modifiers.isAbstract());

    modifiers.setAbstract();
    assertTrue(modifiers.isPublic());
    assertTrue(modifiers.isFinal());
    assertTrue(modifiers.isAbstract());

    modifiers.unsetAbstract();
    assertTrue(modifiers.isPublic());
    assertTrue(modifiers.isFinal());
    assertFalse(modifiers.isAbstract());
  }

  @Test
  public void test_final_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isFinal());
    modifiers.setFinal();
    assertTrue(modifiers.isFinal());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isFinal());
    modifiers.unsetFinal();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isFinal());
  }

  @Test
  public void test_native_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isNative());
    modifiers.setNative();
    assertTrue(modifiers.isNative());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isNative());
    modifiers.unsetNative();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isNative());
  }

  @Test
  public void test_strict_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isStrict());
    modifiers.setStrict();
    assertTrue(modifiers.isStrict());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isStrict());
    modifiers.unsetStrict();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isStrict());
  }

  @Test
  public void test_transient_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isTransient());
    modifiers.setTransient();
    assertTrue(modifiers.isTransient());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isTransient());
    modifiers.unsetTransient();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isTransient());
  }

  @Test
  public void test_interface_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isInterface());
    modifiers.setInterface();
    assertTrue(modifiers.isInterface());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isInterface());
    modifiers.unsetInterface();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isInterface());
  }

  @Test
  public void test_volatile_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isVolatile());
    modifiers.setVolatile();
    assertTrue(modifiers.isVolatile());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isVolatile());
    modifiers.unsetVolatile();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isVolatile());
  }

  @Test
  public void test_synchronized_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isSynchronized());
    modifiers.setSynchronized();
    assertTrue(modifiers.isSynchronized());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isSynchronized());
    modifiers.unsetSynchronized();
    assertTrue(modifiers.isStatic());
    assertFalse(modifiers.isSynchronized());
  }

  @Test
  public void test_static_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertFalse(modifiers.isStatic());
    modifiers.setStatic();
    assertTrue(modifiers.isStatic());
    modifiers.setFinal();
    assertTrue(modifiers.isFinal());
    assertTrue(modifiers.isStatic());
    modifiers.unsetStatic();
    assertTrue(modifiers.isFinal());
    assertFalse(modifiers.isStatic());
  }

  @Test
  public void test_visibility_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setPublic();
    assertTrue(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());

    modifiers.setProtected();
    assertFalse(modifiers.isPublic());
    assertTrue(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());

    modifiers.setPrivate();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertTrue(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());

    modifiers.setDefaultVisibility();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());
  }

  @Test
  public void test_visibility_2()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setPublic();
    assertTrue(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.unsetPublic();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setProtected();
    assertFalse(modifiers.isPublic());
    assertTrue(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.unsetProtected();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setPrivate();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertTrue(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.unsetPrivate();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());
  }

  @Test
  public void test_visibility_3()
  {
    Modifiers modifiers = Modifiers.create();
    
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setVisibility(Modifiers.VIS_PUBLIC);
    assertTrue(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.unsetPublic();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setVisibility(Modifiers.VIS_PROTECTED);
    assertFalse(modifiers.isPublic());
    assertTrue(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.unsetProtected();
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());

    modifiers.setVisibility(Modifiers.VIS_PRIVATE);
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertTrue(modifiers.isPrivate());
    assertFalse(modifiers.isDefaultVisibility());
    modifiers.setVisibility(Modifiers.VIS_DEFAULT);
    assertFalse(modifiers.isPublic());
    assertFalse(modifiers.isProtected());
    assertFalse(modifiers.isPrivate());
    assertTrue(modifiers.isDefaultVisibility());
  }

  @Test
  public void test_reset_1()
  {
    Modifiers modifiers = Modifiers.create();
    
    modifiers.setStatic();
    modifiers.setFinal();
    modifiers.setProtected();
    assertTrue(modifiers.isStatic());
    assertTrue(modifiers.isFinal());
    assertTrue(modifiers.isProtected());

    modifiers.reset();
    assertFalse(modifiers.isStatic());
    assertFalse(modifiers.isFinal());
    assertFalse(modifiers.isProtected());
  }

  @Test
  public void test_equals_1()
  {
    Modifiers m1 = Modifiers.create(Modifier.ABSTRACT | Modifier.FINAL | Modifier.PUBLIC);
    Modifiers m2 = null;

    assertFalse(m1.equals(m2));

    m2 = new Modifiers();
    m2.setAbstract();
    m2.setFinal();
    m2.setPublic();
    assertEquals(m1, m2);

    m2.setVolatile();
    assertFalse(m1.equals(m2));
  }

  @Test
  public void test_hashCode_1()
  {
    Modifiers m1 = Modifiers.create(Modifier.TRANSIENT | Modifier.VOLATILE | Modifier.PROTECTED);
    Modifiers m2 = null;

    m2 = new Modifiers();
    m2.setTransient();
    m2.setVolatile();
    m2.setProtected();
    assertTrue(m1.hashCode() == m2.hashCode());

    m2.setPrivate();
    assertFalse(m1.hashCode() == m2.hashCode());
  }

  @Test
  public void test_toString_1()
  {
    Modifiers m1 = Modifiers.create(Modifier.TRANSIENT | Modifier.VOLATILE | Modifier.PROTECTED);

    assertEquals("protected transient volatile", m1.toString());
  }

  @Test
  public void test_create__field()
  {
    Modifiers m1 = Modifiers.create(ReflectUtil.current().findField(Subclass1.class, "dummy1"));
    
    assertTrue(m1.isFinal());
    assertTrue(m1.isTransient());
    assertTrue(m1.isDefaultVisibility());
  }
  
  @Test
  public void test_isAbstract__class()
  {
    Modifiers m1 = Modifiers.of(BaseClass1.class);
    Modifiers m2 = Modifiers.of(Subclass1.class);
    
    assertTrue(m1.isAbstract());
    assertFalse(m2.isAbstract());
  }
  
  @Test
  public void test_isAbstract__method()
  {
    Modifiers m1 = Modifiers.of(RU.getMethod(BaseClass1.class, "getName"));
    Modifiers m2 = Modifiers.of(RU.getMethod(BaseClass1.class, "getPrefix"));
    
    assertFalse(m1.isAbstract());
    assertTrue(m2.isAbstract());
  }
}

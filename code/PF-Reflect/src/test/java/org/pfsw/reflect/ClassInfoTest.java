// ===========================================================================
// CONTENT  : TEST CLASS ClassInfoTest
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 25/01/2003
// HISTORY  :
//  25/01/2003  duma  CREATED
//
// Copyright (c) 2003, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.pfsw.reflect.testhelper.OtherClass;
import org.pfsw.reflect.testhelper.Subclass1;
import org.pfsw.reflect.testhelper.Subclass2;
import org.pfsw.reflect.testhelper.Superclass;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class ClassInfoTest
{
  // =========================================================================
  // TEST METHODS
  // =========================================================================
  @Test
  public void test_createInstance_1() throws Exception
  {
    ClassInfo ci = new ClassInfo("java.lang.String");
    String str;

    str = (String)ci.createInstance();
    assertNotNull(str);
    assertEquals("", str);
  }

  @Test
  public void test_createInstance_2()
  {
    ClassInfo classInfo;

    classInfo = new ClassInfo("org.pf.text.StringUtil");
    assertNull(classInfo.createInstance());
  }

  @Test
  public void test_newInstance_1() throws Exception
  {
    ClassInfo ci = new ClassInfo("java.util.ArrayList");
    Object list;

    list = ci.newInstance();
    assertNotNull(list);
    assertTrue(list instanceof List);
  }

  @Test
  public void test_newInstanceX_1() throws Exception
  {
    ClassInfo ci = new ClassInfo("java.lang.Integer");

    try
    {
      ci.newInstance();
      fail("Exception expected!");
    }
    catch (ClassNotFoundException e)
    {
      fail("Class should have been found");
    }
    catch (InstantiationException e)
    {
      // Exactly what should happen
    }
    catch (IllegalAccessException e)
    {
      fail("No security manager in place");
    }
  } 

  @Test
  public void test_newInstanceX_2() throws Exception
  {
    ClassInfo ci = new ClassInfo("org.test.unknown.Porsche");

    try
    {
      ci.newInstance();
      fail("Exception expected!");
    }
    catch (ClassNotFoundException e)
    {
      // Exactly what should happen			
    }
    catch (InstantiationException e)
    {
      fail("Class should not have been found");
    }
    catch (IllegalAccessException e)
    {
      fail("No security manager in place");
    }
  } 

  @Test
  public void test_getClassObject_1() throws Exception
  {
    ClassInfo ci = new ClassInfo("java.lang.String");
    Class aClass;

    aClass = ci.getClassObject();
    assertNotNull(aClass);
    assertTrue(aClass.isAssignableFrom(String.class));
  } 

  @Test
  public void test_getClassName_1() throws Exception
  {
    ClassInfo<Set> ci = new ClassInfo(Set.class);
    String name;

    name = ci.getClassName();
    assertEquals("java.util.Set", name);
  } 

  @Test
  public void test_isAssignableFrom_1()
  {
    ClassInfo classInfo = new ClassInfo(Superclass.class.getName());
    assertTrue(classInfo.isAssignableFrom(Subclass1.class));
  } 

  @Test
  public void test_isAssignableFrom_2()
  {
    ClassInfo classInfo = new ClassInfo(Superclass.class.getName());
    assertTrue(!classInfo.isAssignableFrom(OtherClass.class));
  }

  @Test
  public void test_isAssignableFrom_3()
  {
    ClassInfo classInfo = new ClassInfo(Superclass.class.getName());
    assertTrue(!classInfo.isAssignableFrom(null));
  }

  @Test
  public void test_isAssignableFrom_4()
  {
    ClassInfo classInfo = new ClassInfo("com.unknown.Class99");
    assertTrue(!classInfo.isAssignableFrom(Subclass2.class));
  }

  @Test
  public void test_isAssignableTo_1()
  {
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(classInfo.isAssignableTo(Subclass1.class));
  }

  @Test
  public void test_isAssignableTo_2()
  {
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(classInfo.isAssignableTo(Superclass.class));
  }

  @Test
  public void test_isAssignableTo_3()
  {
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(!classInfo.isAssignableTo(Subclass2.class));
  }

  @Test
  public void test_isAssignableTo_4()
  {
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(!classInfo.isAssignableTo(null));
  }

  @Test
  public void test_isAssignableTo_5()
  {
    ClassInfo classInfo = new ClassInfo("");
    assertTrue(!classInfo.isAssignableTo(Subclass1.class));
  }

  @Test
  public void test_isInstance_1()
  {
    Object object = new Subclass1();
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_2()
  {
    Object object = new Subclass2();
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_3()
  {
    Object object = new Subclass1();
    ClassInfo classInfo = new ClassInfo(Superclass.class.getName());
    assertTrue(classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_4()
  {
    Object object = new Subclass1();
    ClassInfo classInfo = new ClassInfo(Subclass2.class.getName());
    assertTrue(!classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_5()
  {
    Object object = null;
    ClassInfo classInfo = new ClassInfo(Subclass2.class.getName());
    assertTrue(!classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_6()
  {
    Object object = new Subclass1();
    ClassInfo classInfo = new ClassInfo("Unknownclass");
    assertTrue(!classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_7()
  {
    Object object = "Test";
    ClassInfo classInfo = new ClassInfo(Subclass1.class.getName());
    assertTrue(!classInfo.isInstance(object));
  }

  @Test
  public void test_isInstance_8()
  {
    Object object = new Subclass1();
    ClassInfo classInfo = new ClassInfo("java.lang.Object");
    assertTrue(classInfo.isInstance(object));
    assertTrue(classInfo.isInstance("String"));
  }

  @Test
  public void test_newArray_1()
  {
    String[] strings;
    ClassInfo classInfo = new ClassInfo("java.lang.String");
    strings = (String[])classInfo.newArray(3);
    assertEquals(3, strings.length);
  }

  @Test
  public void test_newArray_2()
  {
    Subclass1[] objects;
    ClassInfo<Subclass1> classInfo = new ClassInfo(Subclass2.class.getName());
    objects = classInfo.newArray(99);
    assertEquals(99, objects.length);
  }

  @Test
  public void test_newArray_3()
  {
    Subclass1[] objects;
    ClassInfo classInfo;
    try
    {
      classInfo = new ClassInfo(Superclass.class.getName());
      objects = (Subclass1[])classInfo.newArray(99);
      fail("Should have thrown ClassCastException / " + objects.length);
    }
    catch (ClassCastException e)
    {
      // That was expected
    }
  }

  @SuppressWarnings("deprecation")
  @Test
  public void test_newArray_4()
  {
    Object[] objects;
    ClassInfo classInfo = new ClassInfo("UnknownClass");
    objects = classInfo.newArray(11);
    assertEquals(ClassInfo.EMPTY_OBJECT_ARRAY, objects);
  }

  @Test
  public void test_getInstance_1()
  {
    ClassInfo<Subclass2> classInfo;
    Subclass2 object1;
    Subclass2 object2;

    classInfo = new ClassInfo("org.pfsw.reflect.testhelper.Subclass2", true);
    object1 = classInfo.getInstance();
    object2 = classInfo.getInstance();
    assertTrue(object1 == object2);
  }

  @Test
  public void test_getInstance_2()
  {
    ClassInfo<Subclass2> classInfo;
    Subclass2 object1;
    Subclass2 object2;

    classInfo = new ClassInfo(Subclass2.class, false);
    object1 = classInfo.getInstance();
    object2 = classInfo.getInstance();
    assertTrue(object1 != object2);
    assertFalse(classInfo.isSingleton());
  }

  @Test
  public void test_getInstance_3()
  {
    ClassInfo<Subclass2> classInfo;
    Subclass2 object1;
    Subclass2 object2;
    Subclass2 object3;

    classInfo = new ClassInfo(Subclass2.class);
    classInfo.setIsSingleton(true);
    object1 = classInfo.getInstance();
    object2 = classInfo.getInstance();
    object3 = classInfo.getInstance();
    assertTrue(object1 == object2);
    assertTrue(object1 == object3);
    assertTrue(classInfo.isSingleton());
  }

  @Test
  public void test_toString_1()
  {
    ClassInfo classInfo;

    classInfo = new ClassInfo("org.pf.text.StringUtil");
    assertEquals("org.pfsw.reflect.ClassInfo(org.pf.text.StringUtil)", classInfo.toString());
  }

  @Test
  public void test_createSingleton()
  {
    ClassInfo<String> classInfo;
    String singleObject = "alpha";

    classInfo = ClassInfo.createSingleton(singleObject);
    assertEquals("java.lang.String", classInfo.getClassName());
    assertSame(String.class, classInfo.getClassObject());
    for (int i = 0; i < 10; i++)
    {
      String string = classInfo.getInstance();
      assertEquals(String.format("%d : Value is '%s'", i, string), singleObject, string);
    }
    assertTrue(classInfo.isSingleton());
  }

  @Test
  public void test_createSingleton_null()
  {
    try
    {
      ClassInfo.createSingleton(null);
      fail("Expected IllegalArgumentException");
    }
    catch (Exception e)
    {
      assertTrue(e instanceof IllegalArgumentException);
    }
  }
}

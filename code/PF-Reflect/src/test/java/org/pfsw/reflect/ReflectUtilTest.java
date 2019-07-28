// ===========================================================================
// CONTENT  : TEST CLASS ReflectUtilTest
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 28/09/2002
// HISTORY  :
//  28/09/2002  mdu  CREATED
//
// Copyright (c) 2002-2019, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.Proxy.Type;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.jar.JarEntry;

import javax.swing.JScrollPane;
import javax.swing.plaf.ComponentUI;

import org.junit.Test;
import org.pfsw.reflect.testhelper.DummyClass1;
import org.pfsw.reflect.testhelper.ExternalTypeId;
import org.pfsw.reflect.testhelper.IGalacticalCollection;
import org.pfsw.reflect.testhelper.OtherClass;
import org.pfsw.reflect.testhelper.PersonData;
import org.pfsw.reflect.testhelper.Subclass1;
import org.pfsw.reflect.testhelper.Subclass2;
import org.pfsw.reflect.testhelper.Superclass;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReflectUtilTest
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  protected static final ReflectUtil util = ReflectUtil.current();
  private static final Class<?> sampleEnumType = Type.class;

  // =========================================================================
  // TEST METHODS
  // =========================================================================
  @Test
  public void test_findMethod_1()
  {
    Method method;
    String methodName = "toString";

    method = util.findMethod(String.class, methodName, (Class[])null);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PUBLIC, method.getModifiers());
  }

  @Test
  public void test_findMethod_2()
  {
    Method method;
    String methodName = "store";
    Class[] types = { OutputStream.class, String.class };

    method = util.findMethod(Properties.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PUBLIC, method.getModifiers());
  }

  @Test
  public void test_findMethod_3()
  {
    Method method;
    String methodName = "listIterator";

    method = util.findMethod(Vector.class, methodName, (Class[])null);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PUBLIC, method.getModifiers());
  }

  @Test
  public void test_findMethod_4()
  {
    Method method;
    String methodName = "format";
    Class[] types = { Object.class };

    method = util.findMethod(NumberFormat.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PUBLIC, method.getModifiers());
  }

  @Test
  public void test_findMethod_5()
  {
    Method method;
    String methodName = "recalculate";
    Class[] types = { Date.class };

    method = util.findMethod(String.class, methodName, types);
    assertNull("Method '" + methodName + "' must not exist", method);
  }

  @Test
  public void test_findMethod_6()
  {
    Method method;
    String methodName = "format";
    Class[] types = { Field.class, String.class, FieldPosition.class };

    method = util.findMethod(NumberFormat.class, methodName, types);
    assertNull("Method '" + methodName + "' must not exist", method);
  }

  @Test
  public void test_findMethod_7()
  {
    Method method;
    String methodName = "dummy1";
    Class[] types = {};

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PROTECTED, method.getModifiers());
  }

  @Test
  public void test_findMethod_8()
  {
    Method method;
    String methodName = "dummy2";
    Class[] types = {};

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(0, method.getModifiers());
  }

  @Test
  public void test_findMethod_9()
  {
    Method method;
    String methodName = "dummy3";
    Class[] types = {};

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PRIVATE, method.getModifiers());
  }

  @Test
  public void test_findMethod_10()
  {
    Method method;
    String methodName = "dummy4";
    Class[] types = { Long.TYPE };

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PROTECTED, method.getModifiers());
  }

  @Test
  public void test_findMethod_11()
  {
    Method method;
    String methodName = "dummy5";
    Class[] types = { String.class, Date.class };

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(0, method.getModifiers());
  }

  @Test
  public void test_findMethod_12()
  {
    Method method;
    String methodName = "dummy6";
    Class[] types = { Boolean.TYPE };

    method = util.findMethod(Superclass.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PRIVATE, method.getModifiers());
  }

  @Test
  public void test_findMethod_13()
  {
    Method method;
    String methodName = "dummy1";
    Class[] types = {};

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PROTECTED, method.getModifiers());
  }

  @Test
  public void test_findMethod_14()
  {
    Method method;
    String methodName = "dummy2";
    Class[] types = {};

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(0, method.getModifiers());
  }

  @Test
  public void test_findMethod_15()
  {
    Method method;
    String methodName = "dummy3";
    Class[] types = {};

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PRIVATE, method.getModifiers());
  }

  @Test
  public void test_findMethod_16()
  {
    Method method;
    String methodName = "dummy4";
    Class[] types = { Long.TYPE };

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PROTECTED, method.getModifiers());
  }

  @Test
  public void test_findMethod_17()
  {
    Method method;
    String methodName = "dummy5";
    Class[] types = { String.class, Date.class };

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(0, method.getModifiers());
  }

  @Test
  public void test_findMethod_18()
  {
    Method method;
    String methodName = "dummy6";
    Class[] types = { Boolean.TYPE };

    method = util.findMethod(Subclass1.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PRIVATE, method.getModifiers());
  }

  @Test
  public void test_findMethod_19()
  {
    Method method;
    String methodName = "setUI";
    Class[] types = { ComponentUI.class };

    method = util.findMethod(JScrollPane.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PROTECTED, method.getModifiers());
  }

  @Test
  public void test_findMethod_20()
  {
    Method method;
    String methodName = "setLayout";
    Class[] types = { BorderLayout.class };

    method = util.findMethod(JScrollPane.class, methodName, types);
    assertNotNull("Method '" + methodName + "' not found", method);
    assertEquals(methodName, method.getName());
    assertModifier(Modifier.PUBLIC, method.getModifiers());
  }

  @Test
  public void test_findMethod_21()
  {
    assertNotNull(util.findMethod(Subclass1.class, "dummy6", new Class[] { Boolean.TYPE }));
    assertNull(util.findMethod(Subclass1.class, "dummy6", (Class[])null));
  }

  @Test
  public void test_findClass_1()
  {
    assertNotNull(util.findClass("org.pfsw.reflect.testhelper.PersonData"));
  }

  @Test
  public void test_findClass_2()
  {
    assertNotNull(util.findClass("java.io.PrintStream"));
  }

  @Test
  public void test_findClass_3()
  {
    assertNull(util.findClass("java.io.Unknown"));
  }

  @Test
  public void test_findClass_4()
  {
    assertNull(util.findClass("java/io\\Un-�known"));
  }

  @Test
  public void test_findClass_5()
  {
    ReflectUtil reflectUtil;

    reflectUtil = new ReflectUtil(this.getClass().getClassLoader());
    assertNotNull(reflectUtil.findClass("java.util.Date"));
    assertNotNull(reflectUtil.findClass("org.pfsw.reflect.testhelper.OtherClass"));
    assertNull(reflectUtil.findClass("org.pffff.reflect.test.OtherClass"));
    assertNull(reflectUtil.findClass(null));
  }

  @Test
  public void test_classExists_1()
  {
    assertTrue(util.classExists("org.pfsw.reflect.testhelper.OtherClass"));
  }

  @Test
  public void test_classExists_2()
  {
    assertFalse(util.classExists("org.pffff.reflect.test.OtherClass"));
  }

  @Test
  public void test_findField_1() throws Exception
  {
    assertNotNull(util.findField(Subclass2.class, "name"));
  }

  @Test
  public void test_findField_2() throws Exception
  {
    assertNull(util.findField(Subclass2.class, "noname"));
  }

  @Test
  public void test_findField_3() throws Exception
  {
    assertNotNull(util.findField(Subclass2.class, "var_1_2"));
    assertNotNull(util.findField(Subclass2.class, "var_1_4"));
  }

  @Test
  public void test_findField_4() throws Exception
  {
    assertNotNull(util.findField(Subclass2.class, "myClass", Modifier.PUBLIC));
    assertNotNull(util.findField(Subclass2.class, "var_1_2", Modifier.PRIVATE));
    assertNull(util.findField(Subclass2.class, "var_1_2", Modifier.PROTECTED));
    assertNotNull(util.findField(Subclass2.class, "flag1", Modifier.PROTECTED));
    assertNull(util.findField(Subclass2.class, "flag1", Modifier.PRIVATE));
    assertNotNull(util.findField(Subclass2.class, "var_1_4"));
  }

  @Test
  public void test_findField_5() throws Exception
  {
    assertNotNull(util.findField(Subclass2.class, "var_1_1", Modifier.PUBLIC));
    assertNotNull(util.findField(Subclass2.class, "var_1_1", Modifier.PUBLIC + Modifier.TRANSIENT));
    assertNull(util.findField(Subclass2.class, "var_1_1", Modifier.PROTECTED + Modifier.TRANSIENT));
    assertNull(util.findField(Subclass2.class, "var_1_1", Modifier.PUBLIC + Modifier.TRANSIENT + Modifier.STATIC));
  }

  @Test
  public void test_getFieldsOf_1() throws Exception
  {
    Superclass obj = new Superclass();
    List fields;

    fields = util.getFieldsOf(obj);
    assertTrue(fields.size() >= 4);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("flag1", containsField(fields, "flag1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("myClass", containsField(fields, "myClass"));
  }

  @Test
  public void test_getFieldsOf_class_1() throws Exception
  {
    List fields;

    fields = util.getFieldsOf(Superclass.class);
    assertTrue(fields.size() >= 4);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("flag1", containsField(fields, "flag1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("myClass", containsField(fields, "myClass"));
  }

  @Test
  public void test_getFieldsOf_2() throws Exception
  {
    Subclass1 obj = new Subclass1();
    List fields;

    fields = util.getFieldsOf(obj);
    assertTrue(fields.size() >= 8);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("var_1_1", containsField(fields, "var_1_1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("var_1_3", containsField(fields, "var_1_3"));
  }

  @Test
  public void test_getFieldsOf_class_2() throws Exception
  {
    List fields;

    fields = util.getFieldsOf(Subclass1.class);
    assertTrue(fields.size() >= 8);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("var_1_1", containsField(fields, "var_1_1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("var_1_3", containsField(fields, "var_1_3"));
  }

  @Test
  public void test_getFieldsOf_3() throws Exception
  {
    Subclass2 obj = new Subclass2();
    List fields;

    fields = util.getFieldsOf(obj);
    assertTrue(fields.size() >= 12);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("const_2_1", containsField(fields, "const_2_1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("var_1_4", containsField(fields, "var_1_4"));
    assertTrue("var_2_4", containsField(fields, "var_2_4"));
  }

  @Test
  public void test_getFieldsOf_class_3() throws Exception
  {
    List fields;

    fields = util.getFieldsOf(Subclass2.class);
    assertTrue(fields.size() >= 12);
    assertTrue("name", containsField(fields, "name"));
    assertTrue("const_2_1", containsField(fields, "const_2_1"));
    assertTrue("ident", containsField(fields, "ident"));
    assertTrue("var_1_4", containsField(fields, "var_1_4"));
    assertTrue("var_2_4", containsField(fields, "var_2_4"));
  }

  @Test
  public void test_getField_1() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Field field;

    field = util.getField(obj, "name");
    assertNotNull(field);
    field.setAccessible(true);
    assertEquals("Class", String.class, field.getType());
    assertEquals("Value", "Superclass", field.get(obj));
  }

  @Test
  public void test_getField_2() throws Exception
  {
    Subclass1 obj = new Subclass1();
    Field field;

    field = util.getField(obj, "unknown");
    assertNull(field);
  }

  @Test
  public void test_getField_of_class() throws Exception
  {
    Subclass1 obj = new Subclass1();
    Class clazz = obj.getClass();
    Field field;

    field = util.getField(clazz, "var_1_4");
    assertNotNull(field);
  }

  @Test
  public void test_setField_1() throws Exception
  {
    String varName = "var_1_2";
    Integer newValue = new Integer(120);
    Subclass2 obj = new Subclass2();

    assertEquals(new Integer(7), util.getValueOf(obj, varName));
    util.setValueOf(obj, varName, newValue);
    assertEquals(newValue, util.getValueOf(obj, varName));
  }

  @Test
  public void test_setField_2() throws Exception
  {
    String varName = "var_1_1";
    String newValue = "London";
    Subclass2 obj = new Subclass2();

    assertEquals("sub_1_v1", util.getValueOf(obj, varName));
    util.setValueOf(obj, varName, newValue);
    assertEquals(newValue, util.getValueOf(obj, varName));
  }

  @Test
  public void test_setField_3() throws Exception
  {
    String varName = "var_1_4";
    char newValue = 'A';
    Subclass2 obj = new Subclass2();

    assertEquals(new Character('X'), util.getValueOf(obj, varName));
    util.setValueOf(obj, varName, newValue);
    assertEquals(obj.var_1_4(), ((Character)util.getValueOf(obj, varName)).charValue());
  }

  @Test
  public void test_setField_4() throws Exception
  {
    String varName = "unknown";
    Subclass2 obj = new Subclass2();

    try
    {
      util.setValueOf(obj, varName, "Test");
      fail("Expected a NoSuchFieldException");
    }
    catch (Exception e)
    {
      assertTrue(e instanceof NoSuchFieldException);
    }
  }

  @Test
  public void test_setField_static_with_class() throws NoSuchFieldException
  {
    DummyClass1.label = "unset";
    util.setValueOf(DummyClass1.class, "label", "nice");
    assertEquals("nice", DummyClass1.label);
  }

  @Test
  public void test_getValueOf_1() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Object value;

    value = util.getValueOf(obj, "name");
    assertNotNull(value);
    assertEquals("Class", String.class, value.getClass());
    assertEquals("Value", "Superclass", value);
  }

  @Test
  public void test_getValueOf_2() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Object value;

    value = util.getValueOf(obj, "var_1_2");
    assertNotNull(value);
    assertEquals("Class", Integer.class, value.getClass());
    assertEquals("Value", new Integer(7), value);
  }

  @Test
  public void test_getValueOf_3() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Object value;

    value = util.getValueOf(obj, "var_2_3");
    assertNull(value);
  }

  @Test
  public void test_getValueOf_4() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Object value;

    value = util.getValueOf(obj, "const_2_1");
    assertNotNull(value);
    assertEquals("Class", String.class, value.getClass());
    assertEquals("Value", "constant", value);
  }

  @Test
  public void test_getValueOf_5() throws Exception
  {
    Subclass2 obj = new Subclass2();
    Object value;

    value = util.getValueOf(obj, "flag1");
    assertNotNull(value);
    assertEquals("Class", Boolean.class, value.getClass());
    assertEquals("Value", Boolean.TRUE, value);
  }

  @Test
  public void test_getValueOf_6()
  {
    Subclass2 obj = new Subclass2();
    Object value;

    try
    {
      value = util.getValueOf(obj, "unknown");
      fail("Field should not have been found! Expected NoSuchFieldException" + value);
    }
    catch (Exception e)
    {
      assertTrue(e instanceof NoSuchFieldException);
    }
  }

  @Test
  public void test_isDefaultVisibility_1() throws Exception
  {
    int modifiers = Modifier.TRANSIENT | Modifier.STATIC;

    assertTrue(util.isDefaultVisibility(modifiers));
  }

  @Test
  public void test_isDefaultVisibility_2() throws Exception
  {
    int modifiers;

    modifiers = Modifier.PUBLIC | Modifier.STATIC;
    assertTrue("public", !util.isDefaultVisibility(modifiers));

    modifiers = Modifier.PROTECTED | Modifier.STATIC | Modifier.FINAL;
    assertTrue("protected", !util.isDefaultVisibility(modifiers));

    modifiers = Modifier.PRIVATE;
    assertTrue("private", !util.isDefaultVisibility(modifiers));
  }

  @Test
  public void test_getMethod_1()
  {
    assertNull(util.getMethod(null, "getId"));
  }

  @Test
  public void test_getMethod_of_class()
  {
    assertNotNull(util.getMethod(Subclass1.class, "setName", new Class[] { String.class }));
  }

  @Test
  public void test_getMethodsOf_1()
  {
    List methods;
    Object obj = new Subclass1();

    methods = util.getMethodsOf(obj);
    assertTrue("help", !this.containsMethod(methods, "help"));
    assertTrue("dummy2", this.containsMethod(methods, "dummy2"));
    assertTrue("dummy6", this.containsMethod(methods, "dummy6"));
    assertTrue("var_1_2", this.containsMethod(methods, "var_1_2"));
    assertTrue("var_1_4", this.containsMethod(methods, "var_1_4"));
    assertTrue("var_2_1", !this.containsMethod(methods, "var_2_1"));
    assertTrue("var_2_3", !this.containsMethod(methods, "var_2_3"));
  }

  @Test
  public void test_getMethodsOf_class_1() throws Exception
  {
    List methods;

    methods = util.getMethodsOf(Subclass1.class);
    assertTrue("help", !this.containsMethod(methods, "help"));
    assertTrue("dummy2", this.containsMethod(methods, "dummy2"));
    assertTrue("dummy6", this.containsMethod(methods, "dummy6"));
    assertTrue("var_1_2", this.containsMethod(methods, "var_1_2"));
    assertTrue("var_1_4", this.containsMethod(methods, "var_1_4"));
    assertTrue("var_2_1", !this.containsMethod(methods, "var_2_1"));
    assertTrue("var_2_3", !this.containsMethod(methods, "var_2_3"));
  }

  @Test
  public void test_getMethodsOf_2() throws Exception
  {
    List methods;
    Object obj = new Subclass2();

    methods = util.getMethodsOf(obj);
    assertTrue("unknown", !this.containsMethod(methods, "unknown"));
    assertTrue("dummy3", this.containsMethod(methods, "dummy3"));
    assertTrue("dummy4", this.containsMethod(methods, "dummy4"));
    assertTrue("var_1_1", this.containsMethod(methods, "var_1_1"));
    assertTrue("var_1_3", this.containsMethod(methods, "var_1_3"));
    assertTrue("var_2_2", this.containsMethod(methods, "var_2_2"));
    assertTrue("var_2_3", this.containsMethod(methods, "var_2_3"));
  }

  @Test
  public void test_getMethodsOf_class_2() throws Exception
  {
    List methods;

    methods = util.getMethodsOf(Subclass2.class);
    assertTrue("unknown", !this.containsMethod(methods, "unknown"));
    assertTrue("dummy3", this.containsMethod(methods, "dummy3"));
    assertTrue("dummy4", this.containsMethod(methods, "dummy4"));
    assertTrue("var_1_1", this.containsMethod(methods, "var_1_1"));
    assertTrue("var_1_3", this.containsMethod(methods, "var_1_3"));
    assertTrue("var_2_2", this.containsMethod(methods, "var_2_2"));
    assertTrue("var_2_3", this.containsMethod(methods, "var_2_3"));
  }

  @Test
  public void test_getInterfacesOf_class_1()
  {
    Class[] interfaces;

    interfaces = util.getInterfacesOf(TreeMap.class);
    assertTrue(interfaces.length >= 4); // Could be 5 - since Java 6 added one
    assertTrue(util.contains(interfaces, SortedMap.class));
    assertTrue(util.contains(interfaces, Map.class));
    assertTrue(util.contains(interfaces, Cloneable.class));
    assertTrue(util.contains(interfaces, Serializable.class));
  }

  @Test
  public void test_getInterfacesOf_class_2()
  {
    Class[] interfaces;

    interfaces = util.getInterfacesOf((Class)null);
    assertEquals(0, interfaces.length);
  }

  @Test
  public void test_getInterfacesOf_1()
  {
    Class[] interfaces;

    interfaces = util.getInterfacesOf(new Vector());
    assertEquals(6, interfaces.length);
    assertTrue(util.contains(interfaces, Collection.class));
    assertTrue(util.contains(interfaces, List.class));
    assertTrue(util.contains(interfaces, RandomAccess.class));
    assertTrue(util.contains(interfaces, Cloneable.class));
    assertTrue(util.contains(interfaces, Serializable.class));
    assertTrue(util.contains(interfaces, Iterable.class));
  }

  @Test
  public void test_getInterfacesOf_2()
  {
    Class[] interfaces;

    interfaces = util.getInterfacesOf((Date)null);
    assertEquals(0, interfaces.length);
  }

  @Test
  public void test_getConstructorsOf_1()
  {
    List constructors;
    Constructor constructor;
    Subclass1 object = new Subclass1();

    constructors = util.getConstructorsOf(object);
    assertEquals(1, constructors.size());
    constructor = (Constructor)constructors.get(0);
    assertEquals(Subclass1.class, constructor.getDeclaringClass());
    assertEquals("org.pfsw.reflect.testhelper.Subclass1", constructor.getName());
    assertEquals(0, constructor.getParameterTypes().length);
  }

  @Test
  public void test_getConstructorsOf_2()
  {
    List constructors;
    Constructor constructor;
    DummyClass1 object = new DummyClass1(5);

    constructors = util.getConstructorsOf(object);
    assertEquals(5, constructors.size());
    constructor = (Constructor)constructors.get(0);
    assertEquals(DummyClass1.class, constructor.getDeclaringClass());
    assertEquals("org.pfsw.reflect.testhelper.DummyClass1", constructor.getName());
  }

  @Test
  public void test_getConstructorsOf_class_1() throws Exception
  {
    List constructors;
    Constructor constructor;

    constructors = util.getConstructorsOf(Subclass1.class);
    assertEquals(1, constructors.size());
    constructor = (Constructor)constructors.get(0);
    assertEquals(Subclass1.class, constructor.getDeclaringClass());
    assertEquals("org.pfsw.reflect.testhelper.Subclass1", constructor.getName());
    assertEquals(0, constructor.getParameterTypes().length);
  }

  @Test
  public void test_getConstructorsOf_class_2() throws Exception
  {
    List constructors;
    Constructor constructor;

    constructors = util.getConstructorsOf(Subclass2.class);
    assertEquals(4, constructors.size());
    constructor = (Constructor)constructors.get(0);
    assertEquals(Subclass2.class, constructor.getDeclaringClass());
    assertEquals("org.pfsw.reflect.testhelper.Subclass2", constructor.getName());
  }

  @Test
  public void test_getConstructorsOf_class_3() throws Exception
  {
    List constructors;
    Constructor constructor;

    constructors = util.getConstructorsOf(OtherClass.class);
    assertEquals(1, constructors.size());
    constructor = (Constructor)constructors.get(0);
    assertEquals(OtherClass.class, constructor.getDeclaringClass());
    assertEquals("org.pfsw.reflect.testhelper.OtherClass", constructor.getName());
    assertEquals(0, constructor.getParameterTypes().length);
  }

  @Test
  public void test_getVisibility_1() throws Exception
  {
    assertEquals("private", util.getVisibility(Modifier.PRIVATE + Modifier.TRANSIENT));
  }

  @Test
  public void test_getVisibility_2() throws Exception
  {
    assertEquals("protected", util.getVisibility(Modifier.PROTECTED + Modifier.VOLATILE));
  }

  @Test
  public void test_getVisibility_3() throws Exception
  {
    assertEquals("public", util.getVisibility(Modifier.PUBLIC + Modifier.STATIC));
  }

  @Test
  public void test_getVisibility_4() throws Exception
  {
    assertEquals("", util.getVisibility(Modifier.FINAL));
  }

  @Test
  public void test_newInstance_1() throws Exception
  {
    DummyClass1 object;

    object = util.newInstance(DummyClass1.class);
    assertEquals(1, object.getType());
    assertEquals(0, object.intValue);
    assertNull(object.strValue);
  }

  @Test
  public void test_newInstance_2() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(8) };

    object = util.newInstance(DummyClass1.class, params);
    assertEquals(2, object.getType());
    assertEquals(8, object.intValue);
    assertNull(object.strValue);
  }

  @Test
  public void test_newInstance_3() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(126), "Kansas" };

    object = util.newInstance(DummyClass1.class, params);
    assertEquals(3, object.getType());
    assertEquals(126, object.intValue);
    assertEquals("Kansas", object.strValue);
    assertEquals(0.0f, object.floatValue, 0.0f);
    assertNull(object.collValue);
  }

  @Test
  public void test_newInstance_4() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(77), new Float(1.62f) };

    object = util.newInstance(DummyClass1.class, params);
    assertEquals(4, object.getType());
    assertEquals(77, object.intValue);
    assertNull(object.strValue);
    assertEquals(1.62f, object.floatValue, 0.0f);
    assertNull(object.collValue);
  }

  @Test
  public void test_newInstance_5() throws Exception
  {
    DummyClass1 object;
    List list = new Vector();
    Object[] params = { new Integer(200), "Lynyrd Skynyrd", list };

    object = util.newInstance(DummyClass1.class, params);
    assertEquals(5, object.getType());
    assertEquals(200, object.intValue);
    assertEquals("Lynyrd Skynyrd", object.strValue);
    assertEquals(0.0f, object.floatValue, 0.0f);
    assertTrue(list == object.collValue);
  }

  @Test
  public void test_newInstance_6() throws Exception
  {
    DummyClass1 object;
    List list = new Vector();
    Object[] params = { "Lynyrd Skynyrd", list, Boolean.FALSE };

    object = util.newInstance(DummyClass1.class, params);
    assertNull(object);
  }

  @Test
  public void test_newInstance_className_1() throws Exception
  {
    DummyClass1 object;

    object = (DummyClass1)util.newInstance("org.pfsw.reflect.testhelper.DummyClass1");
    assertEquals(1, object.getType());
    assertEquals(0, object.intValue);
    assertNull(object.strValue);
  }

  @Test
  public void test_newInstance_className_2() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(8) };

    object = (DummyClass1)util.newInstance("org.pfsw.reflect.testhelper.DummyClass1", params);
    assertEquals(2, object.getType());
    assertEquals(8, object.intValue);
    assertNull(object.strValue);
  }

  @Test
  public void test_newInstance_className_3() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(126), "Kansas" };

    object = (DummyClass1)util.newInstance("org.pfsw.reflect.testhelper.DummyClass1", params);
    assertEquals(3, object.getType());
    assertEquals(126, object.intValue);
    assertEquals("Kansas", object.strValue);
    assertEquals(0.0f, object.floatValue, 0.0f);
    assertNull(object.collValue);
  }

  @Test
  public void test_newInstance_className_4() throws Exception
  {
    DummyClass1 object;
    Object[] params = { new Integer(77), new Float(1.62f) };

    object = (DummyClass1)util.newInstance("org.pfsw.reflect.testhelper.DummyClass1", params);
    assertEquals(4, object.getType());
    assertEquals(77, object.intValue);
    assertNull(object.strValue);
    assertEquals(1.62f, object.floatValue, 0.0f);
    assertNull(object.collValue);
  }

  @Test
  public void test_newInstance_className_5() throws Exception
  {
    DummyClass1 object;
    List list = new Vector();
    Object[] params = { new Integer(200), "Lynyrd Skynyrd", list };

    object = (DummyClass1)util.newInstance("org.pfsw.reflect.testhelper.DummyClass1", params);
    assertEquals(5, object.getType());
    assertEquals(200, object.intValue);
    assertEquals("Lynyrd Skynyrd", object.strValue);
    assertEquals(0.0f, object.floatValue, 0.0f);
    assertTrue(list == object.collValue);
  }

  @Test
  public void test_newInstance_className_6() throws Exception
  {
    Object object;
    List list = new Vector();
    Object[] params = { "Lynyrd Skynyrd", list, Boolean.FALSE };

    object = util.newInstance("org.pfsw.reflect.testhelper.DummyClass1", params);
    assertNull(object);
  }

  @Test
  public void test_newInstance_className_7() throws Exception
  {
    Object object;

    object = util.newInstance("org.pfsw.reflect.testhelper.Subclass2", "Doobie Brothers");
    assertNotNull(object);
    assertTrue(object instanceof Subclass2);
  }

  @Test
  public void test_newInstance_className_8() throws Exception
  {
    Object object;
    List list = new Vector();

    object = util.newInstance("org.pfsw.reflect.testhelper.Subclass2", "Doobie Brothers", list);
    assertNotNull(object);
    assertTrue(object instanceof Subclass2);
  }

  @Test
  public void test_newInstance_className_9() throws Exception
  {
    try
    {
      util.newInstance("org.pfsw.reflect.testhelper.Subclass2", "bad");
      fail("ReflectionException must have been thrown!");
    }
    catch (RuntimeException e)
    {
      assertTrue(e instanceof ReflectionException);
    }
  }

  @Test
  public void test_newInstance_class_1() throws Exception
  {
    Object object;

    object = util.newInstance(Subclass2.class, "Caesar");
    assertNotNull(object);
  }

  @Test
  public void test_newInstance_class_2() throws Exception
  {
    Object object;

    object = util.newInstance(Subclass2.class, "Caesar", new ArrayList());
    assertNotNull(object);
  }

  @Test
  public void test_hasPublicMethod_1() throws Exception
  {
    assertTrue(util.hasPublicMethod(Subclass2.class, "name"));
  }

  @Test
  public void test_hasPublicMethod_2() throws Exception
  {
    assertTrue(!util.hasPublicMethod(Subclass2.class, "noname"));
  }

  @Test
  public void test_hasPublicMethod_3() throws Exception
  {
    assertTrue(!util.hasPublicMethod(Subclass2.class, "dummy1", (Class[])null));
  }

  @Test
  public void test_hasPublicMethod_4() throws Exception
  {
    assertTrue(!util.hasPublicMethod(Subclass2.class, "dummy3"));
  }

  @Test
  public void test_hasPublicMethod_5() throws Exception
  {
    assertNotNull(util.findMethod(Subclass2.class, "dummy4", new Class[] { Long.TYPE }));
    assertTrue(!util.hasPublicMethod(Subclass2.class, "dummy4", new Class[] { Long.TYPE }));
  }

  @Test
  public void test_hasPublicMethod_6() throws Exception
  {
    assertTrue(!util.hasPublicMethod(Superclass.class, "dummy7", new Class[] { String.class }));
    assertTrue(util.hasPublicMethod(Subclass1.class, "dummy7", new Class[] { String.class }));
    assertTrue(util.hasPublicMethod(Subclass2.class, "dummy7", new Class[] { String.class }));
  }

  @Test
  public void test_hasPublicMethod_7() throws Exception
  {
    assertTrue(!util.hasPublicMethod(new Superclass(), "dummy7", new Class[] { String.class }));
    assertTrue(util.hasPublicMethod(new Subclass1(), "dummy7", new Class[] { String.class }));
    assertTrue(util.hasPublicMethod(new Subclass2(), "dummy7", new Class[] { String.class }));
  }

  @Test
  public void test_hasPublicMethod_8() throws Exception
  {
    assertTrue(!util.hasPublicMethod(null, "dummy7", new Class[] { String.class }));
    assertTrue(!util.hasPublicMethod(new Subclass1(), null, new Class[] { String.class }));
    assertTrue(!util.hasPublicMethod(new Subclass1(), "dummy7"));
  }

  @Test
  public void test_findClass_primitive_1()
  {
    assertTrue(Boolean.TYPE == util.findClass(Boolean.TYPE.toString()));
  }

  @Test
  public void test_findClass_primitive_2()
  {
    assertTrue(Integer.TYPE == util.findClass(Integer.TYPE.toString()));
  }

  @Test
  public void test_findClass_primitive_3()
  {
    assertTrue(Long.TYPE == util.findClass("long"));
  }

  @Test
  public void test_findClass_primitive_4()
  {
    assertTrue(Short.TYPE == util.findClass(Short.TYPE.toString()));
  }

  @Test
  public void test_findClass_primitive_5()
  {
    assertTrue(Byte.TYPE == util.findClass(Byte.TYPE.toString()));
  }

  @Test
  public void test_findClass_primitive_6()
  {
    assertTrue(Character.TYPE == util.findClass("char"));
  }

  @Test
  public void test_findClass_primitive_7()
  {
    assertTrue(Float.TYPE == util.findClass(Float.TYPE.toString()));
  }

  @Test
  public void test_findClass_primitive_8()
  {
    assertTrue(Double.TYPE == util.findClass(Double.TYPE.toString()));
  }

  @Test
  public void test_compatibleTypes_1()
  {
    Class[] types1 = { Integer.class, String.class };
    Class[] types2 = { Integer.class, String.class };

    assertTrue(util.compatibleTypes(types1, types2));
  }

  @Test
  public void test_compatibleTypes_2()
  {
    Class[] types1 = { Integer.class, String.class };
    Class[] types2 = null;

    assertFalse(util.compatibleTypes(types1, types2));
  }

  @Test
  public void test_compatibleTypes_3()
  {
    Class[] types1 = null;
    Class[] types2 = { Integer.class, String.class };

    assertFalse(util.compatibleTypes(types1, types2));
  }

  @Test
  public void test_compatibleTypes_4()
  {
    Class[] types1 = null;
    Class[] types2 = null;

    assertTrue(util.compatibleTypes(types1, types2));
  }

  @Test
  public void test_compatibleTypes_5()
  {
    Class[] types1 = {};
    Class[] types2 = {};

    assertTrue(util.compatibleTypes(types1, types2));
  }

  @Test
  public void test_implementsInterface_1()
  {
    Object object = new LinkedHashMap();

    assertFalse(util.implementsInterface(object, List.class));
    assertTrue(util.implementsInterface(object, Map.class));
    assertFalse(util.implementsInterface(object, RandomAccess.class));
    assertTrue(util.implementsInterface(object, Cloneable.class));
    assertTrue(util.implementsInterface(object, Serializable.class));
    assertFalse(util.implementsInterface(object, Comparable.class));
    assertFalse(util.implementsInterface(object, HashMap.class));
    assertFalse(util.implementsInterface(object, String.class));
    assertFalse(util.implementsInterface(object, null));
    assertFalse(util.implementsInterface(null, Map.class));
  }

  @Test
  public void test_implementsInterface_2()
  {
    Object object;
    InvocationHandler handler;

    handler = new InvocationHandler()
    {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
      {
        return null;
      }
    };
    object = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { IGalacticalCollection.class }, handler);

    assertTrue(util.implementsInterface(object, IGalacticalCollection.class));
    assertTrue(util.implementsInterface(object, Collection.class));
    assertTrue(util.implementsInterface(object, List.class));
    assertTrue(util.implementsInterface(object, Set.class));
    assertTrue(util.implementsInterface(object, Serializable.class));
    assertTrue(util.implementsInterface(object, Comparable.class));
    assertFalse(util.implementsInterface(object, Cloneable.class));
    assertFalse(util.implementsInterface(object, Map.class));
    assertFalse(util.implementsInterface(object, RandomAccess.class));
    assertFalse(util.implementsInterface(object, HashMap.class));
    assertFalse(util.implementsInterface(object, String.class));
    assertFalse(util.implementsInterface(object, null));
    assertFalse(util.implementsInterface(null, Map.class));
  }

  @Test
  public void test_implementsInterface_class_1()
  {
    assertTrue(util.implementsInterface(Vector.class, List.class));
    assertTrue(util.implementsInterface(Vector.class, Collection.class));
    assertTrue(util.implementsInterface(Vector.class, RandomAccess.class));
    assertTrue(util.implementsInterface(Vector.class, Cloneable.class));
    assertTrue(util.implementsInterface(Vector.class, Serializable.class));
    assertFalse(util.implementsInterface(Vector.class, Comparable.class));
  }

  @Test
  public void test_toStringArray_1()
  {
    ArrayList list = new ArrayList();
    String[] names;

    list.add("Jake");
    list.add("Elliot");
    list.add("Sam");
    names = util.toStringArray(list, "this");
    assertEquals(3, names.length);
    assertEquals("Jake", names[0]);
    assertEquals("Elliot", names[1]);
    assertEquals("Sam", names[2]);
  }

  @Test
  public void test_toStringArray_2()
  {
    ArrayList list = new ArrayList();
    String[] names;

    list.add(Map.class);
    list.add(String.class);
    list.add(JarEntry.class);
    names = util.toStringArray(list, "getName");
    assertEquals(3, names.length);
    assertEquals("java.util.Map", names[0]);
    assertEquals("java.lang.String", names[1]);
    assertEquals("java.util.jar.JarEntry", names[2]);
  }

  @Test
  public void test_toStringArray_null()
  {
    String[] names;

    names = util.toStringArray(null, "getName");
    assertNull(names);
  }

  @Test
  public void test_createInstanceOf_null()
  {
    Object object;

    object = util.createInstanceOf(null, null);
    assertNull(object);
  }

  @Test
  public void test_createInstanceOf_unoknon_class()
  {
    Object object;

    object = util.createInstanceOf("com.unknown.company.UnoknownClass", this);
    assertNull(object);
  }

  @Test
  public void test_createInstanceOf_no_empty_constructor()
  {
    Object object;

    object = util.createInstanceOf("java.text.ChoiceFormat", null, "X");
    assertNotNull(object);
    object = util.createInstanceOf("java.text.ChoiceFormat", null);
    assertNull(object);
  }

  @Test
  public void test_createInstanceOf_with_no_argument()
  {
    Object object;

    object = util.createInstanceOf("java.lang.String", this);
    assertEquals("", object);
  }

  @Test
  public void test_createInstanceOf_with_one_argument()
  {
    Object object;

    object = util.createInstanceOf("java.lang.String", this, "Fred");
    assertEquals("Fred", object);
  }

  @Test
  public void test_getAnnotationValueFrom_PersonData()
  {
    String name;

    name = util.getAnnotationValueFrom(PersonData.class, ExternalTypeId.class);
    assertEquals("person", name);
  }

  @Test
  public void test_getAnnotationValueFrom_null()
  {
    String name;

    name = util.getAnnotationValueFrom(PersonData.class, Override.class);
    assertNull(name);
  }

  @Test
  public void test_findClasses_null() throws ClassNotFoundException
  {
    Class[] classes;

    classes = util.findClasses((String[])null);
    assertEquals(0, classes.length);
  }

  @Test
  public void test_findClasses_empty() throws ClassNotFoundException
  {
    Class[] classes;

    classes = util.findClasses();
    assertEquals(0, classes.length);
  }

  @Test
  public void test_findClasses_some() throws ClassNotFoundException
  {
    Class[] classes;

    classes = util.findClasses("org.pfsw.reflect.testhelper.PersonData", "java.lang.Override", "java.util.Map", "java.lang.Integer");
    assertEquals(4, classes.length);
    assertEquals(PersonData.class, classes[0]);
    assertEquals(Override.class, classes[1]);
    assertEquals(Map.class, classes[2]);
    assertEquals(Integer.class, classes[3]);
  }

  @SuppressWarnings("unused")
  @Test
  public void test_findClasses_unknown_class()
  {
    Class[] classes;

    try
    {
      classes = util.findClasses("org.pfsw.reflect.testhelper.PersonData", "com.java.language.Unknown", "java.util.Map");
      fail("Expected ClassNotFoundException!");
    }
    catch (ClassNotFoundException ex)
    {
      assertEquals("com.java.language.Unknown", ex.getMessage());
    }
  }

  @Test
  public void test_getAllTypesOf_1()
  {
    Collection<Class> types;

    types = util.getAllTypesOf(new Vector());
    assertEquals(7, types.size());
    assertTrue(types.contains(Vector.class));
    assertTrue(types.contains(Collection.class));
    assertTrue(types.contains(List.class));
    assertTrue(types.contains(RandomAccess.class));
    assertTrue(types.contains(Cloneable.class));
    assertTrue(types.contains(Serializable.class));
    assertTrue(types.contains(Iterable.class));
  }

  @Test
  public void test_getObjectProperties_of_object()
  {
    List<IObjectProperty> objectProperties;
    IObjectProperty property;

    objectProperties = util.getObjectPropertiesOf(new PersonData());
    assertTrue(objectProperties.size() >= 5); // Code coverage is weaving extra attributes into the class!
    property = objectProperties.get(0);
    assertEquals("firstName", property.getName());
    assertEquals(String.class, property.getType());
    assertTrue(property.getModifiers().isPrivate());
    assertFalse(property.getModifiers().isStatic());
    assertFalse(property.getModifiers().isFinal());
  }

  @Test
  public void test_getObjectProperties_of_object_filtered()
  {
    List<IObjectProperty> objectProperties;
    IObjectProperty property;
    IObjectPropertyFilter filter;

    filter = new IObjectPropertyFilter()
    {
      @Override
      public boolean matches(IObjectProperty objectProperty)
      {
        return !objectProperty.getModifiers().isPublic();
      }
    };

    objectProperties = util.getObjectPropertiesOf(new DummyClass1(100), filter);
    assertTrue(objectProperties.size() >= 1); // Code coverage is weaving extra attributes into the class!
    assertTrue(objectProperties.size() <= 2); // Code coverage is weaving extra attributes into the class!
    property = objectProperties.get(0);
    assertEquals("type", property.getName());
    assertEquals(Integer.TYPE, property.getType());
  }

  @Test
  public void test_getObjectProperties_of_class()
  {
    List<IObjectProperty> objectProperties;
    IObjectProperty property;

    objectProperties = util.getObjectPropertiesOf(DummyClass1.class);
    assertTrue(objectProperties.size() >= 6); // Code coverage is weaving extra attributes into the class!
    property = objectProperties.get(0);
    assertEquals("label", property.getName());
    assertEquals(String.class, property.getType());
    assertTrue(property.getModifiers().isPublic());
    assertTrue(property.getModifiers().isStatic());
    assertFalse(property.getModifiers().isFinal());

    property = objectProperties.get(1);
    assertEquals("type", property.getName());
    assertEquals(Integer.TYPE, property.getType());
    assertTrue(property.getModifiers().isPrivate());
    assertFalse(property.getModifiers().isStatic());
    assertFalse(property.getModifiers().isFinal());
  }

  @Test
  public void test_getObjectProperties_of_class_filtered()
  {
    List<IObjectProperty> objectProperties;
    IObjectProperty property;
    IObjectPropertyFilter filter;

    filter = new IObjectPropertyFilter()
    {
      @Override
      public boolean matches(IObjectProperty objectProperty)
      {
        return !objectProperty.getType().isPrimitive();
      }
    };

    objectProperties = util.getObjectPropertiesOf(DummyClass1.class, filter);
    assertTrue(objectProperties.size() >= 3); // Code coverage is weaving extra attributes into the class!
    assertTrue(objectProperties.size() <= 4); // Code coverage is weaving extra attributes into the class!
    property = objectProperties.get(0);
    assertEquals("label", property.getName());
    assertEquals(String.class, property.getType());

    property = objectProperties.get(1);
    assertEquals("strValue", property.getName());
    assertEquals(String.class, property.getType());

    property = objectProperties.get(2);
    assertEquals("collValue", property.getName());
    assertEquals(Collection.class, property.getType());
  }
  
  @Test
  public void test_getEnumValueOf_null_name() 
  {
    assertNull(util.getEnumValueOf(sampleEnumType, null));
  }

  @Test(expected=ReflectionException.class)
  public void test_getEnumValueOf_no_enum_type() 
  {
    util.getEnumValueOf(ObjectField.class, "dummy");
  }
  
  @Test
  public void test_getEnumValueOf_name_not_found() 
  {
    assertNull(util.getEnumValueOf(sampleEnumType, "FTPS"));
  }
  
  @Test
  public void test_getEnumValueOf_name_found() 
  {
    assertTrue(Type.DIRECT == util.getEnumValueOf(sampleEnumType, "DIRECT"));
    assertTrue(Type.HTTP == util.getEnumValueOf(sampleEnumType, "HTTP"));
    assertTrue(Type.SOCKS == util.getEnumValueOf(sampleEnumType, "SOCKS"));
  }
  
  // =========================================================================
  // HELPER METHODS
  // =========================================================================
  protected void assertModifier(int expected, int modifiers)
  {
    assertEquals((modifiers & expected), expected);
  }

  protected boolean containsField(List fieldList, String name)
  {
    Iterator iter;
    Field field;

    iter = fieldList.iterator();
    while (iter.hasNext())
    {
      field = (Field)iter.next();
      if (name.equals(field.getName()))
      {
        return true;
      }
    }
    return false;
  }

  protected boolean containsMethod(List methodList, String name)
  {
    Iterator iter;
    Method method;

    iter = methodList.iterator();
    while (iter.hasNext())
    {
      method = (Method)iter.next();
      if (name.equals(method.getName()))
        return true;
    }
    return false;
  }

}

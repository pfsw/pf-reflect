// ===========================================================================
// CONTENT  : TEST CLASS DynamicTest
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.1 - 03/07/2004
// HISTORY  :
//  28/09/2002  duma  CREATED
//	03/07/2004	mdu		changed	-->	Renamed all methods; added tests for superclass types 
//
// Copyright (c) 2002-2004, Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JSeparator;

import org.junit.Before;
import org.junit.Test;
import org.pfsw.reflect.testhelper.DummyClass1;
import org.pfsw.reflect.testhelper.Subclass2;

/**
 * Test class for corresponding business class.
 *
 * @author Manfred Duchrow
 * @version 1.2
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DynamicTest
{
  @Before
  public void setUp()
  {
    this.concatStrings("Just", "to call it");
  } 

  // =========================================================================
  // TEST METHODS
  // =========================================================================
  @Test
  public void test_perform_1() throws Exception
  {
    String msg = "The red fox is tired";
    Object result;
    Integer[] args = { new Integer(4), new Integer(7) };

    result = Dynamic.perform(msg, "substring", args);
    assertTrue(result instanceof String);
    assertEquals("red", result);
  } // test_perform_1() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_2() throws Exception
  {
    Vector vector = new Vector();
    Object result;

    vector.add("Test");
    vector.add(Boolean.FALSE);
    vector.add(new Integer(4));

    result = Dynamic.perform(vector, "size");
    assertTrue(result instanceof Integer);
    assertEquals(3, ((Integer)result).intValue());
  } // test_perform_2() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_3()
  {
    String str = "The fox is dead.";

    try
    {
      assertEquals(new Integer(16), Dynamic.perform(str, "length"));
    }
    catch (Exception ex)
    {
      fail("Perform 'length()' caused exception: " + ex.toString());
    }
  } // test_perform_3() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_4()
  {
    try
    {
      assertTrue(((Boolean)Dynamic.perform("Design", "startsWith", "Des")).booleanValue());
    }
    catch (Exception ex)
    {
      fail("Perform 'startsWith()' caused exception: " + ex.toString());
    }
  } 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_5() throws Exception
  {
    JMenu menu;
    JSeparator separator;

    menu = new JMenu();
    separator = new JSeparator();
    Dynamic.perform(menu, "add", separator);
  } // test_perform_5() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_6() throws Exception
  {
    DummyClass1 receiver;

    receiver = new DummyClass1(6);
    try
    {
      Dynamic.perform(receiver, "calc", new Object[] { new Integer(3), Boolean.TRUE }, new Class[] { Integer.TYPE, Boolean.TYPE });
      fail("Exception expected!");
    }
    catch (Exception e)
    {
      // Works as expected
      assertEquals("No such method: org.pfsw.reflect.testhelper.DummyClass1.calc(int,boolean)", e.getMessage());
    }
  } // test_perform_6() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_7() throws Exception
  {
    DummyClass1 receiver;

    receiver = new DummyClass1(2);
    try
    {
      Dynamic.perform(receiver, "calc", null, (Class[])null);
      fail("Exception expected!");
    }
    catch (Exception e)
    {
      // Works as expected
      assertEquals("No such method: org.pfsw.reflect.testhelper.DummyClass1.calc()", e.getMessage());
    }
  } // test_perform_7() 

  // -------------------------------------------------------------------------

  @Test
  public void test_performPrim()
  {
    String str = "The fox is dead.";
    Object[] args = new Integer[2];

    args[0] = new Integer(4);
    args[1] = new Integer(7);
    try
    {
      assertEquals("fox", Dynamic.perform(str, "substring", args));
    }
    catch (Exception ex)
    {
      fail("Perform 'substring()' caused exception: " + ex.toString());
    }
  }  

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_1()
  {
    String str = "Microsoft";
    Object[] args = { new Character('o'), new Character('i') };

    assertEquals("Micrisift", Dynamic.invoke(str, "replace", args));
  } // test_invoke_1() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_2()
  {
    assertEquals("java.lang.String", Dynamic.invoke(String.class, "getName"));
  } // test_invoke_2() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_3()
  {
    Object result = null;

    result = Dynamic.invoke(this, "getProgrammingLanguage");
    assertEquals("Smalltalk", result);
  } // test_invoke_3() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_4()
  {
    Object result = null;
    Object[] args = new String[2];

    args[0] = "Sitting ";
    args[1] = "Bull";

    result = Dynamic.invoke(this, "concatStrings", args);
    assertEquals("Sitting Bull", result);
  } // test_invoke_4() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_5() throws Exception
  {
    Object result = null;
    Object[] args = new String[2];
    String methodName = "concatStrings";
    Class aClass = this.getClass();
    Class[] argTypes = { String.class, String.class };
    Method method = Dynamic.findMethod(aClass, methodName, argTypes);

    assertTrue(!method.isAccessible());

    args[0] = null;
    args[1] = "Bull";
    result = Dynamic.invoke(this, methodName, args); // Must throw an exception internally (NPE)
    assertTrue(result instanceof NullPointerException);
    assertTrue(!method.isAccessible());
  } // test_invoke_5() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_6() throws Exception
  {
    Object result = null;
    Method method;
    Subclass2 receiver;

    receiver = new Subclass2();
    method = ReflectUtil.current().getMethod(receiver, "dummy7", new Class[] { String.class });
    result = Dynamic.invoke(receiver, method, new Object[] { "Test" });
    assertEquals("AAA: Test", result);
  } // test_invoke_6() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_7() throws Exception
  {
    Object result = null;
    DummyClass1 receiver;

    receiver = new DummyClass1(6);
    result = Dynamic.invoke(receiver, "changeType", new Object[] { new Integer(3), Boolean.TRUE }, new Class[] { Integer.TYPE, Boolean.TYPE });
    assertEquals(9, ((Integer)result).intValue());
    result = Dynamic.invoke(receiver, "changeType", new Object[] { new Integer(2), Boolean.FALSE }, new Class[] { Integer.TYPE, Boolean.TYPE });
    assertEquals(4, ((Integer)result).intValue());
  } // test_invoke_7() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_8() throws Exception
  {
    Character ch;

    ch = (Character)Dynamic.invoke("Madness", "charAt", 3);
    assertEquals(new Character('n'), ch);
  } // test_invoke_8() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_9() throws Exception
  {
    Object result;

    result = Dynamic.invoke("Madness", "charAt", 53);
    assertTrue(result instanceof Exception);
  } // test_invoke_9() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_10() throws Exception
  {
    Object result;

    result = Dynamic.invoke("Madness", "charAt", true);
    assertTrue(result instanceof Exception);
  } // test_invoke_10() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_11() throws Exception
  {
    Object result;

    result = Dynamic.invoke("Madness", "size");
    assertTrue(result instanceof Exception);
  } // test_invoke_11() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke_12() throws Exception
  {
    Object result;

    result = Dynamic.invoke("Madness", "charAt", new ArrayList());
    assertTrue(result instanceof Exception);
  } // test_invoke_12() 

  // -------------------------------------------------------------------------

  @Test
  public void test_invoke__with_varargs_and_not_exact_types() throws Exception
  {
    Object result = null;
    Object[] args = new Object[2];
    String methodName = "concatStrings";
    Class aClass = this.getClass();
    Class[] argTypes = { Collection.class, String[].class };
    Method method = Dynamic.findMethod(aClass, methodName, argTypes);

    assertTrue(!method.isAccessible());

    args[0] = new ArrayList();
    ((ArrayList)args[0]).add("Sitting");
    ((ArrayList)args[0]).add("Bull");
    args[1] = new String[] { "is", "chief" };
    result = Dynamic.invoke(this, methodName, args);
    assertEquals("Sitting Bull is chief", result);
  } // test_invoke__with_varargs_and_not_exact_types() 

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_with_not_exact_types() throws Exception
  {
    DummyClass1 receiver = new DummyClass1(2);
    Object[] args = new Object[2];
    String result;

    args[0] = new Subclass2();
    args[1] = 5;
    result = (String)Dynamic.perform(receiver, "process", args);
    assertEquals("Subclass2_5", result);
  } // test_invoke_with_not_exact_types()

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_ValidSignature() throws Exception
  {
    String msg = "The red fox is tired";
    Object result;
    String[] args = { "tired", "asleep" };
    String[] signature = { "java.lang.CharSequence", "java.lang.CharSequence" };
    result = Dynamic.perform(msg, "replace", args, signature);
    assertTrue(result instanceof String);
    assertEquals("The red fox is asleep", result);
  } // test_perform_ValidSignature()

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_InalidSignature() throws Exception
  {
    String msg = "The red fox is tired";
    String[] args = { "tired", "asleep" };
    String[] signature = { "java.lang.CharSequence", "java.lang.Integer" };
    try
    {
      Dynamic.perform(msg, "replace", args, signature);
      fail("Exception expected");
    }
    catch (Exception e)
    {
      // Nothing more to do
    }
  } // test_perform_InalidSignature()

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_UnknownClassInSignature()
  {
    String msg = "The red fox is tired";
    String[] args = { "tired", "asleep" };
    String[] signature = { "java.lang.CharSequence", "foo.bar" };
    try
    {
      Dynamic.perform(msg, "replace", args, signature);
      fail("ClassNotFoundException expected");
    }
    catch (Exception e)
    {
      assertTrue(e instanceof ClassNotFoundException);
    }
  } // test_perform_UnknownClassInSignature()

  // -------------------------------------------------------------------------

  @Test
  public void test_perform_EmptySignature() throws Exception
  {
    String msg = "The red fox is tired";
    String[] args = {};
    String[] signature = {};
    Object result = Dynamic.perform(msg, "length", args, signature);
    assertEquals(msg.length(), result);
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  protected String getProgrammingLanguage()
  {
    return "Smalltalk";
  } 

  // =========================================================================
  // PRIVATE INSTANCE METHODS
  // =========================================================================

  // This dummy method must be here because it is used in a test
  private String concatStrings(String str1, String str2)
  {
    return (str1 + str2);
  } // concatStrings() 

  // -------------------------------------------------------------------------

  // This dummy method must be here because it is used in a test
  protected String concatStrings(Collection<String> strings1, String... strings2)
  {
    String result = "";

    for (String string : strings1)
    {
      result = result + string + " ";
    }
    for (String string : strings2)
    {
      result = result + string + " ";
    }
    return result.trim();
  } 

} 

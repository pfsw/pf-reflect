package org.pfsw.reflect;

import static org.junit.Assert.*;
import static org.pfsw.reflect.testhelper.UnitTestHelper.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.pfsw.reflect.testhelper.PersonData;

public class BigDecimalFieldProxyTest
{
  private static final BigDecimal BASE_VALUE = new BigDecimal("320.00");
  
  @Test
  public void test_inc()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.inc();
    assertEquals(new BigDecimal("321.00"), cash.get());
    cash.inc();
    assertEquals(new BigDecimal("322.00"), cash.get());
    assertEquals(new BigDecimal("322.00"), person.getCash());
  }

  @Test
  public void test_dec()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.dec();
    assertEquals(new BigDecimal("319.00"), cash.get());
    cash.dec();
    assertEquals(new BigDecimal("318.00"), cash.get());
    assertEquals(new BigDecimal("318.00"), person.getCash());
  }
  
  @Test
  public void test_add()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.add(new BigDecimal("15.40"));
    assertEquals(new BigDecimal("335.40"), cash.get());
    assertEquals(new BigDecimal("335.40"), person.getCash());
  }
  
  @Test
  public void test_subtract()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.subtract(new BigDecimal("8.75"));
    assertEquals(new BigDecimal("311.25"), cash.get());
    assertEquals(new BigDecimal("311.25"), person.getCash());
  }
  
  @Test
  public void test_multiply()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.multiply(new BigDecimal("3"));
    assertEquals(new BigDecimal("960.00"), cash.get());
    assertEquals(new BigDecimal("960.00"), person.getCash());
  }
  
  @Test
  public void test_divide()
  {
    PersonData person = createPerson1();
    BigDecimalFieldProxy cash = new BigDecimalFieldProxy(person, "cash");
    
    assertEquals(BASE_VALUE, cash.get());
    cash.divide(new BigDecimal(5L));
    assertEquals(new BigDecimal("64.00"), cash.get());
    assertEquals(new BigDecimal("64.00"), person.getCash());
  }
}
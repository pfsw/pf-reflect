package org.pfsw.reflect;

import static org.junit.Assert.*;
import static org.pfsw.reflect.testhelper.UnitTestHelper.*;

import org.junit.Test;
import org.pfsw.reflect.testhelper.PersonData;

public class IntegerFieldProxyTest
{
  @Test
  public void test_inc()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    assertEquals(12345, zipCode.get().intValue());
    zipCode.inc();
    assertEquals(12346, zipCode.get().intValue());
    zipCode.inc();
    assertEquals(12347, zipCode.get().intValue());
    assertEquals(12347, person.getZipCode());
  }

  @Test
  public void test_dec()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    assertEquals(12345, zipCode.get().intValue());
    zipCode.dec();
    assertEquals(12344, zipCode.get().intValue());
    zipCode.dec();
    assertEquals(12343, zipCode.get().intValue());
    assertEquals(12343, person.getZipCode());
  }
  
  @Test
  public void test_add()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    assertEquals(12345, zipCode.get().intValue());
    zipCode.add(15);
    assertEquals(12360, zipCode.get().intValue());
    assertEquals(12360, person.getZipCode());
  }
  
  @Test
  public void test_subtract()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    assertEquals(12345, zipCode.get().intValue());
    zipCode.subtract(12340);
    assertEquals(5, zipCode.get().intValue());
    assertEquals(5, person.getZipCode());
  }
  
  @Test
  public void test_multiply()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    person.setZipCode(5);
    
    assertEquals(5, zipCode.get().intValue());
    zipCode.multiply(3);
    assertEquals(15, zipCode.get().intValue());
    assertEquals(15, person.getZipCode());
  }
  
  @Test
  public void test_divide()
  {
    PersonData person = createPerson1();
    IntegerFieldProxy zipCode = new IntegerFieldProxy(person, "zipCode");
    
    person.setZipCode(20);
    
    assertEquals(20, zipCode.get().intValue());
    zipCode.divide(5);
    assertEquals(4, zipCode.get().intValue());
    assertEquals(4, person.getZipCode());
  }
}
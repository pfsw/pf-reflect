package org.pfsw.reflect;

import static org.junit.Assert.*;
import static org.pfsw.reflect.testhelper.UnitTestHelper.*;

import org.junit.Test;
import org.pfsw.reflect.testhelper.PersonData;

public class FieldProxyTest
{
  @Test
  public void test_get_set_int()
  {
    PersonData person = createPerson1();
    FieldProxy<Integer> zipCode = new FieldProxy<Integer>(person, "zipCode");
    
    assertEquals(12345, zipCode.get().intValue());
    zipCode.set(6789);
    assertEquals(6789, person.getZipCode());
  }

  @Test
  public void test_get_set_Integer()
  {
    PersonData person = createPerson1();
    FieldProxy<Integer> vatId = new FieldProxy<Integer>(person, "vatId");
    
    assertEquals(678, vatId.get().intValue());
    vatId.set(Integer.valueOf(7777));
    assertEquals(Integer.valueOf(7777), vatId.get());
    assertEquals(Integer.valueOf(7777), person.getVatId());
  }
  
  @Test
  public void test_get_set_String()
  {
    PersonData person = createPerson1();
    FieldProxy<String> lastName = new FieldProxy<String>(person, "lastName");
    
    assertEquals("Poppins", lastName.get());
    lastName.set("Higgins");
    assertEquals("Higgins", lastName.get());
    assertEquals("Higgins", person.getLastName());
  }

  @Test
  public void test_isNull()
  {
    PersonData person = createPerson1();
    IValueHolder<String> lastName = new FieldProxy<String>(person, "lastName");
    
    assertFalse(lastName.isNull());
    lastName.set(null);
    assertTrue(lastName.isNull());
    assertNull(person.getLastName());
  }
  
  @Test
  public void test_isPresent()
  {
    PersonData person = createPerson1();
    IValueHolder<Integer> zipCode = new FieldProxy<Integer>(person, "zipCode");
    IValueHolder<String> lastName = new FieldProxy<String>(person, "lastName");
    
    assertTrue(zipCode.isPresent());
    assertTrue(lastName.isPresent());
    
    zipCode.set(null);
    lastName.set(null);

    assertTrue(zipCode.isPresent());
    assertFalse(lastName.isPresent());
    assertEquals(12345, person.getZipCode());
    assertNull(person.getLastName());
  }
}
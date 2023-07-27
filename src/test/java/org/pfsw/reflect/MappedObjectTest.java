package org.pfsw.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.pfsw.reflect.testhelper.UnitTestHelper.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.pfsw.reflect.testhelper.PersonData;

public class MappedObjectTest
{
  @Test
  public void test_get()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertEquals("Mary", mappedObject.get("firstName"));
    assertEquals("Poppins", mappedObject.get("lastName"));
    assertEquals(Boolean.TRUE, mappedObject.get("female"));
    assertEquals(new BigDecimal("320.00"), mappedObject.get("cash"));
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void test_get_bad_key()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertNull(mappedObject.get(new Date()));
  }
  
  @SuppressWarnings("unlikely-arg-type")
  @Test(expected=ReflectionException.class)
  public void test_get_bad_key_exception()
  {
    MappedObject mappedObject = new MappedObject(createPerson1(), false);
    
    mappedObject.get(new Date());
  }
  
  @Test
  public void test_putAll()
  {
    PersonData person = createPerson1();
    MappedObject mappedObject = new MappedObject(person);
    Map<String, Object> map = new HashMap<String, Object>();
    
    map.put("firstName", "Joe");
    map.put("lastName", "Cocker");
    map.put("female", false);
    mappedObject.putAll(map);
    
    assertEquals("Joe", person.getFirstName());
    assertEquals("Cocker", mappedObject.get("lastName"));
    assertFalse(person.isFemale());
  }
  
  @Test
  public void test_clear()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    mappedObject.clear();
    assertEquals(new BigDecimal("320.00"), mappedObject.get("cash"));
    assertEquals(Integer.valueOf(678), mappedObject.get("vatId"));
    assertEquals("Poppins", mappedObject.get("lastName"));
  }
  
  @Test
  public void test_remove()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertEquals(new BigDecimal("320.00"), mappedObject.remove("cash"));
    assertEquals(Integer.valueOf(678), mappedObject.remove("vatId"));
    assertEquals("Poppins", mappedObject.remove("lastName"));
    
    assertEquals(new BigDecimal("320.00"), mappedObject.get("cash"));
    assertEquals(Integer.valueOf(678), mappedObject.get("vatId"));
    assertEquals("Poppins", mappedObject.get("lastName"));
  }
  
  @Test
  public void test_containsKey_true()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertTrue(mappedObject.containsKey("cash"));
    assertTrue(mappedObject.containsKey("vatId"));
    assertTrue(mappedObject.containsKey("lastName"));
  }
  
  @Test
  public void test_containsKey_false()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertFalse(mappedObject.containsKey("summer"));
    assertFalse(mappedObject.containsKey(new Object()));
    assertFalse(mappedObject.containsKey(null));
  }
  
  @Test
  public void test_containsValue_true()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertTrue(mappedObject.containsValue("Mary"));
    assertTrue(mappedObject.containsValue(Integer.valueOf(678)));
    assertTrue(mappedObject.containsValue(100L));
  }
  
  @Test
  public void test_containsvalue_false()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertFalse(mappedObject.containsValue("Peter"));
    assertFalse(mappedObject.containsValue(new Object()));
    assertFalse(mappedObject.containsValue(null));
    assertFalse(mappedObject.containsValue(false));
    assertFalse(mappedObject.containsValue(Integer.valueOf(55)));
  }
  
  @Test
  public void test_put_unknown_field_silent()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertNull(mappedObject.put("unknown", "1984"));
  }
  
  @Test(expected=ReflectionException.class)
  public void test_put_unknown_field_exception()
  {
    MappedObject mappedObject = new MappedObject(createPerson1(), false);
    
    mappedObject.put("unknown", "1984");
  }
  
  @Test
  public void test_size_0()
  {
    MappedObject mappedObject;
    
    mappedObject = new MappedObject(new Object());
    assertThat(mappedObject.size(), is(0));
    assertTrue(mappedObject.isEmpty());
    
    mappedObject = new MappedObject(null);
    assertThat(mappedObject.size(), is(0));
    assertTrue(mappedObject.isEmpty());
  }
  
  @Test
  public void test_keySet()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertThat(mappedObject.keySet(), hasItems("female", "firstName", "lastName", "zipCode", "vatId", "cash", "numberOfChildren", "transactions"));
  }
  
  @Test
  public void test_isEmpty()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertFalse(mappedObject.isEmpty());
  }
  
  @Test
  public void test_getObjectType_null()
  {
    MappedObject mappedObject = new MappedObject(null);
    
    assertEquals(Void.TYPE, mappedObject.getObjectType());
  }
  
  @Test
  public void test_getObjectType()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertEquals(PersonData.class, mappedObject.getObjectType());
  }
  
  @Test
  public void test_get_unknown_field_silently()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    
    assertNull(mappedObject.get("unknown"));
  }
  
  @Test(expected=ReflectionException.class)
  public void test_get_unknown_field_exception()
  {
    MappedObject mappedObject = new MappedObject(createPerson1(), false);
    
    mappedObject.get("unknown");
  }
  
  @Test
  public void test_entrySet()
  {
    MappedObject mappedObject = new MappedObject(createPerson1());
    int counter = 0;
    
    for (Entry<String, Object> entry : mappedObject.entrySet())
    {
      if ("firstName".equals(entry.getKey()))
      {
        assertEquals("Mary", entry.getValue());
        counter++;
      }
      else if ("transactions".equals(entry.getKey()))
      {
        assertEquals(Long.valueOf(100L), entry.getValue());
        counter++;
      }
      else if ("female".equals(entry.getKey()))
      {
        assertEquals(Boolean.TRUE, entry.getValue());
        counter++;
      }
      else if ("numberOfChildren".equals(entry.getKey()))
      {
        assertEquals(Byte.valueOf((byte)2), entry.getValue());
        counter++;
      }
    }
    assertEquals(4, counter);
  }
}
package org.pfsw.reflect;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapEntryTest
{
  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void test_hashCode_equals()
  {
    MapEntry<String, Integer> entry1 = new MapEntry<String, Integer>("alpha", 25);
    MapEntry<String, Integer> entry2 = new MapEntry<String, Integer>("alpha", 25);
    MapEntry<String, Integer> entry3 = new MapEntry<String, Integer>("beta", 25);
    MapEntry<String, Integer> entry4 = new MapEntry<String, Integer>("alpha", 11);
    MapEntry<String, Integer> entry5 = new MapEntry<String, Integer>(null, 25);
    MapEntry<String, Integer> entry6 = new MapEntry<String, Integer>("alpha", null);
    String string1 = "alpha";
    
    assertTrue(entry1.hashCode() == entry2.hashCode());
    assertTrue(entry1.equals(entry2));
    
    assertFalse(entry1.hashCode() == entry3.hashCode());
    assertFalse(entry1.equals(entry3));
    
    assertFalse(entry1.hashCode() == entry4.hashCode());
    assertFalse(entry1.equals(entry4));
    
    assertFalse(entry5.hashCode() == entry1.hashCode());
    assertFalse(entry5.equals(entry1));
    
    assertFalse(entry6.hashCode() == entry1.hashCode());
    assertFalse(entry6.equals(entry1));
    
    assertFalse(entry1.hashCode() == string1.hashCode());
    assertFalse(entry1.equals(string1));
  }

  @Test
  public void test_toString()
  {
    MapEntry<String, Integer> entry1 = new MapEntry<String, Integer>("alpha", 25);
    
    assertEquals("MapEntry('alpha' : 25)", entry1.toString());
  }
}
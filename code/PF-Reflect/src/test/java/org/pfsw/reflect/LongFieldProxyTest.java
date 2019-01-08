package org.pfsw.reflect;

import static org.junit.Assert.*;
import static org.pfsw.reflect.testhelper.UnitTestHelper.*;

import org.junit.Test;
import org.pfsw.reflect.testhelper.PersonData;

public class LongFieldProxyTest
{
  @Test
  public void test_inc()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    assertEquals(100L, transactions.get().longValue());
    transactions.inc();
    assertEquals(101L, transactions.get().longValue());
    transactions.inc();
    assertEquals(102L, transactions.get().longValue());
    assertEquals(102L, person.getTransactions());
  }

  @Test
  public void test_dec()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    assertEquals(100L, transactions.get().longValue());
    transactions.dec();
    assertEquals(99L, transactions.get().longValue());
    transactions.dec();
    assertEquals(98L, transactions.get().longValue());
    assertEquals(98L, person.getTransactions());
  }
  
  @Test
  public void test_add()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    assertEquals(100L, transactions.get().longValue());
    transactions.add(15L);
    assertEquals(115L, transactions.get().longValue());
    assertEquals(115L, person.getTransactions());
  }
  
  @Test
  public void test_subtract()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    assertEquals(100L, transactions.get().longValue());
    transactions.subtract(30L);
    assertEquals(70L, transactions.get().longValue());
    assertEquals(70L, person.getTransactions());
  }
  
  @Test
  public void test_multiply()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    person.setTransactions(5L);
    
    assertEquals(5L, transactions.get().longValue());
    transactions.multiply(3L);
    assertEquals(15L, transactions.get().longValue());
    assertEquals(15L, person.getTransactions());
  }
  
  @Test
  public void test_divide()
  {
    PersonData person = createPerson1();
    LongFieldProxy transactions = new LongFieldProxy(person, "transactions");
    
    person.setTransactions(20L);
    
    assertEquals(20L, transactions.get().longValue());
    transactions.divide(5L);
    assertEquals(4L, transactions.get().longValue());
    assertEquals(4L, person.getTransactions());
  }
}
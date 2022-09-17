// ===========================================================================
// CONTENT  : CLASS NumericFieldProxy
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 08/01/2019
// HISTORY  :
//  08/01/2019  mdu  CREATED
//
// Copyright (c) 2019, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Field;

/**
 * Provides an abstract field proxy for numeric types which offers some
 * extra convenience methods for basic calculation.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public abstract class NumericFieldProxy<T> extends FieldProxy<T>
{
  protected NumericFieldProxy(Object owner, Field field)
  {
    super(owner, field);
  }

  protected  NumericFieldProxy(Object owner, String fieldName)
  {
    super(owner, fieldName);
  }

  /**
   * Increments this field's value by 1, stores the result in the field and returns it.
   */
  public T inc() 
  {
    return add(one());
  }
  
  /**
   * Decrements this field's value by 1, stores the result in the field and returns it.
   */
  public T dec() 
  {
    return subtract(one());
  }
  
  /**
   * Subtracts the given value from this field's value, stores the result in the field and returns it.
   */
  public T subtract(T value) 
  {
    return add(negate(value));
  }
  
  /**
   * Adds the given value to this field's value and returns the result.
   */
  public abstract T add(T value); 

  /**
   * Multiplies this field's value with the given value, stores the result 
   * in the field and returns the result.
   */
  public abstract T multiply(T value); 
  
  /**
   * Divides this field's value by the given value, stores the result 
   * in the field and returns the result.
   */
  public abstract T divide(T value); 
  
  /**
   * Returns the negated value of the given value.
   */
  protected abstract T negate(T value); 
  
  /**
   * Returns the representation of 1 as type T.
   */
  protected abstract T one();
  
  protected T changeToValue(T value) 
  {
    set(value);
    return value;
  }
}

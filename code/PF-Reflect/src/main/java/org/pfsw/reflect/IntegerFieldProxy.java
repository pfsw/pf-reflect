// ===========================================================================
// CONTENT  : CLASS IntegerFieldProxy
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
 * Provides a field proxy for an integer type which offers some
 * extra convenience methods for basic calculation.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class IntegerFieldProxy extends NumericFieldProxy<Integer>
{
  private static final Integer ONE = Integer.valueOf(1);
  
  public IntegerFieldProxy(Object owner, Field field)
  {
    super(owner, field);
  }

  public IntegerFieldProxy(Object owner, String fieldName)
  {
    super(owner, fieldName);
  }
  
  @Override
  public Integer add(Integer value)
  {
    int newValue = get().intValue() + value.intValue();
    return changeToValue(newValue);
  }

  @Override
  public Integer multiply(Integer value)
  {
    int newValue = get().intValue() * value.intValue();
    return changeToValue(newValue);
  }
  
  @Override
  public Integer divide(Integer value)
  {
    int newValue = get().intValue() / value.intValue();
    return changeToValue(newValue);
  }
  
  @Override
  protected Integer one()
  {
    return ONE;
  }
  
  @Override
  protected Integer negate(Integer value)
  {
    return -1 * value.intValue();
  }
}

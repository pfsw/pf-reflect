// ===========================================================================
// CONTENT  : CLASS LongFieldProxy
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
 * Provides a field proxy for a Long type which offers some
 * extra convenience methods for basic calculation.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class LongFieldProxy extends NumericFieldProxy<Long>
{
  private static final Long ONE = Long.valueOf(1);
  
  public LongFieldProxy(Object owner, Field field)
  {
    super(owner, field);
  }

  public LongFieldProxy(Object owner, String fieldName)
  {
    super(owner, fieldName);
  }
  
  @Override
  public Long add(Long value)
  {
    long newValue = get().longValue() + value.longValue();
    return changeToValue(newValue);
  }

  @Override
  public Long multiply(Long value)
  {
    long newValue = get().longValue() * value.longValue();
    return changeToValue(newValue);
  }
  
  @Override
  public Long divide(Long value)
  {
    long newValue = get().longValue() / value.longValue();
    return changeToValue(newValue);
  }
  
  @Override
  protected Long one()
  {
    return ONE;
  }
  
  @Override
  protected Long negate(Long value)
  {
    return -1L * value.longValue();
  }
}

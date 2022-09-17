// ===========================================================================
// CONTENT  : CLASS BigDecimalFieldProxy
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 08/01/2019
// HISTORY  :
//  08/01/2019  mdu  CREATED
//
// Copyright (c) 2019, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Provides a field proxy for a BigDecimal type which offers some
 * extra convenience methods for basic calculation.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class BigDecimalFieldProxy extends NumericFieldProxy<BigDecimal>
{
  private static final BigDecimal ONE = BigDecimal.valueOf(1L);
  
  public BigDecimalFieldProxy(Object owner, Field field)
  {
    super(owner, field);
  }

  public BigDecimalFieldProxy(Object owner, String fieldName)
  {
    super(owner, fieldName);
  }
  
  @Override
  public BigDecimal add(BigDecimal value)
  {
    BigDecimal newValue = get().add(value);
    return changeToValue(newValue);
  }

  @Override
  public BigDecimal multiply(BigDecimal value)
  {
    BigDecimal newValue = get().multiply(value);
    return changeToValue(newValue);
  }
  
  @Override
  public BigDecimal divide(BigDecimal value)
  {
    BigDecimal newValue = get().divide(value);
    return changeToValue(newValue);
  }
  
  @Override
  protected BigDecimal one()
  {
    return ONE;
  }
  
  @Override
  protected BigDecimal negate(BigDecimal value)
  {
    return value.negate();
  }
}

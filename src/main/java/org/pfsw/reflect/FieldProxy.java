// ===========================================================================
// CONTENT  : CLASS FieldProxy
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
 * A proxy for a specific field in a target object that allows
 * getting and setting to that field via reflection, regardless of the
 * field's visibility (except if a {@link SecurityManager} is active).
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class FieldProxy<T> implements IValueHolder<T>
{
  private static final ReflectUtil RU = ReflectUtil.current(); 
  
  private final Object owner;
  private final Field field;

  public FieldProxy(Object owner, Field field)
  {
    super();
    this.owner = owner;
    this.field = field;
  }

  public FieldProxy(Object owner, String fieldName)
  {
    this(owner, RU.getField(owner, fieldName));
  }
  
  /**
   * Returns the current value (might be null).
   */
  @Override
  public T get()
  {
    return RU.getValueOf(getOwner(), getField());
  }

  /**
   * Sets the current value.
   * 
   * @param value The new value (might be null).
   */
  @Override
  public void set(T value)
  {
    RU.setValueOf(getOwner(), getField(), value);
  }
  
  /**
   * Returns whether or not the underlying value is null.
   */
  @Override
  public boolean isNull()
  {
    return get() == null;
  }
  
  /**
   * Returns whether or not the underlying value is not null.
   */
  @Override
  public boolean isPresent()
  {
    return !isNull();
  }
  
  protected Object getOwner()
  {
    return this.owner;
  }
  
  protected Field getField()
  {
    return this.field;
  }
}

// ===========================================================================
// CONTENT  : CLASS ObjectField
// AUTHOR   : Manfred Duchrow
// VERSION  : 2.0 - 27/07/2023
// HISTORY  :
//  18/03/2017  mdu  CREATED
//  27/07/2023  mdu  changed -> extends AbstractElementWithModifiers
//
// Copyright (c) 2017-2023, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Field;

/**
 * This is an IObjectProperty implementing wrapper around a {@link Field}.
 *
 * @author Manfred Duchrow
 * @version 2.0
 */
public class ObjectField extends AbstractElementWithModifiers implements IObjectProperty
{
  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private final Field field; 
  
  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  public ObjectField(Field field)
  {
    super();
    if (field == null)
    {
      throw new IllegalArgumentException("Parameter 'field' must not be null.");
    }
    this.field = field;
  }
  
  @Override
  public String getName()
  {
    return getField().getName();
  }
  
  public Field getField()
  {
    return this.field;
  }
  
  @Override
  public Class<?> getType()
  {
    return getField().getType();
  }
  
  @Override
  public Modifiers getModifiers()
  {
    return new Modifiers(getField().getModifiers());
  }

  @Override
  public String toString()
  {
    return String.format("%s(%s %s)", ObjectField.class.getSimpleName(), getType().getSimpleName(), getName());
  }
}

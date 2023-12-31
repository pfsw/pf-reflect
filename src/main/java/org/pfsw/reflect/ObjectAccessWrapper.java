// ===========================================================================
// CONTENT  : CLASS ObjectAccessWrapper
// AUTHOR   : M.Duchrow
// VERSION  : 1.1 - 13/05/2020
// HISTORY  :
//  19/12/2008  mdu  CREATED
//  13/05/2020  mdu   changed --> moved makeGetterName() makeSetterName() to ReflectUtil
//
// Copyright (c) 2008-2020, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Field;
import java.util.List;

/**
 * A wrapper that is capable to read and write object fields via getter and
 * setter methods or directly. The access is possible for all visibilities.   
 *
 * @author Manfred Duchrow
 * @version 1.1
 */
public class ObjectAccessWrapper implements AttributeReadWriteAccess
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================	
  protected static final String[] EMPTY_STRING_ARRAY = new String[0];
  protected static final ReflectUtil RU = ReflectUtil.current();

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private Object object;

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with an object.
   * @param object The object to be accessed by this wrapper (must not be null)
   */
  public ObjectAccessWrapper(Object object)
  {
    super();
    setObject(object);
  }

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
  /**
   * Returns the return value of the getter method of the given field name
   * or null in any case of reflection problem.
   */
  public Object get(final String fieldName)
  {
    String getterName;

    getterName = RU.makeGetterName(fieldName);
    try
    {
      return Dynamic.perform(getObject(), getterName);
    }
    catch (Exception e)
    {
      handleException(e);
      return null;
    }
  }

  /**
   * Invokes the setter method of the given field name and passes the
   * specified value as parameter to it. 
   */
  public void set(final String fieldName, final Object value)
  {
    String setterName;

    setterName = RU.makeSetterName(fieldName);
    try
    {
      Dynamic.perform(getObject(), setterName, value);
    }
    catch (Exception e)
    {
      handleException(e);
    }
  }

  /**
   * Returns the value of the field with the given field name or
   * null in any case of reflection error.
   */
  public Object getValueOfField(final String fieldName)
  {
    try
    {
      return getAttributeValue(fieldName);
    }
    catch (UnknownFieldException e)
    {
      handleException(e);
    }
    return null;
  }

  /**
   * Modifies the field with the given name directly to the specified value
   * without calling the setter. 
   */
  public void setValueOfField(final String fieldName, final Object value)
  {
    try
    {
      setAttributeValue(fieldName, value);
    }
    catch (UnknownFieldException e)
    {
      handleException(e);
    }
  }

  /**
   * Returns the current value of the attribute (field) with the given name.
   *
   * @param name The attribute's name ( case sensitive )
   * @throws UnknownFieldException If there is no attribute with the given name
   */
  @Override
  public Object getAttributeValue(String name) throws UnknownFieldException
  {
    return RU.getValueOf(getObject(), name);
  }

  /**
   * Sets the current value of the attribute (field) with the given name.    <br>
   *
   * @param name The attribute's name ( case sensitive )
   * @param value The value to be put into the attribute's 'slot'
   * @throws UnknownFieldException If there is no attribute with the given name
   */
  @Override
  public void setAttributeValue(String name, Object value) throws UnknownFieldException
  {
    RU.setValueOf(getObject(), name, value);
  }

  /**
   * Returns the names of all attributes that can be accessed by the
   * method getAttributeValue().
   */
  @Override
  public String[] getAttributeNames()
  {
    List<Field> fields;

    if (getObject() == null)
    {
      return EMPTY_STRING_ARRAY;
    }
    fields = RU.getFieldsOf(getObject());
    if (fields == null)
    {
      return EMPTY_STRING_ARRAY;
    }
    return ReflectUtil.current().toStringArray(fields, "getName");
  }

  public Object getObject()
  {
    return object;
  }

  /**
   * Set the underlying object.
   * @param object The object to be accessed by this wrapper (must not be null)
   */
  public void setObject(Object object)
  {
    this.object = object;
  }

  /**
   * Returns the type of the wrapped object.
   * In the case of null object it returns {@link Void#TYPE}.
   */
  public Class<?> getObjectType()
  {
    if (getObject() == null)
    {
      return Void.TYPE;
    }
    return getObject().getClass();
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================

  /**
   * Handles all exceptions that might occur due to reflection access.
   * Subclasses may override this method to do some logging.
   */
  @SuppressWarnings("unused")
  protected void handleException(Exception e)
  {
    // Nothing to do here
  }
}

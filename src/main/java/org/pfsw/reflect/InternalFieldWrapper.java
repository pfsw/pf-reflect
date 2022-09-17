// ===========================================================================
// CONTENT  : CLASS InternalFieldWrapper
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 17/09/2022
// HISTORY  :
//  17/09/2022  mdu  CREATED
//
// Copyright (c) 2022, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import static org.pfsw.reflect.ReflectUtil.RU;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class InternalFieldWrapper
{
  private static final boolean DEBUG = ReflectUtil.DEBUG;

  private final Field field;

  private boolean saveAccessibility;
  private int savedModifiers;
  private boolean savedIsFinal;

  static InternalFieldWrapper create(Field field)
  {
    return new InternalFieldWrapper(field);
  }

  private InternalFieldWrapper(Field field)
  {
    this.field = field;
  }

  void setValueOf(final Object obj, final Object value, final boolean isPrimitive)
  {
    makeFieldAccessible();
    try
    {
      if (isPrimitive)
      {
        if (value instanceof Character)
        {
          field.setChar(obj, ((Character)value).charValue());
        }
        else if (value instanceof Integer)
        {
          field.setInt(obj, ((Integer)value).intValue());
        }
        else if (value instanceof Long)
        {
          field.setLong(obj, ((Long)value).longValue());
        }
        else if (value instanceof Boolean)
        {
          field.setBoolean(obj, ((Boolean)value).booleanValue());
        }
        else if (value instanceof Double)
        {
          field.setDouble(obj, ((Double)value).doubleValue());
        }
        else if (value instanceof Float)
        {
          field.setFloat(obj, ((Float)value).floatValue());
        }
        else if (value instanceof Byte)
        {
          field.setByte(obj, ((Byte)value).byteValue());
        }
        else if (value instanceof Short)
        {
          field.setShort(obj, ((Short)value).shortValue());
        }
      }
      else
      {
        field.set(obj, value);
      }
    }
    catch (RuntimeException e)
    {
      // Ignore this, because null values are allowed !
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
    catch (IllegalAccessException ex1)
    {
      throw new IllegalAccessError(ex1.getMessage());
    }
    finally
    {
      resetFieldState();
    }
  }

  private void makeFieldAccessible()
  {
    saveAccessibility = field.isAccessible();
    savedModifiers = field.getModifiers();
    savedIsFinal = isFinal(savedModifiers);

    field.setAccessible(true);
    if (savedIsFinal)
    {
      removeFinal();
    }
  }

  private void resetFieldState()
  {
    field.setAccessible(saveAccessibility);
    if (savedIsFinal)
    {
      setModifiers(field, savedModifiers);
      removeFieldAccessor(field);
    }
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>final</tt> modifier,
   * false otherwise.
   */
  private boolean isFinal(int modifiers)
  {
    return Modifier.isFinal(modifiers);
  }

  private void removeFinal()
  {
    setModifiers(field, field.getModifiers() & ~Modifier.FINAL);
    removeFieldAccessor(field);
  }

  private static final String FN_OVERRIDE_FIELD_ACCESSOR = "overrideFieldAccessor";
  private static final String FN_ROOT_FIELD = "root";
  private void removeFieldAccessor(Field internalField)
  {
    if (internalField != null)
    {
      Object overrideFieldAccessor = RU.getValueOf(internalField, FN_OVERRIDE_FIELD_ACCESSOR);
      if (overrideFieldAccessor != null)
      {
        RU.setValueOf(internalField, FN_OVERRIDE_FIELD_ACCESSOR, null);
        Field root = RU.getValueOf(internalField, FN_ROOT_FIELD);
        removeFieldAccessor(root);
      }
    }
  }

  private static final String FN_MODIFIERS = "modifiers";
  private void setModifiers(Field field, int modifiers)
  {
    Field modifiersField = null;
    boolean saveAccess = false;

    try
    {
      modifiersField = Field.class.getDeclaredField(FN_MODIFIERS);
      saveAccess = modifiersField.isAccessible();
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, modifiers);
    }
    catch (Exception e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
    finally
    {
      if (modifiersField != null)
      {
        modifiersField.setAccessible(saveAccess);
      }
    }
  }
}

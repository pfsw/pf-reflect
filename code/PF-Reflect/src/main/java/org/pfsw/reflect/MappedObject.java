// ===========================================================================
// CONTENT  : CLASS MappedObject
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 02/08/2019
// HISTORY  :
//  02/08/2019  mdu  CREATED
//
// Copyright (c) 2019, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import static org.pfsw.reflect.ReflectUtil.RU;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a wrapper for an arbitrary object to allow accessing its field through
 * the {@link Map} interface where the field names are the keys.
 * <p>
 * The contained object can be replaced whenever needed.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class MappedObject implements Map<String, Object>
{
  private Object object;
  private boolean silent = true;
  private final Map<String, FieldProxy<Object>> fieldAccessors = new HashMap<String, FieldProxy<Object>>();

  /**
   * Creates an instance on the given object and ignores problems silently.
   * 
   * @param object The object to be wrapped (may be null).
   */
  public MappedObject(Object object)
  {
    this(object, true);
  }

  /**
   * Creates an instance on the given object and handles
   * problems silently if silent is true, otherwise problems
   * will cause a {@link ReflectionException}.
   * 
   * @param object The object to be wrapped (may be null).
   * @param silent Defines whether or not problems should silently be ignored.
   */
  public MappedObject(Object object, boolean silent)
  {
    super();
    setSilent(silent);
    setObject(object);
  }

  /**
   * Returns the original object wrapped by this to a {@link Map} interface.
   */
  public Object getObject()
  {
    return this.object;
  }

  /**
   * Sets the object to be wrapped for allowing it being used as {@link Map}.
   * 
   * @param object The object to be wrapped (may be null).
   */
  public void setObject(Object object)
  {
    this.object = object;
    initFieldAccessors();
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

  @Override
  public Object get(Object fieldName)
  {
    FieldProxy<Object> accessor;

    if (fieldName instanceof String)
    {
      accessor = getFieldAccessor((String)fieldName);
      if (accessor == null)
      {
        return null;
      }
      return accessor.get();
    }
    handleProblem("The given fieldName is no string: %s", fieldName);
    return null;
  }

  @Override
  public Object put(String fieldName, Object value)
  {
    FieldProxy<Object> accessor;
    Object oldValue;

    accessor = getFieldAccessor(fieldName);
    if (accessor == null)
    {
      return null;
    }
    oldValue = accessor.get();
    accessor.set(value);
    return oldValue;
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> map)
  {
    for (Entry<? extends String, ? extends Object> entry : map.entrySet())
    {
      put(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Returns all field names of the underlying object.
   */
  @Override
  public Set<String> keySet()
  {
    return getFieldAccessors().keySet();
  }

  @Override
  public Set<Entry<String, Object>> entrySet()
  {
    Set<Entry<String, Object>> entrySet;
    MapEntry<String, Object> entry;

    entrySet = new HashSet<Entry<String, Object>>();
    for (Entry<String, FieldProxy<Object>> fieldAccessorEntry : getFieldAccessors().entrySet())
    {
      entry = new MapEntry<String, Object>(fieldAccessorEntry.getKey(), fieldAccessorEntry.getValue().get());
      entrySet.add(entry);
    }
    return entrySet;
  }

  @Override
  public Collection<Object> values()
  {
    Collection<Object> values;
    
    values = new ArrayList<Object>();
    for (FieldProxy<Object> accessor : getFieldAccessors().values())
    {
      values.add(accessor.get());
    }
    return values;
  }

  @Override
  public boolean containsValue(Object value)
  {
    return values().contains(value);
  }

  @Override
  public boolean containsKey(Object fieldName)
  {
    return (fieldName instanceof String) ? getFieldAccessor((String)fieldName) != null : false;
  }

  @Override
  public int size()
  {
    return getFieldAccessors().size();
  }

  @Override
  public boolean isEmpty()
  {
    return size() == 0;
  }

  /**
   * Returns the value for the given fieldName, but does not remove anything.
   * Actually this method works identical to {@link #get(Object)}.
   * @throws ReflectionException if the filed cannot be found and silent is false.
   */
  @Override
  public Object remove(Object fieldName)
  {
    return get(fieldName);
  }

  /**
   * Does nothing.
   */
  @Override
  public void clear()
  {
    // does nothing
  }

  /**
   * Returns whether or not the underlying object is null.
   */
  public boolean isNull()
  {
    return getObject() == null;
  }

  /**
   * Returns true if this object has been configured to ignore
   * exceptions silently.
   */
  public boolean isSilent()
  {
    return this.silent;
  }

  protected FieldProxy<Object> getFieldAccessor(String fieldName)
  {
    FieldProxy<Object> fieldAccessor;
    
    fieldAccessor = getFieldAccessors().get(fieldName);
    if (fieldAccessor == null)
    {
      // Might throw an exception
      handleProblem("Field with name '%s' not found.", fieldName);
    }
    return fieldAccessor;
  }

  protected void initFieldAccessors()
  {
    List<Field> fields;
    FieldProxy<Object> accessor;

    getFieldAccessors().clear();
    if (isNull())
    {
      return;
    }
    fields = RU.getFieldsOf(getObject());
    for (Field field : fields)
    {
      accessor = new FieldProxy<Object>(getObject(), field);
      getFieldAccessors().put(field.getName(), accessor);
    }
  }

  /**
   * Ignores the problem if silent=true, otherwise throws a {@link ReflectionException}.
   */
  protected void handleProblem(String message, Object... args)
  {
    if (!isSilent())
    {
      throw new ReflectionException(message, args);
    }
  }

  protected void setSilent(boolean silent)
  {
    this.silent = silent;
  }

  protected Map<String, FieldProxy<Object>> getFieldAccessors()
  {
    return this.fieldAccessors;
  }
}

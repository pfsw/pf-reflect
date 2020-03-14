// ===========================================================================
// CONTENT  : CLASS MapEntry
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 03/08/2019
// HISTORY  :
//  03/08/2019  mdu  CREATED
//
// Copyright (c) 2019, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.util.Map;
import java.util.Map.Entry;

/**
 * A simple implementation of {@link Entry} interface that can be used 
 * with any kind of {@link Map} implementation.
 * <p>
 * There's one difference from the interface's definition: The {@link #setValue(Object)}
 * method does not write through to the map.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class MapEntry<K, V> implements Entry<K, V>
{
  private final K key;
  private V value;
  
  public MapEntry(K key, V value)
  {
    super();
    this.key = key;
    setValue(value);
  }

  @Override
  public K getKey()
  {
    return this.key;
  }

  @Override
  public V getValue()
  {
    return this.value;
  }

  /**
   * Replaces the value of this entry by the given one and returns the old one.
   * <strong>This method does NOT write through to the original map!</strong>
   */
  @Override
  public V setValue(V value)
  {
    V old = getValue();
    this.value = value;
    return old;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj)
  {
    MapEntry other;

    if (obj instanceof MapEntry)
    {
      other = (MapEntry)obj;
      if (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey()))
      {
        if (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()))
        {
          return true;
        }

      }
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
  }

  @Override
  public String toString()
  {
    return String.format("%s('%s' : %s)", getClass().getSimpleName(), getKey(), getValue());
  }
}

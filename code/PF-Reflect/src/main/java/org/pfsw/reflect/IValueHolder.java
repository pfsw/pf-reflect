// ===========================================================================
// CONTENT  : INTERFACE IValueHolder
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 08/01/2019
// HISTORY  :
//  08/01/2019  mdu  CREATED
//
// Copyright (c) 2019, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect ;

/**
 * A generic interface to a single value held by another object.
 * <T> is the type of the held object. 
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public interface IValueHolder<T>
{
  /**
   * Returns the current value (might be null).
   */
  public T get();
  
  /**
   * Sets the current value.
   * 
   * @param value The new value (might be null).
   */
  public void set(T value);
  
  /**
   * Returns whether or not the underlying value is null.
   */
  public boolean isNull();
  
  /**
   * Returns whether or not the underlying value is not null.
   */
  public boolean isPresent();
}
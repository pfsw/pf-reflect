// ===========================================================================
// CONTENT  : INTERFACE IObjectProperty
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 18/03/2017
// HISTORY  :
//  18/03/2017  mdu  CREATED
//
// Copyright (c) 2017, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * This is the generic view on a single object property (field / instance variable). 
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public interface IObjectProperty
{
  /**
   * Returns the name of the property.
   */
  public String getName();
  
  /**
   * Returns the type of the property.
   */
  public Class<?> getType();
  
  /**
   * Returns all modifier settings of the property. 
   */
  public Modifiers getModifiers();
}
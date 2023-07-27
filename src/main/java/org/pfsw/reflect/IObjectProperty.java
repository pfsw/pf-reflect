// ===========================================================================
// CONTENT  : INTERFACE IObjectProperty
// AUTHOR   : Manfred Duchrow
// VERSION  : 2.0 - 27/07/2023
// HISTORY  :
//  18/03/2017  mdu  CREATED
//  27/07/2023  mdu  extends ElementWithModifiers
//
// Copyright (c) 2017-2023, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * This is the generic view on a single object property (field / instance variable). 
 *
 * @author Manfred Duchrow
 * @version 2.0
 */
public interface IObjectProperty extends ElementWithModifiers
{
  /**
   * Returns the name of the property.
   */
  String getName();
  
  /**
   * Returns the type of the property.
   */
  Class<?> getType();
}
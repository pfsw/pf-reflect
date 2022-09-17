// ===========================================================================
// CONTENT  : INTERFACE IObjectPropertyFilter
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 19/03/2017
// HISTORY  :
//  19/03/2017  mdu  CREATED
//
// Copyright (c) 2017, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * A generic filter function definition for IObjectProperty objects. 
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public interface IObjectPropertyFilter
{
  /**
   * Returns true if the given objectProperty matches the filter criteria.
   */
  public boolean matches(IObjectProperty objectProperty);
}
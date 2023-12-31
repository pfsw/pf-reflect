// ===========================================================================
// CONTENT  : INTERFACE AttributeReadAccess
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 21/05/2004
// HISTORY  :
//  21/05/2004  mdu  CREATED
//
// Copyright (c) 2004, by M.Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * Specifies the methods an object must implement to provide generic read 
 * access to its attributes.<br>
 * Such attributes can be instance variables of an object or values stored
 * in properties or maps. 
 *
 * @author M.Duchrow
 * @version 1.0
 */
public interface AttributeReadAccess
{
  /**
   * Returns the current value of the attribute with the given name.
   *
   * @param name The attribute's name ( case sensitive )
   * @throws UnknownFieldException If there is no attribute with the given name
   */
  public Object getAttributeValue(String name) throws UnknownFieldException;

  /**
   * Returns the names of all attributes that can be accessed by the
   * method getAttributeValue().
   */
  public String[] getAttributeNames();

}

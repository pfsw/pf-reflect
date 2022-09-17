// ===========================================================================
// CONTENT  : CLASS UnknownFieldException
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 19/03/2017
// HISTORY  :
//  19/03/2017  mdu  CREATED
//
// Copyright (c) 2017, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * A runtime exception to signal that a field cannot be found by reflection.
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class UnknownFieldException extends ReflectionException
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  private static final long serialVersionUID = -6284556788697146177L;

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  public UnknownFieldException(String message, Object...args)
  {
    super(message, args);
  }

  public UnknownFieldException(Throwable cause, String message, Object...args)
  {
    super(cause, message, args);
  }
}

// ===========================================================================
// CONTENT  : CLASS ReflectionException
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 03/06/2006
// HISTORY  :
//  03/06/2006  mdu  CREATED
//
// Copyright (c) 2006, by M.Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

/**
 * Special runtime exception to wrap all exceptions that might occur due
 * to reflective access to objects and classes.
 *
 * @author M.Duchrow
 * @version 1.0
 */
public class ReflectionException extends RuntimeException
{
  private static final long serialVersionUID = -3871871488724817984L;

  public ReflectionException(Throwable rootCause)
  {
    super(rootCause);
  }
}

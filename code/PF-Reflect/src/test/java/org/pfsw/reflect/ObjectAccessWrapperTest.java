// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 19/12/2008
// HISTORY  :
//  19/12/2008  mdu  CREATED
//
// Copyright (c) 2008, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import org.pfsw.reflect.ObjectAccessWrapper;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 * @version 2.0
 */
public class ObjectAccessWrapperTest extends CommonObjectAccessWrapperTestCases
{
  // =========================================================================
  // TEST METHODS
  // =========================================================================

  // =========================================================================
  // HELPER METHODS
  // =========================================================================
  @Override
  protected ObjectAccessWrapper createAccessWrapper(Object object)
  {
    return new ObjectAccessWrapper(object);
  }
}

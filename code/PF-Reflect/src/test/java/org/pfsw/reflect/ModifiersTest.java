// ===========================================================================
// CONTENT  : TEST CLASS {ClassName}
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 13/01/2008
// HISTORY  :
//  13/01/2008  mdu  CREATED
//
// Copyright (c) 2008, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect ;

// ===========================================================================
// IMPORTS
// ===========================================================================
import java.lang.reflect.Modifier;

import org.pfsw.reflect.Modifiers;

import junit.framework.* ;

/**
 * Test class for corresponding business class.
 *
 * @author M.Duchrow
 * @version 1.0
 */
public class ModifiersTest extends TestCase
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
	Modifiers modifiers ;

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public ModifiersTest( String name )
  {
    super( name ) ;
  } // ModifiersTest() 

  // =========================================================================
  // PUBLIC CLASS METHODS
  // =========================================================================
  public static Test suite()
  {
		return new TestSuite( ModifiersTest.class ) ;
	} // suite() 

  // =========================================================================
  // TEST METHODS
  // =========================================================================

  public void test_abstract_1()
  {
  	assertFalse( modifiers.isAbstract() ) ;
  	
  	modifiers.setAbstract() ;
  	assertTrue( modifiers.isAbstract() ) ;
  	
  	modifiers = new Modifiers( Modifier.PUBLIC | Modifier.FINAL ) ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertFalse( modifiers.isAbstract() ) ;
  	
  	modifiers.setAbstract() ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertTrue( modifiers.isAbstract() ) ;

  	modifiers.unsetAbstract() ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertFalse( modifiers.isAbstract() ) ;
  } // test_abstract_1() 

  // -------------------------------------------------------------------------

  public void test_final_1()
  {
  	assertFalse( modifiers.isFinal() ) ;
  	modifiers.setFinal() ;
  	assertTrue( modifiers.isFinal() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isFinal() ) ;
  	modifiers.unsetFinal() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isFinal() ) ;
  } // test_final_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_native_1()
  {
  	assertFalse( modifiers.isNative() ) ;
  	modifiers.setNative() ;
  	assertTrue( modifiers.isNative() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isNative() ) ;
  	modifiers.unsetNative() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isNative() ) ;
  } // test_native_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_strict_1()
  {
  	assertFalse( modifiers.isStrict() ) ;
  	modifiers.setStrict() ;
  	assertTrue( modifiers.isStrict() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isStrict() ) ;
  	modifiers.unsetStrict() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isStrict() ) ;
  } // test_strict_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_transient_1()
  {
  	assertFalse( modifiers.isTransient() ) ;
  	modifiers.setTransient() ;
  	assertTrue( modifiers.isTransient() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isTransient() ) ;
  	modifiers.unsetTransient() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isTransient() ) ;
  } // test_transient_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_interface_1()
  {
  	assertFalse( modifiers.isInterface() ) ;
  	modifiers.setInterface() ;
  	assertTrue( modifiers.isInterface() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isInterface() ) ;
  	modifiers.unsetInterface() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isInterface() ) ;
  } // test_interface_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_volatile_1()
  {
  	assertFalse( modifiers.isVolatile() ) ;
  	modifiers.setVolatile() ;
  	assertTrue( modifiers.isVolatile() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isVolatile() ) ;
  	modifiers.unsetVolatile() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isVolatile() ) ;
  } // test_volatile_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_synchronized_1()
  {
  	assertFalse( modifiers.isSynchronized() ) ;
  	modifiers.setSynchronized() ;
  	assertTrue( modifiers.isSynchronized() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isSynchronized() ) ;
  	modifiers.unsetSynchronized() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isSynchronized() ) ;
  } // test_synchronized_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_static_1()
  {
  	assertFalse( modifiers.isStatic() ) ;
  	modifiers.setStatic() ;
  	assertTrue( modifiers.isStatic() ) ;
  	modifiers.setFinal() ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertTrue( modifiers.isStatic() ) ;
  	modifiers.unsetStatic() ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertFalse( modifiers.isStatic() ) ;
  } // test_static_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_visibility_1()
  {
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setPublic() ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;

  	modifiers.setProtected() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setPrivate() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertTrue( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setDefaultVisibility() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  } // test_visibility_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_visibility_2()
  {
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setPublic() ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.unsetPublic() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setProtected() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.unsetProtected() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setPrivate() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertTrue( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.unsetPrivate() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  } // test_visibility_2() 
  
  // -------------------------------------------------------------------------
  
  public void test_visibility_3()
  {
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setVisibility( Modifiers.VIS_PUBLIC ) ;
  	assertTrue( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.unsetPublic() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setVisibility( Modifiers.VIS_PROTECTED ) ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertTrue( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.unsetProtected() ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  	
  	modifiers.setVisibility( Modifiers.VIS_PRIVATE ) ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertTrue( modifiers.isPrivate() ) ;
  	assertFalse( modifiers.isDefaultVisibility() ) ;
  	modifiers.setVisibility( Modifiers.VIS_DEFAULT ) ;
  	assertFalse( modifiers.isPublic() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  	assertFalse( modifiers.isPrivate() ) ;
  	assertTrue( modifiers.isDefaultVisibility() ) ;
  } // test_visibility_3() 
  
  // -------------------------------------------------------------------------
  
  public void test_reset_1()
  {
  	modifiers.setStatic() ;
  	modifiers.setFinal() ;
  	modifiers.setProtected() ;
  	assertTrue( modifiers.isStatic() ) ;
  	assertTrue( modifiers.isFinal() ) ;
  	assertTrue( modifiers.isProtected() ) ;
  	
  	modifiers.reset() ;
  	assertFalse( modifiers.isStatic() ) ;
  	assertFalse( modifiers.isFinal() ) ;
  	assertFalse( modifiers.isProtected() ) ;
  } // test_reset_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_equals_1()
  {
  	Modifiers m1 = new Modifiers( Modifier.ABSTRACT | Modifier.FINAL | Modifier.PUBLIC ) ;
  	Modifiers m2 = null ;

  	assertFalse( m1.equals( m2 ) ) ;
  	
  	m2 = new Modifiers();
  	m2.setAbstract() ;
  	m2.setFinal() ;
  	m2.setPublic() ;
  	assertEquals( m1, m2 ) ;
  	
  	m2.setVolatile() ;
  	assertFalse( m1.equals( m2 ) ) ;
  } // test_equals_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_hashCode_1()
  {
  	Modifiers m1 = new Modifiers( Modifier.TRANSIENT | Modifier.VOLATILE | Modifier.PROTECTED ) ;
  	Modifiers m2 = null ;
  	
  	m2 = new Modifiers();
  	m2.setTransient() ;
  	m2.setVolatile() ;
  	m2.setProtected() ;
  	assertTrue( m1.hashCode() == m2.hashCode() ) ;
  	
  	m2.setPrivate() ;
  	assertFalse( m1.hashCode() == m2.hashCode() ) ;
  } // test_hashCode_1() 
  
  // -------------------------------------------------------------------------
  
  public void test_toString_1()
  {
  	Modifiers m1 = new Modifiers( Modifier.TRANSIENT | Modifier.VOLATILE | Modifier.PROTECTED ) ;
  	
  	assertEquals( "protected transient volatile", m1.toString() ) ;
  } // test_toString_1() 
  
  // -------------------------------------------------------------------------
  
  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  @Override
  protected void setUp() throws Exception
  {
    super.setUp() ;
    modifiers = new Modifiers() ;
	} // setUp() 

  // -------------------------------------------------------------------------

} // class ModifiersTest 

// ===========================================================================
// CONTENT  : CLASS AbstractElementWithModifiers
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 27/07/2023
// HISTORY  :
//  27/07/2023  mdu  CREATED
//
// Copyright (c) 2023, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

abstract class AbstractElementWithModifiers implements ElementWithModifiers
{
  protected AbstractElementWithModifiers()
  {
    super();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>abstract</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isAbstract()
  {
    return getModifiers().isAbstract();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>final</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isFinal()
  {
    return getModifiers().isFinal();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>interface</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isInterface()
  {
    return getModifiers().isInterface();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>native</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isNative()
  {
    return getModifiers().isNative();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>private</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isPrivate()
  {
    return getModifiers().isPrivate();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>protected</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isProtected()
  {
    return getModifiers().isProtected();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>public</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isPublic()
  {
    return getModifiers().isPublic();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>static</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isStatic()
  {
    return getModifiers().isStatic();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>strict</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isStrict()
  {
    return getModifiers().isStrict();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>synchronized</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isSynchronized()
  {
    return getModifiers().isSynchronized();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>transient</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isTransient()
  {
    return getModifiers().isTransient();
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>volatile</tt> modifier, 
   * false otherwise.
   */
  @Override
  public boolean isVolatile()
  {
    return getModifiers().isVolatile();
  }

  /**
   * Return <tt>true</tt> if the modifier bits does not include any of the 
   * visibility modifiers <tt>public</tt>, <tt>protected</tt> <tt>private</tt>, 
   * false otherwise.
   */
  @Override
  public boolean isDefaultVisibility()
  {
    return getModifiers().isDefaultVisibility();
  }
}

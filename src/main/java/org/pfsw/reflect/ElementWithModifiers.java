// ===========================================================================
// CONTENT  : CLASS ElementWithModifiers
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 27/07/2023
// HISTORY  :
//  27/07/2023  mdu  CREATED
//
// Copyright (c) 2023, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

public interface ElementWithModifiers
{
  /**
   * Returns all modifier settings of the property. 
   */
  Modifiers getModifiers();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>abstract</tt> modifier, 
   * false otherwise.
   */
  boolean isAbstract();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>final</tt> modifier, 
   * false otherwise.
   */
  boolean isFinal();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>interface</tt> modifier, 
   * false otherwise.
   */
  boolean isInterface();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>native</tt> modifier, 
   * false otherwise.
   */
  boolean isNative();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>private</tt> modifier, 
   * false otherwise.
   */
  boolean isPrivate();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>protected</tt> modifier, 
   * false otherwise.
   */
  boolean isProtected();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>public</tt> modifier, 
   * false otherwise.
   */
  boolean isPublic();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>static</tt> modifier, 
   * false otherwise.
   */
  boolean isStatic();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>strict</tt> modifier, 
   * false otherwise.
   */
  boolean isStrict();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>synchronized</tt> modifier, 
   * false otherwise.
   */
  boolean isSynchronized();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>transient</tt> modifier, 
   * false otherwise.
   */
  boolean isTransient();

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>volatile</tt> modifier, 
   * false otherwise.
   */
  boolean isVolatile();

  /**
   * Return <tt>true</tt> if the modifier bits does not include any of the 
   * visibility modifiers <tt>public</tt>, <tt>protected</tt> <tt>private</tt>, 
   * false otherwise.
   */
  boolean isDefaultVisibility();
}

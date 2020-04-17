// ===========================================================================
// CONTENT  : CLASS Modifiers
// AUTHOR   : M.Duchrow
// VERSION  : 1.1 - 14/03/2020
// HISTORY  :
//  13/01/2008  mdu  CREATED
//  14/03/2020  mdu   added -> create()
//
// Copyright (c) 2008-2020, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * This class provides methods to conveniently set/unset modifier bits
 * without having to fiddle around with bit logic.
 *
 * @author M.Duchrow
 * @version 1.1
 */
public class Modifiers
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  /**
   * String constant for "public".
   */
  public static final String VIS_PUBLIC = "public";
  /**
   * String constant for "protected".
   */
  public static final String VIS_PROTECTED = "protected";
  /**
   * String constant for "private".
   */
  public static final String VIS_PRIVATE = "private";
  /**
   * String constant for "".
   */
  public static final String VIS_DEFAULT = "";

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private int bits = 0;

  // =========================================================================
  // CLASS METHODS
  // =========================================================================
  public static Modifiers create()
  {
    return new Modifiers();
  }

  public static Modifiers create(int initialValue)
  {
    return new Modifiers(initialValue);
  }

  public static Modifiers create(Field field)
  {
    return create(field.getModifiers());
  }

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default value 0.
   */
  public Modifiers()
  {
    super();
  }

  /**
   * Initialize the new instance with given value.
   */
  public Modifiers(int initialValue)
  {
    super();
    setBits(initialValue);
  }

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
  /**
   * Return the bits as they are currently set.
   */
  public int getBits()
  {
    return this.bits;
  }

  /**
   * Resets the modifier to 0.
   */
  public Modifiers reset()
  {
    setBits(0);
    return this;
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>abstract</tt> modifier, 
   * false otherwise.
   */
  public boolean isAbstract()
  {
    return Modifier.isAbstract(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>final</tt> modifier, 
   * false otherwise.
   */
  public boolean isFinal()
  {
    return Modifier.isFinal(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>interface</tt> modifier, 
   * false otherwise.
   */
  public boolean isInterface()
  {
    return Modifier.isInterface(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>native</tt> modifier, 
   * false otherwise.
   */
  public boolean isNative()
  {
    return Modifier.isNative(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>private</tt> modifier, 
   * false otherwise.
   */
  public boolean isPrivate()
  {
    return Modifier.isPrivate(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>protected</tt> modifier, 
   * false otherwise.
   */
  public boolean isProtected()
  {
    return Modifier.isProtected(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>public</tt> modifier, 
   * false otherwise.
   */
  public boolean isPublic()
  {
    return Modifier.isPublic(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>static</tt> modifier, 
   * false otherwise.
   */
  public boolean isStatic()
  {
    return Modifier.isStatic(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>strict</tt> modifier, 
   * false otherwise.
   */
  public boolean isStrict()
  {
    return Modifier.isStrict(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>synchronized</tt> modifier, 
   * false otherwise.
   */
  public boolean isSynchronized()
  {
    return Modifier.isSynchronized(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>transient</tt> modifier, 
   * false otherwise.
   */
  public boolean isTransient()
  {
    return Modifier.isTransient(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>volatile</tt> modifier, 
   * false otherwise.
   */
  public boolean isVolatile()
  {
    return Modifier.isVolatile(getBits());
  }

  /**
   * Return <tt>true</tt> if the modifier bits does not include any of the 
   * visibility modifiers <tt>public</tt>, <tt>protected</tt> <tt>private</tt>, 
   * false otherwise.
   */
  public boolean isDefaultVisibility()
  {
    return !(isPublic() || isProtected() || isPrivate());
  }

  /**
   * Return a string describing the access modifier flags in the specified modifier.
   */
  @Override
  public String toString()
  {
    return Modifier.toString(getBits());
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   */
  @Override
  public boolean equals(Object object)
  {
    if (object instanceof Modifiers)
    {
      Modifiers mod = (Modifiers)object;
      return getBits() == mod.getBits();
    }
    return false;
  }

  /**
   * Returns a hash code value for the object.
   */
  @Override
  public int hashCode()
  {
    return getBits();
  }

  /**
   * Sets the <tt>ABSTRACT</tt> bit.
   * @return this object.
   */
  public Modifiers setAbstract()
  {
    setModifier(Modifier.ABSTRACT);
    return this;
  }

  /**
   * Sets the <tt>FINAL</tt> bit.
   * @return this object.
   */
  public Modifiers setFinal()
  {
    setModifier(Modifier.FINAL);
    return this;
  }

  /**
   * Sets the <tt>INTERFACE</tt> bit.
   * @return this object.
   */
  public Modifiers setInterface()
  {
    setModifier(Modifier.INTERFACE);
    return this;
  }

  /**
   * Sets the <tt>NATIVE</tt> bit.
   * @return this object.
   */
  public Modifiers setNative()
  {
    setModifier(Modifier.NATIVE);
    return this;
  }

  /**
   * Sets the <tt>PRIVATE</tt> bit.
   * This automatically unsets the <tt>PUBLIC</tt> and <tt>PROTECTED</tt> bits. 
   * @return this object.
   */
  public Modifiers setPrivate()
  {
    setDefaultVisibility();
    setModifier(Modifier.PRIVATE);
    return this;
  }

  /**
   * Sets the <tt>PROTECTED</tt> bit.
   * This automatically unsets the <tt>PUBLIC</tt> and <tt>PRIVATE</tt> bits. 
   * @return this object.
   */
  public Modifiers setProtected()
  {
    setDefaultVisibility();
    setModifier(Modifier.PROTECTED);
    return this;
  }

  /**
   * Sets the <tt>PUBLIC</tt> bit.
   * This automatically unsets the <tt>PROTECTED</tt> and <tt>PRIVATE</tt> bits. 
   * @return this object.
   */
  public Modifiers setPublic()
  {
    setDefaultVisibility();
    setModifier(Modifier.PUBLIC);
    return this;
  }

  /**
   * Sets the <tt>STATIC</tt> bit.
   * @return this object.
   */
  public Modifiers setStatic()
  {
    setModifier(Modifier.STATIC);
    return this;
  }

  /**
   * Sets the <tt>STRICT</tt> bit.
   * @return this object.
   */
  public Modifiers setStrict()
  {
    setModifier(Modifier.STRICT);
    return this;
  }

  /**
   * Sets the <tt>SYNCHRONIZED</tt> bit.
   * @return this object.
   */
  public Modifiers setSynchronized()
  {
    setModifier(Modifier.SYNCHRONIZED);
    return this;
  }

  /**
   * Sets the <tt>TRANSIENT</tt> bit.
   * @return this object.
   */
  public Modifiers setTransient()
  {
    setModifier(Modifier.TRANSIENT);
    return this;
  }

  /**
   * Sets the <tt>VOLATILE</tt> bit.
   * @return this object.
   */
  public Modifiers setVolatile()
  {
    setModifier(Modifier.VOLATILE);
    return this;
  }

  /**
   * Sets the visibility to default that means unset
   * <tt>PUBLIC</tt>, <tt>PROTECTED</tt>, <tt>PRIVATE</tt> bits.
   * @return this object.
   */
  public Modifiers setDefaultVisibility()
  {
    unsetModifier(Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE);
    return this;
  }

  /**
   * Sets the visibility from the given string. If the given string is not
   * one of "public", "protected", "private" the the default visibility is set.
   * 
   * @param visibility One of the visibility strings
   * @return this object.
   */
  public Modifiers setVisibility(String visibility)
  {
    if (VIS_PUBLIC.equals(visibility))
    {
      setPublic();
    }
    else if (VIS_PROTECTED.equals(visibility))
    {
      setProtected();
    }
    else if (VIS_PRIVATE.equals(visibility))
    {
      setPrivate();
    }
    else
    {
      setDefaultVisibility();
    }
    return this;
  }

  /**
   * Unsets the <tt>ABSTRACT</tt> bit.
   * @return this object.
   */
  public Modifiers unsetAbstract()
  {
    unsetModifier(Modifier.ABSTRACT);
    return this;
  }

  /**
   * Unsets the <tt>FINAL</tt> bit.
   * @return this object.
   */
  public Modifiers unsetFinal()
  {
    unsetModifier(Modifier.FINAL);
    return this;
  }

  /**
   * Unsets the <tt>INTERFACE</tt> bit.
   * @return this object.
   */
  public Modifiers unsetInterface()
  {
    unsetModifier(Modifier.INTERFACE);
    return this;
  }

  /**
   * Unsets the <tt>NATIVE</tt> bit.
   * @return this object.
   */
  public Modifiers unsetNative()
  {
    unsetModifier(Modifier.NATIVE);
    return this;
  }

  /**
   * Unsets the <tt>PRIVATE</tt> bit.
   * @return this object.
   */
  public Modifiers unsetPrivate()
  {
    unsetModifier(Modifier.PRIVATE);
    return this;
  }

  /**
   * Unsets the <tt>PROTECTED</tt> bit.
   * @return this object.
   */
  public Modifiers unsetProtected()
  {
    unsetModifier(Modifier.PROTECTED);
    return this;
  }

  /**
   * Unsets the <tt>PUBLIC</tt> bit.
   * @return this object.
   */
  public Modifiers unsetPublic()
  {
    unsetModifier(Modifier.PUBLIC);
    return this;
  }

  /**
   * Unsets the <tt>STATIC</tt> bit.
   * @return this object.
   */
  public Modifiers unsetStatic()
  {
    unsetModifier(Modifier.STATIC);
    return this;
  }

  /**
   * Unsets the <tt>STRICT</tt> bit.
   * @return this object.
   */
  public Modifiers unsetStrict()
  {
    unsetModifier(Modifier.STRICT);
    return this;
  }

  /**
   * Unsets the <tt>SYNCHRONIZED</tt> bit.
   * @return this object.
   */
  public Modifiers unsetSynchronized()
  {
    unsetModifier(Modifier.SYNCHRONIZED);
    return this;
  }

  /**
   * Unsets the <tt>TRANSIENT</tt> bit.
   * @return this object.
   */
  public Modifiers unsetTransient()
  {
    unsetModifier(Modifier.TRANSIENT);
    return this;
  }

  /**
   * Unsets the <tt>VOLATILE</tt> bit.
   * @return this object.
   */
  public Modifiers unsetVolatile()
  {
    unsetModifier(Modifier.VOLATILE);
    return this;
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  /**
   * Sets the bits in the underlying int that are specified by mod.
   */
  protected void setModifier(int mod)
  {
    setBits(getBits() | mod);
  }

  /**
   * Unsets the bits in the underlying int that are specified by mod.
   */
  protected void unsetModifier(int mod)
  {
    setBits(getBits() & ~mod);
  }

  protected void setBits(int newValue)
  {
    this.bits = newValue;
  }
}

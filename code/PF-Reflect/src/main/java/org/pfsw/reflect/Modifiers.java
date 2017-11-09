// ===========================================================================
// CONTENT  : CLASS Modifiers
// AUTHOR   : M.Duchrow
// VERSION  : 1.0 - 13/01/2008
// HISTORY  :
//  13/01/2008  mdu  CREATED
//
// Copyright (c) 2008, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

// ===========================================================================
// IMPORTS
// ===========================================================================
import java.lang.reflect.Modifier;

/**
 * This class provides methods to conveniently set/unset modifier bits
 * without having to fiddle around with bit logic.
 *
 * @author M.Duchrow
 * @version 1.0
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
    this.setBits(initialValue);
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
  public void reset()
  {
    this.setBits(0);
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>abstract</tt> modifier, 
   * false otherwise.
   */
  public boolean isAbstract()
  {
    return Modifier.isAbstract(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>final</tt> modifier, 
   * false otherwise.
   */
  public boolean isFinal()
  {
    return Modifier.isFinal(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>interface</tt> modifier, 
   * false otherwise.
   */
  public boolean isInterface()
  {
    return Modifier.isInterface(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>native</tt> modifier, 
   * false otherwise.
   */
  public boolean isNative()
  {
    return Modifier.isNative(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>private</tt> modifier, 
   * false otherwise.
   */
  public boolean isPrivate()
  {
    return Modifier.isPrivate(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>protected</tt> modifier, 
   * false otherwise.
   */
  public boolean isProtected()
  {
    return Modifier.isProtected(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>public</tt> modifier, 
   * false otherwise.
   */
  public boolean isPublic()
  {
    return Modifier.isPublic(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>static</tt> modifier, 
   * false otherwise.
   */
  public boolean isStatic()
  {
    return Modifier.isStatic(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>strict</tt> modifier, 
   * false otherwise.
   */
  public boolean isStrict()
  {
    return Modifier.isStrict(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>synchronized</tt> modifier, 
   * false otherwise.
   */
  public boolean isSynchronized()
  {
    return Modifier.isSynchronized(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>transient</tt> modifier, 
   * false otherwise.
   */
  public boolean isTransient()
  {
    return Modifier.isTransient(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifiers includes the <tt>volatile</tt> modifier, 
   * false otherwise.
   */
  public boolean isVolatile()
  {
    return Modifier.isVolatile(this.getBits());
  }

  /**
   * Return <tt>true</tt> if the modifier bits does not include any of the 
   * visibility modifiers <tt>public</tt>, <tt>protected</tt> <tt>private</tt>, 
   * false otherwise.
   */
  public boolean isDefaultVisibility()
  {
    return !(this.isPublic() || this.isProtected() || this.isPrivate());
  }

  /**
   * Return a string describing the access modifier flags in the specified modifier.
   */
  @Override
  public String toString()
  {
    return Modifier.toString(this.getBits());
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
      return this.getBits() == mod.getBits();
    }
    return false;
  }

  /**
   * Returns a hash code value for the object.
   */
  @Override
  public int hashCode()
  {
    return this.getBits();
  }

  /**
   * Sets the <tt>ABSTRACT</tt> bit.
   */
  public void setAbstract()
  {
    this.setModifier(Modifier.ABSTRACT);
  }

  /**
   * Sets the <tt>FINAL</tt> bit.
   */
  public void setFinal()
  {
    this.setModifier(Modifier.FINAL);
  }

  /**
   * Sets the <tt>INTERFACE</tt> bit.
   */
  public void setInterface()
  {
    this.setModifier(Modifier.INTERFACE);
  }

  /**
   * Sets the <tt>NATIVE</tt> bit.
   */
  public void setNative()
  {
    this.setModifier(Modifier.NATIVE);
  }

  /**
   * Sets the <tt>PRIVATE</tt> bit.
   * This automatically unsets the <tt>PUBLIC</tt> and <tt>PROTECTED</tt> bits. 
   */
  public void setPrivate()
  {
    this.setDefaultVisibility();
    this.setModifier(Modifier.PRIVATE);
  }

  /**
   * Sets the <tt>PROTECTED</tt> bit.
   * This automatically unsets the <tt>PUBLIC</tt> and <tt>PRIVATE</tt> bits. 
   */
  public void setProtected()
  {
    this.setDefaultVisibility();
    this.setModifier(Modifier.PROTECTED);
  }

  /**
   * Sets the <tt>PUBLIC</tt> bit.
   * This automatically unsets the <tt>PROTECTED</tt> and <tt>PRIVATE</tt> bits. 
   */
  public void setPublic()
  {
    this.setDefaultVisibility();
    this.setModifier(Modifier.PUBLIC);
  }

  /**
   * Sets the <tt>STATIC</tt> bit.
   */
  public void setStatic()
  {
    this.setModifier(Modifier.STATIC);
  }

  /**
   * Sets the <tt>STRICT</tt> bit.
   */
  public void setStrict()
  {
    this.setModifier(Modifier.STRICT);
  }

  /**
   * Sets the <tt>SYNCHRONIZED</tt> bit.
   */
  public void setSynchronized()
  {
    this.setModifier(Modifier.SYNCHRONIZED);
  }

  /**
   * Sets the <tt>TRANSIENT</tt> bit.
   */
  public void setTransient()
  {
    this.setModifier(Modifier.TRANSIENT);
  }

  /**
   * Sets the <tt>VOLATILE</tt> bit.
   */
  public void setVolatile()
  {
    this.setModifier(Modifier.VOLATILE);
  }

  /**
   * Sets the visibility to default that means unset
   * <tt>PUBLIC</tt>, <tt>PROTECTED</tt>, <tt>PRIVATE</tt> bits.
   */
  public void setDefaultVisibility()
  {
    this.unsetModifier(Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE);
  }

  /**
   * Sets the visibility from the given string. If the given string is not
   * one of "public", "protected", "private" the the default visibility is set.
   * 
   *  @param visibility One of the visibility strings
   */
  public void setVisibility(String visibility)
  {
    if (VIS_PUBLIC.equals(visibility))
    {
      this.setPublic();
      return;
    }
    if (VIS_PROTECTED.equals(visibility))
    {
      this.setProtected();
      return;
    }
    if (VIS_PRIVATE.equals(visibility))
    {
      this.setPrivate();
      return;
    }
    this.setDefaultVisibility();
  }

  /**
   * Unsets the <tt>ABSTRACT</tt> bit.
   */
  public void unsetAbstract()
  {
    this.unsetModifier(Modifier.ABSTRACT);
  }

  /**
   * Unsets the <tt>FINAL</tt> bit.
   */
  public void unsetFinal()
  {
    this.unsetModifier(Modifier.FINAL);
  }

  /**
   * Unsets the <tt>INTERFACE</tt> bit.
   */
  public void unsetInterface()
  {
    this.unsetModifier(Modifier.INTERFACE);
  }

  /**
   * Unsets the <tt>NATIVE</tt> bit.
   */
  public void unsetNative()
  {
    this.unsetModifier(Modifier.NATIVE);
  }

  /**
   * Unsets the <tt>PRIVATE</tt> bit.
   */
  public void unsetPrivate()
  {
    this.unsetModifier(Modifier.PRIVATE);
  }

  /**
   * Unsets the <tt>PROTECTED</tt> bit.
   */
  public void unsetProtected()
  {
    this.unsetModifier(Modifier.PROTECTED);
  }

  /**
   * Unsets the <tt>PUBLIC</tt> bit.
   */
  public void unsetPublic()
  {
    this.unsetModifier(Modifier.PUBLIC);
  }

  /**
   * Unsets the <tt>STATIC</tt> bit.
   */
  public void unsetStatic()
  {
    this.unsetModifier(Modifier.STATIC);
  }

  /**
   * Unsets the <tt>STRICT</tt> bit.
   */
  public void unsetStrict()
  {
    this.unsetModifier(Modifier.STRICT);
  }

  /**
   * Unsets the <tt>SYNCHRONIZED</tt> bit.
   */
  public void unsetSynchronized()
  {
    this.unsetModifier(Modifier.SYNCHRONIZED);
  }

  /**
   * Unsets the <tt>TRANSIENT</tt> bit.
   */
  public void unsetTransient()
  {
    this.unsetModifier(Modifier.TRANSIENT);
  }

  /**
   * Unsets the <tt>VOLATILE</tt> bit.
   */
  public void unsetVolatile()
  {
    this.unsetModifier(Modifier.VOLATILE);
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  /**
   * Sets the bits in the underlying int that are specified by mod.
   */
  protected void setModifier(int mod)
  {
    this.setBits(this.getBits() | mod);
  }

  /**
   * Unsets the bits in the underlying int that are specified by mod.
   */
  protected void unsetModifier(int mod)
  {
    this.setBits(this.getBits() & ~mod);
  }

  protected void setBits(int newValue)
  {
    this.bits = newValue;
  }
}

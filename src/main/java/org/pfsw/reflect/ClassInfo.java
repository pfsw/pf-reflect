// ===========================================================================
// CONTENT  : CLASS ClassInfo
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.5 - 21/05/2017
// HISTORY  :
//  25/01/2003  mdu  CREATED
//	27/02/2007	mdu		adedd		-->	isAssignableFrom(), isAssignableTo(), isInstance()
//	16/11/2007	mdu		added		-->	getInstance()
//	17/11/2010	mdu	changed		-->	to generic type
//  21/05/2017  mdu   added   --> createSingleton(T instance)
//
// Copyright (c) 2003-2017, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Array;

/**
 * Contains the name of a class and the class itself. It can create new 
 * instances.
 *
 * @author Manfred Duchrow
 * @version 1.5
 */
public class ClassInfo<T>
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private String className = null;
  private Class<T> classObject = null;
  private boolean isSingleton = false;
  private T soleInstance = null;

  // =========================================================================
  // CLASS METHODS
  // =========================================================================
  /**
   * Creates a new instance with a singleton.
   * 
   * @param instance The instance to be used as singleton (must not be null).
   * @throws IllegalArgumentException If the given instance is null.
   */
  @SuppressWarnings("unchecked")
  public static <T> ClassInfo<T> createSingleton(T instance)
  {
    ClassInfo<T> info;

    if (instance == null)
    {
      throw new IllegalArgumentException("No null value allowed here");
    }
    info = new ClassInfo<T>((Class<T>)instance.getClass(), true);
    info.setSoleInstance(instance);
    return info;
  }

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with a class name.
   * 
   * @param className The name of the class the new object should represent.
   */
  public ClassInfo(String className)
  {
    super();
    setClassName(className);
  }

  /**
   * Initialize the new instance with a class name and a flag if it is a
   * singleton.
   * 
   * @param className The name of the class the new object should represent.
   * @param singleton if true method getInstance() will return always the identical instance
   */
  public ClassInfo(String className, boolean singleton)
  {
    this(className);
    setIsSingleton(singleton);
  }

  /**
   * Initialize the new instance with a class.
   * 
   * @param aClass The class the new object should represent.
   */
  public ClassInfo(Class<T> aClass)
  {
    super();
    setClassObject(aClass);
  }

  /**
   * Initialize the new instance with a class.
   * 
   * @param aClass The class the new object should represent.
   * @param singleton if true method getInstance() will return always the identical instance
   */
  public ClassInfo(Class<T> aClass, boolean singleton)
  {
    this(aClass);
    setIsSingleton(singleton);
  }

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================

  public boolean isSingleton()
  {
    return this.isSingleton;
  }

  public void setIsSingleton(boolean newValue)
  {
    this.isSingleton = newValue;
  }

  /**
   * Determines if the class or interface represented by this ClassInfo object is 
   * either the same as, or is a superclass or superinterface of, the class 
   * or interface represented by the specified Class parameter. 
   * It returns true if so; otherwise it returns false. 
   * If this ClassInfo object represents a primitive type, this method returns 
   * true if the specified Class parameter is exactly this Class object; 
   * otherwise it returns false.
   * 
   * @param type the Class object to be checked
   */
  public boolean isAssignableFrom(Class<?> type)
  {
    if (type == null)
    {
      return false;
    }
    if (getClassObject() == null)
    {
      return false;
    }
    return getClassObject().isAssignableFrom(type);
  }

  /**
   * Determines if the class or interface represented by the specified class parameter is 
   * either the same as, or is a superclass or super interface of, the class 
   * or interface represented by this ClassInfo object. 
   * It returns true if so; otherwise it returns false. 
   * If specified class parameter is a primitive type, this method returns 
   * true if this ClassInfo object represents exactly the same Class object; 
   * otherwise it returns false.
   * 
   * @param type the Class object to be checked
   */
  public boolean isAssignableTo(Class<?> type)
  {
    if (type == null)
    {
      return false;
    }
    if (getClassObject() == null)
    {
      return false;
    }
    return type.isAssignableFrom(getClassObject());
  }

  /**
   * Determines if the specified Object is assignment-compatible with the object 
   * represented by this ClassInfo. 
   * This method is the dynamic equivalent of the Java language instanceof  operator. 
   * The method returns true if the specified Object argument is non-null and can be 
   * cast to the reference type represented by this ClassInfo object without raising 
   * a ClassCastException. It returns false otherwise.
   * 
   * @param object the object to check
   */
  public boolean isInstance(Object object)
  {
    if (object == null)
    {
      return false;
    }
    if (getClassObject() == null)
    {
      return false;
    }
    return getClassObject().isInstance(object);
  }

  /**
   * Returns the name of the class, this object represents.
   */
  public String getClassName()
  {
    return className();
  }

  /**
   * Set the name of the class this object should represent.
   * Additionally the corresponding class object is set to null.
   * 
   * @param className A fully qualified class name
   */
  public void setClassName(String className)
  {
    if (className != null)
    {
      className(className);
      classObject(null);
    }
  }

  /**
   * Returns the the class object.
   * If the class can't be found by the class loader, null will be returned.
   */
  public Class<T> getClassObject()
  {
    if (classObject() == null)
    {
      initClassObject();
    }
    return classObject();
  }

  /**
   * Set the class object. The name will be set automatically to the given
   * class's name.
   * 
   * @param aClass A class
   */
  public void setClassObject(Class<T> aClass)
  {
    if (aClass != null)
    {
      classObject(aClass);
      className(aClass.getName());
    }
  }

  /**
   * Returns either a new instance of the underlying class or always the 
   * identical instance if it is defined to be a singleton.
   * 
   * @see #isSingleton()
   * @see #setIsSingleton(boolean)
   * @return An instance of the underlying class
   */
  public T getInstance()
  {
    if (isSingleton())
    {
      if (getSoleInstance() == null)
      {
        setSoleInstance(createInstance());
      }
      return getSoleInstance();
    }
    return createInstance();
  }

  /**
   * Returns a new instance of the class or null in any case of error.
   * If more detailed information about a failed instance creation is
   * necessary, the better method is <code>newInstance</code>.
   * 
   * @see #newInstance()
   */
  public T createInstance()
  {
    try
    {
      return newInstance();
    }
    catch (Exception ex)
    {
      handleException(ex);
    }
    return null;
  }

  /**
   * Returns a new instance of the class.
   * 
   * @see #createInstance()
   * @throws ClassNotFoundException If the class represented by this object can't be found
   * @throws InstantiationException If no none-argument constructor is available/visible
   * @throws IllegalAccessException If for security reasons the instance creation of this class prohibited
   */
  public T newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    Class<T> aClass;
    aClass = getClassObject();
    if (aClass == null)
    {
      throw new ClassNotFoundException(className());
    }
    return aClass.newInstance();
  }

  /**
   * Returns a new array of the type represented by this ClassInfo. Returns
   * Object[0] if this ClassInfo does not represent a valid and loadable class.
   *  
   * @param size The number of elements the new array whould have (must not be negative)
   * @return the new array
   * @throws NullPointerException if the specified componentType parameter is null
   * @throws IllegalArgumentException if componentType is Void.TYPE
   * @throws NegativeArraySizeException if the specified length is negative
   */
  @SuppressWarnings("unchecked")
  public T[] newArray(int size)
  {
    if (getClassObject() != null)
    {
      return (T[])Array.newInstance(getClassObject(), size);
    }
    return (T[])EMPTY_OBJECT_ARRAY;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return getClass().getName() + "(" + getClassName() + ")";
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  @SuppressWarnings("unchecked")
  protected void initClassObject()
  {
    Class<T> aClass;

    aClass = (Class<T>)ReflectUtil.current().findClass(getClassName());
    if (aClass != null)
    {
      classObject(aClass);
    }
  }

  @SuppressWarnings("unused")
  protected void handleException(Exception exception)
  {
    // Currently nothing - just ignore
  }

  protected String className()
  {
    return this.className;
  }

  protected void className(String newValue)
  {
    this.className = newValue;
  }

  protected Class<T> classObject()
  {
    return this.classObject;
  }

  protected void classObject(Class<T> newValue)
  {
    this.classObject = newValue;
  }

  protected T getSoleInstance()
  {
    return this.soleInstance;
  }

  protected void setSoleInstance(T newValue)
  {
    this.soleInstance = newValue;
  }

}

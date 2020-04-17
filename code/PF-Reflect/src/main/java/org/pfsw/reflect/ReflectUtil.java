// ===========================================================================
// CONTENT  : CLASS ReflectUtil
// AUTHOR   : Manfred Duchrow
// VERSION  : 2.4 - 28/07/2019
// HISTORY  :
//  27/09/2002  duma  CREATED
//	24/10/2002	duma	added		-> isDefaultVisibility()
//	03/07/2004	mdu		bugfix	-> findMethod() now checks isAssignable() for all argument types
//	03/06/2006	mdu		changed	-> visibility of constructor to protected
//	25/02/2007	mdu		added		-> hasPublicMethod()
//	16/11/2007	mdu		changed	-> findClass()
//	21/03/2008	mdu		added		-> class loader based instance
//	19/12/2008	mdu		added		-> getInstancesOf(), implementsInterface(), indexOf()
//	13/11/2009	mdu		added		-> toArray()
//	15/01/2012	mdu		added 	-> getAnnotationValueFrom()
//	12/08/2012	mdu		added 	-> getAllTypesOf()
//  27/07/2014  mdu   changed -> signatures of method finder methods to use varargs rather than arrays
//  18/03/2017  mdu   added   -> asObjectProperty(), asObjectProperties()
//  28/07/2019  mdu   added   -> RU, getEnumValueOf()
//
// Copyright (c) 2002-2019, by Manfred Duchrow. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The current instance (Singleton) of this class can be accessed by method
 * <em>current()</em>.  <p>
 * It provides convenience methods on top of the normal standard Java
 * reflection API. However, it allows access to fields, methods and constructors
 * regardless of their visibility (i.e. private, protected, default, public).
 * Of course ignoring the visibility is only possible in environments that
 * have not Java 2 Security turned on. With a security manager present such
 * access to normally invisible members will cause an exception. 
 *
 * @author Manfred Duchrow
 * @version 2.4
 */
public class ReflectUtil
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  /**
   * An instance of this class which can be easily included
   * as static import and then used like <code>RU.findClass("java.util.ArrayList")</code>.
   */
  public static final ReflectUtil RU = new ReflectUtil();

  private static final boolean DEBUG = "true".equals(System.getProperty("org.pfsw.reflect.debug", "false"));

  /**
   *  A reusable empty array of type Class[]
   */
  public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];

  /**
   *  A reusable empty array of type Field[]
   */
  public static final Field[] EMPTY_FIELD_ARRAY = new Field[0];

  /**
   *  A reusable empty array of type Method[]
   */
  public static final Method[] EMPTY_METHOD_ARRAY = new Method[0];

  /**
   *  A reusable empty array of type Constructor[]
   */
  public static final Constructor<?>[] EMPTY_CONSTRUCTOR_ARRAY = new Constructor[0];

  private static final int NOT_FOUND = -1;

  // =========================================================================
  // CLASS VARIABLES
  // =========================================================================
  private static ReflectUtil currentInstance = RU;

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private ClassLoader loader = null;

  // =========================================================================
  // CLASS METHODS
  // =========================================================================
  /**
   * Returns an instance this class supports. It uses this calls' class loader.
   */
  public static ReflectUtil current()
  {
    return currentInstance;
  }

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  protected ReflectUtil()
  {
    super();
  }

  /**
   * Initialize the new instance with a different class loader.
   * 
   * @param classLoader The class loader to be used to load classes by name.
   */
  public ReflectUtil(ClassLoader classLoader)
  {
    super();
    this.loader = classLoader;
  }

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
  /**
   * Returns true, if the class with the given name can be found in the
   * classpath.
   */
  public boolean classExists(String className)
  {
    return (findClass(className) != null);
  }

  /**
   * Returns the classes corresponding to the given (full qualified) class names.
   * 
   * @param classNames The full qualified names of the classes to look for.
   * @return An array with the same number of elements as the input parameter containing
   * the class object corresponding to the given class names.
   * @throws ClassNotFoundException If any of the classes cannot be found.
   */
  public Class<?>[] findClasses(String... classNames) throws ClassNotFoundException
  {
    Class<?>[] classes;
    Class<?> clazz;

    if (classNames == null)
    {
      return new Class[0];
    }
    classes = new Class[classNames.length];
    for (int i = 0; i < classNames.length; i++)
    {
      clazz = findClass(classNames[i]);
      if (clazz == null)
      {
        throw new ClassNotFoundException(classNames[i]);
      }
      classes[i] = clazz;
    }
    return classes;
  }

  /**
   * Returns the class object corresponding to the given class name or null,
   * if the class can't be found. For primitive types the names "boolean",
   * "int", "float" and so on can be used. The corresponding Boolean.TYPE,
   * Integer.TYPE, Float.TYPE and so on will be returned.
   * 
   * @param className The full qualified name of the class
   */
  public Class<?> findClass(String className)
  {
    Class<?> clazz = null;

    if (className == null)
    {
      return null;
    }
    if (className.equals("boolean"))
    {
      return Boolean.TYPE;
    }
    if (className.equals("int"))
    {
      return Integer.TYPE;
    }
    if (className.equals("long"))
    {
      return Long.TYPE;
    }
    if (className.equals("char"))
    {
      return Character.TYPE;
    }
    if (className.equals("byte"))
    {
      return Byte.TYPE;
    }
    if (className.equals("short"))
    {
      return Short.TYPE;
    }
    if (className.equals("float"))
    {
      return Float.TYPE;
    }
    if (className.equals("double"))
    {
      return Double.TYPE;
    }

    try
    {
      clazz = getLoader().loadClass(className);
    }
    catch (ClassNotFoundException e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
      return null;
    }
    return clazz;
  }

  /**
   * Tries to create an instance of the class with the given name. It uses the
   * given caller to find the class (via forName()) to ensure that the correct
   * classloader is used. If the caller is null it uses this class for the lookup.
   * 
   * @param className The name of the class to instantiate.
   * @param caller The object which class will be used to search for the className.
   * @param params 0-n parameters that define the constructor to be used.
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public Object createInstanceOf(String className, Object caller, Object... params)
  {
    return createInstanceOfType(Object.class, className, caller, params);
  }

  /**
   * Tries to create an instance of the class with the given name. It uses the
   * given caller to find the class (via forName()) to ensure that the correct
   * classloader is used. If the caller is null it uses this class for the lookup.
   * 
   * @param type The expected type of the instance (could be an interface or also java.lang.Object) 
   * @param className The name of the class to instantiate.
   * @param caller The object which class will be used to search for the className.
   * @param params 0-n parameters that define the constructor to be used.
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  @SuppressWarnings("unchecked")
  public <T> T createInstanceOfType(Class<T> type, String className, Object caller, Object... params)
  {
    Class<?> callerClass;
    Class<T> aClass;

    if (className == null)
    {
      return null;
    }
    if (caller == null)
    {
      callerClass = getClass();
    }
    else
    {
      callerClass = caller.getClass();
    }
    try
    {
      aClass = (Class<T>)callerClass.forName(className);
    }
    catch (ClassNotFoundException e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
      return null;
    }
    return newInstanceOf(aClass, params);
  }

  /**
   * Returns all interfaces the given object's class implements.
   * The resulting array contains all directly implemented interfaces as well
   * as those that are only indirectly implemented by extension.
   * If no interface is found an empty array will be returned. 
   */
  public Class<?>[] getInterfacesOf(Object object)
  {
    if (object == null)
    {
      return EMPTY_CLASS_ARRAY;
    }
    return getInterfacesOf(object.getClass());
  }

  /**
   * Returns all interfaces the given class implements.
   * The resulting array contains all directly implemented interfaces as well
   * as those that are only indirectly implemented by extension. 
   * If no interface is found an empty array will be returned. 
   */
  public Class<?>[] getInterfacesOf(Class<?> aClass)
  {
    Set<Class<?>> result;

    result = new HashSet<Class<?>>(20);
    if (aClass != null)
    {
      collectInterfaces(result, aClass);
    }
    if (result.isEmpty())
    {
      return EMPTY_CLASS_ARRAY;
    }
    return result.toArray(new Class<?>[result.size()]);
  }

  /**
   * Returns the method with the specified name if it exists in the given
   * class. The method will only be found if it has all modifiers set that
   * are defined in parameter modifiers.
   * 
   * @param aClass The class in which to search the method 
   * @param methodName The name of the searched method
   * @param paramTypes The types of the method's parameters (null and Class[0] ar the same)
   * @param modifiers The modifiers that must be set at the method too look for
   * 
   * @return The method or null if not found
   * @see #getMethod(Object, String, Class[])
   * @see #findMethod(Class, String, Class[])
   */
  public Method findMethod(Class<?> aClass, String methodName, Class<?>[] paramTypes, int modifiers)
  {
    Method method = null;
    Method[] methods = null;
    Class<?>[] types = null;
    Class<?>[] lookupParamTypes = null;
    Class<?> superclass = null;
    int index = 0;

    if (paramTypes == null)
    {
      lookupParamTypes = EMPTY_CLASS_ARRAY;
    }
    else
    {
      lookupParamTypes = paramTypes;
    }

    methods = aClass.getDeclaredMethods();
    for (index = 0; index < methods.length; index++)
    {
      if (methods[index].getName().equals(methodName))
      {
        types = methods[index].getParameterTypes();
        if (compatibleTypes(lookupParamTypes, types))
        {
          // All specified modifiers must be set
          if ((methods[index].getModifiers() & modifiers) == modifiers)
          {
            return methods[index];
          }
        }
      }
    }
    superclass = aClass.getSuperclass();
    if (superclass != null)
    {
      method = findMethod(superclass, methodName, lookupParamTypes, modifiers);
    }
    return method;
  }

  /**
   * Returns the method with the specified name if it exists in the given
   * class or any of its superclasses regardless of the method's visibility.
   * 
   * @param aClass The class in which to search the method 
   * @param methodName The name of the searched method
   * @param paramTypes The types of the method's parameters (null and Class[0] ar the same)
   * @return The method or null if not found
   * @see #getMethod(Object, String, Class[])
   */
  public Method findMethod(Class<?> aClass, String methodName, Class<?>... paramTypes)
  {
    return findMethod(aClass, methodName, paramTypes, 0);
  }

  /**
   * Returns the method with the specified name if it exists in the class
   * of the given object or any of its superclasses regardless the 
   * method's visibility.
   * 
   * @param object The object in which the method should be found
   * @param methodName The name of the searched method
   * @param paramTypes The types of the method's parameters (null and Class[0] are the same)
   * @return The method or null if not found
   * @see #findMethod(Class, String, Class[])
   */
  public Method getMethod(Object object, String methodName, Class<?>... paramTypes)
  {
    Class<?> clazz;

    if (object == null)
    {
      return null;
    }
    if (object instanceof Class)
    {
      clazz = (Class<?>)object;
    }
    else
    {
      clazz = object.getClass();
    }
    return findMethod(clazz, methodName, paramTypes);
  }

  /**
   * Returns a list of all methods the given objects contains.
   * This includes all inherited methods, regardless of their visibility or
   * other modifiers.
   * 
   * @param obj The object of which to get the methods 
   * @return A List of java.lang.reflect.Method
   */
  public List<Method> getMethodsOf(Object obj)
  {
    Class<?> aClass;

    aClass = obj.getClass();
    return getMethodsOf(aClass);
  }

  /**
   * Returns a list of all methods the given class contains.
   * This includes all inherited methods, regardless of their visibility or
   * other modifiers.
   * 
   * @param aClass The class of which to get the methods 
   * @return A List of java.lang.reflect.Method
   */
  public List<Method> getMethodsOf(Class<?> aClass)
  {
    List<Method> methods = new ArrayList<Method>(40);

    addInheritedMethods(methods, aClass.getSuperclass());
    addMethodsToList(methods, aClass.getDeclaredMethods());
    return methods;
  }

  /**
   * Returns a list of all constructors the class of the given object contains.
   * This includes all constructors, regardless of their visibility or
   * other modifiers. Even if it has only the default constructor it will
   * be returned.
   * 
   * @param object The object of which to get the constructors 
   * @return A List of java.lang.reflect.Constructor
   */
  @SuppressWarnings("rawtypes")
  public List<Constructor> getConstructorsOf(Object object)
  {
    return getConstructorsOf(object.getClass());
  }

  /**
   * Returns a list of all constructors the given class contains.
   * This includes all constructors, regardless of their visibility or
   * other modifiers. Even if it has only the default constructor it will
   * be returned.
   * 
   * @param aClass The class of which to get the constructors 
   * @return A List of java.lang.reflect.Constructor
   */
  @SuppressWarnings("rawtypes")
  public List<Constructor> getConstructorsOf(Class<?> aClass)
  {
    List<Constructor> constructors = new ArrayList<Constructor>(10);
    Constructor[] constructorArray;

    constructorArray = aClass.getDeclaredConstructors();
    for (int i = 0; i < constructorArray.length; i++)
    {
      constructors.add(constructorArray[i]);
    }
    return constructors;
  }

  /**
   * Returns a list of all fields the given objects contains.
   * This includes all inherited fields, regardless their visibility or
   * other modifier states.
   * 
   * @param obj The object to get the fields from
   * @return A List of java.lang.reflect.Field
   */
  public List<Field> getFieldsOf(Object obj)
  {
    Class<?> aClass;

    aClass = obj.getClass();
    return getFieldsOf(aClass);
  }

  /**
   * Returns a list of all properties the given objects contains.
   * This includes all inherited fields, regardless their visibility or
   * other modifier states.
   * 
   * @param object The object to get the fields from.
   * @return A List of object properties that correspond to the declared fields of the given object.
   */
  public List<IObjectProperty> getObjectPropertiesOf(Object object)
  {
    return asObjectProperties(getFieldsOf(object));
  }

  /**
   * Returns a list of all properties the given objects contains and that match
   * the specified filter criteria.
   * 
   * @param object The object to get the fields from (must not be null).
   * @param filter The filter criteria the object properties must match (must not be null).
   * @return A List of filtered object properties.
   */
  public List<IObjectProperty> getObjectPropertiesOf(Object object, IObjectPropertyFilter filter)
  {
    List<IObjectProperty> result;

    result = getObjectPropertiesOf(object);
    return onlyMatching(result, filter);
  }

  /**
   * Returns a list of all fields the given class contains.
   * This includes all inherited fields, regardless their visibility or
   * other modifier states.
   * 
   * @param aClass The class to get the fields from
   * @return A List of java.lang.reflect.Field
   */
  public List<Field> getFieldsOf(Class<?> aClass)
  {
    List<Field> fields = new ArrayList<Field>(30);

    addInheritedFields(fields, aClass.getSuperclass());
    addFieldsToList(fields, aClass.getDeclaredFields());
    return fields;
  }

  /**
   * Returns a list of all properties the given objects contains.
   * This includes all inherited fields, regardless their visibility or
   * other modifier states.
   * 
   * @param aClass The class to get the fields from
   * @return A List of object properties that correspond to the declared fields of the given object.
   */
  public List<IObjectProperty> getObjectPropertiesOf(Class<?> aClass)
  {
    return asObjectProperties(getFieldsOf(aClass));
  }

  /**
   * Returns a list of all properties of the given class and that match
   * the specified filter criteria.
   * 
   * @param aClass The class to get the fields from (must not be null).
   * @param filter The filter criteria the object properties must match (must not be null).
   * @return A List of filtered object properties.
   */
  public List<IObjectProperty> getObjectPropertiesOf(Class<?> aClass, IObjectPropertyFilter filter)
  {
    List<IObjectProperty> result;

    result = getObjectPropertiesOf(aClass);
    return onlyMatching(result, filter);
  }

  /**
   * Returns the field with the specified name in the given class and all
   * the specified modifiers set.
   * If the field can't be found, null is returned.
   * 
   * @param aClass The class that might contain the field
   * @param name The name of the field to look for
   * @param modifiers The modifiers the field must have set
   * 
   * @throws IllegalArgumentException If aClass or name is null
   */
  public Field findField(Class<?> aClass, String name, int modifiers)
  {
    List<Field> fields;
    Iterator<Field> iter;
    Field field;

    if ((aClass == null) || (name == null))
    {
      throw new IllegalArgumentException("Given class or field name is null");
    }

    fields = getFieldsOf(aClass);
    iter = fields.iterator();
    while (iter.hasNext())
    {
      field = iter.next();
      if (name.equals(field.getName()))
      {
        if ((field.getModifiers() & modifiers) == modifiers)
        {
          return field;
        }
      }
    }
    return null;
  }

  /**
   * Returns the field with the specified name in the given class.
   * If the field can't be found, null is returned.
   * 
   * @param aClass The class that might contain the field
   * @param name The name of the field to look for
   * @throws IllegalArgumentException If aClass or name is null
   */
  public Field findField(Class<?> aClass, String name)
  {
    return findField(aClass, name, 0);
  }

  /**
   * Returns the field with the specified name in the given object.
   * If the field can't be found, null is returned.
   * <p>
   * If the given object is an instance of Class, the field will be looked-up
   * in the type represented by the class and not in the class object itself.
   * 
   * @param object The object that (perhaps) contains the field. This can also be an instance of Class.
   * @param name The name of the field to look for
   * @throws IllegalArgumentException If object or name is null
   */
  public Field getField(Object object, String name)
  {
    Class<?> clazz;

    if ((object == null) || (name == null))
    {
      throw new IllegalArgumentException("Given object or field name is null");
    }
    if (object instanceof Class)
    {
      clazz = (Class<?>)object;
    }
    else
    {
      clazz = object.getClass();
    }
    return findField(clazz, name);
  }

  /**
   * Returns the current value of the field with the specified name in the 
   * given object.
   * 
   * @param obj The object that contains the field (must not be null).
   * @param name The name of the field to look for (must not be null).
   * @throws UnknownFieldException If the field is unknown in the given object.
   * @throws IllegalArgumentException If obj or name is null.
   */
  public <T> T getValueOf(Object obj, String name)
  {
    Field field;

    field = getField(obj, name);
    if (field == null)
    {
      throw new UnknownFieldException("Field name: %s", name);
    }
    return getValueOf(obj, field);
  }

  /**
   * Returns the current value of the field in the given object.
   * 
   * @param obj The object that contains the field (must not be null).
   * @param field The field to look for (must not be null).
   * @throws IllegalArgumentException If obj or name is null.
   */
  @SuppressWarnings("unchecked")
  public <T> T getValueOf(final Object obj, final Field field)
  {
    T value = null;
    boolean saveAccessibility = false;

    if (field == null)
    {
      throw new IllegalArgumentException("Field must not be null");
    }

    saveAccessibility = field.isAccessible();
    field.setAccessible(true);
    try
    {
      value = (T)field.get(obj);
    }
    catch (RuntimeException e)
    {
      // Ignore this, because null values are allowed !
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
    catch (IllegalAccessException ex1)
    {
      throw new IllegalAccessError(ex1.getMessage());
    }
    finally
    {
      field.setAccessible(saveAccessibility);
    }

    return value;
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param targetObject The object that contains the field.
   * @param name The name of the field to set.
   * @param value The value to assign to the field.
   * @throws UnknownFieldException If the field is unknown in the given object.
   * @throws IllegalArgumentException If obj or name is null.
   */
  public void setValueOf(Object targetObject, String name, Object value)
  {
    setValueOf(targetObject, name, value, false);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, char value)
  {
    setValueOf(obj, name, new Character(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, int value)
  {
    setValueOf(obj, name, new Integer(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, byte value)
  {
    setValueOf(obj, name, new Byte(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object.
   * @throws IllegalArgumentException If obj or name is null.
   */
  public void setValueOf(Object obj, String name, boolean value)
  {
    setValueOf(obj, name, value ? Boolean.TRUE : Boolean.FALSE, true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, long value)
  {
    setValueOf(obj, name, new Long(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, short value)
  {
    setValueOf(obj, name, new Short(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, double value)
  {
    setValueOf(obj, name, new Double(value), true);
  }

  /**
   * Sets the value of the field with the specified name to the 
   * given value.
   * 
   * @param obj The object that contains the field
   * @param name The name of the field to set
   * @param value The value to assign to the field
   * @throws UnknownFieldException If the field is unknown in the given object
   * @throws IllegalArgumentException If obj or name is null
   */
  public void setValueOf(Object obj, String name, float value)
  {
    setValueOf(obj, name, new Float(value), true);
  }

  /**
   * Sets the specified field of the given object to the provided value.
   * 
   * @param obj The object that contains the field (must not be null).
   * @param field The field to be modified (must not be null).
   * @param value The new value to assign to the field (may be null).
   */
  public void setValueOf(final Object obj, final Field field, final Object value)
  {
    setValueOf(obj, field, value, field.getType().isPrimitive());
  }

  /**
   * Returns true if a public method with the specified name exists in
   * the given class or any of its superclasses.
   * 
   * @param aClass The class in which to look for the method 
   * @param methodName The name of the method to look for
   * @param paramTypes The types of the method's parameters (null and Class[0] ar the same)
   * 
   * @return true if the method was found and is public
   */
  public boolean hasPublicMethod(Class<?> aClass, String methodName, Class<?>... paramTypes)
  {
    Method method;

    if (aClass == null)
    {
      return false;
    }
    method = findMethod(aClass, methodName, paramTypes, Modifier.PUBLIC);
    return (method != null);
  }

  /**
   * Returns true if a public method with the specified name exists in the class
   * of the given object or any of its superclasses.
   * 
   * @param obj The object in which the method should be found
   * @param methodName The name of the method to look for
   * @param paramTypes The types of the method's parameters (null and Class[0] ar the same)
   * 
   * @return true if the method was found and is public
   */
  public boolean hasPublicMethod(Object obj, String methodName, Class<?>... paramTypes)
  {
    if (obj == null)
    {
      return false;
    }
    return hasPublicMethod(obj.getClass(), methodName, paramTypes);
  }

  /**
   * Returns true if the given field is not null and is package visible.
   */
  public boolean isPackageVisible(Field field)
  {
    if (field == null)
    {
      return false;
    }
    return isDefaultVisibility(field.getModifiers());
  }

  /**
   * Returns true if the given method is not null and is package visible.
   */
  public boolean isPackageVisible(Method method)
  {
    if (method == null)
    {
      return false;
    }
    return isDefaultVisibility(method.getModifiers());
  }

  /**
   * Returns true, if the visibility defined by the given modifiers
   * is the default (package) visibility.
   */
  public boolean isDefaultVisibility(int modifiers)
  {
    final int explicitVisibility = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    return ((modifiers & explicitVisibility) == 0);
  }

  /**
   * Returns the visibility defined by the given modifiers as string.
   * That is, "" for the default (package) visibility and "public",
   * "protected", "private" for the others.
   */
  public String getVisibility(int modifiers)
  {
    if (Modifier.isPublic(modifiers))
    {
      return "public";
    }
    if (Modifier.isProtected(modifiers))
    {
      return "protected";
    }
    if (Modifier.isPrivate(modifiers))
    {
      return "private";
    }
    return "";
  }

  /**
   * If the given class has a constructor without parameters it will be used
   * to create a new instance. The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param aClass The class of which a new instance must be created (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public <T> T newInstance(Class<T> aClass)
  {
    return newInstance(aClass, null);
  }

  /**
   * If the given class has a constructor with one parameter type matching the
   * given parameter it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param aClass The class of which a new instance must be created (must not be null)
   * @param param The initialization parameter for the constructor (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public <T> T newInstance(Class<T> aClass, Object param)
  {
    return newInstance(aClass, new Object[] { param });
  }

  /**
   * If the given class has a constructor with two parameter type matching the
   * given parameters it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param aClass The class of which a new instance must be created (must not be null)
   * @param param1 The first initialization parameter for the constructor (must not be null)
   * @param param2 The second initialization parameter for the constructor (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public <T> T newInstance(Class<T> aClass, Object param1, Object param2)
  {
    return newInstance(aClass, new Object[] { param1, param2 });
  }

  /**
   * If the given class has a constructor with types corresponding to the given
   * parameters it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * <br>
   * This method is exactly the same as {@link #newInstanceOf(Class, Object...)}.
   * It is just kept for compatibility.
   * 
   * @param aClass The class of which a new instance must be created (must not be null)
   * @param params The initialization parameters for the constructor (may be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public <T> T newInstance(Class<T> aClass, Object[] params)
  {
    return newInstanceOf(aClass, params);
  }

  /**
   * If the given class has a constructor with types corresponding to the given
   * parameters it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * <br>
   * This method is exactly the same as {@link #newInstance(Class, Object[])}.
   * It is just uses the varargs declaration that is available since Java 1.5.
   * 
   * @param aClass The class of which a new instance must be created (must not be null)
   * @param params The initialization parameters for the constructor (may be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public <T> T newInstanceOf(Class<T> aClass, Object... params)
  {
    Constructor<T> constructor;
    boolean accessible;
    Exception ex = null;
    Class<?>[] paramTypes;

    paramTypes = getTypesFromParameters(params);
    constructor = findConstructor(aClass, paramTypes);
    if (constructor != null)
    {
      accessible = constructor.isAccessible();
      constructor.setAccessible(true);
      try
      {
        return constructor.newInstance(params);
      }
      catch (Exception e)
      {
        ex = e;
      }
      finally
      {
        constructor.setAccessible(accessible);
      }
      throw new ReflectionException(ex);
    }
    return null;
  }

  /**
   * Tries to find the class with the given name and to create an instance of it.
   * If the given class has a constructor without parameters it will be used
   * to create a new instance. The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param className The name of the class of which a new instance must be created (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public Object newInstance(String className)
  {
    return newInstance(className, null);
  }

  /**
   * Tries to find the class with the given name and to create an instance of it.
   * If the given class has a constructor with one parameter type matching the
   * given parameter it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param className The name of the class of which a new instance must be created (must not be null)
   * @param param The initialization parameter for the constructor (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public Object newInstance(String className, Object param)
  {
    return newInstance(className, new Object[] { param });
  }

  /**
   * Tries to find the class with the given name and to create an instance of it.
   * If the given class has a constructor with two parameter type matching the
   * given parameters it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param className The name of the class of which a new instance must be created (must not be null)
   * @param param1 The first initialization parameter for the constructor (must not be null)
   * @param param2 The second initialization parameter for the constructor (must not be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public Object newInstance(String className, Object param1, Object param2)
  {
    return newInstance(className, new Object[] { param1, param2 });
  }

  /**
   * Tries to find the class with the given name and to create an instance of it.
   * If the found class has a constructor with types corresponding to the given
   * parameters it will be used to create a new instance. 
   * The constructor will be called regardless of
   * its visibility. That is, even private, protected and default constructors
   * are used to create the new instance.
   * 
   * @param className The name of the class of which a new instance must be created (must not be null)
   * @param params The initialization parameters for the constructor (may be null)
   * @return The new created instance or null if no matching constructor can be found 
   * @throws ReflectionException A runtime exception that wraps the original exception.
   */
  public Object newInstance(String className, Object[] params)
  {
    Class<?> clazz = null;

    try
    {
      clazz = getLoader().loadClass(className);
    }
    catch (ClassNotFoundException e)
    {
      throw new ReflectionException(e);
    }
    return newInstance(clazz, params);
  }

  /**
   * Returns the constructor of the given class for the specified parameter
   * types of null if no such constructor can be found.
   * The visibility of the constructor is ignored. A private constructor
   * can be used with the newInstance() methods of this class to create
   * instances. 
   * 
   * @return A constructor or null
   * @see #newInstance(Class)
   */
  @SuppressWarnings("unchecked")
  public <T> Constructor<T> findConstructor(final Class<T> aClass, final Class<?>[] paramTypes)
  {
    Constructor<T>[] constructors;
    Class<?>[] types;
    Class<?>[] expectedTypes;

    expectedTypes = (paramTypes == null) ? new Class[0] : paramTypes;

    constructors = (Constructor<T>[])aClass.getDeclaredConstructors();
    for (int i = 0; i < constructors.length; i++)
    {
      types = constructors[i].getParameterTypes();
      if (compatibleTypes(expectedTypes, types))
      {
        return constructors[i];
      }
    }
    return null;
  }

  /**
   * Returns an array of the types (classes) corresponding to the parameter
   * objects given to this methods.
   * 
   * @param params The parameters to derive the types from (may be null)
   * @return The types or an empty array if params == null
   */
  public Class<?>[] getTypesFromParameters(Object... params)
  {
    Class<?>[] types;

    if (params == null)
    {
      return EMPTY_CLASS_ARRAY;
    }
    types = new Class[params.length];
    for (int i = 0; i < params.length; i++)
    {
      types[i] = getTypeOf(params[i]);
    }
    return types;
  }

  /**
   * Returns the type of the given object. For the special objects like
   * Integer, Boolean, ... it returns the primitive type.
   * If the given object is null it returns Object.class
   * 
   * @param object The object of which to determine the type 
   */
  public Class<?> getTypeOf(Object object)
  {
    if (object == null)
    {
      return Object.class;
    }
    if (object instanceof Integer)
      return Integer.TYPE;
    if (object instanceof Boolean)
      return Boolean.TYPE;
    if (object instanceof Long)
      return Long.TYPE;
    if (object instanceof Short)
      return Short.TYPE;
    if (object instanceof Double)
      return Double.TYPE;
    if (object instanceof Float)
      return Float.TYPE;
    if (object instanceof Character)
      return Character.TYPE;
    if (object instanceof Byte)
      return Byte.TYPE;

    return object.getClass();
  }

  /**
   * Returns all types of the given object or an empty collection if the object is null.
   * The returned array contains the object's class and all interfaces it implements,
   * including all inherited interfaces.
   * 
   * @param object The object to derive all types of
   */
  @SuppressWarnings("rawtypes")
  public List<Class> getAllTypesOf(Object object)
  {
    Class<?> baseType;
    List<Class> types;

    types = new ArrayList<Class>();
    if (object == null)
    {
      return types;
    }
    baseType = getTypeOf(object);
    types.add(baseType);
    if (!baseType.isPrimitive())
    {
      types.addAll(Arrays.asList(getInterfacesOf(object.getClass())));
    }
    return types;
  }

  /**
   * Returns true if the given class is found in the provided class array.
   */
  public boolean contains(Class<?>[] classes, Class<?> aClass)
  {
    return indexOf(classes, aClass) >= 0;
  }

  /**
   * Returns the index of the given class in the provided class array or -1
   * if the class is not in the array.
   */
  public int indexOf(Class<?>[] classes, Class<?> aClass)
  {
    if (isNullOrEmpty(classes) || (aClass == null))
    {
      return NOT_FOUND;
    }
    for (int i = 0; i < classes.length; i++)
    {
      if (aClass.equals(classes[i]))
      {
        return i;
      }
    }
    return NOT_FOUND;
  }

  /**
   * Returns true if the class of the given object implements the 
   * specified interfaceType.
   */
  public boolean implementsInterface(Object object, Class<?> anInterface)
  {
    if ((object == null) || (anInterface == null))
    {
      return false;
    }
    return implementsInterface(object.getClass(), anInterface);
  }

  /**
   * Returns true if the given class implements the specified interfaceType.
   */
  public boolean implementsInterface(Class<?> aClass, Class<?> anInterface)
  {
    Class<?>[] interfaces;

    if ((aClass == null) || (anInterface == null))
    {
      return false;
    }
    if (aClass.isInterface() || !anInterface.isInterface())
    {
      return false;
    }
    interfaces = getInterfacesOf(aClass);
    return contains(interfaces, anInterface);
  }

  /**
   * Returns an array containing all objects that are returned by the specified
   * method name executed against each element in the given collection.
   * 
   * @param coll The collection with elements of which the objects have to be extracted
   * @param methodName The name of the method to be executed on the collections's elements (must not be null)
   * This method must have no argument and must return an object of the specified elementType.
   * @param elementType The type of the elements returned by the method and of the elements in the return array
   * @return Returns an array of the specified elementType or null if the given collection is null. 
   */
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(Collection<?> coll, String methodName, Class<T> elementType)
  {
    T[] objects;
    Object element;
    Iterator<?> iter;
    int i = 0;

    if (coll == null)
    {
      return null;
    }

    objects = (T[])Array.newInstance(elementType, coll.size());
    iter = coll.iterator();
    while (iter.hasNext())
    {
      element = iter.next();
      if ("this".equals(methodName) && (elementType.isInstance(element)))
      {
        objects[i] = (T)element;
      }
      else
      {
        objects[i] = (T)Dynamic.invoke(element, methodName);
      }
      i++;
    }
    return objects;
  }

  /**
   * Returns a string array containing all objects that are returned by the specified
   * method name executed against each element in the given collection.
   * 
   * @param coll The collection with elements of which the strings have to be extracted
   * @param methodName The name of the method to be executed on the collections's elements (must not be null).
   * This method must have no argument and must return a string.
   * @return Returns a string array or null if the given collection is null. 
   */
  public String[] toStringArray(Collection<?> coll, String methodName)
  {
    return toArray(coll, methodName, String.class);
  }

  /**
   * Tries to find the annotation of the specified type and return its "value"
   * as String.
   * 
   * @param aClass The potentially annotated class
   * @param annotationType The annotation to look for
   * @return The value string of the annotation of null if not found
   */
  public String getAnnotationValueFrom(Class<?> aClass, Class<? extends Annotation> annotationType)
  {
    Annotation annotation;

    annotation = aClass.getAnnotation(annotationType);
    if (annotation == null)
    {
      return null;
    }
    return (String)Dynamic.invoke(annotation, "value");
  }

  public IObjectProperty asObjectProperty(Field field)
  {
    return new ObjectField(field);
  }

  public List<IObjectProperty> asObjectProperties(List<Field> fields)
  {
    List<IObjectProperty> objectProperties;

    objectProperties = new ArrayList<IObjectProperty>(fields.size());
    for (Field field : fields)
    {
      objectProperties.add(new ObjectField(field));
    }
    return objectProperties;
  }

  /**
   * Returns a list of all object properties that match the given filter criteria. 
   * 
   * @param properties The list of properties to be filtered (must not be null).
   * @param filter The filter that decides which property goes into the result list (must not be null).
   * @return A List of object properties that correspond to the declared fields of the given object.
   */
  public List<IObjectProperty> onlyMatching(final List<IObjectProperty> properties, final IObjectPropertyFilter filter)
  {
    List<IObjectProperty> result;

    result = new ArrayList<IObjectProperty>(properties.size());
    for (IObjectProperty objectProperty : properties)
    {
      if (filter.matches(objectProperty))
      {
        result.add(objectProperty);
      }
    }
    return result;
  }

  /**
   * A generic mechanism to get for a given name the corresponding enum constant. 
   * 
   * @param enumType The enum type class (must not be null).
   * @param enumValueName The name of the enum constant to be retrieved (may be null).
   * @return null if enumValueName is null or if no corresponding enum constant can be found,
   *         otherwise it returns the enum constant.
   * @throws ReflectionException If the given type is no enum type or accessing the enum constants 
   *        caused a problem.
   */
  public <E> E getEnumValueOf(Class<E> enumType, String enumValueName)
  {
    E[] enumConstants;
    String enumConstName;

    if (enumValueName == null)
    {
      return null;
    }

    if (!enumType.isEnum())
    {
      throw new ReflectionException("The given type %s is no enum type!", enumType.getName());
    }

    enumConstants = enumType.getEnumConstants();
    if ((enumConstants != null))
    {
      try
      {
        for (E enumConstant : enumConstants)
        {
          enumConstName = (String)Dynamic.perform(enumConstant, "name");
          if (enumValueName.equals(enumConstName))
          {
            return enumConstant;
          }
        }
      }
      catch (Exception e)
      {
        throw new ReflectionException(e, "Failed to get enum constant for name '%s' in enum type %s", enumValueName, enumType.getName());
      }
    }
    return null;
  }

  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  protected void addMethodsToList(List<Method> methodList, Method[] methods)
  {
    for (int i = 0; i < methods.length; i++)
    {
      methodList.add(methods[i]);
    }
  }

  protected void addInheritedMethods(List<Method> methods, Class<?> aClass) throws SecurityException
  {
    if (aClass != null)
    {
      addInheritedMethods(methods, aClass.getSuperclass());
      addMethodsToList(methods, aClass.getDeclaredMethods());
    }
  }

  protected void addFieldsToList(List<Field> fieldList, Field[] fields)
  {
    for (int i = 0; i < fields.length; i++)
    {
      fieldList.add(fields[i]);
    }
  }

  protected void addInheritedFields(List<Field> fields, Class<?> aClass) throws SecurityException
  {
    if (aClass != null)
    {
      addInheritedFields(fields, aClass.getSuperclass());
      addFieldsToList(fields, aClass.getDeclaredFields());
    }
  }

  protected void setValueOf(Object obj, String name, Object value, boolean isPrimitive)
  {
    Field field;

    field = getField(obj, name);
    if (field == null)
    {
      throw new UnknownFieldException("Field name: %s", name);
    }
    setValueOf(obj, field, value, isPrimitive);
  }

  protected void setValueOf(final Object obj, final Field field, final Object value, final boolean isPrimitive)
  {
    boolean saveAccessibility = false;

    if (field == null)
    {
      throw new NullPointerException("'field' parameter is null");
    }

    saveAccessibility = field.isAccessible();
    field.setAccessible(true);
    try
    {
      if (isPrimitive)
      {
        if (value instanceof Character)
        {
          field.setChar(obj, ((Character)value).charValue());
        }
        else if (value instanceof Integer)
        {
          field.setInt(obj, ((Integer)value).intValue());
        }
        else if (value instanceof Long)
        {
          field.setLong(obj, ((Long)value).longValue());
        }
        else if (value instanceof Boolean)
        {
          field.setBoolean(obj, ((Boolean)value).booleanValue());
        }
        else if (value instanceof Double)
        {
          field.setDouble(obj, ((Double)value).doubleValue());
        }
        else if (value instanceof Float)
        {
          field.setFloat(obj, ((Float)value).floatValue());
        }
        else if (value instanceof Byte)
        {
          field.setByte(obj, ((Byte)value).byteValue());
        }
        else if (value instanceof Short)
        {
          field.setShort(obj, ((Short)value).shortValue());
        }
      }
      else
      {
        field.set(obj, value);
      }
    }
    catch (RuntimeException e)
    {
      // Ignore this, because null values are allowed !
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
    catch (IllegalAccessException ex1)
    {
      throw new IllegalAccessError(ex1.getMessage());
    }
    finally
    {
      field.setAccessible(saveAccessibility);
    }
  }

  /**
   * Returns true if the types of the first array are assignable to the 
   * types of the second array. The second array is immutable.
   */
  protected boolean compatibleTypes(Class<?>[] paramTypes, Class<?>[] signatureTypes)
  {
    if (paramTypes == null)
    {
      return (signatureTypes == null);
    }
    if (signatureTypes == null)
    {
      return false;
    }
    if (paramTypes.length != signatureTypes.length)
    {
      return false;
    }

    for (int i = 0; i < paramTypes.length; i++)
    {
      if (!signatureTypes[i].isAssignableFrom(paramTypes[i]))
      {
        return false;
      }
    }
    return true;
  }

  protected void collectInterfaces(Set<Class<?>> result, Class<?> aClass)
  {
    Class<?>[] interfaces;

    if (aClass == null)
    {
      return;
    }
    if (aClass.isInterface())
    {
      result.add(aClass);
    }
    else
    {
      collectInterfaces(result, aClass.getSuperclass());
    }
    interfaces = aClass.getInterfaces();
    for (int i = 0; i < interfaces.length; i++)
    {
      collectInterfaces(result, interfaces[i]);
    }
  }

  protected boolean isNullOrEmpty(Object[] objects)
  {
    return (objects == null) || (objects.length == 0);
  }

  protected boolean isNullOrEmpty(Collection<?> collection)
  {
    return (collection == null) || (collection.isEmpty());
  }

  /**
   * Returns the externally assigned class loader or if not present the class
   * load of this class.
   */
  protected ClassLoader getLoader()
  {
    if (loader != null)
    {
      return loader;
    }
    return getClass().getClassLoader();
  }

}

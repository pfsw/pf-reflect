// ===========================================================================
// CONTENT  : CLASS XmlReflection
// AUTHOR   : Manfred Duchrow
// VERSION  : 1.0 - 14/01/2012
// HISTORY  :
//  14/01/2012  mdu  CREATED
//
// Copyright (c) 2012, by MDCS. All rights reserved.
// ===========================================================================
package org.pfsw.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Provides a mechanism to create Java object instances from XML meta-data
 * describing the objects to create.<p>
 *
 * @author Manfred Duchrow
 * @version 1.0
 */
public class XmlReflection
{
  // =========================================================================
  // CONSTANTS
  // =========================================================================
  private static final ReflectUtil RU = ReflectUtil.current();

  // =========================================================================
  // INSTANCE VARIABLES
  // =========================================================================
  private String attrNameForClass = "class";
  private String tagNameForField = "property";
  private String attrNameForFieldName = "name";
  private String attrNameForFieldValue = "value";

  // =========================================================================
  // CONSTRUCTORS
  // =========================================================================
  /**
   * Initialize the new instance with default values.
   */
  public XmlReflection()
  {
    super();
  }

  // =========================================================================
  // PUBLIC INSTANCE METHODS
  // =========================================================================
  public Object createInstance(final Element xmlElement)
  {
    return createInstance(xmlElement, Object.class);
  }

  public <T> T createInstance(final Element xmlElement, Class<T> expectedType)
  {
    String className;
    T newObject;

    className = xmlElement.getAttribute(getAttrNameForClass());
    newObject = RU.createInstanceOfType(expectedType, className, this);
    return newObject;
  }

  public Object createInitializedInstance(final Element xmlElement) throws UnknownFieldException
  {
    return createInitializedInstance(xmlElement, Object.class);
  }

  public <T> T createInitializedInstance(Element xmlElement, Class<T> expectedType) throws UnknownFieldException
  {
    T newObject;

    newObject = createInstance(xmlElement, expectedType);
    if (newObject == null)
    {
      throw new UnknownFieldException("Attribute '" + getAttrNameForClass() + "' not found in XML element <" + xmlElement.getTagName() + ">");
    }
    initProperties(xmlElement, newObject);
    return newObject;
  }

  public <T> List<T> createInitializedInstances(List<Element> xmlElements, Class<T> expectedType) throws UnknownFieldException
  {
    List<T> result;
    T object;

    result = new ArrayList<T>(xmlElements.size());
    for (Element element : xmlElements)
    {
      object = createInitializedInstance(element, expectedType);
      result.add(object);
    }
    return result;
  }

  public String getAttrNameForClass()
  {
    return this.attrNameForClass;
  }

  public void setAttrNameForClass(String newValue)
  {
    this.attrNameForClass = newValue;
  }

  public String getTagNameForField()
  {
    return this.tagNameForField;
  }

  public void setTagNameForField(String newValue)
  {
    this.tagNameForField = newValue;
  }

  public String getAttrNameForFieldName()
  {
    return this.attrNameForFieldName;
  }

  public void setAttrNameForFieldName(String newValue)
  {
    this.attrNameForFieldName = newValue;
  }

  public String getAttrNameForFieldValue()
  {
    return this.attrNameForFieldValue;
  }

  public void setAttrNameForFieldValue(String newValue)
  {
    this.attrNameForFieldValue = newValue;
  }
  // =========================================================================
  // PROTECTED INSTANCE METHODS
  // =========================================================================
  protected void initProperties(final Element xmlElement, Object object) throws UnknownFieldException
  {
    NodeList fieldTags;
    Node node;
    Element tag;
    String fieldName;
    String textValue;
    Field field;
    Object value;

    fieldTags = xmlElement.getElementsByTagName(this.getTagNameForField());
    for (int i = 0; i < fieldTags.getLength(); i++)
    {
      node = fieldTags.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE)
      {
        tag = (Element)node;
        fieldName = tag.getAttribute(getAttrNameForFieldName());
        if (hasValueAttrName())
        {
          textValue = tag.getAttribute(getAttrNameForFieldValue());
          if (textValue == null)
          {
            textValue = tag.getTextContent();
          }
        }
        else
        {
          textValue = tag.getTextContent();
        }
        field = RU.getField(object, fieldName);
        if (field == null)
        {
          throw new UnknownFieldException("Cannot find field '%s' in class %s", fieldName, object.getClass().getName());
        }
        value = convertToType(textValue, field.getType());
        RU.setValueOf(object, fieldName, value);
      }
    }
  }

  protected Object convertToType(String text, Class<?> type)
  {
    if (type == String.class)
    {
      return text;
    }
    if ((type == Integer.TYPE) || (type == Integer.class))
    {
      return Integer.parseInt(text);
    }
    if ((type == Long.TYPE) || (type == Long.class))
    {
      return Long.parseLong(text);
    }
    if ((type == Byte.TYPE) || (type == Byte.class))
    {
      return Byte.parseByte(text);
    }
    if ((type == Short.TYPE) || (type == Short.class))
    {
      return Short.parseShort(text);
    }
    if ((type == Float.TYPE) || (type == Float.class))
    {
      return Float.parseFloat(text);
    }
    if ((type == Double.TYPE) || (type == Double.class))
    {
      return Double.parseDouble(text);
    }
    if ((type == Boolean.TYPE) || (type == Boolean.class))
    {
      return Boolean.parseBoolean(text);
    }
    if ((type == Character.TYPE) || (type == Character.class))
    {
      return text.charAt(0);
    }
    try
    {
      Constructor<?> constructor = type.getConstructor(String.class);
      return constructor.newInstance(text);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return null;
    }
  }

  protected boolean hasValueAttrName()
  {
    if ((getAttrNameForFieldValue() == null) || (getAttrNameForFieldValue().length() == 0))
    {
      return false;
    }
    char ch = getAttrNameForFieldValue().charAt(0);
    if (ch < 'A')
    {
      return false;
    }
    if (ch > 'z')
    {
      return false;
    }
    if ((ch > 'Z') && (ch < 'a'))
    {
      return false;
    }
    return true;
  }

}

package org.pfsw.reflect.testhelper;

import java.util.Collection;

@SuppressWarnings({ "rawtypes" })
public class DummyClass1
{
  public static String label = "nothing";

  private int type = 0;

  public int intValue;
  public String strValue;
  public float floatValue = 0.0f;
  public Collection collValue;

  DummyClass1()
  {
    this.type = 1;
  }

  public DummyClass1(int initialIntValue)
  {
    this.type = 2;
    this.intValue = initialIntValue;
  }

  DummyClass1(int initialIntValue, String initialString)
  {
    this.type = 3;
    this.intValue = initialIntValue;
    this.strValue = initialString;
  }

  protected DummyClass1(int initialIntValue, float initialFloat)
  {
    this.type = 4;
    this.intValue = initialIntValue;
    this.floatValue = initialFloat;
  }

  @SuppressWarnings("unused")
  private DummyClass1(int initialIntValue, String initialString, Collection initialCollection)
  {
    this.type = 5;
    this.intValue = initialIntValue;
    this.strValue = initialString;
    this.collValue = initialCollection;
  }

  public int getType()
  {
    return type;
  }

  public int changeType(int change, boolean plus)
  {
    if (plus)
    {
      return intValue + change;
    }
    return intValue - change;
  }

  protected String process(Superclass s, int value)
  {
    return s.getClass().getSimpleName() + "_" + value;
  }
}

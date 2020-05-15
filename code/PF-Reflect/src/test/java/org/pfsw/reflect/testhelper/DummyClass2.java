package org.pfsw.reflect.testhelper;

/**
 * A class with NO default constructor.
 */
public class DummyClass2
{
  private final String name;

  public DummyClass2(String name)
  {
    super();
    this.name = name;
  }

  public String getName()
  {
    return this.name;
  }
}

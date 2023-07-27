# Programmer's Friend - Reflection Utilities

The purpose of this library is to provide a simple way of using Java reflection.

This library is built with JDK **6** and has no dependencies!  


## Usage

To use it set the following maven or gradle dependencies as appropriate for your project:

**Maven:**

````xml
<dependencies>
  <dependency>
    <groupId>org.pfsw</groupId>
    <artifactId>pf-reflect</artifactId>
    <version>4.5.0</version>
    <scope>test</scope>
  </dependency>
</dependencies>
````

**Gradle:**

````groovy
dependencies {
  testImplementation group: 'org.pfsw', name: 'pf-reflect', version: '4.5.0'
}
````

Mostly used classes are ``ReflectUtil`` to access object fields and ``Dynamic`` for dynamic method invocations.
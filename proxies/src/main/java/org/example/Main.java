package org.example;

import java.lang.reflect.Proxy;

public class Main {

  public static void main(String[] args) {
    StudentServiceIfc studentService = createStudentService(); // Here we get the object created by Proxy, not the object which will create Spring for us

    studentService.findAllStudents().forEach(System.out::println);

    System.out.println("---");

    studentService.findAllStudents().forEach(System.out::println);
  }

  private static StudentServiceIfc createStudentService() { // Must work with interfaces only
    return (StudentServiceIfc)Proxy.newProxyInstance(
        Main.class.getClassLoader(),
        new Class[]{StudentServiceIfc.class},
        new CacheableInvocationHandler(new StudentService())
    );
  }
}
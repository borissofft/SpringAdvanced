package org.example;

import java.util.List;

public class StudentService implements StudentServiceIfc {

  @Cacheable("students")
  @Override
  public List<StudentDTO> findAllStudents() { // Invokes only first time, because the parameters of DTO are static and @Cacheable load the resources in the memory
                                              // On second invocation the result is loaded from the memory(cache) because we return objects with same instances
    System.out.println("Downloading students...");
    dummyWait();
    System.out.println("Students downloaded...");
    return List.of(
        new StudentDTO().setName("Pesho").setAge(21),
        new StudentDTO().setName("Anna").setAge(22)
    );
  }

  private void dummyWait() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}

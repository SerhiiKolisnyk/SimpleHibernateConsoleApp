package kolisnyk.task.util;

import java.math.BigDecimal;

public class Answer {

  static public void headOfDepartment(String departmentName, String firstName,String lastName) {
    System.out.printf("Head of %s department is %s %s%n", departmentName, firstName,lastName);
  }

  static public void departmentStatistic(String name, int count) {
    System.out.printf("%s - %d.",name, count);
  }

  static public void avgSalary(String departmentName, BigDecimal avg) {
    System.out.printf("The average salary of %s is %s ", departmentName, avg.toString());
  }
  static public void countOfEmployee(int count) {
    System.out.printf("Size of employee %d", count);
  }

  static public void searchByTemplate(String str) {
    System.out.printf("We found: %s%n", str);
  }

}

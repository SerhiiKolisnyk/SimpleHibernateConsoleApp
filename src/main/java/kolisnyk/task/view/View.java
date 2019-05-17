package kolisnyk.task.view;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import kolisnyk.task.OutOfAttemptException;
import kolisnyk.task.entity.DegreeEntity;
import kolisnyk.task.entity.DepartmentEntity;
import kolisnyk.task.entity.LectorEntity;
import kolisnyk.task.persistant.HibernateUtil;
import kolisnyk.task.service.DegreeService;
import kolisnyk.task.service.DepartmentService;
import kolisnyk.task.service.LectorService;
import kolisnyk.task.util.Answer;
import kolisnyk.task.util.ConsoleRead;
import kolisnyk.task.util.Printable;

public class View {

  private Map<String, String> menu;
  private Map<String, Printable> methodsMenu;
  private static Scanner input = new Scanner(System.in);

  public View() {
    menu = new LinkedHashMap<>();
    methodsMenu = new LinkedHashMap<>();
    menu.put("A", "   A - Select all table");
    menu.put("B", "   B - Init DB");
    menu.put("C", "   C - Who is head of department ?");
    menu.put("D", "   D - Show department ? statistic");
    menu.put("E", "   E - Show the average salary for department ?");
    menu.put("F", "   F - Show count of employee for department ?");
    menu.put("G", "   G - Global search by {template}");

    menu.put("1", "   1 - Table: Department");
    menu.put("11", "  11 - Create for Department");
    menu.put("12", "  12 - Update Department");
    menu.put("13", "  13 - Delete from Department");
    menu.put("14", "  14 - Select Department");
    menu.put("15", "  15 - Find Department by ID");
    menu.put("16", "  16 - Set Head of Department");

    menu.put("2", "   2 - Table: Lector");
    menu.put("21", "  21 - Create for Lector");
    menu.put("22", "  22 - Update Lector");
    menu.put("23", "  23 - Delete from Lector");
    menu.put("24", "  24 - Select Lector");
    menu.put("25", "  25 - Find Lector by ID");
    menu.put("26", "  26 - Set Degree of Lector");

    menu.put("3", "   3 - Table: Degree");
    menu.put("31", "  31 - Create for Degree");
    menu.put("34", "  34 - Select Degree");

    menu.put("Q", "   Q - exit");

    methodsMenu.put("A", this::selectAllTable);
    methodsMenu.put("B", this::init);
    methodsMenu.put("C", this::findHead);
    methodsMenu.put("D", this::showStatistic);
    methodsMenu.put("E", this::showAverageSalary);
    methodsMenu.put("F", this::showCountOfEmployee);
    methodsMenu.put("G", this::search);
    methodsMenu.put("Q", this::exit);

    methodsMenu.put("11", this::createForDepartment);
    methodsMenu.put("12", this::updateDepartmentName);
    methodsMenu.put("13", this::deleteFromDepartment);
    methodsMenu.put("14", this::selectDepartment);
    methodsMenu.put("15", this::findDepartmentByID);
    methodsMenu.put("16", this::setHeadDepartment);

    methodsMenu.put("21", this::createForLector);
    methodsMenu.put("22", this::updateLector);
    methodsMenu.put("23", this::deleteFromLector);
    methodsMenu.put("24", this::selectLector);
    methodsMenu.put("25", this::findLectorByID);
    methodsMenu.put("26", this::setDegreeHead);

    methodsMenu.put("31", this::createForDegree);
    methodsMenu.put("34", this::selectDegree);


  }

  private void exit() {
    HibernateUtil.shutdown();
  }

  private void findHead() throws SQLException {
    String depName;
    depName = ConsoleRead.readString("Write name of depart department");
    DepartmentService departmentService = new DepartmentService();
    List<LectorEntity> list = departmentService.findHeadByName(depName);
    System.out.println("Found" + list.size());
    list.forEach(a -> Answer.headOfDepartment(depName, a.getFirstName(), a.getLastName()));
  }

  private void showStatistic() throws SQLException {
    String depName;
    depName = ConsoleRead.readString("Write name of depart department");
    DepartmentService departmentService = new DepartmentService();
    List<DegreeEntity> degreeEntities = new DegreeService().findAll();
    System.out.println(" statistic for " + depName);
    for (DegreeEntity dg : degreeEntities) {
      int c = departmentService.countLectoresDegee(depName, dg);
      Answer.departmentStatistic(dg.getFullName(), c);
    }
  }

  private void showAverageSalary() throws SQLException {
    String depName;
    depName = ConsoleRead.readString("Write name of depart department");
    DepartmentService departmentService = new DepartmentService();
    BigDecimal salary = departmentService.averageSalary(depName);
    Answer.avgSalary(depName, salary);
  }

  private void showCountOfEmployee() throws SQLException {
    String depName;
    depName = ConsoleRead.readString("Write name of depart department");
    DepartmentService departmentService = new DepartmentService();
    int count = departmentService.countOfEmployee(depName);
    Answer.countOfEmployee(count);
  }

  private void search() throws SQLException {
    String depName;
    depName = ConsoleRead.readString("Write template");
    LectorService lectorService = new LectorService();
    List<LectorEntity> list = lectorService.findByTemplate(depName);
    System.out.println("We found " + list.size());
    list.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
  }


  private void init() throws SQLException {
    DegreeService degreeService = new DegreeService();
    DegreeEntity deg1 = new DegreeEntity("assistant");
    DegreeEntity deg2 = new DegreeEntity("associate professor");
    DegreeEntity deg3 = new DegreeEntity("professor");
    degreeService.create(deg1);
    degreeService.create(deg2);
    degreeService.create(deg3);
    // add lector
    DepartmentService departmentService = new DepartmentService();
    DepartmentEntity d1 = new DepartmentEntity("DEP_1", null);
    DepartmentEntity d2 = new DepartmentEntity("DEP_2", null);
    DepartmentEntity d3 = new DepartmentEntity("DEP_3", null);
    departmentService.create(d1);
    departmentService.create(d2);
    departmentService.create(d3);
    // add lector
    LectorService lectorService = new LectorService();
    LectorEntity l1 = new LectorEntity("Serhii", "Kolisnyk", "kolisnyk.sk@gmail.com",
        new BigDecimal(7600), d1, deg1);
    LectorEntity l2 = new LectorEntity("Dmitro", "Ivanov", "sda.sk@gmail.com", new BigDecimal(2220),
        d1, deg2);
    LectorEntity l3 = new LectorEntity("Ivan", "Dmitriv", "dmtr.sk@gmail.com",
        new BigDecimal(22772), d2, deg1);
    LectorEntity l4 = new LectorEntity("Andiy", "Haf", "gaf.sk@gmail.com", new BigDecimal(9772),
        d2, deg1);
    LectorEntity l5 = new LectorEntity("Adale", "Skyfall", "skyfall.sk@gmail.com",
        new BigDecimal(1772), null, deg2);
    LectorEntity l6 = new LectorEntity("Boris", "Koval", "s2s1.sk@gmail.com", new BigDecimal(8123),
        d2, deg2);
    LectorEntity l7 = new LectorEntity("Oleh", "Zadorov", "ol.sk@gmail.com", new BigDecimal(8123),
        d3, deg2);
    LectorEntity l8 = new LectorEntity("Volodymyr", "Tkach", "t@gmail.com",
        new BigDecimal(11000), d3, deg3);
    LectorEntity l9 = new LectorEntity("Jon", "Watter", "wtter@gmail.com", new BigDecimal(11453),
        d3, deg3);
    LectorEntity l10 = new LectorEntity("Brenk", "Stalone", "fake_stalone@gmail.com",
        new BigDecimal(5000), d3, deg3);
    lectorService.create(l1);
    lectorService.create(l2);
    lectorService.create(l3);
    lectorService.create(l4);
    lectorService.create(l5);
    lectorService.create(l6);
    lectorService.create(l7);
    lectorService.create(l8);
    lectorService.create(l9);
    lectorService.create(l10);

    d1.setHead(l1);
    d2.setHead(l2);
    d3.setHead(l3);
    departmentService.update(d1);
    departmentService.update(d2);
    departmentService.update(d3);
  }

  private void selectAllTable() throws SQLException {
    selectDepartment();
    selectLector();
    selectDegree();
  }

  //----------------------------------------------------------
  private void deleteFromDepartment() throws SQLException {
    int id;
    try {
      id = ConsoleRead.readPositiveInt("Input ID for Department: ",
          "Write positive and correct number!",
          3);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    DepartmentService departmentService = new DepartmentService();
    departmentService.delete(id);
  }

  private void createForDepartment() throws SQLException {
    String nameDep;
    nameDep = ConsoleRead.readSolidString("Input name of Department");

    DepartmentEntity entity = new DepartmentEntity();
    entity.setDepartmentName(nameDep);
    DepartmentService departmentService = new DepartmentService();
    departmentService.create(entity);
    System.out.println("Create department ..." + entity);
  }

  private void updateDepartmentName() throws SQLException {
    int id;
    try {
      id = ConsoleRead.readPositiveInt("Input ID for Department: ",
          " ",
          4);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    String nameDep;
    nameDep = ConsoleRead.readSolidString("Input new name of Department");
    DepartmentEntity entity = new DepartmentEntity();
    entity.setDepartmentID(id);
    entity.setDepartmentName(nameDep);

    DepartmentService departmentService = new DepartmentService();
    departmentService.update(entity);
    System.out.println("Update department ..." + entity);
  }

  private void selectDepartment() throws SQLException {
    System.out.println("\nTable: Department");
    DepartmentService departmentService = new DepartmentService();
    List<DepartmentEntity> departments = departmentService.findAll();
    System.out.println("Find: " + departments.size());
    for (DepartmentEntity entity : departments) {
      System.out.println(entity);
    }
  }

  private void findDepartmentByID() throws SQLException {
    int id;
    try {
      id = ConsoleRead.readPositiveInt("Input ID for Department: ",
          "Write positive and correct number!",
          1);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    DepartmentService departmentService = new DepartmentService();
    DepartmentEntity departmentEntity = departmentService.findById(id);
    System.out.println("\n Find : " + departmentEntity);
  }

  private void setHeadDepartment() throws SQLException {
    int idLector, idDep;
    try {
      idLector = ConsoleRead.readPositiveInt("Input ID of Lector: ",
          "Write positive and correct number!",
          3);
      idDep = ConsoleRead.readPositiveInt("Input ID of Department: ",
          "Write positive and correct number!",
          2);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }

    LectorService lectorService = new LectorService();
    LectorEntity head = lectorService.findById(idLector);
    if (head == null) {
      System.out.println("Lector not found");
      return;
    }

    DepartmentService departmentService = new DepartmentService();
    DepartmentEntity departmentEntity = departmentService.findById(idDep);
    if (departmentEntity == null) {
      System.out.println("Department not found");
      return;
    }

    departmentEntity.setHead(head);
    departmentService.update(departmentEntity);
    System.out.println("Update ...");

  }
  //------------------------------------------------------------------------

  private void deleteFromLector() throws SQLException {
    int id;
    try {
      id = ConsoleRead.readPositiveInt("Input ID for Lector: ",
          "Write positive and correct number!",
          3);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    LectorService lectorService = new LectorService();
    lectorService.delete(id);
  }

  private void createForLector() throws SQLException {
    String firstName, secondName, mail;
    BigDecimal salary;
    firstName = ConsoleRead.readSolidString("Input first name of Lector");
    secondName = ConsoleRead.readSolidString("Input last name of Lector");
    mail = ConsoleRead.readSolidString("Input mail of Lector");
    try {
      int id = ConsoleRead.readPositiveInt("Input ID salary! ",
          "Write positive and correct number!",
          3);
      salary = new BigDecimal(id);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    LectorEntity lectorEntity = new LectorEntity();
    lectorEntity.setFirstName(firstName);
    lectorEntity.setLastName(secondName);
    lectorEntity.setEmail(mail);
    lectorEntity.setSalary(salary);

    LectorService lectorService = new LectorService();
    lectorService.create(lectorEntity);
    System.out.println("Create ..." + lectorEntity);
  }

  private void updateLector() throws SQLException {
    String firstName, secondName, mail;
    BigDecimal salary;
    firstName = ConsoleRead.readSolidString("Input first name of Lector");
    secondName = ConsoleRead.readSolidString("Input last name of Lector");
    mail = ConsoleRead.readSolidString("Input mail of Lector");
    try {
      int id = ConsoleRead.readPositiveInt("Input ID for Lector: ",
          "Write positive and correct number!",
          2);
      salary = new BigDecimal(id);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    LectorEntity lectorEntity = new LectorEntity();
    lectorEntity.setFirstName(firstName);
    lectorEntity.setLastName(secondName);
    lectorEntity.setEmail(mail);
    lectorEntity.setSalary(salary);

    LectorService lectorService = new LectorService();
    lectorService.update(lectorEntity);
    System.out.println("Update ..." + lectorEntity);

  }

  private void selectLector() throws SQLException {
    System.out.println("\nTable: Lector");
    LectorService lectorService = new LectorService();
    List<LectorEntity> lectors = lectorService.findAll();
    System.out.println("Find: " + lectors.size());
    for (LectorEntity entity : lectors) {
      System.out.println(entity);
    }
  }

  private void findLectorByID() throws SQLException {
    int id;
    try {
      id = ConsoleRead.readPositiveInt("Input ID for Lector: ",
          "Write positive and correct number!",
          1);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    LectorService lectorService = new LectorService();
    LectorEntity lectorEntity = lectorService.findById(id);
    System.out.println("\n Find : " + lectorEntity);
  }

  private void setDegreeHead() throws SQLException {
    int idLector, degree;
    try {
      idLector = ConsoleRead.readPositiveInt("Input ID of Lector: ",
          "Write positive and correct number!",
          3);
      degree = ConsoleRead.readPositiveInt("Input ID of Degree: ",
          "Write positive and correct number!",
          2);
    } catch (OutOfAttemptException e) {
      System.out.println(" ... ");
      return;
    }
    LectorService lectorService = new LectorService();
    LectorEntity lector = lectorService.findById(idLector);
    if (lector == null) {
      System.out.println("Lector not found");
      return;
    }

    DegreeService degreeService = new DegreeService();
    DegreeEntity degreeEntity = degreeService.findById(degree);
    if (degreeEntity == null) {
      System.out.println("Degree not found");
      return;
    }

    lector.setDegree(degreeEntity);
    lectorService.update(lector);
    System.out.println("Update ...");

  }


  //-------------------------------------
  private void createForDegree() throws SQLException {
    String nameDegree;
    nameDegree = ConsoleRead.readString("Input name of degree");

    DegreeEntity degreeEntity = new DegreeEntity();
    degreeEntity.setFullName(nameDegree);
    DegreeService degreeServics = new DegreeService();
    degreeServics.create(degreeEntity);
    System.out.println("Create degree ..." + degreeEntity);
  }

  private void selectDegree() throws SQLException {
    System.out.println("\nTable: Degree");
    DegreeService degreeService = new DegreeService();
    List<DegreeEntity> degreeEntityList = degreeService.findAll();
    System.out.println("Find: " + degreeEntityList.size());
    for (DegreeEntity entity : degreeEntityList) {
      System.out.println(entity);
    }
  }

  //-------------------------------------------------------------------------

  private void outputMenu() {
    System.out.println("\nMENU:");
    for (String key : menu.keySet()) {
      if (key.length() == 1) {
        System.out.println(menu.get(key));
      }
    }
  }

  private void outputSubMenu(String fig) {

    System.out.println("\nSubMENU:");
    for (String key : menu.keySet()) {
      if (key.length() != 1 && key.substring(0, 1).equals(fig)) {
        System.out.println(menu.get(key));
      }
    }
  }

  public void show() {
    String keyMenu;
    System.out.println("HELLO! ");
    do {
      outputMenu();
      System.out.println("PLEASE, SELECT MENU POINT");
      keyMenu = input.nextLine().toUpperCase();

      if (keyMenu.matches("^\\d")) {
        outputSubMenu(keyMenu);
        System.out.println("Please, select menu point.");
        keyMenu = input.nextLine().toUpperCase();
      }

      try {
        methodsMenu.get(keyMenu).print();
      } catch (Exception e) {
      }
    } while (!keyMenu.equals("Q"));
  }
}

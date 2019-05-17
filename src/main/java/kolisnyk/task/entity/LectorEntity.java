package kolisnyk.task.entity;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lector")
public class LectorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "salary")
  private BigDecimal salary;

  @ManyToOne()
  @JoinColumn(name = "department_department_id", referencedColumnName = "department_id")
  private DepartmentEntity currentDepartment;

  @ManyToOne()
//  @JoinColumn(name = "degree", referencedColumnName = "degree_id")
  private DegreeEntity degree;


  @OneToMany(mappedBy = "head")
  private Set<DepartmentEntity> departmentHead;

  public LectorEntity() {
  }

  public LectorEntity(String firstName, String lastName, String email, BigDecimal salary,
      DepartmentEntity currentDepartment, DegreeEntity degree) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.salary = salary;
    this.currentDepartment = currentDepartment;
    this.degree = degree;
  }

  public int getId() {
    return id;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public DepartmentEntity getCurrentDepartment() {
    return currentDepartment;
  }

  public void setCurrentDepartment(DepartmentEntity departmentEntity) {
    this.currentDepartment = departmentEntity;
  }

  public DegreeEntity getDegree() {
    return degree;
  }

  public void setDegree(DegreeEntity degree) {
    this.degree = degree;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "LectorEntity{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", salary=" + salary + '\'' +
        ", departmentEntity=" + currentDepartment.getDepartmentName() +
        ", degree=" + degree +
        '}';
  }
}

package kolisnyk.task.entity;

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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Department")
public class DepartmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "department_id", nullable = false)
  private int departmentID;

  @Column(name = "department_name")
  private String departmentName;

  @ManyToOne()
  @JoinColumn(name = "head", referencedColumnName = "id")
  private LectorEntity head;

  @OneToMany(mappedBy = "currentDepartment")
  @Fetch(value = FetchMode.SUBSELECT)
  private Set<LectorEntity> lectores;

  public DepartmentEntity(String departmentName, LectorEntity head) {
    this.departmentName = departmentName;
    this.head = head;
  }

  public DepartmentEntity() {
  }


  public int getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(int departmentID) {
    this.departmentID = departmentID;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Set<LectorEntity> getLectores() {
    return lectores;
  }

  public void setLectores(Set<LectorEntity> employees) {
    this.lectores = employees;
  }

  public LectorEntity getHead() {
    return head;
  }

  public void setHead(LectorEntity head) {
    this.head = head;
  }

  @Override
  public String toString() {
    return "DepartmentEntity{" +
        "departmentID=" + departmentID +
        ", departmentName='" + departmentName + '\'' +
        ", head=" + head +
        '}';
  }
}

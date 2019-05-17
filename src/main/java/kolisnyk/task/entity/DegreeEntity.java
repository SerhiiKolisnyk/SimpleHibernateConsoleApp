package kolisnyk.task.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Degree")
public class DegreeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "degree_id")
  private int id;

  @Column(name = "fullName")
  private String fullName;

  @OneToMany(mappedBy = "degree")
  private Set<LectorEntity> lectores;

  public DegreeEntity() {
  }

  public DegreeEntity(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Set<LectorEntity> getLectores() {
    return lectores;
  }

  public void setLectores(Set<LectorEntity> employees) {
    this.lectores = employees;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "DegreeEntity{" +
        "id=" + id +
        ", fullName='" + fullName + '\'' +
        '}';
  }
}

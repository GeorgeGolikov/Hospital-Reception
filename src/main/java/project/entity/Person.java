package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person
{
    @Id
//    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @Column(name = "FIRST_NAME")
    private String firstName;

//    @Column(name = "LAST_NAME")
    private String lastName;

//    @Column(name = "FATHER_NAME")
    private String fatherName;

    // диагноз - внешний ключ
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "has",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    )
    private Set<Diagnosis> diagnosis;

    // палата - внешний ключ
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    public Person()
    {

    }

    public Person(Integer id, String firstName,
                  String lastName, String fatherName,
                  Set<Diagnosis> diagnosis, Ward ward)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.diagnosis = diagnosis;
        this.ward = ward;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFatherName()
    {
        return fatherName;
    }

    public void setFatherName(String fatherName)
    {
        this.fatherName = fatherName;
    }

    public Set<Diagnosis> getDiagnosis()
    {
        return diagnosis;
    }

    public void setDiagnosis(Set<Diagnosis> diagnosis)
    {
        this.diagnosis = diagnosis;
    }

    public Ward getWard()
    {
        return ward;
    }

    public void setWard(Ward ward)
    {
        this.ward = ward;
    }
}

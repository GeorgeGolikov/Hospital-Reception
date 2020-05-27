package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
public class Diagnosis
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "diagnosis")
    private Set<Person> people;

    public Diagnosis()
    {

    }

    public Diagnosis(Integer id, String name, Set<Person> people)
    {
        this.id = id;
        this.name = name;
        this.people = people;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Person> getPeople()
    {
        return people;
    }

    public void setPeople(Set<Person> people)
    {
        this.people = people;
    }
}
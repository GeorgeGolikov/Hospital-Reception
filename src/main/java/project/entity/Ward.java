package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ward")
public class Ward
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer maxCount;

    @OneToMany(mappedBy = "ward")
    private Set<Person> people;

    public Ward()
    {

    }

    public Ward(Integer id, String name, Integer maxCount, Set<Person> people)
    {
        this.id = id;
        this.name = name;
        this.maxCount = maxCount;
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

    public Integer getMaxCount()
    {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount)
    {
        this.maxCount = maxCount;
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

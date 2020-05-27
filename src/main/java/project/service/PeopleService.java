package project.service;

import project.entity.Person;
import java.util.List;

public interface PeopleService
{
    List<Person> listOfPeople();
    Person findPerson(int id);
    void addPerson(Person person);
    boolean deletePerson(Person person);
    boolean editPerson(Integer id, Person newPerson);
}

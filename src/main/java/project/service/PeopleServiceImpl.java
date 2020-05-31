package project.service;

import project.entity.Person;
import project.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

// Сервис - это реализация паттерна "Фасад"

@Service
public class PeopleServiceImpl implements PeopleService
{
    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<Person> listOfPeople()
    {
        return (List<Person>) peopleRepository.findAll();
    }

    @Override
    public Person findPerson(int id)
    {
        if (peopleRepository.existsById(id))
        {
            return peopleRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void addPerson(Person person)
    {
        peopleRepository.save(person);
    }

    @Override
    public boolean deletePerson(Integer id)
    {
        if (peopleRepository.existsById(id))
        {
            peopleRepository.delete(peopleRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editPerson(Integer id, Person newPerson)
    {
        if (peopleRepository.existsById(id))
        {
            newPerson.setId(id);
            peopleRepository.save(newPerson);
            return true;
        }
        else return false;
    }
}

package project.service;

import project.entity.Diagnosis;
import project.entity.Person;
import project.entity.Ward;
import project.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

// Сервис - это реализация паттерна "Фасад"

@Service
public class PeopleServiceImpl implements PeopleService
{
    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<Person> listOfPeople()
    {
        List<Person> peopleList = (List<Person>) peopleRepository.findAll();
        peopleList.forEach(p -> {
            Set<Diagnosis> diagnosisList = p.getDiagnosis();
            Ward ward = p.getWard();

            diagnosisList.forEach(d -> d.setPeople(null));
            if (ward != null) ward.setPeople(null);
        });
        return peopleList;
    }

    @Override
    public Person findPerson(int id)
    {
        if (peopleRepository.existsById(id))
        {
            Person person = peopleRepository.findById(id).get();
            Set<Diagnosis> diagnosisList = person.getDiagnosis();
            Ward ward = person.getWard();
            diagnosisList.forEach(d -> d.setPeople(null));
            if (ward != null) ward.setPeople(null);

            return person;
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

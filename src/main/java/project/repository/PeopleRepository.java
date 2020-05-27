package project.repository;

import project.entity.Person;
import org.springframework.data.repository.CrudRepository;

// peopleRepository = peopleDAO
public interface PeopleRepository extends CrudRepository<Person, Integer>
{

}

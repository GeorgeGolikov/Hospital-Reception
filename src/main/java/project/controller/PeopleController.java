package project.controller;

import project.entity.Person;
import project.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController
{
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/peopleShowAll")
    public ResponseEntity<List<Person>> getAllPeople()
    {
        List<Person> list = peopleService.listOfPeople();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/peopleShowAll/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable(name = "id") int id)
    {
        Person person = peopleService.findPerson(id);
        if (person != null) return new ResponseEntity<>(person, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addPerson")
    public ResponseEntity<?> addPerson(@RequestBody Person person)
    {
        peopleService.addPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePerson")
    public ResponseEntity<?> deletePerson(@RequestBody Person person)
    {
        final boolean deleted = peopleService.deletePerson(person);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editPerson/{id}")
    public ResponseEntity<?> editPerson(@PathVariable(name = "id") int id, @RequestBody Person person)
    {
        final boolean updated = peopleService.editPerson(id, person);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

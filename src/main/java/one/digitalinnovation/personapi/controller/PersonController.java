package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody  Person person){
        Person personSaved = this.personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID: " + personSaved.getId())
                .build();
    }
}

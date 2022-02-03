package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person personSaved = this.personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID: " + personSaved.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = this.personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        //Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = verifyIfExists(id);

//        if(optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id)
//;        }
//        return personMapper.toDTO(optionalPerson.get());

        return personMapper.toDTO(person);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        personRepository.delete(person);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person personSaved = this.personRepository.save(personToUpdate);
        return MessageResponseDTO
                .builder()
                .message("Person with ID : " + personSaved.getId() + " was updated with success!")
                .build();
    }
}

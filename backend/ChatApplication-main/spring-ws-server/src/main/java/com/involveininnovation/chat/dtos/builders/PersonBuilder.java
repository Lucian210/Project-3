package com.involveininnovation.chat.dtos.builders;

import com.involveininnovation.chat.dtos.PersonDTO;
import com.involveininnovation.chat.dtos.PersonDetailsDTO;
import com.involveininnovation.chat.entities.Person;

public class PersonBuilder {

    private PersonBuilder() {
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getAge());
    }

    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        return new PersonDetailsDTO(person.getId(), person.getName(), person.getAddress(), person.getAge());
    }

    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getName(),
                personDetailsDTO.getAddress(),
                personDetailsDTO.getAge());
    }
}

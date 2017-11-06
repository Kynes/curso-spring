package es.tecnocom.cursospring.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tecnocom.cursospring.model.entities.Person;
import es.tecnocom.cursospring.model.repositories.person.PersonRepository;

@Service
public class PersonService implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private PersonRepository repository;

  public Person savePerson( Person p ) {
    return this.repository.savePojo( p );
  }

  public List<Person> getPersons() {
    return this.repository.getALL();
  }

}

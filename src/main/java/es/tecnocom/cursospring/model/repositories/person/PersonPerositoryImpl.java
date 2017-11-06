package es.tecnocom.cursospring.model.repositories.person;

import org.springframework.stereotype.Repository;

import es.tecnocom.cursospring.base.repository.GenericRepositoryImpl;
import es.tecnocom.cursospring.model.entities.Person;

@Repository( "personRepository" )
public class PersonPerositoryImpl extends GenericRepositoryImpl<Person>
    implements PersonRepository {
  private static final long serialVersionUID = 1L;

  public PersonPerositoryImpl() {
    super( Person.class );
  }

}

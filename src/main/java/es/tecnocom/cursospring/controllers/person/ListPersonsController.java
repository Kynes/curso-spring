package es.tecnocom.cursospring.controllers.person;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.tecnocom.cursospring.model.entities.Person;
import es.tecnocom.cursospring.model.services.PersonService;

@Controller
public class ListPersonsController implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  private static final String PERSON__SAVED__WITH__SUCCESS = "Person Saved With Success!!!";

  @Autowired
  private PersonService service;

  private Person person;

  public ListPersonsController() {
  }

  public String doListPersons() {
    return "listPersons";
  }

  public final void doCreatePerson() {
    this.person = new Person();
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson( Person person ) {
    this.person = person;
  }

  public List<Person> getPersons() {
    return this.service.getPersons();
  }

  public void savePerson() {
    this.service.savePerson( this.person );
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage( null,
        new FacesMessage( FacesMessage.SEVERITY_INFO, PERSON__SAVED__WITH__SUCCESS,
            PERSON__SAVED__WITH__SUCCESS ) );
  }
}

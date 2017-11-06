package es.tecnocom.cursospring.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table( name = "persons" )
public class Person implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  @Column( nullable = false )
  private boolean active;

  @Column( name = "name_of_person", nullable = false, length = 120 )
  private String name;

  @Column( nullable = false, name = "date_of_birth" )
  @Temporal( javax.persistence.TemporalType.DATE )
  private Date dateOfBirth;

  @Temporal( javax.persistence.TemporalType.TIMESTAMP )
  @Column( name = "registration_date", nullable = false )
  private Date registrationDate;

  @Temporal( javax.persistence.TemporalType.DATE )
  @Column( name = "inativation_date" )
  private Date inativationDate;

  @Column( nullable = false, length = 50 )
  private String email;

  public Person() {
    this( null, null, null, null );
  }

  public Person(Long id, String name, Date dateOfBirth, String email) {
    this.id = id;
    this.active = Boolean.TRUE;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.registrationDate = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId( Long id ) {
    this.id = id;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive( boolean active ) {
    this.active = active;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth( Date dateOfBirth ) {
    this.dateOfBirth = dateOfBirth;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate( Date registrationDate ) {
    this.registrationDate = registrationDate;
  }

  public Date getInativationDate() {
    return inativationDate;
  }

  public void setInativationDate( Date inativationDate ) {
    this.inativationDate = inativationDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals( Object object ) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if( !(object instanceof Person) ) {
      return false;
    }
    Person other = (Person) object;
    return !((this.id == null && other.id != null)
        || (this.id != null && !this.id.equals( other.id )));
  }

  @Override
  public String toString() {
    return "es.tecnocom.cursospring.model.entities.Person[ id=" + id + " ]";
  }

}

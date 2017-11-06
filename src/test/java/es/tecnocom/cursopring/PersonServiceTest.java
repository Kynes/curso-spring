package es.tecnocom.cursopring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.tecnocom.cursospring.base.conf.JPAConfiguration;
import es.tecnocom.cursospring.model.entities.Person;
import es.tecnocom.cursospring.model.services.PersonService;

@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration( classes = { JPAConfiguration.class } )
public class PersonServiceTest {
  @Autowired
  private PersonService service;

  public PersonServiceTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of savePerson method, of class PersonService.
   */
  @Test
  public void testSavePerson() {
    System.out.println( "savePerson" );
    Person p = new Person( null, "Teste Person", new Date(), "teste@teste.com.br" );
    Person personSaved = this.service.savePerson( p );
    assertNotNull( personSaved );
    assertNotNull( personSaved.getId() );
    assertEquals( personSaved.getId(), new Long( "1" ) );
  }

}

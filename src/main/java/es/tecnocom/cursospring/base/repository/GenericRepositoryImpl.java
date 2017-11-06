package es.tecnocom.cursospring.base.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings( "serial" )
@Repository
public class GenericRepositoryImpl<E> implements GenericRepository<E> {
  private final Class<E> classToCast;

  @PersistenceContext
  private EntityManager em;

  private GenericRepositoryImpl() {
    this( null );
  }

  public GenericRepositoryImpl(Class<E> classToCast) {
    this.classToCast = classToCast;
  }

  @Override
  @Transactional
  public E savePojo( E classToSave ) {
    this.em.persist( classToSave );
    this.em.flush();
    return classToSave;
  }

  /**
   * Metodo que recupera um pojo na base de dados pela chave primaria.
   *
   * @param id
   * @return
   */
  @Override
  @Transactional
  public E getPojoByPrimaryKey( Serializable id ) {
    return this.em.find( this.classToCast, id );
  }

  /**
   * Metodo que recupera um pojo na base de dados conforme a string e os parametros passados.
   *
   * @param query
   * @param values
   * @return
   */
  @Override
  @Transactional
  @SuppressWarnings( "unchecked" )
  public E getPojo( String query, Object... values ) {
    Query qr = this.createQuery( query, values );
    return (E) qr.setMaxResults( 1 ).getSingleResult();
  }

  @Override
  @Transactional
  public E updatePojo( E classToUpdate ) {
    this.em.merge( classToUpdate );
    this.em.flush();
    return classToUpdate;
  }

  @Override
  @Transactional
  public void removePojo( Serializable id ) {
    E classToRemove = this.getPojoByPrimaryKey( id );
    try {
      this.em.remove( classToRemove );
      this.em.flush();
    } catch( NullPointerException ex ) {
      ex.printStackTrace();
    }
  }

  /**
   * Metodo que retorna todos os registros de uma tabela na base de dados.
   *
   * @return
   */
  @Override
  @SuppressWarnings( "unchecked" )
  @Transactional
  public List<E> getALL() {
    final Query query = this.createQuery( this.getBaseSelect().toString() );
    return query.getResultList();
  }

  /**
   * Metodo que retorna todos os registros de uma tabela na base de dados conforme os parametros
   * passados.
   *
   * @param query
   * @param values
   * @return
   */
  @Override
  @SuppressWarnings( "unchecked" )
  @Transactional
  public List<E> getList( String query, Object... values ) {
    Query qr = this.createQuery( query, values );
    return qr.getResultList();
  }

  protected Query createQuery( String query, Object... values ) {
    try {
      Query qr = this.em.createQuery( query, this.classToCast );
      this.setQueryParams( values, qr );
      return qr;
    } catch( NoResultException ex ) {
      ex.getMessage();
      return null;
    }
  }

  /**
   * Metodo que monta um select simples para efetuar a busca de todos os dados de uma tabela na base
   * de dados.
   *
   * @return
   */
  protected StringBuilder getBaseSelect() {
    final StringBuilder queryString = new StringBuilder( "SELECT E FROM " );
    queryString.append( this.classToCast.getSimpleName() ).append( " E " );
    return queryString;
  }

  /**
   * Method that sets params in queries.
   *
   * @param values
   * @param qr
   */
  private void setQueryParams( Object[] values, Query qr ) {
    for( int i = 0; i < values.length; i++ ) {
      if( this.isNotValuesEmpty( values, i ) ) {
        Object object = values[i];
        qr.setParameter( i + 1, object );
      }
    }
  }

  /**
   * Method that verify if a element of arrays is null and is empty.
   *
   * @param values
   * @param i
   * @return
   */
  private boolean isValuesEmpty( Object[] values, int i ) {
    return ((values[i] != null) && values[i].equals( "" ));
  }

  /**
   * Method that verify if a element of arrays not is null and is not empty
   *
   * @param values
   * @param i
   * @return
   */
  private boolean isNotValuesEmpty( Object[] values, int i ) {
    return !this.isValuesEmpty( values, i );
  }
}

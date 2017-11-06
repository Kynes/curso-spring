package es.tecnocom.cursospring.base.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan( basePackages = { "es.tecnocom.cursospring.*" } )
public class JPAConfiguration {
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource( dataSource() );
    em.setPackagesToScan( new String[] { "es.tecnocom.cursospring.*" } );
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter( vendorAdapter );
    em.setJpaProperties( additionalProperties() );
    return em;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName( "org.postgresql.Driver" );
    dataSource.setUrl( "jdbc:postgresql://127.0.0.1:5432/jsf_spring_jpa_junit_integration" );
    dataSource.setUsername( "teste" );
    dataSource.setPassword( "teste" );
    return dataSource;
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
    properties.setProperty( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
    properties.setProperty( "hibernate.show_sql", "true" );
    properties.setProperty( "hibernate.format_sql", "true" );
    return properties;
  }

  @Bean
  public PlatformTransactionManager transactionManager( EntityManagerFactory emf ) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory( emf );
    return transactionManager;
  }
}

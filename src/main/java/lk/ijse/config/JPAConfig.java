package lk.ijse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author : Jayani_Arunika  10/11/2023 - 12:54 AM
 * @since : v0.01.0
 **/
@Configuration
@EnableJpaRepositories(basePackages = "lk.ijse.repo")
@EnableTransactionManagement
public class JPAConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter vad){
        //This is the spring data jpa main object which handles all the features
        LocalContainerEntityManagerFactoryBean factory= new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(ds);// for access a data source
        factory.setJpaVendorAdapter(vad); // for accessing a vendor (hibernate)
        factory.setPackagesToScan("lk.ijse.spring.entity");// set entity records location to the Spring Data JPA
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds= new DriverManagerDataSource();
        ds.setUsername("root");
        ds.setPassword("1234");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/d2?createDatabaseIfNotExist=true");
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter va= new HibernateJpaVendorAdapter();
        va.setDatabase(Database.MYSQL); // what is the DB
        va.setGenerateDdl(true); //Data definition language enable
        va.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect"); //platform version
        va.setShowSql(true); //if you wanted to see generated sql
        return va;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }
}

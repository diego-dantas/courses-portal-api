package com.course.portal.api.model.dao.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;

/**
 *  @author : Diego Dantas
 *  @since  : 2018/01/20
 *  @obs    : class de configuração de banco de dados.
 *            A classe pode ser ultilizada para configurar qual quer database
 *            relacional, ex: mysql, oracle, sqlServer entre outros.
 */


@Configuration
public class DataConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        //Parametos de conficuração do banco de dados
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // dataSource.setUrl("jdbc:mysql://google/mydb?socketFactory=com.google.cloud.sql.mysql.SocketFactory&cloudSqlInstance=apicurso1:southamerica-east1:eodontodigitaldb/eodontodigital_prd");
        // dataSource.setUsername("root");
        // dataSource.setPassword("");
        // dataSource.setUrl("jdbc:mysql://courses-api-202517:us-central1:eodontodigital-db");
        // dataSource.setUsername("eodontodigital-db");
        // dataSource.setPassword("102030");
        dataSource.setUrl("jdbc:mysql://mysql.visualmidia.info:3306/visualmidia05");
        dataSource.setUsername("visualmidia05");
        dataSource.setPassword("senha102030");
    //    dataSource.setUrl("jdbc:mysql://localhost:3306/eodontodigitaldb");
    //    dataSource.setUsername("root");
    //    dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){

        //Parametros de configuração do Hibernate
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}

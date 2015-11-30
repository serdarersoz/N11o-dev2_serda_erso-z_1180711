package com.odev2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class InitDatabase {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate=new JdbcTemplate(dataSource);

    }


    @PostConstruct
    public void initialize(){




        String books="CREATE TABLE IF NOT EXISTS  books(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY," +
                "name VARCHAR(100)," +
                "author VARCHAR(100)," +
                "isbn VARCHAR(100)," +
                "price DOUBLE )";


        jdbcTemplate.execute(books);



    }

}

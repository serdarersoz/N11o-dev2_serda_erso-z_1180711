package com.odev2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class Helper {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
jdbcTemplate=new JdbcTemplate(dataSource);
    }


    @CacheEvict(value = "books",allEntries = true)
    public void addBook(Model model){

        String sql="INSERT INTO books(name,author,isbn,price) VALUES(?,?,?,?)";


        jdbcTemplate.update(sql, model.getName(), model.getIsbn(), model.getAuthor(), model.getPrice());

    }

    @Cacheable( value = "books")
    public List<Model> getBooks(){

        System.out.println("NOT FROM CACHE");

        String sql="SELECT * FROM books";


        List<Model> models =jdbcTemplate.query(sql, new RowMapper<Model>() {
            @Override
            public Model mapRow(ResultSet resultSet, int i) throws SQLException {

                Model model =new Model( resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getFloat("price"));


                return model;
            }
        });

        return models;
    }









}

package com.example.SpringJDBCProj.repository;

import com.example.SpringJDBCProj.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlienRepository {

// create the JDBC template
    private JdbcTemplate jdbcTemplate;
//    generate getter and setter for JdbcTemplate jdbcTemplate


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Alien alien){

//        create the SQL statement
        String query = "insert into alien (id,name,tech) values (?,?,?)"; // the (?,?,?) represents the value of each field
       int rows = jdbcTemplate.update(query,alien.getId(),alien.getName(),alien.getTech());
        System.out.println(rows + " row/s affected ");
        System.out.println("Added to database");
    }

    public List<Alien> findAll(){ //List is an interface

        String sql = "select * from alien";

//        create the object of RowMapper to get the data row by row
        RowMapper<Alien> rowMapper = new RowMapper<Alien>() {
            @Override
            public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alien alien = new Alien();
                alien.setId(rs.getInt(1)); // first row
                alien.setName(rs.getString(2));
                alien.setTech(rs.getString(3));
                return alien;
            }
        };
        List<Alien> aliens = jdbcTemplate.query(sql,rowMapper);
        return aliens;

    }
}

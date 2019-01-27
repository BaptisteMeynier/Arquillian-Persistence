package com.javaee8.rest;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
public class Repository {

    @Resource(lookup = "java:/jdbc/market/bookDs")
    private DataSource bookDatasource;


    public List<String> getBooks() {
        List<String> result = new ArrayList<>();

        try (Connection con = bookDatasource.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT NAME FROM BOOK");
             ResultSet resultSet = pr.executeQuery()
        ) {
            while (resultSet.next()) {
                result.add(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
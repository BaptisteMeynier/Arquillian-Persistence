package com.javaee8.rest;


import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
public class Repository {

    @Inject
    private DataSource bookDatasource;


    public List<String> getBooksName() {
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

    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();

        try (Connection con = bookDatasource.getConnection();
             PreparedStatement pr = con.prepareStatement("SELECT ID,NAME,PRICE FROM BOOK");
             ResultSet resultSet = pr.executeQuery()
        ) {
            while (resultSet.next()) {
                result.add(new Book(
                                resultSet.getInt("ID"),
                                resultSet.getString("NAME"),
                                resultSet.getInt("PRICE")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteBook(int id) {
        try (Connection con = bookDatasource.getConnection();
             PreparedStatement pr = con.prepareStatement("DELETE FROM BOOK WHERE ID= " + id)
        ) {
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
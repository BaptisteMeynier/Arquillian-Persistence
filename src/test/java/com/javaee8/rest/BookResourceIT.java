package com.javaee8.rest;


import org.arquillian.ape.api.UsingDataSet;
import org.arquillian.ape.rdbms.ShouldMatchDataSet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Arquillian.class)
public class BookResourceIT {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(Book.class)
                .addClass(Repository.class)
                .addClass(DatasourceProducer.class)
                .addAsResource("init.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Repository repository;

    @Test
    @UsingDataSet("datasets/books.yml")
    public void should_get_books_name() {
        List<String> books = repository.getBooksName();
        Assert.assertTrue(books.contains("Mes haines"));
    }

    @Test
    @UsingDataSet("datasets/books.yml")
    @ShouldMatchDataSet("datasets/expected-books.yml")
    public void should_get_books_with_price_than_5() {
        List<Book> books = repository.getBooks();
        books.stream().filter(book -> book.getPrice() <= 5).forEach(book->repository.deleteBook(book.getId()));
    }

    @Test
    @UsingDataSet("datasets/books.yml")
    @ShouldMatchDataSet("datasets/books.yml")
    public void should_get_books() {
        repository.getBooks();
    }

}
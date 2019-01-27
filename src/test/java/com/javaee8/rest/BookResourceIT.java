package com.javaee8.rest;


import org.arquillian.ape.api.UsingDataSet;
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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@RunWith(Arquillian.class)
public class BookResourceIT {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(MarketApplication.class)
                .addClass(BookResource.class)
                .addClass(Repository.class)
                //.addAsResource("sql/insert.sql", "insert.sql")
             //   .addAsResource("import.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @UsingDataSet("datasets/books.yml")
    public void should_get_books(){

    }

/*
    @ArquillianResource
    private URI deploymentUrl;

    private Client client;
    private WebTarget bookTarget;

    @Before
    public void init() {
        client = ClientBuilder.newClient();
        bookTarget = client.target(deploymentUrl).path("rest");
    }
*/
/*
    @Test
    @RunAsClient
    public void should_contact_book_endpoint(){
        Response response =
                bookTarget.path("book")
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    @RunAsClient
    public void should_get_books(){
        Response response =
                bookTarget.path("book")
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get();
        String result = response.readEntity(String.class);
        System.out.println(result);
        Assert.assertTrue(result.contains("La metamorphose"));
    }
*/
}
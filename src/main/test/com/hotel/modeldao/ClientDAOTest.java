package main.test.com.hotel.modeldao;

import main.java.com.hotel.modeldao.HibernateFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 30/04/2017.
 */
public class ClientDAOTest {
    @Before
    public void setUp() throws Exception {
        HibernateFactory.buildIfNeeded();
    }

    @After
    public void tearDown() throws Exception {
        HibernateFactory.closeFactory();
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

}
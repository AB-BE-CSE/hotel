package main.test.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Usertype;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.HibernateFactory;
import main.java.com.hotel.modeldao.UtilisateurDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 30/04/2017.
 */
public class UtilisateurDAOTest {
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
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        Usertype usertype = utilisateurDAO.find(1).getUsertype();
        Assert.assertEquals(5, usertype.getPermissions().size());
        System.out.println(usertype.getPermissions());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void isExist() throws Exception {
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        Assert.assertTrue(utilisateurDAO.isExist("admin"));

    }

}
package main.test.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.modeldao.CategorieDAO;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.HibernateFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 30/04/2017.
 */
public class CategorieDAOTest {

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
        Categorie categorie = new Categorie();
        categorie.setNom("Single");
        categorie.setPrix(2000.0);
        categorie.setDescription("la chambre est belle lol :p ");
        // on va appeler notre DAO mtn, atoi ? vas y utiise catégorie dao comme on a fait le matin
        CategorieDAO  categorieDAO = (CategorieDAO) DAOFactory.getDAO(StringRessources.CATEGORIE);
        categorieDAO.create(categorie);
        Assert.assertNotEquals(new Integer(0),categorie.getIdCategorie());
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
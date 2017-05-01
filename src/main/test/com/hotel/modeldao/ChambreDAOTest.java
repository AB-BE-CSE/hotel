package main.test.com.hotel.modeldao;

import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.HibernateFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 30/04/2017.
 */
public class ChambreDAOTest {
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
        Chambre chambre = new Chambre();
        chambre.setCategorie(null);
        chambre.setCheck(false);
        chambre.setEtage(2);
        chambre.setNumeroChambre(102);
        ((ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE)).create(chambre);

        Assert.assertNotEquals(0,chambre.getIdChambre());
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
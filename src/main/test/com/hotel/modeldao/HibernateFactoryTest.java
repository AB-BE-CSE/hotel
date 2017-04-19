package main.test.com.hotel.modeldao;

import main.java.com.hotel.modeldao.HibernateFactory;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
/**
 * Created by Admin on 19/04/2017.
 */
public class HibernateFactoryTest {

    @Test
    public void buildIfNeeded() throws Exception {
        assertNotNull(HibernateFactory.buildIfNeeded());
    }

}
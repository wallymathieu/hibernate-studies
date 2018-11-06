import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.gewalli.commands.Command;
import se.gewalli.data.EntityNotFound;
import se.gewalli.data.InMemoryRepository;
import se.gewalli.data.Repository;
import xmlimport.GetCommands;
import org.hibernate.Criteria;
import org.hibernate.LazyInitializationException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDataTests {
    private static SessionFactory sessionFactory = null;

    @BeforeClass
    public static void setUp() throws Exception
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        GetCommands getCommands=new GetCommands();
        for (Command command : getCommands.Get()) {
            command.run(repository);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception
    {
        sessionFactory.close();
    }
    Repository repository;
    @BeforeEach
    public void beforeEach() throws EntityNotFound {
        repository=new InMemoryRepository();

    }
    @Test
    public void canGetCustomerById() throws EntityNotFound {
        assertEquals("Steve" , repository.getCustomer(1).firstname);
    }
    @Test
    public void canGetProductById() throws EntityNotFound {
        assertEquals("Yo-yo" , repository.getProduct(1).name);
    }
    @Test
    public void orderContainsProduct() throws EntityNotFound {
        assertEquals(3 , repository.getOrder(1).products.size());
    }
}

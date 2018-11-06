import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.gewalli.commands.Command;
import se.gewalli.data.EntityNotFound;
import se.gewalli.data.HibernateRepository;
import se.gewalli.data.Repository;
import xmlimport.GetCommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDataTests {
    private static SessionFactory sessionFactory = null;

    @BeforeAll
    public static void setUp() throws Exception
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        GetCommands getCommands=new GetCommands();
        Session session =sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        HibernateRepository repository = new HibernateRepository(session);

        for (Command command : getCommands.Get()) {
            command.run(repository);
        }
        session.close();
    }

    @AfterAll
    public static void tearDown() {
        sessionFactory.close();
    }
    Repository repository;
    @BeforeEach
    public void beforeEach() {
        repository=new HibernateRepository(sessionFactory.openSession());
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

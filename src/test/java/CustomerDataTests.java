import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
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

        for (Command command : getCommands.Get()) {
            runInSession(command);
        }
    }
    private static void runInSession(Command command){
        Session session = null;
        Transaction transaction=null;
        try {
            session= sessionFactory.openSession();
            transaction = session.beginTransaction();
            HibernateRepository repository = new HibernateRepository(session);

            command.run(repository);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null)transaction.rollback();
            System.out.println(e.getMessage());
        }finally {
            if (session!=null)session.close();
        }
    }


    @AfterAll
    public static void tearDown() {
        sessionFactory.close();
    }
    Repository repository;
    Session _session;
    @BeforeEach
    public void beforeEach() {
        _session=sessionFactory.openSession();
        repository=new HibernateRepository(_session);
    }
    @AfterEach
    public void afterEach(){
        _session.close();
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

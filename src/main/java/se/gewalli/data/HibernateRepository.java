package se.gewalli.data;

import org.hibernate.Session;
import se.gewalli.entities.Customer;
import se.gewalli.entities.Order;
import se.gewalli.entities.Product;

import java.util.Collection;
import java.util.Optional;

public class HibernateRepository extends Repository  {
    private final Session session;

    HibernateRepository(Session session){
        this.session=session;
    }
    @Override
    public Optional<Customer> tryGetCustomer(int customerId) {
        session.
        return Optional.empty();
    }

    @Override
    public Optional<Product> tryGetProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> tryGetOrder(int orderId) {
        return Optional.empty();
    }

    @Override
    public void save(Product obj) {

    }

    @Override
    public void save(Order obj) {

    }

    @Override
    public void save(Customer obj) {

    }

    @Override
    public Collection<Customer> getCustomers() {
        return null;
    }

    @Override
    public Collection<Product> getProducts() {
        return null;
    }

    @Override
    public Collection<Order> getOrders() {
        return null;
    }
}

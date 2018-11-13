package se.gewalli.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import se.gewalli.data.HibernateRepository;
import se.gewalli.data.Repository;

@Configuration()
public class HibernateConfig {
    @Bean()
    @Scope( ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }


    @Bean()
    @Scope(value = WebApplicationContext.SCOPE_REQUEST)
    public Session session(@Autowired SessionFactory sessionFactory){
        return sessionFactory.openSession();
    }

    @Bean()
    @Scope(value =WebApplicationContext.SCOPE_REQUEST)
    public Repository repository(@Autowired Session session){
        return new HibernateRepository(session);
    }
}

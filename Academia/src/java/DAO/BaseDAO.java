/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Yuri
 */
public class BaseDAO {
     public static Session openSession(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry standardRegistry= new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(standardRegistry);
            Session session = sessionFactory.openSession();
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package ru.pentragon.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MyDataPrepare {
    public static void main(String[] args) {
        dataFill();
    }

    public static void dataFill(){
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            //session.createNativeQuery("CREATE TABLE my_test_table (id bigserial PRIMARY KEY, title1 VARCHAR(255), title2 int);").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

}

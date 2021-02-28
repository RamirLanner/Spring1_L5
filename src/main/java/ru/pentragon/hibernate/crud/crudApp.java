package ru.pentragon.hibernate.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.pentragon.hibernate.MyDataPrepare;

import java.util.Scanner;

public class crudApp {

    private static SessionFactory factory;

    public static void main(String[] args) {
        init();
        System.out.println("Pls insert command:");
        System.out.println("/***/\nread idProduct\nupdate idProduct newTitle newCost\n" +
                "delete idProduct\ncreate title cost\nexit\n/***/");
        Scanner scanner = new Scanner(System.in);
        boolean doIt = true;
        while (doIt){
            String cmd = scanner.nextLine();
            if(cmd.startsWith("exit")){
                doIt = false;
            }

            if(cmd.startsWith("create")){
                String[] data = cmd.split(" ");
                create(new Product(Long.parseLong(data[1]),data[2], Float.parseFloat(data[3])));
                System.out.println("Product added");
            }

            if(cmd.startsWith("read")){
                String[] data = cmd.split(" ");
                System.out.println(read(Long.parseLong(data[1])));
            }

            if(cmd.startsWith("update")){
                String[] data = cmd.split(" ");
                update(Long.parseLong(data[1]),
                        data[2],
                        Float.parseFloat(data[3]));
                System.out.println("Product updated");
            }

            if(cmd.startsWith("delete")){
                String[] data = cmd.split(" ");
                delete(Long.parseLong(data[1]));
                System.out.println("Product deleted");
            }

        }

    }

    public static void init() {
        MyDataPrepare.dataFill();
        factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void create(Product product){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public static Product read(Long id){
        Product product = new Product();
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public static void update(Long id, String newTitle, float newCost){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setTitle(newTitle);
            product.setCost(newCost);
            session.getTransaction().commit();
        }
    }

    public static void delete(Long id){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}

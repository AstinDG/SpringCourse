package com.astindg.onetoone;

import com.astindg.onetoone.model.Principal;
import com.astindg.onetoone.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            //Get Principal and his school
            System.out.println("Get Principal and his school");
            Principal principal = session.get(Principal.class, 1);
            System.out.println(principal);
            System.out.println(principal.getSchool());
            System.out.println("\n\n\n");

            //Get school and its Principal
            System.out.println("Get school and its Principal");
            School school = session.get(School.class, 1);
            System.out.println(school);
            System.out.println(school.getPrincipal());
            System.out.println("\n\n\n");

            //Create new principal and a new school. Build relation between entities.
            System.out.println("Create new principal and a new school. Build relation between entities.");
            Principal newPrincipal = new Principal("Nik", 25);
            School newSchool = new School(149);
            newSchool.setPrincipal(newPrincipal);
            session.save(newPrincipal);
            System.out.println(newSchool);
            System.out.println("\n\n\n");

            //Change principal at existed school
            System.out.println("Change principal at existed school");
            Principal newPrincipal1 = new Principal("Mike", 32);

            System.out.println(newPrincipal1);
            System.out.println(school);

            school.setPrincipal(newPrincipal1);
            session.save(newPrincipal1);
            session.update(newPrincipal1);
            System.out.println(school);
            System.out.println("\n\n\n");

            //Set more than one principal at existed school
            // IllegalStateException "object references an unsaved transient instance "
            Principal newPrincipal2 = new Principal("Lisa", 25);
            System.out.println(school);
            school.setPrincipal(newPrincipal2);
            System.out.println(school);

            session.getTransaction().commit();
        }

    }
}

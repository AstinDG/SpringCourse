package com.astindg.onetomany;

import com.astindg.onetomany.model.Director;
import com.astindg.onetomany.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(final String... args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory factory = configuration.buildSessionFactory();

        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //Get any Director and list of movies
            System.out.println("Get any Director and list of movies");

            Director director = session.get(Director.class, 1);
            System.out.println(director);
            for (Movie movie : director.getMovies()){
                System.out.println(movie);
            }

            System.out.println("\n\n");

            //Get any Movie and Director
            System.out.println("Get any Movie and Director");
            Movie movieFromHibernate = session.get(Movie.class, 1);
            System.out.println(movieFromHibernate);
            System.out.println(movieFromHibernate.getDirector());

            System.out.println("\n\n");
            //Add one more movie
            System.out.println("Add one more movie");
            Movie newMovie = new Movie("Inglourious Basterds", 2009);
            newMovie.setDirector(director);
            System.out.println(newMovie);

            System.out.println("\n\n");
            //Add new Director and new Movie, build relations between this entities
            System.out.println("Add new Director and new Movie, build relations between this entities");
            Director newDirector = new Director("James Cameron", 69);
            Movie newMovie2 = new Movie("Titanic", 1997);
            newMovie2.setDirector(newDirector);
            session.save(newDirector);
            System.out.println(newMovie);

            System.out.println("\n\n");
            //Change Director at existed Movie
            System.out.println("Change Director at existed Movie");
            Movie movie = session.get(Movie.class, 1);
            System.out.println("Before changing: " + movie);
            movie.setDirector(newDirector);
            System.out.println("After changing: " + movie);
            System.out.println("\n\n");

            //Remove one Movie
            System.out.println("Remove one Movie");
            Movie movieToRemove = session.get(Movie.class, 11);
            System.out.println("Move to remove: " + movieToRemove);
            session.delete(movieToRemove);

            System.out.println("\n\n");

            session.getTransaction().commit();
        }
    }
}

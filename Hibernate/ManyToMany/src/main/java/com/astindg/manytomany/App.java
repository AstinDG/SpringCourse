package com.astindg.manytomany;

import com.astindg.manytomany.model.Actor;
import com.astindg.manytomany.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(final String... args) {
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory factory = cfg.buildSessionFactory();

        try (factory){
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            /*---------------------------------------------------------------------*/
            System.out.println("Create movie and several actors. Build relations between this entities.");
            Movie newMovie = new Movie("Titanic", 1997);
            Actor newActor1 = new Actor("Leonardo DiCaprio", 48);
            Actor newActor2 = new Actor("Kate Winslet", 47);

            newMovie.setActors(new ArrayList<>(List.of(newActor1, newActor2)));

            newActor1.setMovies(new ArrayList<>(Collections.singletonList(newMovie)));
            newActor2.setMovies(new ArrayList<>(Collections.singletonList(newMovie)));

            session.save(newMovie);

            session.save(newActor1);
            session.save(newActor2);
            System.out.println("\n\n");
            /*---------------------------------------------------------------------*/


            /*---------------------------------------------------------------------*/
            System.out.println("Get any movie and its actors.");
            Movie movie = session.get(Movie.class, 1);
            System.out.println("Movie: " + movie + "\n" +
                                "Actors: " + movie.getActors());
            System.out.println("\n\n");
            /*---------------------------------------------------------------------*/

            /*---------------------------------------------------------------------*/
            System.out.println("Get any actor and add a movie for him.");
            Movie newMovie2 = new Movie("The Revenant", 2015);
            Actor existedActor = session.get(Actor.class, 1);

            newMovie2.setActors(new ArrayList<>(List.of(existedActor)));
            existedActor.getMovies().add(newMovie2);

            session.save(newMovie2);
            //existedActor persistent object
            System.out.println("\n\n");
            /*---------------------------------------------------------------------*/

            /*---------------------------------------------------------------------*/
            System.out.println("Get any actor and remove one of his movies.");
            Actor actor = session.get(Actor.class, 1);
            System.out.println("Actor: " + actor + "\n"
                    + "Movies: " + actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);
            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
            /*---------------------------------------------------------------------*/
        }
    }
}

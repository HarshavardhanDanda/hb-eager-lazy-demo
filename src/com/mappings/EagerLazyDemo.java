package com.mappings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session= factory.getCurrentSession();
        try{
            //start a transaction
            session.beginTransaction();

            int theId=1;
            Instructor tempInstructor= session.get(Instructor.class, theId);

            System.out.println("Instructor: "+ tempInstructor);

            System.out.println("Courses: "+ tempInstructor.getCourses());


            //commit transaction
            session.getTransaction().commit();

            session.close();
            System.out.println("\nthe session is closed here!!!\n");

            //as the .getCourses() is already lazy loaded when the session was open,
            //we can fetch even after session is closed
            System.out.println("Courses: "+ tempInstructor.getCourses());


            System.out.println("completed!!");
        }finally{
            session.close();
            factory.close();
        }
    }
}

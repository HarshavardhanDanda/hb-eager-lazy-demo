package com.mappings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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

            //with hql we can access lazy data even after session is closed
            //option 2: hibernate query with hql
            int theId=1;
            Query<Instructor> query=session.createQuery("select i from Instructor i "+"JOIN FETCH i.courses "+
                    "where i.id=: theInstructorId", Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId", theId);

            //execute query and get instructor
            Instructor tempInstructor= query.getSingleResult();

            System.out.println("Instructor: "+ tempInstructor);

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

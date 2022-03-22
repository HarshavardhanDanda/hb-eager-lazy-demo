package com.mappings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {
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

            int theId=10;
            Course tempCourse= session.get(Course.class, theId);

            System.out.println("deleting course:   "+tempCourse);
            //deleting course but not instructor cuz not cascaded delete
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            session.close();
            factory.close();
        }
    }
}

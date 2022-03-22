package com.mappings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            //create some new courses
            Course tempCourse1= new Course("this is first course");
            Course tempCourse2= new Course("this is second course");

            //add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            //save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("completed!!");
        }finally{
            session.close();
            factory.close();
        }
    }
}

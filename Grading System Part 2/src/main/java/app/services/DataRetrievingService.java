package app.services;

import database.Database;

import java.util.ArrayList;

public class DataRetrievingService
{
    private DataRetrievingService()
    {
    }
    
    public static int getMark(String student_email , String course_name)
    {
        return Database.getMark( student_email , course_name );
    }
    
    public static int getLowestMark(String course_name)
    {
        return Database.getLowestMark( course_name );
    }
    
    public static int getHighestMark(String course_name)
    {
        return Database.getHighestMark( course_name );
    }
    
    public static int getAverageMark(String course_name)
    {
        return Database.getAverageMark( course_name );
    }
    
    public static int getMedianMark(String course_name)
    {
        return Database.getMedianMark( course_name );
    }
    
    public static String getStudentName(String student_email)
    {
        return Database.getStudentName( student_email );
    }
    
    public static ArrayList<String> getCourses(String student_email)
    {
        return Database.getCourses( student_email );
    }
    
    public static ArrayList<String> getCoursesAndMarks(String student_email)
    {
        return Database.getCoursesAndMarks( student_email );
    }
    
    public static int getStudentID(String student_email)
    {
        return Database.getStudentID( student_email );
    }
    
    public static int getMarkByStudentID(int student_id , String course_name)
    {
        return Database.getMarkByStudentID( student_id , course_name );
    }
}

import database.Database;

import java.sql.*;

public class Testing
{
    public static void main(String[] args)
    {
        //testing();
        String student_email = "ea@ea.com";
        String student_password = "123";
        String course_name = "computer";
        if(Database.isStudent( student_email , student_password ))
        {
            System.out.println( Database.getMark( student_email.toUpperCase() , course_name ) );
            System.out.println( Database.getAverageMark( course_name.toUpperCase() ) );
            System.out.println( Database.getMedianMark( course_name ) );
            System.out.println( Database.getHighestMark( course_name ) );
            System.out.println( Database.getLowestMark( course_name ) );
            System.out.println( Database.getCoursesAndMarks( student_email.toUpperCase() ) );
            System.out.println( Database.getMarkByStudentID( Database.getStudentID( student_email.toUpperCase() ) ,
                    Database.getCourseID( course_name ) ) );
        }
    }
    
    private static void testing()
    {
        //connect to mysql database
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306" + "/testim" , "root" ,
                    "00913900487" );
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement( "SELECT * FROM users" );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println( resultSet.getString( "id" ) );
                System.out.println( resultSet.getString( "name" ) );
                System.out.println( resultSet.getString( "password" ) );
            }
            
            //insert into database
            preparedStatement = connection.prepareStatement( "INSERT INTO users (name, " + "password) VALUES (?, ?)" );
            preparedStatement.setString( 1 , "test" );
            preparedStatement.setString( 2 , "test" );
            preparedStatement.executeUpdate();
            
            //update database
            preparedStatement = connection.prepareStatement( "UPDATE users SET name = ?" + " WHERE id = ?" );
            preparedStatement.setString( 1 , "koko" );
            preparedStatement.setString( 2 , "3" );
            preparedStatement.executeUpdate();
            
            preparedStatement = connection.prepareStatement( "SELECT * FROM users" );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println( resultSet.getString( "id" ) );
                System.out.println( resultSet.getString( "name" ) );
                System.out.println( resultSet.getString( "password" ) );
            }
            
            //delete from database
            preparedStatement = connection.prepareStatement( "DELETE FROM users WHERE " + "id = ?" );
            preparedStatement.setString( 1 , "3" );
            preparedStatement.executeUpdate();
            
            preparedStatement = connection.prepareStatement( "SELECT * FROM users" );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println( resultSet.getString( "id" ) );
                System.out.println( resultSet.getString( "name" ) );
                System.out.println( resultSet.getString( "password" ) );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

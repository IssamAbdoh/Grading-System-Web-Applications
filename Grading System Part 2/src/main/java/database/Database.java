package database;

import java.sql.*;
import java.util.ArrayList;

public class Database
{
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    private Database()
    {
    }
    
    private static void initializeConnection()
    {
        if(connection == null)
        {
            try
            {
                Class.forName( "com.mysql.jdbc.Driver" );
                connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306" + "/students_grading_system"
                        , "root" , "00913900487" );
                /*
                //Initialize the script runner
                ScriptRunner sr = new ScriptRunner( connection );
                //Creating a reader object
                Reader reader = new BufferedReader( new FileReader( "src/main/java" +
                        "/database" + "/statements.sql" ) );
                //Running the script
                sr.runScript( reader );
                */
                System.out.println( "Database connected" );
            }
            catch(SQLException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean isStudent(String student_email , String student_password)
    {
        initializeConnection();
        try
        {
            preparedStatement = connection.prepareStatement( "SELECT * FROM students " + "WHERE " + "LOWER" +
                    "(student_email) = LOWER(?) AND student_password = ? ;" );
            preparedStatement.setString( 1 , student_email );
            preparedStatement.setString( 2 , student_password );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static int getMark(String student_email , String course_name)
    {
        initializeConnection();
        try
        {
            String sql_statement =
                    "SELECT m.mark FROM marks m " + "WHERE m.student_id " + "in (SELECT s.student_id " + "FROM " +
                            "students s WHERE LOWER(s" + ".student_email) = LOWER(?)) " + "AND " + "m.course_id in " + "(SELECT " + "c" + ".course_id from courses c WHERE LOWER(c.course_name) = LOWER" + "(?)); ";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , student_email );
            preparedStatement.setString( 2 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt( "mark" );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public static int getLowestMark(String course_name)
    {
        initializeConnection();
        try
        {
            String sql_statement = "SELECT MIN(m.mark) FROM marks m " + "WHERE m" + ".course_id in (SELECT c" +
                    ".course_id from courses c WHERE LOWER(c" + ".course_name) = LOWER(?)); ";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public static int getHighestMark(String course_name)
    {
        initializeConnection();
        try
        {
            String sql_statement = "SELECT MAX(m.mark) FROM marks m " + "WHERE m" + ".course_id in (SELECT c" +
                    ".course_id from courses c WHERE LOWER(c" + ".course_name) = LOWER(?)); ";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public static int getAverageMark(String course_name)
    {
        initializeConnection();
        try
        {
            String sql_statement = "SELECT AVG(m.mark) FROM marks m " + "WHERE m" + ".course_id in (SELECT c" +
                    ".course_id from courses c WHERE LOWER(c" + ".course_name) = LOWER(?)); ";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public static int getMedianMark(String course_name)
    {
        initializeConnection();
        try
        {
            String sql_statement = "SET @rowindex := -1;";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.executeUpdate();
            //String sql_statement2 = " SELECT AVG(d.mark) as Median  \n" + "FROM
            // (SELECT @rowindex:=@rowindex + 1 AS rowindex, sm.mark AS mark  \n" +
            // "FROM (SELECT m.mark FROM marks AS m WHERE m.course_id IN  \n" + "
            // (SELECT c.course_id FROM courses c WHERE c.course_name = ? )) AS sm  \n"
            // + "ORDER BY sm.mark) AS d  \n" + "WHERE d.rowindex IN (FLOOR(@rowindex /
            // 2), CEIL(@rowindex / 2));";
            
            String sql_statement2 = " SELECT " + " AVG(d.mark) as Median " + " FROM " + " (SELECT " + "@rowindex" +
                    ":=@rowindex + 1 AS rowindex, " + " sm.mark AS " + "mark " + " FROM (SELECT m.mark FROM marks AS "
                    + "m WHERE m" + ".course_id IN " + " (SELECT c.course_id FROM courses c " + "WHERE LOWER(c" +
                    ".course_name) = LOWER(?) )) AS sm " + " ORDER" + " BY sm.mark) AS d " + " WHERE " + " d.rowindex"
                    + " IN (FLOOR" + "(@rowindex / 2), CEIL(@rowindex / 2)); ";
            
            preparedStatement = connection.prepareStatement( sql_statement2 );
            preparedStatement.setString( 1 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public static String getStudentName(String student_email)
    {
        initializeConnection();
        try
        {
            String sql_statement = "SELECT s.student_name FROM students s WHERE s" + ".student_email = ?; ";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , student_email );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getString( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static ArrayList<String> getCourses(String student_email)
    {
        initializeConnection();
        ArrayList<String> courses = new ArrayList<>();
        try
        {
            String sql_statement =
                    "SELECT LOWER(c.course_name) \n" + "FROM courses c " + "\n" + "WHERE c.course_id " + "IN \n" +
                            "(SELECT m2.course_id\n" + "FROM" + " marks " + "m2\n" + "WHERE m2.student_id IN \n" +
                            "(SELECT s" + ".student_id\n" + "FROM students s\n" + "WHERE s.student_email = ?)" + ");\n";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , student_email );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                courses.add( resultSet.getString( 1 ) );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return courses;
    }
    
    //get courses and marks for a student
    public static ArrayList<String> getCoursesAndMarks(String student_email)
    {
        initializeConnection();
        ArrayList<String> courses = new ArrayList<>();
        int student_id = getStudentID( student_email );
        try
        {
            String sql_statement =
                    "SELECT c.course_name , m.mark\n" + "FROM courses c " + ", marks m\n" + "WHERE c" + ".course_id " + "IN \n" + "(SELECT m2" + ".course_id\n" + "FROM marks m2\n" + "WHERE m2.student_id = " + "?)\n" + "AND c.course_id = m.course_id\n" + "AND m.student_id = ?;";
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setInt( 1 , student_id );
            preparedStatement.setInt( 2 , student_id );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                courses.add( resultSet.getString( 1 ) + " " + resultSet.getString( 2 ) );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return courses;
    }
    
    public static int getStudentID(String student_email)
    {
        initializeConnection();
        int student_id = 0;
        String sql_statement = "SELECT s.student_id\n" + "FROM students s\n" + "WHERE " + "s" + ".student_email = ?;";
        try
        {
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , student_email );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                student_id = resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return student_id;
        }
    }
    
    public static int getMarkByStudentID(int student_id , int course_id)
    {
        initializeConnection();
        int mark = -1;
        String sql_statement = "SELECT m.mark\n" + "FROM marks m\n" + "WHERE m" + ".student_id = ?\n" + "AND m" +
                ".course_id = ?;";
        try
        {
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setInt( 1 , student_id );
            preparedStatement.setInt( 2 , course_id );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                mark = resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return mark;
    }
    
    public static int getMarkByStudentID(int student_id , String course_name)
    {
        initializeConnection();
        int course_id = getCourseID( course_name );
        return getMarkByStudentID( student_id , course_id );
    }
    
    public static int getCourseID(String course_name)
    {
        initializeConnection();
        int course_id = 0;
        String sql_statement = "SELECT c.course_id\n" + "FROM courses c\n" + "WHERE c.course_name = ?;";
        try
        {
            preparedStatement = connection.prepareStatement( sql_statement );
            preparedStatement.setString( 1 , course_name );
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                course_id = resultSet.getInt( 1 );
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return course_id;
    }
}

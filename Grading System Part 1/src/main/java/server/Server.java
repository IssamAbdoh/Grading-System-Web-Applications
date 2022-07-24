package server;

import database.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try
        {
            // server is listening on port 1234
            server = new ServerSocket( 1234 );
            server.setReuseAddress( true );
            
            // running infinite loop for getting
            // client request
            while(true)
            {
                // socket object to receive incoming client
                // requests
                System.out.println( "Waiting for client request ...\n" );
                Socket client = server.accept();
                
                // Displaying that new client is connected
                // to server
                System.out.println( "New client connected " + client.getInetAddress().getHostAddress() + "\n" );
                
                // create a new thread object
                ClientHandler clientSock = new ClientHandler( client );
                
                // This thread will handle the client
                // separately
                new Thread( clientSock ).start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(server != null)
            {
                try
                {
                    server.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static class ClientHandler implements Runnable
    {
        private final Socket clientSocket;
        private String student_email;
        private String student_password;
        private String student_name;
        private ArrayList<String> courses;
        PrintWriter out = null;
        BufferedReader in = null;
        
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
        
        public void run()
        {
            try
            {
                // get the output stream of client
                out = new PrintWriter( clientSocket.getOutputStream() , true );
                
                // get the input stream of client
                in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
                
                signIn();
                serveStudent();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(out != null)
                    {
                        out.close();
                    }
                    if(in != null)
                    {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        private void serveStudent()
        {
            try
            {
                do
                {
                    displayCourses();
                    out.println( "Choose a course or 'exit' to quit :" );
                    out.println( "\0" );
                    String course = in.readLine().toLowerCase();
                    if(course.equals( "exit" ))
                    {
                        break;
                    }
                    else if(!courses.contains( course ))
                    {
                        out.println( "Invalid course" );
                    }
                    else
                    {
                        printCourseInfo( course );
                    }
                }
                while(true);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        private void printCourseInfo(String course)
        {
            /*
             * - Students can see their marks in different courses,
             * and select a particular course to see
             * their mark and the class avg/ median/ highest/lowest marks.
             */
            StringBuilder sb = new StringBuilder();
            sb.append( "Your mark in " ).append( course ).append( " is " ).append( Database.getMark( student_email ,
                    course ) ).append( "\n" );
            sb.append( "Class Average is " ).append( Database.getAverageMark( course ) ).append( "\n" );
            sb.append( "Class Median is " ).append( Database.getMedianMark( course ) ).append( "\n" );
            sb.append( "Class Highest is " ).append( Database.getHighestMark( course ) ).append( "\n" );
            sb.append( "Class Lowest is " ).append( Database.getLowestMark( course ) ).append( "\n" );
            out.println( sb );
            System.out.println( "Printing course info for :\nCourse : " + course + "\nStudent Email : " + student_email + "\n" + "Student Name : " + student_name + "\n" );
        }
        
        private void displayCourses()
        {
            out.println( "Your courses are :" );
            courses = Database.getCourses( student_email );
            for(String c : Database.getCoursesAndMarks( student_email ))
            {
                out.println( c );
            }
            // writing the received message from
            // client
            System.out.println( "Displaying courses for :\nStudent Email : " + student_email + "\n" + "Student Name " +
                    ":" + " " + student_name + "\n" );
        }
        
        private void signIn()
        {
            boolean is_Student;
            do
            {
                takeEmailAndPassword();
                is_Student = Database.isStudent( student_email , student_password );
                if(!is_Student)
                {
                    out.println( "Invalid email or password" );
                }
            }
            while(!is_Student);
            student_name = Database.getStudentName( student_email );
            out.println( "Successfully signed in as student \nWelcome " + student_name );
            System.out.println( "A student has successfully signed in \nEmail : " + student_email + "\nName : " + student_name + "\n" );
        }
        
        public void takeEmailAndPassword()
        {
            try
            {
                String line;
                do
                {
                    out.println( "Enter your email :" );
                    out.println( "\0" );
                    line = in.readLine();
                    
                    //if line is not null
                    if(line == null)
                    {
                        out.println( "input is not valid" );
                    }
                    else
                    {
                        student_email = line;
                    }
                }
                while((line) == null || (line).equals( "" ));
                
                do
                {
                    out.println( "Enter your password :" );
                    out.println( "\0" );
                    line = in.readLine();
                    
                    //if line is not null
                    if(line == null)
                    {
                        out.println( "input is not valid" );
                    }
                    else
                    {
                        student_password = line;
                    }
                }
                while((line) == null || (line).equals( "" ));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

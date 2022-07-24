package app.servlets;

import app.services.DataRetrievingService;
import app.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet (urlPatterns = "/login.servlets")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException
    {
        request.getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request , response );
    }
    
    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException,
            ServletException
    {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        
        boolean is_valid_user = LoginService.validateUser( email , password );
        
        if(is_valid_user)
        {
            request.setAttribute( "email" , email );
            request.setAttribute( "name" , DataRetrievingService.getStudentName( email ) );
            request.setAttribute( "courses" , DataRetrievingService.getCourses( email ) );
            request.setAttribute( "student_ID" , DataRetrievingService.getStudentID( email ) );
            request.getRequestDispatcher( "/WEB-INF/views/welcome.jsp" ).forward( request , response );
        }
        else
        {
            request.setAttribute( "errorMessage" , "Invalid Credentials !!" );
            request.getRequestDispatcher( "/WEB-INF/views/login.jsp" ).forward( request , response );
        }
    }
}
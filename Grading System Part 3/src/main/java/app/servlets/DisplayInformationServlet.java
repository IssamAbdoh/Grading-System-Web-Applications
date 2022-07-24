package app.servlets;

import app.services.DataRetrievingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/course-information.servlets")
public class DisplayInformationServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException
    {
        String course = request.getParameter( "course" );
        int student_ID = Integer.parseInt( request.getParameter( "studentID" ) );
        
        request.setAttribute( "course" , course );
        request.setAttribute( "student_ID" , student_ID );
        
        /*
         * Students can see their marks in different courses,and select a particular
         * course to see their mark and the class avg/ median/ highest/lowest marks.
         *
         */
        
        request.setAttribute( "student_mark" , DataRetrievingService.getMarkByStudentID( student_ID , course ) );
        request.setAttribute( "class_average" , DataRetrievingService.getAverageMark( course ) );
        request.setAttribute( "class_median" , DataRetrievingService.getMedianMark( course ) );
        request.setAttribute( "class_highest" , DataRetrievingService.getHighestMark( course ) );
        request.setAttribute( "class_lowest" , DataRetrievingService.getLowestMark( course ) );
        
        request.getRequestDispatcher( "/WEB-INF/views/course-information.jsp" ).forward( request , response );
    }
}

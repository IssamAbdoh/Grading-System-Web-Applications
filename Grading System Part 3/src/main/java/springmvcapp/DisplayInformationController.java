package springmvcapp;

import app.services.DataRetrievingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes ({ "studentID" , "name" , "email" , "courses" })
public class DisplayInformationController
{
    @RequestMapping (value = "/course-information.springmvc", method = RequestMethod.GET)
    public String showCourseInformation(ModelMap model , @RequestParam String course)
    {
        int studentID = (int) model.get( "studentID" );
        System.out.println( "Student ID: " + studentID );
        
        model.put( "course" , course );
        
        /*
         * Students can see their marks in different courses,and select a particular
         * course to see their mark and the class avg/ median/ highest/lowest marks.
         */
        
        model.put( "student_mark" , DataRetrievingService.getMarkByStudentID( studentID , course ) );
        model.put( "class_average" , DataRetrievingService.getAverageMark( course ) );
        model.put( "class_median" , DataRetrievingService.getMedianMark( course ) );
        model.put( "class_highest" , DataRetrievingService.getHighestMark( course ) );
        model.put( "class_lowest" , DataRetrievingService.getLowestMark( course ) );
        
        return "course-information";
    }
    
    @RequestMapping (value = "/returnToHomePage.springmvc", method = RequestMethod.GET)
    public String returnToHomePage()
    {
        return "welcome";
        //return "redirect:welcome.jsp";
    }
}

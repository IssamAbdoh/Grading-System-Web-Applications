package springmvcapp;

import app.services.DataRetrievingService;
import app.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes ({ "studentID" , "name" , "email" , "courses" })
public class LoginController
{
    @RequestMapping (value = "/login.springmvc", method = RequestMethod.GET)
    public String showLoginPage()
    {
        return "login";
    }
    
    @RequestMapping (value = "/login.springmvc", method = RequestMethod.POST)
    public String handleLoginRequest(ModelMap model , @RequestParam String email , @RequestParam String password)
    {
        boolean is_valid_user = LoginService.validateUser( email , password );
        
        if(is_valid_user)
        {
            model.put( "email" , email );
            //model.addAttribute("email", email);
            model.put( "name" , DataRetrievingService.getStudentName( email ) );
            model.put( "courses" , DataRetrievingService.getCourses( email ) );
            model.put( "studentID" , DataRetrievingService.getStudentID( email ) );
            model.put( "password" , password );
            
            return "welcome";
        }
        else
        {
            model.put( "errorMessage" , "Invalid Credentials !!" );
            
            return "login";
        }
    }
}

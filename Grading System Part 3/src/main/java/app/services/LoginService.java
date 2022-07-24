package app.services;

import database.Database;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    private LoginService()
    {
    }
    
    public static boolean validateUser(String student_email , String student_password)
    {
        return Database.isStudent( student_email , student_password );
    }
}

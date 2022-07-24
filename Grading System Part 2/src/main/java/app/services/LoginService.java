package app.services;

import database.Database;

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

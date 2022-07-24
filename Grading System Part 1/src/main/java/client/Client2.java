package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2
{
    // driver code
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner( System.in );
        PrintWriter out;
        BufferedReader in;
        // establish a connection by providing host and port
        // number
        try
        {
            Socket socket = new Socket( "localhost" , 1234 );
            // writing to server
            out = new PrintWriter( socket.getOutputStream() , true );
            
            // reading from server
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            
            // object of scanner class
            String line;
            while(true)
            {
                //read from server until they stop
                do
                {
                    line = in.readLine();
                    if(line.contains( "\0" ))
                    {
                        break;
                    }
                    System.out.println( line );
                }
                while(!line.contains( "\n" ));
                line = scanner.nextLine();
                
                // sending the user input to server
                out.println( line );
                out.flush();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // closing the scanner object
            scanner.close();
        }
    }
}

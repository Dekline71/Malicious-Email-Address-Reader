/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jake
 */
public class EmailReader 
{
/**
* @param args the command line arguments
*/
    public static void main(String[] args) 
    {
        if (args.length < 1) 
        {
            // Handle error
            System.out.println("Missing argument. Terminating program.");
            System.exit(1); //Signal Error
        }
        
        // Read the filename from the command line argument
        String filename = args[0];
        BufferedReader inputStream = null;
        String fileLine;

        // Validate Filename input before reading file.
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]"); //Whitelist of accepted characters
        Matcher matcher = pattern.matcher(filename);
        System.out.println(filename);

        if (matcher.find()) // If filename is invalid, reject it.
        {
            // File name contains bad chars; handle error
            System.out.println(filename + "Contains invalid input.");
        }
        else
        {
            try 
            {
                inputStream = new BufferedReader(new FileReader(filename));
                System.out.println("Email Addresses:");
                // Read one Line using BufferedReader
                while ((fileLine = inputStream.readLine()) != null) 
                {
                    // Validate input of file.
                    pattern = Pattern.compile("[^@A-Za-z0-9._]"); //Whitelist of accepted characters
                    matcher = pattern.matcher(fileLine);

                    if (matcher.find()) // If fileLine is invalid, reject it.
                    {
                        // File name contains bad chars; handle error
                        System.out.println(fileLine + "Contains invalid input. Email will not be accepted.");
                    }
                    else
                    {
                        System.out.println(fileLine);
                    }
                }
                } catch (IOException io) 
                {
                    System.out.println("File IO exception" + io.getMessage());
                } 
                finally 
                {
                    // Need another catch for closing 
                    // the streams          
                    try 
                    {
                        if (inputStream != null) 
                        {
                            inputStream.close();
                        }
                    } catch (IOException io) 
                    {
                        System.out.println("Issue closing the Files" + 
                        io.getMessage());
                    }
                }
        }
    }
}




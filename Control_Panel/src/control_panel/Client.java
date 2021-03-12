/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_panel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author risingstar
 */
public class Client {
    
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList<String>();
    int port;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    Panel frame;
    
    Client(int port, String username, Panel frame)
    {
        this.port = port;
        this.username = username;
        this.frame = frame;
    }
    
    //--------------------------//
    
    public void ListenThread() 
    {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
        
    public void send()
    {
        //Getting the current date
        Date date = new Date();
        //This method returns the time in millis
        long timeMilli = date.getTime();
        if (writer != null)
        {
            writer.println(Long.toString(timeMilli));
            writer.flush();
        }
        else
        {
            frame.logProcess("Please connect user. \n");
        }
    }
    
    public void connect()
    {
        if (isConnected == false) 
        {
            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                frame.logProcess(username + " connected.\n");
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                frame.logProcess("Cannot Connect! Try Again. \n");
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            frame.logProcess("You are already connected. \n");
        }
    }

    //--------------------------//
    
    public void disconnect() 
    {
        try 
        {
            sock.close();
            frame.logProcess(username + " has been disconnected. \n");
        } catch(Exception ex) {
            frame.logProcess("Failed to disconnect. \n");
        }
        isConnected = false;

    }
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String stream;

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                    //Getting the current date
                    Date date = new Date();
                    //This method returns the time in millis
                    long timeMilli = date.getTime();
                    
                    long elapsedTime = timeMilli - Long.parseLong(stream);
                    String msg = Long.toString(elapsedTime);
                    frame.queue.put(msg);
//                    frame.showTime(msg);
                    
                }
           } catch (Exception ex) { }
        }
    }
}

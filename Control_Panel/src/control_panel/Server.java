/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_panel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author risingstar
 */
public class Server{
    
    ArrayList<PrintWriter> clientOutputStreams;
    ArrayList<String> users;
    ArrayList<ServerSocket> sockets = new ArrayList<ServerSocket>();
    
    Panel frame;
    
    int port;
    
    Server(int port, Panel frame)
    {
        this.port = port;
        this.frame = frame;
    }

    public class ClientHandler implements Runnable
    {
        BufferedReader reader;
        Socket sock;
        PrintWriter client;

        public ClientHandler(Socket clientSocket, PrintWriter user)
        {
            client = user;
            try
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex)
            {
                frame.logProcess("Unexpected error... \n");
            }
        }

        @Override
        public void run() 
        {
            String message;

            try
            {
                while ((message = reader.readLine()) != null) 
                {
                    tellEveryone(message);
                } 
            } 
            catch (Exception ex) 
            {
                frame.logProcess("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            } 
        }
    }
    
    public class ServerStart implements Runnable 
    {
        int nPort;
        ServerStart(int port)
        {
            nPort = port;
        }
        
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList<PrintWriter>();
            users = new ArrayList<String>(); 
            
            try
            {
                ServerSocket serverSock = new ServerSocket(nPort);
                sockets.add(serverSock);
                
                frame.logProcess("Server started. \n");

                while (true) 
                {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                    frame.logProcess("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                frame.logProcess("Error making a connection. \n");
            }
        }
    }
    
    public void tellEveryone(String message) 
    {
        Iterator<PrintWriter> it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = it.next();
                writer.println(message);
//                ta_chat.append("Sending: " + message + "\n");
                writer.flush();
//                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
            } 
            catch (Exception ex) 
            {
                frame.logProcess("Error broadcasting message. \n");
            }
        }
    }
    
    public void start()
    {
        Thread starter = new Thread(new ServerStart(port));
        starter.start();
    }
    
    public void stop()
    {
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
            for (int i = 0; i < sockets.size(); i++)
            {
                try {
                    sockets.get(i).close();
                    System.out.println("Socket" + Integer.toString(i) + " has been closed");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        frame.logProcess("Server stopping... \n");
    }
}

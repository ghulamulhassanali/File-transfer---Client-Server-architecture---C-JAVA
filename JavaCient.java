/*
 *Client program to receive files across netwrok from a server. Written in java. Can accept files from either of java or c written servers.
 *Advanced Programming - Lab 03
 *Author: Ghulam Ul Hassan Ali - 00261
 *Date: 4th March 2015
 */



package advanced.programming.lab.pkg3;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class JavaClient {

  public final static int SOCKET_PORT = 55556;      // you may change this
  public final static String SERVER = "192.168.108.128";  // localhost
  public final static String
       FILE_TO_RECEIVED = "C:\\Hassan\\recieved.txt";  // change this path  

  public final static int FILE_SIZE = 6022386; // file size assumed, it should larger than the file to be transferred

  public static void main (String [] args ) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    try {
      sock = new Socket(SERVER, SOCKET_PORT);//creating socket
      System.out.println("Connecting...");

      // receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);  //writing to the output stream
      bos.flush();
      System.out.println("File " + FILE_TO_RECEIVED
          + " downloaded (" + current + " bytes read)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

}

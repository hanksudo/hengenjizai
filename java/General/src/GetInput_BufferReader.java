/*
 *  �ϥ� BufferReader Ū���ϥΪ̿�J��r
 *  �iŪ�J�ťզr����J
 *  2006/12/26
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetInput_BufferReader { 
    public static void main(String[] args) throws IOException { 
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 

        System.out.print("Please your name: "); 
        String text = buf.readLine(); 
        System.out.println("Hello! " + text + "."); 
    } 
} 

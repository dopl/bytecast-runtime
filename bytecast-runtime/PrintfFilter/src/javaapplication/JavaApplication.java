/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;


import java.util.*;

public class JavaApplication {
   
    public static void main(String[] args) {
        
        Filter ft= new Filter();
       ArrayList<ArrayList<String>> results= ft.filtPinrtf_1("objdump_a.out2");
       
       for(ArrayList<String> printf:results)
       {
        System.out.println("\n=========Filter Printf=========\n");   
       
        for(String p:printf)
        {
         System.out.println(p);
       
        }
       } 
    }
}

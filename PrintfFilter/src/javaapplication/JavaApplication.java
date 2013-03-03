/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

/**
 *
 * @author chengxu
 */

import java.util.*;

public class JavaApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO 123 code application logic here
        
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

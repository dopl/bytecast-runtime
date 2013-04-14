/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.io.*;
import java.util.*;
import java.io.InputStreamReader;


public class Filter {
    
    //printf("The value is %d\n", ret);
    public ArrayList<ArrayList<String>> filtPinrtf_1(String path)
    {
        ArrayList<ArrayList<String>> AllprintfS=new ArrayList<ArrayList<String>>();
        try 
        {

            BufferedReader br=null;
            
            br= new BufferedReader(new FileReader(path));
            
            String line;
            
            
            int bufferLineCount=10;
            int linecount=0;
            ArrayList<String> Lines= new ArrayList<String>();
            ArrayList<String> lastLines=new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                
                linecount++;
                if(linecount>bufferLineCount)
                {
                    linecount=0;
                    lastLines=(ArrayList<String>)Lines.clone();
                    Lines.clear();
                }
                Lines.add(line);
                if(line.contains("callq  4003e0 <printf@plt>"))
                {
                 // find the nearest mov    $0x4006ae,%eax
                    String linenumber=line.substring(0,line.indexOf(":"));
                     ArrayList<String> printfS= findPrintfCommands(Lines,lastLines,linenumber,linecount);
                     AllprintfS.add((ArrayList<String>)printfS.clone());                
                }
             }
            return AllprintfS;            
        } 
        catch (Exception e) 
        {
            return null;
         // System.out( "The process failed: {0}", e.toString());
        }

    }
    
    private ArrayList<String> findPrintfCommands(ArrayList<String> lines,ArrayList<String> lastlines,String printLineNumber, int linecount)
    {
        
        // find the nearest mov    $0x4006ae,%eax
        ArrayList<String> printfS=new ArrayList<String>();
        // search it in lines
        boolean findIt=false;
        int findCount=0;
        for(int i=linecount;i>=0;i--)
        {
           String line=lines.get(i);
           if(line.contains("mov    $0x") && line.contains(",%eax"))
           {
                  findCount++;
                  if(findCount==2)
                  {
                  findIt=true;
                  for(int j=i;j<=linecount;j++)
                      printfS.add(lines.get(j));
                  }
           }
           
           
        }
        
        // if not found, search it in last 10 lines.
        if(!findIt)
        {
           for(int i=lastlines.size()-1;i>=0;i--)
           {
              String line=lastlines.get(i);
              if(line.contains("mov    $0x") && line.contains(",%eax"))
              {
                  findCount++;
                  if(findCount==2)
                  {
                  findIt=true;
                  
                  // add the printf in prev buffer
                  for(int j=i;j<lastlines.size();j++)
                      printfS.add(lastlines.get(j));
                  
                  // add the printf in this buffer
                  for(int k=0;k<=linecount;k++)
                      printfS.add(lines.get(k));
                  }
           
              }
           
           }
        
        }
        
        int a=0;
        int b=0;
        return printfS;
        
    
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.jimple.runtime;

import java.io.*;
import java.util.List;

public class FindConstString {
    List<String> ConstStringList; 
    public void find(String rodataFilePath)
    {        
        try
        {
            String line;
            String str;
            BufferedReader br=null;            
            br= new BufferedReader(new FileReader(rodataFilePath));            
           
            while ((line = br.readLine()) != null) {
                if(line.contains("rodata"))
                {
                    line = br.readLine();
                    if (line.lastIndexOf("..")+1 != line.lastIndexOf("."))
                    {
                        str = line.substring(line.lastIndexOf("..")+2,line.lastIndexOf("."));
                        ConstStringList.add(str);
                    }
                    else
                    {
                        str = line.substring(line.lastIndexOf("..")+2);
                        line = br.readLine();
                        ConstStringList.add(str+findNextRecur(line,br));                        
                    }
                }
            }
        }
        catch (Exception e) 
        {
         // System.out( "The process failed: {0}", e.toString());
        }    
    }
    
    public String findNextRecur(String line,BufferedReader br)
    {  
        String str = "";
        try
        {
            if (line.contains("."))
            {
                str = line.substring(line.indexOf("  ")+2,line.lastIndexOf("."));
            }
            else
            {
                line = br.readLine();
                str += findNextRecur(line,br);
            }
        }
        catch (Exception e) 
        {
            return str;
         // System.out( "The process failed: {0}", e.toString());
        }   
        return str;
    }
}

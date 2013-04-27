/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.runtime;

import java.io.*;
import edu.syr.bytecast.amd64.api.constants.FileFormats;
import edu.syr.bytecast.amd64.api.output.IExecutableFile;
import edu.syr.bytecast.amd64.api.output.ISection;
import edu.syr.bytecast.interfaces.fsys.ExeObjSegment;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author mengxi
 */
public class FindConstString {
    private String ConstString; 
    
        public String getConstString() {
        return ConstString;
    }

    /**
     * @param ConstString the ConstString to set
     */
    public void setConstString(String ConstString) {
        this.ConstString = ConstString;
    }
    
    public String find(List<ExeObjSegment> raw_data,long mem_addr)
    {
        List<Byte> ret = new ArrayList<Byte>();
        Boolean findEnd = true;
        int endOffset = 0;
        for (ExeObjSegment seg : raw_data)
        {
            if (mem_addr >= seg.getStartAddress()&&mem_addr<= seg.getEndAddress())
            {
                findEnd = false;
                long offset_l = mem_addr - seg.getStartAddress();
                int offset_i = safeLongToInt(offset_l);
                for (int i=offset_i;i<seg.getSize();i++)
                {
                    if (seg.getBytes().get(i) == 00)
                    {
                        findEnd = true;
                        endOffset = i;
                        ret = seg.getBytes(offset_i,(endOffset-offset_i));
                    }  break;                 
                }
                if (findEnd == false)
                {
                    ret = seg.getBytes(offset_i,(seg.getSize()-offset_i));
                }
            }
            if (!findEnd)
            {
                for (int i=0;i<seg.getSize();i++)
                {
                    if (seg.getBytes().get(i) == 00)
                    {
                        findEnd = true;
                        endOffset = i;
                        for (Byte bytes:seg.getBytes(0,endOffset))
                        {
                            ret.add(bytes);
                        }
                    }
                    break;
                }
                 if (findEnd == false)
                {
                    for (Byte bytes:seg.getBytes(0,endOffset))
                    {
                       ret.add(bytes);
                    }                    
                }
            }
        }
        setConstString(BytesToString(ret));
        return getConstString(); 
    }
     
    public static int safeLongToInt(long l) {
        int i = (int)l;
        if ((long)i != l) {
        throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return i;
    }    
    
    public String BytesToString(List<Byte> _bytes)
    {
        String file_string = "";

        for(int i = 0; i < _bytes.size(); i++)
        {
           file_string += (char)(int)_bytes.get(i);
        }

        return file_string;    
    }
//    public void find(String rodataFilePath)
//    {        
//        try
//        {                        
//            br= new BufferedReader(new FileReader(rodataFilePath));
//            Boolean first = true;
//            String line;
//            String str;
//            while ((line = br.readLine()) != null) {
//                if(line.contains("rodata"))
//                {
//                    line = br.readLine();
//                    if (line.lastIndexOf("..")+1 != line.lastIndexOf("."))
//                    {
//                        str = line.substring(line.lastIndexOf("..")+2,line.lastIndexOf("."));
//                        ConstStringList.add(str);
//                        str = line.substring(line.lastIndexOf(".")+1);
//                    }
//                    else
//                    {
//                        str = line.substring(line.lastIndexOf("..")+2);
//                        line = br.readLine();
//                        ConstStringList.add(str+findNextRecur(line));
//                        str = line.substring(line.lastIndexOf(".")+1);
//                    }
//                    first = false;
//                }
//                if (!first && line.contains("."))
//                {
//                    
//                }
//            }
//        }
//        catch (Exception e) 
//        {
//         // System.out( "The process failed: {0}", e.toString());
//        }    
//    }
//    
//    public String findNextRecur(String line)
//    {  
//        String str = "";
//        try
//        {
//            if (line.contains("."))
//            {
//                str = line.substring(line.indexOf("  ")+2,line.lastIndexOf("."));
//            }
//            else
//            {
//                str = line.substring(line.indexOf("  ")+2);
//                line = br.readLine();
//                str += findNextRecur(line);
//            }
//        }
//        catch (Exception e) 
//        {
//            return str;
//         // System.out( "The process failed: {0}", e.toString());
//        }   
//        return str;
//    }

    /**
     * @return the ConstString
     */

}

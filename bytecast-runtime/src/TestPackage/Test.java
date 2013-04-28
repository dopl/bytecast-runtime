/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import edu.syr.bytecast.amd64.api.output.IExecutableFile;
import edu.syr.bytecast.amd64.util.AMD64MockGenerator;
import edu.syr.bytecast.test.mockups.MockBytecastFsys;
import edu.syr.bytecast.util.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.syr.bytecast.runtime.*;
import edu.syr.bytecast.interfaces.fsys.*;
import edu.syr.bytecast.test.mockups.MockBytecastFsysPoc3;

/**
 *
 * @author mengxi
 * 
 *  Test some of bytecast-runtime function, using AMD64 MockGenerator
 */
public class Test {
    public static void main(String a[]) throws Exception{   
              
//        Set<String> exclusion = new HashSet<String>();
//         Paths.v().setRoot("/home/mengxi/code/bytecast/");                  
//        try {
//            Paths.v().parsePathsFile();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        try{
        LoadDLL loader = new LoadDLL();
        String path = "./src/edu/syr/bytecast/runtime/libPrintStr.so";
        loader.load(path);
//        loader.load("/home/mengxi/code/bytecast/bytecast-runtime/bytecast-runtime/src/edu/syr/bytecast/runtime/libPrintStr.so");
        
        
//        char[] strToPrint=new String("the value is").toCharArray();
//        
//        int str_len=strToPrint.length;
//        
        BytecastRuntime bytecast_runtime = new BytecastRuntime("the value is ", 110);
        
        bytecast_runtime.Print();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
//        exclusion.add("<_IO_printf>");
//        AMD64MockGenerator gen = 
//                new AMD64MockGenerator(new MockBytecastFsysPoc3(),
//                "/home/mengxi/code/bytecast/bytecast-documents/AsciiManip01Prototype/a.out.static.objdump",
//                "<main>",exclusion);
//        try {
//            IExecutableFile ex = gen.generate().buildInstructionObjects();
//            FindConstString fcs = new FindConstString();
//            System.out.println(fcs.find(ex.getSectionsWithRawData(), 4195996));
//            
//        } catch (FileNotFoundException ex1) {
//            Logger.getLogger(AMD64MockGenerator.class.getName()).log(Level.SEVERE, null, ex1);
//        } catch (IOException ex1) {
//            Logger.getLogger(AMD64MockGenerator.class.getName()).log(Level.SEVERE, null, ex1);
//        }
//    }
}

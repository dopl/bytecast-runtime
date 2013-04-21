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

/**
 *
 * @author mengxi
 * 
 *  Test some of bytecast-runtime function, using AMD64 MockGenerator
 */
public class Test {
    public static void main(String a[]) throws Exception{
        Set<String> exclusion = new HashSet<String>();
         Paths.v().setRoot("/home/code/");                  
        try {
            Paths.v().parsePathsFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exclusion.add("<_IO_printf>");
        AMD64MockGenerator gen = 
                new AMD64MockGenerator(new MockBytecastFsys(),
                "/home/code/bytecast-documents/AsciiManip01Prototype/a.out.static.objdump",
                "<main>",exclusion);
        try {
            IExecutableFile ex = gen.generate().buildInstructionObjects();
            
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(AMD64MockGenerator.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex1) {
            Logger.getLogger(AMD64MockGenerator.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}

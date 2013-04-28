/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.runtime;

/**
 *
 * @author mengxi
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExtractDLL
{
    public void extractBinary(String filename) throws IOException {
        String path = "/home/code/bytecast/bytecast-runtime/bytecast-runtime/src/edu/syr/bytecast/runtime/"+filename;
        InputStream is = ExtractDLL.class.getResourceAsStream(path);
        OutputStream os = new FileOutputStream(filename);
        while(true){
            byte[] buffer = new byte[32*1024];
            int len = is.read(buffer);
            if(len == -1) { 
                break; 
            }
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close(); 
        is.close();
    }
}
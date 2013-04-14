package javaapplication1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
public class ExtractDll {
 
  public void extractBinary() throws IOException {
    String path = "/javaapplication1/helloworld.so";
    InputStream is = ExtractDll.class.getResourceAsStream(path);
    OutputStream os = new FileOutputStream("helloworld.so");
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

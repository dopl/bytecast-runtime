package javaapplication1;

import java.io.File;

/**
 *
 * @author invictus
 */
public class HelloWorld {
     private native void print();
     public static void main(String[] args) {
       new HelloWorld().print();
     }

     static {
       ExtractDll extractor = new ExtractDll();
       try {
         extractor.extractBinary();
       } catch(Exception ex){
         ex.printStackTrace();
       }
       File file = new File("helloworld.so");
       System.load(file.getAbsolutePath());
     }
}

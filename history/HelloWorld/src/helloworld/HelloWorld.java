/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

/**
 *
 * @author tongxu
 */
public class HelloWorld {
    
    static{
        System.loadLibrary("Hello");
    
    }
    
    public native void DisplayHelloWorld();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new HelloWorld().DisplayHelloWorld();
        
        
    }
}

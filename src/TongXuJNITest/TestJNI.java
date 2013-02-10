/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TongXuJNITest;

/**dfddsdf fdsa
 *
 * @author tongxu
 */

public class TestJNI {
    
    static{
        try{
                System.loadLibrary("hello");
        }
        catch(UnsatisfiedLinkError e){
            System.err.println("Cannot load hello library:\n" + e.toString());
        }
            
    }

    public native void SayHello(String strName);
    
    
}

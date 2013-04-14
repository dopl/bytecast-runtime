
package runtime;

/**
 *
 * @author tongxu
 */
import java.util.*;
public class RunTime {
    private Stack<Object> m_stack;
    
    public void push(Object obj){
        m_stack.push(obj);
    }
    
    public void printf(String str){
            printString(str);
    }
    
    private native void printString(String str);
    
    public void syscall(){
    }
    
    public static void main(String[] args) {
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.syr.bytecast.runtime;

import java.util.Stack;

/**
 *
 * @author Tongxu
 */
public class Runtime {
    private String m_ConstString;
    private Stack<Object> m_parameters;
    
    public Runtime(String str, Stack<Object> s){
        this.m_ConstString = str;
        this.m_parameters = s;
    }
    
    /**
     * @return the constString
     */
    public String getConstString() {
        return m_ConstString;
    }

    /**
     * @param constString the constString to set
     */
    public void setConstString(String constString) {
        this.m_ConstString = constString;
    }

    /**
     * @return the parameters
     */
    public Stack<Object> getParameters() {
        return m_parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Stack<Object> parameters) {
        this.m_parameters = parameters;
    }
    
    public void push(Object obj){
        getParameters().push(obj);
    }
    
    public void printf(){
            printString(getConstString(), getParameters());
    }
    
    private native void printString(String str, Stack<Object> s);
    
    public void syscall(){
    }
    
    public static void main(String[] args) {
        
    }

    
}

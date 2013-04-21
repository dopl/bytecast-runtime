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
    
    public void push(Object obj){
        m_parameters.push(obj);
    }
    
    public void printf(){
            printString(m_ConstString,m_parameters);
    }
    
    private native void printString(String str, Stack<Object> s);
    
    public void syscall(){
    }
    
    public static void main(String[] args) {
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.syr.bytecast.runtime;

import java.util.*;

/**
 *
 * @author Tongxu
 */
public class Runtime {
    private String m_ConstString;
    private Stack<Integer> m_parameters;
    private Byte[] commands;
    
    public Runtime(String str, Stack<Integer> s){
        this.m_ConstString = str;
        this.m_parameters = s;
    }
    
    public Runtime(String str, Stack<Integer> s, Byte[] a){
        this(str,s);
        this.commands = a;
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
    public Stack<Integer> getParameters() {
        return m_parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Stack<Integer> parameters) {
        this.m_parameters = parameters;
    }
    
    public void addParameter(int p){
        getParameters().add(p);
    }
    
    public void printf(){
        printString(getConstString(),getParameters(),commands);
    }
    
    private native void printString(String str, List<Integer> s, Byte[] c);
    
    public void syscall(){
    }
    
    public static void main(String[] args) {
        
    }

}

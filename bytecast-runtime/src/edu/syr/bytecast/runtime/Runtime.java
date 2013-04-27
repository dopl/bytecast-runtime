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
<<<<<<< HEAD
    private List<Integer> m_parameters;
    private Byte[] commands;
    
    public Runtime(String str, List<Integer> s)
    {
=======
    private Stack<Integer> m_parameters;
    private Byte[] commands;
    
    public Runtime(String str, Stack<Integer> s){
>>>>>>> 5525746d2281019932a301e3430042e4d8afdc41
        this.m_ConstString = str;
        this.m_parameters = s;
    }
    
<<<<<<< HEAD
    public Runtime(String str, List<Integer> s, Byte[] a)
    {
        this.m_ConstString = str;
        this.m_parameters = s;
        this.commands= a;
=======
    public Runtime(String str, Stack<Integer> s, Byte[] a){
        this(str,s);
        this.commands = a;
>>>>>>> 5525746d2281019932a301e3430042e4d8afdc41
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
<<<<<<< HEAD
    public List<Integer> getParameters() {
=======
    public Stack<Integer> getParameters() {
>>>>>>> 5525746d2281019932a301e3430042e4d8afdc41
        return m_parameters;
    }

    /**
     * @param parameters the parameters to set
     */
<<<<<<< HEAD
    public void setParameters(List<Integer> parameters) {
=======
    public void setParameters(Stack<Integer> parameters) {
>>>>>>> 5525746d2281019932a301e3430042e4d8afdc41
        this.m_parameters = parameters;
    }
    
    public void addParameter(int p){
        getParameters().add(p);
    }
    
    public void printf(){
<<<<<<< HEAD
            printString(getConstString(), getParameters(), commands);
    }
    
    private native void printString(String str,  List<Integer> s, Byte[] c);
    
=======
        printString(getConstString(),getParameters(),commands);
    }
    
    private native void printString(String str, List<Integer> s, Byte[] c);
    
    public void syscall(){
    }
    
    public static void main(String[] args) {
        
    }

>>>>>>> 5525746d2281019932a301e3430042e4d8afdc41
}

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
    private char[] m_ConstString;
    private int m_parameters;
    private char[] commands;
    
    
    public Runtime(String s, int i){
        this.m_ConstString = s.toCharArray();
        this.m_parameters = i;
    }
    
    public Runtime(String s, int i, char[] a)
    {
        this.m_ConstString = s.toCharArray();
        this.m_parameters = i;
        this.commands= a;
    }
    
    public void Print(){
        printString(getCommands(), getConstString(), getParameters());
    }
    
    private native void printString(char[] cmd, char[] string, int parameter);

    /**
     * @return the ConstString
     */
    public char[] getConstString() {
        return m_ConstString;
    }

    /**
     * @param ConstString the ConstString to set
     */
    public void setConstString(char[] ConstString) {
        this.m_ConstString = ConstString;
    }

    /**
     * @return the parameters
     */
    public int getParameters() {
        return m_parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(int parameters) {
        this.m_parameters = parameters;
    }

    /**
     * @return the commands
     */
    public char[] getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(char[] commands) {
        this.commands = commands;
    }
            

    
}

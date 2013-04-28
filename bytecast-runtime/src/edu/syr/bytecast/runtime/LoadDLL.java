/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.runtime;

/**
 *
 * @author mengxi
 */
import java.io.File;

public class LoadDLL {
    
    public void load(String path){
        File file = new File(path);
        System.load(file.getAbsolutePath());
    }
}

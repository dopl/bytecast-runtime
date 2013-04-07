/*
 *      Author: TongXu MengxiChen
 */
import java.util.*;

public class RunTime {
    private List<RuntimeState> m_state;
    
//    public void addByte(bytevalue){
//        
//    }
    
    public void movEax(int value){
        RuntimeState state = new RuntimeState();
        state._type = "mov";
        state._reg1 ="eax";
        state._reg2 = "edi";
        m_state.add(state);
    }
    
    public void callq(int addr){
        int index = 0;
        byte[] new_mem = new byte[10];
        for(RuntimeState state:m_state){
            new_mem[index] = state.toArry();
            index++;
        }
        callq0(new_mem,addr);
    }
    
    public native void callq0(byte[] ins,int addr);
    
}



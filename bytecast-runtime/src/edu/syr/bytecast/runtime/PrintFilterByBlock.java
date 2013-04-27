/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.runtime;

import edu.syr.bytecast.amd64.api.constants.InstructionType;
import edu.syr.bytecast.amd64.api.constants.OperandType;
import edu.syr.bytecast.amd64.api.constants.RegisterType;
import edu.syr.bytecast.amd64.api.instruction.IInstruction;
import edu.syr.bytecast.amd64.api.output.MemoryInstructionPair;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mengxi
 */
public class PrintFilterByBlock {
    private int start;
    private int end;  
    public ArrayList<MemoryInstructionPair> printfInstList;
    
    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }
    
    public void filter(List<MemoryInstructionPair> instList, int startIndex)
    {   
        this.setStart(0);
        this.setEnd(0);
        Boolean find = false;
        for (int index = startIndex;index<instList.size();index++)
        {
            IInstruction inst = instList.get(index).getInstruction();
            
            if(inst.getInstructiontype().equals(InstructionType.CALLQ)
                    && inst.getOperands().get(1).getOperandType().equals(OperandType.SECTION_NAME)
                    && (inst.getOperands().get(1).getOperandValue().equals("Printf@plt")                
                    ||inst.getOperands().get(1).getOperandValue().equals("printf@plt")))
            {
                setEnd(index);                
                for (index -= 10;index<getEnd();index++)
                {
                    IInstruction inst1 = instList.get(index).getInstruction();
                    if(inst1.getInstructiontype().equals(InstructionType.MOV))
                    {
                        if(inst1.getOperands().get(0).getOperandType().equals(OperandType.MEMORY_EFFECITVE_ADDRESS)
                           && !inst1.getOperands().get(0).getOperandValue().equals(0L)
                           && inst1.getOperands().get(1).getOperandType().equals(OperandType.REGISTER)
                           && inst1.getOperands().get(1).getOperandValue().equals(RegisterType.EAX))
                        {                         
                            setStart(index) ;
                        }
                    }
                }
                if((getEnd() - getStart()) <= 10)
                {                
                    find = true;
                    while (getStart()<=getEnd())
                    {                        
                        printfInstList.add(instList.get(getStart()));
                        setStart(getStart() + 1);
                    }
                }
            }
            if (find)
            {
                break;
            }
        }       
    } 

}

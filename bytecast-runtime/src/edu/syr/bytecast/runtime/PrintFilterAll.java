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
public class PrintFilterAll {
    private int start = 0;
    private int end = 0;  
    public List<List<Integer>> printfIndexList;
    
    public void filter(List<MemoryInstructionPair> instList)
    {
        int index;
        for (index = 0;index<instList.size();index++)
        {
            IInstruction inst = instList.get(index).getInstruction();
            
            if(inst.getInstructiontype().equals(InstructionType.CALLQ)
                    && inst.getOperands().get(1).getOperandType().equals(OperandType.SECTION_NAME)
                    && (inst.getOperands().get(1).getOperandValue().equals("Printf@plt")                
                    ||inst.getOperands().get(1).getOperandValue().equals("printf@plt")))
            {
                end = index;                
                for (index -= 10;index<end;index++)
                {
                    IInstruction inst1 = instList.get(index).getInstruction();
                    if(inst1.getInstructiontype().equals(InstructionType.MOV))
                    {
                        if(inst1.getOperands().get(0).getOperandType().equals(OperandType.MEMORY_EFFECITVE_ADDRESS)
                           && !inst1.getOperands().get(0).getOperandValue().equals(0L)
                           && inst1.getOperands().get(1).getOperandType().equals(OperandType.REGISTER)
                           && inst1.getOperands().get(1).getOperandValue().equals(RegisterType.EAX))
                        {                      
                            start = index ;
                        }
                    }
                }
                if((end - start) <= 10)
                {
                    List<Integer> temp = new ArrayList<Integer>();
                    while (start<=end)
                    {                        
                        temp.add(start);
                        start++;
                    }
                    printfIndexList.add(temp);
                }
            }
        }       
    } 
}
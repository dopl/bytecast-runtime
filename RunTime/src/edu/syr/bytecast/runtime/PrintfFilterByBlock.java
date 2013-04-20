
package edu.syr.bytecast.runtime;

import edu.syr.bytecast.amd64.api.constants.InstructionType;
import edu.syr.bytecast.amd64.api.constants.OperandType;
import edu.syr.bytecast.amd64.api.instruction.IInstruction;
import edu.syr.bytecast.amd64.api.output.MemoryInstructionPair;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TongXu
 */
public class PrintfFilterByBlock {
    private int starter = 0;
    private int end = 0;  
    public ArrayList<MemoryInstructionPair> printfInstList;
    
    public void filter(List<MemoryInstructionPair> instList, int startIndex)
    {        
        for (int index = startIndex;index<instList.size();index++)
        {
            IInstruction inst = instList.get(index).getInstruction();
            
            if(inst.getInstructiontype() == InstructionType.CALLQ
                    && (inst.getOperands().get(1).getOperandValue() == "<Printf@plt>"
                    ||inst.getOperands().get(1).getOperandValue() == "<printf@plt>") )
            {
                end = index;                
                for (index -= 10;index<end;index++)
                {
                    IInstruction inst1 = instList.get(index).getInstruction();
                    if(inst1.getInstructiontype() == InstructionType.MOV)
                    {
                        if(inst1.getOperands().get(0).getOperandType() == OperandType.CONSTANT
                           && inst1.getOperands().get(0).getOperandValue() != "$0x0"
                           && inst1.getOperands().get(1).getOperandType() == OperandType.REGISTER
                           && inst1.getOperands().get(1).getOperandValue() == "%eax" )
                        {                         
                            starter = index ;
                        }
                    }
                }
                if((end - starter) <= 10)
                {                    
                    while (starter<=end)
                    {                        
                        printfInstList.add(instList.get(starter));
                        starter++;
                    }
                }
            }
            break;
        }       
    } 
}

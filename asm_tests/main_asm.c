#include <stdio.h>

int main(int argc, char * argv[]){

  asm("sub	$32, %esp");
	asm("movl	%edi, -20(%ebp)");
	asm("mov	%esi, -32(%ebp)");
	asm("movb	$99, -1(%ebp)");
	asm("lea	-1(%ebp), %eax");
	asm("mov	%eax, %edi");
	asm("movl	$0, %eax");
	asm("call	printf");
	asm("movl	$0, %eax");
	asm("leave");
  return 0;
}

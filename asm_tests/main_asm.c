#include <stdio.h>

int main(int argc, char * argv[]){

  asm("subq	$32, %rsp");
	asm("movl	%edi, -20(%rbp)");
	asm("movq	%rsi, -32(%rbp)");
	asm("movb	$99, -1(%rbp)");
	asm("leaq	-1(%rbp), %rax");
	asm("movq	%rax, %rdi");
	asm("movl	$0, %eax");
	asm("call	printf");
	asm("movl	$0, %eax");
	asm("leave");
  return 0;
}

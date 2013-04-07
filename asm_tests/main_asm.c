#include <stdio.h>

char Format[] = "the pure print out put\n";

int main (void)
{
   asm
   (
      // Make stack space for arguments to printf
      "subl $20, %esp\n"
      "movl %eax, 4(%esp)\n"
      "movl $Format, (%esp)\n"
      "call printf\n"
      // Clean-up the stack
      "addl $20, %esp\n"
   );
   return 0;
}
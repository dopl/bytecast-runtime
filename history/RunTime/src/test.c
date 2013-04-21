#include<stdio.h>

int main()
{ 
  __asm__("mov    $0x40069c,%eax;"
          "mov    %eax,%edi;"
          "mov    $0x0,%eax;"
    "call  printf");
}

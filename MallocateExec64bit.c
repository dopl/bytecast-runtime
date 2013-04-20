/* 
 * File:   main.c
 * Author: chengxu
 *
 * Created on April 20, 2013, 9:15 AM
 */
/* 
 * File:   main2.c
 * Author: chengxu
 *
 * Created on March 10, 2013, 12:56 PM
 */
#include <sys/types.h>
#include <sys/mman.h>
#include <err.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

// objdump 
/*
 *  40051c:	55                   	push   %rbp
  40051d:	48 89 e5             	mov    %rsp,%rbp
  400520:	48 83 ec 10          	sub    $0x10,%rsp
  400524:	89 7d fc             	mov    %edi,-0x4(%rbp)
  400527:	48 89 75 f0          	mov    %rsi,-0x10(%rbp)
  printf("default");
  40052b:	bf f4 05 40 00       	mov    $0x4005f4,%edi
  400530:	b8 00 00 00 00       	mov    $0x0,%eax
  400535:	e8 b6 fe ff ff       	callq  4003f0 <printf@plt>
  return 1;
  40053a:	b8 01 00 00 00       	mov    $0x1,%eax
  40053f:	c9                   	leaveq 
  400540:	c3                   	retq   
 * 
 * 
 */



int main(void)
{
    // get the address of printf function in libc.
    void (*p)()=printf;
    long p_add=p;
   
    int fd = -1;
    char *anon;
    if ((fd = open("/dev/zero", O_RDWR, 0)) == -1)
                err(1, "open");
    anon = mmap(NULL, 4096, PROT_READ|PROT_WRITE |PROT_EXEC, MAP_ANON|MAP_SHARED, -1, 0);
   
    if (anon == MAP_FAILED )
         errx(1, "allocate memory failed");
    
     memset (anon,0x90,4096);
  // string to print out        
        anon[0]=0x65;
        anon[1]=0x66;
        anon[2]=0x67;
        anon[3]=0x00;
        
   unsigned char * tempCode = (unsigned char *) (anon+32);
        
  // executable code start
     tempCode[0]=0x55;   //push   %rbp
     
     tempCode[1] = 0x48;
     tempCode[2] = 0x89;
     tempCode[3] = 0xe5;   // mov    %rsp,%rbp
     
     
     tempCode[4] = 0x48;
     tempCode[5] = 0x83;
     tempCode[6] = 0xec;
     tempCode[7]=0x10;   // sub    $0x10,%rsp
       
     
       tempCode[8] = 0x89;        
     tempCode[9] = 0x7d;
     tempCode[10] = 0xfc;  //mov    %edi,-0x4(%rbp)
       
     tempCode[11]=0x48;
     tempCode[12] = 0x89;
     tempCode[13] = 0x75;
     tempCode[14] = 0xf0;   //mov    %rsi,-0x10(%rbp)
     
   
     
     
    //  mov    addressOfStr,%rdi
     tempCode[15]=0x48;
      tempCode[16]=0xbf;
     // tempcode 17~24 is the 8 bytes address of string
     long string_add=anon;
     tempCode[17]=string_add&0xff;
     tempCode[18]=(string_add>>8)&0xff;
     tempCode[19]=(string_add>>16)&0xff;
     tempCode[20]=(string_add>>24)&0xff;
      tempCode[21]=(string_add>>32)&0xff;
     tempCode[22]=(string_add>>40)&0xff;
     tempCode[23]=(string_add>>48)&0xff;
     tempCode[24]=(string_add>>56)&0xff;
     
     
     
      int i=25;
      
      // mov    $0x0,%eax
     tempCode[i++] = 0xb8;
     tempCode[i++] = 0x00;
     tempCode[i++] = 0x00;
     tempCode[i++] = 0x00;   
     tempCode[i++] = 0x00;
    
     
    // push rax
        tempCode[i++] = 0x51;
     
        
    // move address Of "Printf" to rax
        tempCode[i++] = 0x48;
     tempCode[i++] = 0xb9;
        
        
      tempCode[i++] = p_add&0xff;
     tempCode[i++] = ( p_add>>8)&0xff;
     tempCode[i++] = ( p_add>>16)&0xff;
      tempCode[i++] = ( p_add>>24)&0xff;
     tempCode[i++] = ( p_add>>32)&0xff;
     tempCode[i++] = ( p_add>>40)&0xff;
     tempCode[i++] = ( p_add>>48)&0xff;
       tempCode[i++] = ( p_add>>56)&0xff;
    
      
       
       // call rax
         tempCode[i++] = 0xff;
     tempCode[i++] = 0xd1;  
     
     // pop rax
      tempCode[i++] = 0x59;
       
    // mov    $0x1,%eax
    tempCode[i++] = 0xb8;
     tempCode[i++] = 0x01;
     tempCode[i++] = 0x00;
     tempCode[i++] = 0x00;   
     tempCode[i++] = 0x00;
 
     // leaveq 
      tempCode[i++] = 0xc9;
      
    //  retq  
     tempCode[i] = 0xc3;
     
// typedef pfun
    typedef void(*pfun)();
  // run the code.
   pfun myprint=tempCode;
    myprint();
     
   return (EXIT_SUCCESS);
}

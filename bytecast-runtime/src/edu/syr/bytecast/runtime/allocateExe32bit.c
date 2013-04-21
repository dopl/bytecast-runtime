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

int main(void)
{
    // get the address of printf function in libc.
    void (*p)()=printf;
    int p_add=p;
    
    typedef void(*pfun)();
   // int parpid = getpid(), childpid;
    int fd = -1;
    char *anon;
    if ((fd = open("/dev/zero", O_RDWR, 0)) == -1)
                err(1, "open");
    anon = mmap(NULL, 4096, PROT_READ|PROT_WRITE |PROT_EXEC, MAP_ANON|MAP_SHARED, -1, 0);
         
 
    if (anon == MAP_FAILED )
         errx(1, "allocate memory failed");
 
    unsigned char * tempCode = (unsigned char *) (anon+32);
      

        
    memset (anon,0x90,4096);
            
  // string to print out        
        anon[0]=0x61;
        anon[1]=0x61;
        anon[2]=0x61;
        anon[3]=0x00;
        
        
  // executable code start

     tempCode[0]=0x55;
     tempCode[1] = 0x89;
     tempCode[2] = 0xe5;
     tempCode[3] = 0x83;
     tempCode[4] = 0xe4;
     tempCode[5] = 0xf0;
     tempCode[6] = 0x83;
     tempCode[7] = 0xec;
     tempCode[8] = 0x10;   
     tempCode[9] = 0xb8;
   
  //  tempCOde[10~13] is the add of string
  //  reverse order!
     long string_add=anon;
     tempCode[10]=string_add&0xff;
     tempCode[11]=(string_add>>8)&0xff;
     tempCode[12]=(string_add>>16)&0xff;
     tempCode[13]=(string_add>>24)&0xff;
     tempCode[14] = 0x89;
     tempCode[15] = 0x04;
     tempCode[16] = 0x24;

     
     // call printf
     tempCode[17] = 0xe8;  // call
   
   
   // calculate the absolute address
   int current_add=&tempCode[18]+4;
   
   int relateve_add=p_add-current_add;
   
   tempCode[18] = relateve_add&0xff;
   tempCode[19] = (relateve_add>>8)&0xff;
   tempCode[20] = (relateve_add>>16)&0xff;
   tempCode[21] = (relateve_add>>24)&0xff;
   
   tempCode[22]=0xc9;
   tempCode[23]=0xc3;

  // run the code.
   pfun myprint=tempCode;
   myprint();
     
   return (EXIT_SUCCESS);
}




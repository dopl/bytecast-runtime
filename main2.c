


/*

#include <stdio.h>
#include <sys/mman.h>

typedef unsigned (*asmFunc)(void);

int main(int argc, char *argv[])
{
   // probably needs to be page aligned...

   unsigned int codeBytes = 4096;
   void * virtualCodeAddress = 0;

   virtualCodeAddress = mmap(
       NULL,
       codeBytes,
       PROT_READ | PROT_WRITE | PROT_EXEC,
       MAP_ANONYMOUS | MAP_PRIVATE,
       0,
       0);

   printf("virtualCodeAddress = %p\n", virtualCodeAddress);

   // write some code in
   unsigned char * tempCode = (unsigned char *) (virtualCodeAddress);
/*
   tempCode[0] = 0xb8;
   tempCode[1] = 0x00;
   tempCode[2] = 0x11;
   tempCode[3] = 0xdd;
   tempCode[4] = 0xee;
*/
   

/*
   tempCode[0] = 0x55;
   tempCode[1] = 0x89;
   tempCode[2] = 0xe5;
   tempCode[3] = 0x83;
   tempCode[4] = 0xec;
   tempCode[5] = 0x18;
   tempCode[6] = 0xb8;
   tempCode[7] = 0xe0;
   tempCode[8] = 0x84;   
   tempCode[9] = 0x04;
   tempCode[10] = 0x08;
   tempCode[11] = 0x89;
   tempCode[12] = 0x04;
   tempCode[13] = 0x24;
   tempCode[14] = 0xe8;
   tempCode[15] = 0x09;
   tempCode[16] = 0xff;
   tempCode[17] = 0xff;
   tempCode[18] = 0xff;
   

   
   // ret code! Very important!

   tempCode[19] = 0xc9;
   tempCode[20] = 0xc3;
   


   asmFunc myFunc = (asmFunc) (virtualCodeAddress);

  myFunc();

   printf("out is \n");

   return 0;
}
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
       
        int parpid = getpid(), childpid;
        int fd = -1;
        char *anon, *zero;
 
        if ((fd = open("/dev/zero", O_RDWR, 0)) == -1)
                err(1, "open");
 
        anon = mmap(NULL, 4096, PROT_READ|PROT_WRITE |PROT_EXEC, MAP_ANON|MAP_SHARED, -1, 0);
      //  zero = mmap(NULL, 4096, PROT_READ|PROT_WRITE, MAP_FILE|MAP_SHARED, fd, 0);
 
        if (anon == MAP_FAILED || zero == MAP_FAILED)
                errx(1, "either mmap");
 
        unsigned char * tempCode = (unsigned char *) (anon);
        
        // printf as global function : run failed
/*
         tempCode[0] = 0x55;
   tempCode[1] = 0x89;
   tempCode[2] = 0xe5;
   tempCode[3] = 0x83;
   tempCode[4] = 0xec;
   tempCode[5] = 0x18;
   tempCode[6] = 0xb8;
   tempCode[7] = 0xe0;
   tempCode[8] = 0x84;   
   tempCode[9] = 0x04;
   tempCode[10] = 0x08;
   tempCode[11] = 0x89;
   tempCode[12] = 0x04;
   tempCode[13] = 0x24;
   tempCode[14] = 0xe8;
   tempCode[15] = 0x09;
   tempCode[16] = 0xff;
   tempCode[17] = 0xff;
   tempCode[18] = 0xff;

     tempCode[19] = 0xc9;
*/
        

/*
 
*/
   // tempCode[5] = 0x18;

   
   // ret code! Very important!

  // tempCode[19] = 0xc3;
  // tempCode[20] = 0xc3;
        memset (anon,0xc3,4096);
   
        // executable code : printf in main function ( run failed)
/*
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
   tempCode[10] = 0xe0;
   tempCode[11] = 0x84;
   tempCode[12] = 0x04;
   tempCode[13] = 0x08;
   tempCode[14] = 0x89;
   tempCode[15] = 0x04;
     tempCode[16] = 0x24;
   tempCode[17] = 0xe8;
   tempCode[18] = 0x06;
     tempCode[19] = 0xff;
     
      tempCode[16] = 0xff;
   tempCode[17] = 0xff;
   tempCode[18] = 0xb8;
     tempCode[19] = 0x01;
     
      tempCode[20] = 0x00;
   tempCode[21] = 0x00;
   tempCode[22] = 0x00;
     tempCode[23] = 0xc9;

   tempCode[24] = 0xc3;
*/
   
   // executable code 2: test, return value 1 in main function.
     tempCode[0]=0x55;
       tempCode[1] = 0x89;
   tempCode[2] = 0xe5;

   tempCode[3] = 0xb8;
   tempCode[4] = 0x01;
   
    tempCode[5] = 0x00;
   tempCode[6] = 0x00;
   tempCode[7] = 0x00;
   
   
    tempCode[8] = 0x5d;   
   tempCode[9] = 0xc3;
   
   
     int a=   ((int(*)(void))anon)();
     printf("get a value:%d from the executable function",a);
        //strcpy(anon, str1);
       // strcpy(zero, str1);
//  strcpy(zero, str2);
      //  printf("PID %d:\tanonymous %s, zero-backed %s\n", parpid, anon, zero);
       
 
       // sleep(2);
       // strcpy(anon, str2);
       
 
     //   printf("PID %d:\tanonymous %s, zero-backed %s\n", parpid, anon, zero);
/*
        munmap(anon, 4096);
        munmap(zero, 4096);
        close(fd);
*/
        return (EXIT_SUCCESS);
}


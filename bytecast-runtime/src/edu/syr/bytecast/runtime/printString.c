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
#include <jni.h>
#include "edu_syr_bytecast_runtime_BytecastRuntime.h"
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


// printf("the value is %d",a);

// void memoryAlloc(unsigned char cmd[], char str[], int value)
JNIEXPORT void JNICALL Java_edu_syr_bytecast_runtime_BytecastRuntime_printString
  (JNIEnv *env, jobject this_obj, jcharArray cmd, jcharArray str , jint value, jint len)

{
    //char* str_=malloc(5*sizeof(char));
    jchar *str_=(*env)->GetCharArrayElements(env,str,JNI_FALSE);
 // memcpy(str_,str_a,5*sizeof(char));

//    char * str_ = (char *) ref;  
//  jint * narray = (*env)->GetIntArrayElements(env, array, JNI_FALSE);     
 // memcpy(dest, narray, len*sizeof(int));
    

// get the address of printf function in libc.
    void (*p)()=printf;
    long p_add=p;
   
    int fd = -1;
    char *anon;
printf("line 2 executed\n");
    if ((fd = open("/dev/zero", O_RDWR, 0)) == -1)
                err(1, "open");
    anon = mmap(NULL, 4096, PROT_READ|PROT_WRITE |PROT_EXEC, MAP_ANON|MAP_SHARED, -1, 0);
   printf("line 3 executed\n");
    if (anon == MAP_FAILED )
         errx(1, "allocate memory failed");
    
     memset (anon,0x90,4096);
  // string to print out
printf("line 4 executed\n");     
int k=0;
     
     //while(str_[k]!='\0')
while(k<len)
     {
         
         anon[k]=str_[k];
         k++;
     }
    printf("line 5 executed\n");
     // 0x30~39 represents 0~9
     // value to print out
     
     // for integer
    
     // go backward for integer, like 130, we get 031. then reverse it
     unsigned char* reversedValue=malloc(32*sizeof(char));
     printf("line 6 executed\n");
     int j=0;
     while(value>0)
     {
        int bitValue=value%10;
        value=value/10;
        
        unsigned char bitV;
        switch(bitValue)
        {
            case 0: bitV=0x30; break;
            case 1: bitV=0x31; break;
            case 2: bitV=0x32; break;
            case 3: bitV=0x33; break;     
            case 4: bitV=0x34; break;
            case 5: bitV=0x35; break;
            case 6: bitV=0x36; break;
            case 7: bitV=0x37; break;
            case 8: bitV=0x38; break;
            case 9: bitV=0x39; break;
        
        
        }
        printf("line 7 executed\n");
        reversedValue[j]=bitV;
        j++;
     }
printf("line 8 executed\n");
     j--;
     for(;j>=0;j--)
     {
         anon[k++]=reversedValue[j];
     }
     
printf("line 9 executed\n");
     anon[k]='\0';
     
   unsigned char * tempCode = (unsigned char *) (anon+128);
   int i=0;
  // executable code start
     tempCode[i++]=0x55;   //push   %rbp
     
     tempCode[i++] = 0x48;
     tempCode[i++] = 0x89;
     tempCode[i++] = 0xe5;   // mov    %rsp,%rbp
     
     
     tempCode[i++] = 0x48;
     tempCode[i++] = 0x83;
     tempCode[i++] = 0xec;
     tempCode[i++]=0x10;   // sub    $0x10,%rsp
       
     
       tempCode[i++] = 0x89;        
     tempCode[i++] = 0x7d;
     tempCode[i++] = 0xfc;  //mov    %edi,-0x4(%rbp)
       
     tempCode[i++]=0x48;
     tempCode[i++] = 0x89;
     tempCode[i++] = 0x75;
     tempCode[i++] = 0xf0;   //mov    %rsi,-0x10(%rbp)
     
   
          // try to move the value to esi
  //  tempCode[i++] = 0x48;
     tempCode[i++] = 0xbe;
   //  tempCode[i++] = 0xc6;
     tempCode[i++] = 0x56;   
     tempCode[i++] = 0x00;
      tempCode[i++] = 0x00;   
     tempCode[i++] = 0x00; 
     
    //  mov    addressOfStr,%rdi
     
     
     // find "bf" in byte array
     
     tempCode[i++]=0x48;
      tempCode[i++]=0xbf;
     // tempcode 17~24 is the 8 bytes address of string
     long string_add=anon;
     tempCode[i++]=string_add&0xff; 
     tempCode[i++]=(string_add>>8)&0xff;
     tempCode[i++]=(string_add>>16)&0xff;
     tempCode[i++]=(string_add>>24)&0xff;
      tempCode[i++]=(string_add>>32)&0xff;
     tempCode[i++]=(string_add>>40)&0xff;
     tempCode[i++]=(string_add>>48)&0xff;
     tempCode[i++]=(string_add>>56)&0xff;
     
     
     
      
      
      
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
     
printf("line 10 executed\n");
// typedef pfun
    typedef void(*pfun)();
  // run the code.
   pfun myprint=tempCode;
printf("line a executed\n");
    myprint();
printf("line 11 executed\n");

    (*env)->ReleaseCharArrayElements(env,str,str_,JNI_ABORT);
printf("line 12 executed\n");
    return;
}



 

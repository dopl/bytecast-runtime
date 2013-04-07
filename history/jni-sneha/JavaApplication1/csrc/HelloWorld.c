 #include <jni.h>
 #include <stdio.h>
 #include "javaapplication1_HelloWorld.h"
 
 JNIEXPORT void JNICALL 
 Java_javaapplication1_HelloWorld_print(JNIEnv *env, jobject obj)
 {
     printf("Hello World!\n");
     return;
 }

#include "runtime_Runtime.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_runtime_Runtime_mov0
  (JNIEnv *, jobject, jstring, jstring)
{
    printf("From jni_helloworldImpl.cpp :");
    printf("Hello world ! \n");
    return;
}
JNIEXPORT void JNICALL Java_runtime_Runtime_lea0
  (JNIEnv *, jobject, jstring, jstring)
{
    
}
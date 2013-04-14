#include <jni.h>
#include "helloworld_HelloWorld.h"
#incldue <stdio.h>
JNIEXPORT void JNICALL Java_helloworld_HelloWorld_DisplayHelloWorld
  (JNIEnv *, jobject);
{ 
    printf("Hello world!\n");
    return;

}

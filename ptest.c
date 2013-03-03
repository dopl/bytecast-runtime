#include<stdio.h>

int main(int argc, char* argv[])
{
            int ret = 0;
            int ret2 = 0;
            char str[] = "abc";
            printf("the pure printout");
            printf("the int si%d",ret);
printf("the string is %s",str);
printf("the string is %s%d",str,ret2);
printf("The value is %d%d\n",ret,ret2);
printf("The value is %d%d%s",ret,ret2,str);
return 0;

}

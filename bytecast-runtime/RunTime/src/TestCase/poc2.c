#include <stdio.h>

int sum(int a, int b)
{
	return a + b;
}
int halve(int a)
{
	return a/2;
}
int dostuff(int a, int b)
{
	if(a < b)
		return sum(a,b);
	else
		return halve(a);
}

int main(int argc, char* argv[])
{
	if(argc == 3){
		int ret = dostuff((int)argv[1][0],(int)argv[2][0]);
		printf("The value is %d\n", ret);
	}
	else{
		return 1;
	}
	return 0;
}

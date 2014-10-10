 Programming exercise:

 

      Write a small console application that uses a string comparison function (that you

      will also write) that meets the following prototype:

 

            int MyStrCmp(char *a, char *b) - for C;

            or assume passed String data types - for C# / Java / PHP

 

      Entered or passed strings may be of any length; trap for edge conditions and

      display error messages. Functions cannot make use of or "wrap" any existing API

      calls. Function must return an int that is:

<0 if string1 is less than string2;

0 if string1 is the same as string2; or

>0 if string1 is greater than string2;

      and display the 2 strings as noted below.

 

      If the return value is <0, then returned strings are inverted, IE abcdef and uvwxyz,

      would be displayed as fedcba and zyxwvu

 

      If the return value is 0, then one string is returned and is made up with the merged

      2 input strings; IE abcdef and uvwxyz, would be displayed as aubvcwdxeyfz.

 

      If the return value is >0, then one string is returned and is made up with the merged

      2 input strings; IE abcdef and uvwxyz, would be displayed as zfyexdwcvbua
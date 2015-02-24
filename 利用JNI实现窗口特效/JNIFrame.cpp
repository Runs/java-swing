#include <Windows.h>
#include <jni.h>
#include "JNIFrame.h"

JNIEXPORT jint JNICALL Java_JNIFrame_getHwnd
  (JNIEnv *env, jclass obj, jstring title)
{
   HWND hwnd=NULL;
   const char *str;

   str=(*env).GetStringUTFChars(title,JNI_FALSE);
   hwnd=FindWindow(NULL,str);
   (*env).ReleaseStringUTFChars(title,str);
   
   return (jint)hwnd;
}

JNIEXPORT void JNICALL Java_JNIFrame_testJNI
  (JNIEnv *evn, jclass obj, jint hwnd)
{
   //SendMessage((HWND)hwnd,WM_SYSCOMMAND,SC_ICON,1L);
   SetWindowPos((HWND)hwnd,HWND_TOP,0,0,0,0,0L);	
   return ;
}
#define USEMSV_ANDROID
#define USEMSV_ANDROID_LOGPRINT "zverus:"
//#define USEMSV_FILEPRINT

//BEGIN_INCLUDE(all)
#include <jni.h>
#include <errno.h>
#include <android/log.h>

//#include "com_msv_skylee_skylee.h"
#include "ru_centerix_zverus_zverus_ZverusActivity.h"

#include "../../../../../../zverus/zverus.cpp"

JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_Login__Ljava_lang_String_2Ljava_lang_String_2
  (JNIEnv *env, jobject, jstring login, jstring pass){
	return ZverusApi.Login(JVSTRING(login), JVSTRING(pass));
}


JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_Register
  (JNIEnv *env, jobject, jstring login, jstring email, jstring pass){
	return ZverusApi.Register(JVSTRING(login), JVSTRING(email), JVSTRING(pass));
}


JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_Login__
  (JNIEnv *, jobject){
	return ZverusApi.Login();
}

JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_LogOut
  (JNIEnv *, jobject){
	return ZverusApi.LogOut();
}

JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetLoginId
  (JNIEnv *, jobject){
	return ZverusApi.GetLoginId();
}

JNIEXPORT jstring JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetLoginName
  (JNIEnv *env, jobject){
	return env->NewStringUTF((const char*)ZverusApi.GetLoginName());
}

JNIEXPORT jstring JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetLoginError
  (JNIEnv *env, jobject){
	return env->NewStringUTF((const char*)ZverusApi.GetLastError());
}


JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_AddContact
  (JNIEnv *, jobject, jstring){
	  return -1;
}


// contacts
JNIEXPORT jobjectArray JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetContacts
  (JNIEnv *env, jobject obj){
	return ZverusContacts.GetContacts(env);
}

JNIEXPORT jobjectArray JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetContact
  (JNIEnv * env, jobject, jstring login){
	return ZverusContacts.GetContact(env, JVSTRING(login));
}


// chats
JNIEXPORT jobjectArray JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetChats
  (JNIEnv *env, jobject){
return ZverusChats.GetChats(env);
}

JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetChatsUpd
  (JNIEnv *, jobject){
return ZverusChats.GetChatsUpd();
}


// chat
JNIEXPORT jobjectArray JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetMessages
  (JNIEnv *env, jobject, jint cid){
	return ZverusChats.GetMessages(env, cid);
}

JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_GetMessagesUpd
  (JNIEnv *, jobject, jint cid){
	return ZverusChats.GetMessagesUpd(cid);
}

JNIEXPORT jboolean JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_SendMessage
  (JNIEnv *env, jobject, jint cid, jstring msg){
	return ZverusChats.SendMessage(cid, JVSTRING(msg));
}

JNIEXPORT void JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_NativeNmea
  (JNIEnv *env, jobject, jstring line){
	NativeNmea(JVSTRING(line));
	return ;
}

JNIEXPORT void JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_NativeInitAndroidId
(JNIEnv *env, jobject, jstring path, jstring id, jstring uniq, jstring market){
	InitAndroidId(JVSTRING(path), JVSTRING(id), JVSTRING( uniq), JVSTRING(market));
}



JNIEXPORT jint JNICALL Java_ru_centerix_zverus_zverus_ZverusActivity_Test
  (JNIEnv *, jobject){
	  return 1;
}
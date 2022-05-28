#include <jni.h>

#ifndef JNI_CALLBACK_H
#define JNI_CALLBACK_H

namespace Jni
{
void InitCallbacks(JNIEnv* env);

void CallImStrConsumer(JNIEnv* env, jobject consumer, const char* str);
jstring CallImStrSupplier(JNIEnv* env, jobject supplier);
}

#endif

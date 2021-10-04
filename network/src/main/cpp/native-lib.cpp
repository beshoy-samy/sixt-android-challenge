//
// Created by Beshoy Samy on 9/28/2021.
//
#include <jni.h>
#include <string>

extern "C" jstring
Java_com_sixt_network_NetworkKeys_getBaseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "https://cdn.sixt.io/";
    return env->NewStringUTF(baseUrl.c_str());
}
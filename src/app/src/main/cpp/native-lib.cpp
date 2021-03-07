//
// Created by pwams on 07/03/2021.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_test_MainActivity_baseUrlFromJNI(JNIEnv *env, jclass clazz) {
    // TODO: implement baseUrlFromJNI()
    std::string baseURL = "aHR0cHM6Ly82MDA3ZjFhNDMwOWY4YjAwMTdlZTUwMjIubW9ja2FwaS5pby9hcGkvbTEvYWNjb3VudHMv";
    return env->NewStringUTF(baseURL.c_str());
}
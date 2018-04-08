#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_test_musicdownload_APIConstants_base163API(JNIEnv *env, jobject) {

    std::string api163 = "https://music.163.com/api/";

    return env->NewStringUTF(api163.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_test_musicdownload_APIConstants_baseqqAPI(JNIEnv *env, jobject) {

    std::string apiqq = "https://c.y.qq.com/";

    return env->NewStringUTF(apiqq.c_str());
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_test_musicdownload_APIConstants_qqm4aUrl(JNIEnv *env, jobject) {

    std::string qqurl = "http://dl.stream.qqmusic.qq.com/";

    return env->NewStringUTF(qqurl.c_str());
}
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ch_fhnw_ether_video_avfoundation_AVAsset */

#ifndef _Included_ch_fhnw_ether_video_avfoundation_AVAsset
#define _Included_ch_fhnw_ether_video_avfoundation_AVAsset
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeCreate
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeCreate
  (JNIEnv *, jclass, jstring);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeDispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeDispose
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetDuration
 * Signature: (J)D
 */
JNIEXPORT jdouble JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetDuration
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetFrameRate
 * Signature: (J)D
 */
JNIEXPORT jdouble JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetFrameRate
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetFrameCount
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetFrameCount
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetWidth
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetHeight
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeRewind
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeRewind
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetFrame
 * Signature: (JD)[B
 */
JNIEXPORT jbyteArray JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetFrame
  (JNIEnv *, jclass, jlong, jdouble);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeGetNextFrame
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeGetNextFrame
  (JNIEnv *, jclass, jlong);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeLoadFrame
 * Signature: (JDI)I
 */
JNIEXPORT jint JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeLoadFrame
  (JNIEnv *, jclass, jlong, jdouble, jint);

/*
 * Class:     ch_fhnw_ether_video_avfoundation_AVAsset
 * Method:    nativeLoadFrames
 * Signature: (JII)I
 */
JNIEXPORT jint JNICALL Java_ch_fhnw_ether_video_avfoundation_AVAsset_nativeLoadFrames
  (JNIEnv *, jclass, jlong, jint, jint);

#ifdef __cplusplus
}
#endif
#endif

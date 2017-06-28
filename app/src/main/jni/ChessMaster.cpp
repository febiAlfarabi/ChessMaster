#include <jni.h>
#include <string>
#include <android/log.h>
#include <model/Piece.h>
#include <model/Board.h>
#include <tools/Tools.h>

using namespace std;

// 	DOC
/*
 *	http://docs.oracle.com/javase/1.5.0/docs/guide/jni/spec/types.html
 *	http://stackoverflow.com/questions/16850204/how-to-send-a-hashmap-from-java-to-c-via-jni
 *	http://onegazhang.wordpress.com/2011/07/15/cpp-call-java-hashmap/
 *
 */

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "Chess-Master", __VA_ARGS__))

#ifdef __cplusplus
extern "C"{

 	JNIEXPORT jstring JNICALL Java_com_alfarabi_chezzmaster_StartActivity_stringFromJNICPP(JNIEnv * env, jobject obj){
 		return env->NewStringUTF("Hello From CPP");
 	}

 	JNIEXPORT jintArray JNICALL Java_com_alfarabi_chessmaster_tools_Util_locToInt(JNIEnv * env, jobject obj, jstring value){
 		const char* s = env->GetStringUTFChars(value, (jboolean*)0);
 		const string st = s;
 		int x = atoi(st.substr(0, st.find(":")).c_str());
		int y = atoi(st.substr(st.find(":")+1, st.length()-1).c_str());
		jint xy[2] ;
 		xy[0] = x;
 		xy[1] = y;
 		jsize len = 2;
 		jintArray j = env->NewIntArray(len);
 		env->SetIntArrayRegion(j,0,len, xy);
 		return j;
 	}


 	JNIEXPORT jstring JNICALL Java_com_alfarabi_chessmaster_tools_Util_locToString(JNIEnv * env, jobject obj, jint x_value, jint y_value){
 		char x[3] ;
 		string s ;
 		sprintf(x, "%d", x_value);
 		s.append(x);
 		s.append(":");
 		sprintf(x, "%d", y_value);
 		s.append(x);
 		return env->NewStringUTF(s.c_str());
 	}

	JNIEXPORT jobject JNICALL Java_com_alfarabi_chessmaster_tools_Util_getDecissionNative(
			JNIEnv * env, jobject obj, jint level, jlongArray myPieces, jlongArray yourPieces
			, jobject jColumnsMap, jobject jPiecesInColumnMap, jobject jPiecesMap){

		Board * board ;
		jlong * jlMyPieces = env->GetLongArrayElements(myPieces, NULL);
		jlong * jlyourPieces = env->GetLongArrayElements(yourPieces, NULL);

		LOGI("LEVEL = %i", level);
		LOGI("SIZE MY = %i", env->GetArrayLength(myPieces));
		LOGI("SIZE YOU = %i", env->GetArrayLength(yourPieces));

		jclass jcColumnsMap = env->GetObjectClass(jColumnsMap);
		jmethodID jmGetColumn = env->GetMethodID(jcColumnsMap, "get", "(Ljava/lang/String)Lcom/java/Object;");//com/alfarabi/chessmaster/model/Column
//		jobject joColumn = env->CallObjectMethod(jColumnsMap,jmGetColumn);
//		jmethodID jmGetColumn = env->GetM
		return NULL;
	}

}


//
// 	JNIEXPORT jobject JNICALL Java_com_alfarabi_chessmaster_tools_Util_countAndGetDecissionNative(JNIEnv * env, jobject obj, jobject jboard, jobjectArray jarrPieces){
// 			Board board = cutil::getJavaBoard(env, obj, jboard, jarrPieces);
// 			jobject dMoveReturn ;
//
// 			return NULL;
// 	 	}
//
// 	JNIEXPORT jobjectArray JNICALL Java_com_alfarabi_chessmaster_tools_Util_getPossibleMovesNative(JNIEnv * env, jobject obj, jobject jboard, jobjectArray jarrPieces, jobject jPiece){
// 			Board board = cutil::getJavaBoard(env, obj, jboard, jarrPieces);
//
// 			jclass cPieces = env->GetObjectClass(jPiece);
////			bool threatned ;
//			jfieldID jThreatned = env->GetFieldID(cPieces, "threatned", "Z");
//			bool jbThreatned = env->GetBooleanField(jPiece, jThreatned);
//
////			char* pTAG ;
//			jfieldID jPTag = env->GetFieldID(cPieces, "pTAG", "Ljava/lang/String;");
//			jstring jsPTag = (jstring)env->GetObjectField(jPiece, jPTag);
//			string charPTag = env->GetStringUTFChars(jsPTag, (jboolean*)NULL);
//
////			char* loc ;
//			jfieldID jloc = env->GetFieldID(cPieces, "loc", "Ljava/lang/String;");
//			jstring jsloc = (jstring)env->GetObjectField(jPiece, jloc);
//			string charloc =  env->GetStringUTFChars(jsloc, (jboolean*)NULL);
//
////			bool pMINE ;
//			jfieldID jPMine = env->GetFieldID(cPieces, "pMINE", "Z");
//			bool jbPMine = env->GetBooleanField(jPiece, jPMine);
//
////			int status ;
//			jfieldID jStatus = env->GetFieldID(cPieces, "status", "I");
//			int jiStatus = env->GetIntField(jPiece,jStatus);
//
////			long id ;
//			jfieldID jId = env->GetFieldID(cPieces, "id", "J");
//			long jlId = env->GetLongField(jPiece, jId);
//
//			char * tag ;
//			strstr(tag, charPTag.c_str());
//			char * loc ;
//			strstr(loc, charloc.c_str());
//			Piece piece(tag, loc, jbPMine, jlId, jbThreatned, jiStatus);
//			LOGI("%s pieces Tag", charPTag.c_str());
//			LOGI("%s pieces loc", charloc.c_str());
//			LOGI("%i pieces Boolean Threatned", (bool)jbThreatned);
////			LOGI("%i Id", jlId);
//// 			std::list<std::string> possible = Mobilitiez::getPossiblePlaced(board, piece);
// 			jobjectArray possibleMoves ;
//
// 	 		return possibleMoves;
// 	 	}



#endif

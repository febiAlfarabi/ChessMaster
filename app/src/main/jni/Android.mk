LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ChessMaster
LOCAL_SRC_FILES := ChessMaster.cpp
APP_STL := gnustl_static
LOCAL_LDLIBS    := -llog
LOCAL_CPPFLAGS += -fexceptions

include $(BUILD_SHARED_LIBRARY)

# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("cmake_app")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        cmake_app

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

find_library(android-lib android)
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

add_library(tflite
        SHARED
        IMPORTED)

set_target_properties(tflite
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_LIST_DIR}/tf-lite-api/generated-libs/${ANDROID_ABI}/libtensorflowlite.so )
#        /Users/gaeunkim/AndroidStudioProjects/Cmake_app/app/src/main/cpp/lib/${ANDROID_ABI}/libtensorflowlite.so)
set(pathLIBOPENCV_JAVA /Users/gaeunkim/AndroidStudioProjects/Cmake_app/opencv/native)

set( INCLUDE_DIRS
        ${CMAKE_CURRENT_LIST_DIR}/tf-lite-api/include
        ${CMAKE_CURRENT_LIST_DIR}/tf-lite-api/include/flatbuffers/include
        ${pathLIBOPENCV_JAVA}/jni/include
        )
include_directories(${INCLUDE_DIRS})


set(path_OPENCV ${pathOPENCV}/native/libs/${ANDROID_ABI}/libopencv_java4.so)

add_library(lib_opencv SHARED IMPORTED)
#set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${pathLIBOPENCV_JAVA})
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${path_OPENCV})



target_link_libraries( # Specifies the target library.
        cmake_app
        tflite
        lib_opencv
        ${android-lib}
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
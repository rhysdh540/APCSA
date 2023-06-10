#include <iostream>
#include "dev_rdh_compsci_util_ConsoleUtils.h"

#ifdef _WIN32
    // Windows-specific code
    #include <windows.h>

    JNIEXPORT jint JNICALL Java_dev_rdh_compsci_util_ConsoleUtils_terminalWidth(JNIEnv *env, jclass cls) {
        CONSOLE_SCREEN_BUFFER_INFO csbi;
        GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
        return csbi.dwSize.X;
    }
    JNIEXPORT jint JNICALL Java_dev_rdh_compsci_util_ConsoleUtils_terminalWidth(JNIEnv *env, jclass cls) {
        CONSOLE_SCREEN_BUFFER_INFO csbi;
        GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
        return csbi.dwSize.Y;
    }

#elif defined(__APPLE__) || defined(__unix__)
    // macOS/Unix-specific code
    #include <sys/ioctl.h>
    #include <unistd.h>

    JNIEXPORT jint JNICALL Java_dev_rdh_compsci_util_ConsoleUtils_terminalWidth(JNIEnv *env, jclass cls) {
        struct winsize size;
        ioctl(STDOUT_FILENO, TIOCGWINSZ, &size);
        return size.ws_col;
    }
    JNIEXPORT jint JNICALL Java_dev_rdh_compsci_util_ConsoleUtils_terminalHeight(JNIEnv *env, jclass cls) {
        struct winsize size;
        ioctl(STDOUT_FILENO, TIOCGWINSZ, &size);
        return size.ws_row;
    }

#endif

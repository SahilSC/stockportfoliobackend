[Call Python from Java]
https://medium.com/geekculture/how-to-execute-python-modules-from-java-2384041a3d6d
JEP - Used for python interpreter. Need to download JEP to a specific directory

[Call C++ from Python]
Look below for creating the dynamic library for C++.
pythontoc.py
```
import ctypes
import pathlib
import timeit

if __name__ == "__main__":
    # Load the shared library into ctypes
    # libname = pathlib.Path().absolute() / "libcmult.so"
    libname = '/Users/sahils/CLionProjects/stockproject/cmake-build-debug/libstockproject.dylib'
    c_lib = ctypes.CDLL(libname)
    x, y = 6, 2.31
    answer = c_lib.cmult(ctypes.c_int(x), ctypes.c_float(y))

    # Measure the time taken by the C++ multiplication
    cpp_time = timeit.timeit(
        lambda: c_lib.cmult(ctypes.c_int(x), ctypes.c_float(y)),
        number=1)

    # Measure the time taken by the Python multiplication
    python_time = timeit.timeit(lambda: x * y, number=100000000)

    print(f'C++ Multiplication Time: {cpp_time:.6f} seconds')
    print(f'Python Multiplication Time: {python_time:.6f} seconds')
```

[Configuring connection to database, IP Address]
```
application.properties : looks like this
spring.datasource.url=jdbc:mysql://localhost:3306/stockdata?useSSL=false
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
server.address=10.0.0.54
server.port=8080
```

[SQL Side]
Need to first create a new schema, then create a database within that schema
<img width="301" alt="image" src="https://github.com/SahilSC/stockportfoliobackend/assets/66689316/776428b2-998f-4465-8d1f-0a3a6364a972">
<img width="927" alt="image" src="https://github.com/SahilSC/stockportfoliobackend/assets/66689316/54f1795f-b1ba-49ff-9eb7-c39cd108ee24">
SQLWorkbench is the UI for modifying an existing SQL server. By default, hosted on port 3036.

[Maven dependencies]
Selected mySQL driver, mySQL, Spring Boot Web, MongoDB (maybe other things, I followed the Intellij Spring Boot tutorial)  
When trying to add dependencies in your pom.xml file, sometimes it will appear red. To download them, you need to use
```mvn clean install```
Note you need to download maven if you haven't already

[Call C++ from Python]
Create a shared library in C++ by configuring the CMakeLists.txt to
```
cmake_minimum_required(VERSION 3.25)
project(stockproject)

set(CMAKE_CXX_STANDARD 20)

add_library(stockproject SHARED matmul.cpp)
#add_executable()
```
building it should yield a .dylib (dynamic shared library). Folder structure should be like

<img width="345" alt="image" src="https://github.com/SahilSC/stockportfoliobackend/assets/66689316/7279d27a-9174-444d-b53a-7a4d7e4432ba">

matmul.cpp
```
#include "matmul.h"
#include <chrono>

using namespace std;

extern "C" {
    double cmult(int int_param, float float_param) {
        auto start = chrono::high_resolution_clock::now();
        for (int i = 0; i < 100000000; i++) {
            float return_value = int_param * float_param;
        }
        auto end_time = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end_time - start);
        return duration.count();
//        printf("    In cmult : int: %d float %.1f returning  %.1f\n", int_param,
//               float_param, return_value);
//        return return_value;
    }
}
```
matmul.h
```
#ifndef STOCKPROJECT_MAIN_H
#define STOCKPROJECT_MAIN_H


extern "C" {
    double cmult(int int_param, float float_param);
}
#endif //STOCKPROJECT_MAIN_H

```

Using extern "C" prevents C++ compiler from modifying my methods so I can call them exactly as named in Python


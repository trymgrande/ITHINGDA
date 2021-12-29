cmake_minimum_required(VERSION 3.10)
project(oving2_2)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")

add_executable(oving2_2 main.cpp)
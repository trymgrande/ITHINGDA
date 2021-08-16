cmake_minimum_required(VERSION 3.10)
project(oving1)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")

add_executable(oving1 main.cpp)
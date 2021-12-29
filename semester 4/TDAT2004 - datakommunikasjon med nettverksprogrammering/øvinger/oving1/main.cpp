//#include <iostream>
//#include <thread>
//#include <vector>
//
//using namespace std;
//
//vector<thread> threads;
//vector<vector<int>> primeVectors;
//
//void findPrimes(int index, int from, int to) {
//    vector<int> primes;
//    bool flag;
//    for (int i = from; i < to; i++) {
//        flag = false;
//        for (int j = 2; j < i; j++) {
//            if (i % j == 0) {
//                flag = true; //not prime
//            }
//        }
//        if (!flag) {
//            //number is prime
//            cout << i << endl;
//            primes.push_back(i);
//        }
//    }
//    primeVectors[index] = primes;
//}
//
//int main() {
//    int numberOfThreads = 1;
//    int numbers = 100;
//    int interval;
//
//    if (numbers % numberOfThreads == 0) {
//        interval = numbers / numberOfThreads;
//    }
//    else {
//        cout << "invalid numbers! Please enter " << endl;
//        exit(-1);
//    }
//
//
//
//    // making threads and delegating tasks
//    for (int i = 0; i < numberOfThreads; i++) {
//        int from = i*interval;
//        int to = from+interval;
//        threads.emplace_back([i, from, to] {
//            threads[i] = thread(findPrimes, i, from, to);
//        });
//    }
//
//    for (auto &thread : threads) {
//        cout << thread.get_id() << endl;
//        thread.join();
//    }
//
//
////    thread t1([&threadVector] {
////        threadVector.push_back(findPrimes(2,40));
////        }
////    );
////
////    thread t2([&threadVector] {
////        threadVector.push_back(findPrimes(40,100));
////        }
////    );
////
////    t1.join();
////    t2.join();
//
////     loops threads
//    for (int i = 0; i < primeVectors.size(); i++) {
//        // loops ints in each threads
//        for (int j = 0; j < primeVectors.at(i).size(); j++) {
//            cout << primeVectors.at(i).at(j) << ' ';
//        }
//    }
//}




// V2.0




#include <iostream>
#include <thread>
#include <vector>

using namespace std;

// To enable threading runtime checks, change the set()-line in CMakeLists.txt to:
// set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fsanitize=thread -pthread -std=c++1y -Wall -Wextra")
vector<int> primes;
bool prime;
int isPrime(int n) {
    bool prime = true;
    for(int i = 2; i <= n/2; ++i) {
        if (n%i == 0) {
            prime = false;
            break;
        }
    }
    return prime;
}

int main() {
    int from = 0;
    int to = 100;
    int threadsNumber = 101;
    vector<thread> threads;

    for (int i = 1; i <= threadsNumber; i++) {
        threads.emplace_back([i, from, to, threadsNumber] { // i is copied to the thread, do not capture i as reference (&r) as might be freed before thread finishes.
            for (int j = (from+1) + (i * to / threadsNumber) - to / threadsNumber; j < (from + 1) + (i * to / threadsNumber); ++j) {
                if (isPrime(j)) {
                    primes.push_back(j);
                }
            }
        });
    }

    for (auto &thread : threads)
        thread.join();

    for (int i = 0;i < primes.size(); i++){
        cout << primes[i] << endl;
    }
    return 0;
}
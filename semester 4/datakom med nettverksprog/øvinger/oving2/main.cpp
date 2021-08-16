#include <functional>
#include <iostream>
#include <list>
# include <condition_variable>
#include <thread>
#include <vector>
#include <chrono>

using namespace std;

class Workers {
private:
    int number_of_threads;
    list<function<void()>> tasks;
    mutex tasks_mutex;
    vector<thread> worker_threads;

    bool tasks_available = true;
    mutex wait_mutex;
    condition_variable cv;

public:
    explicit Workers(int num_of_threads) {
        number_of_threads = num_of_threads;
    }

    int getNum() {
        return number_of_threads;
    }

    void start() {
        for (int i = 0; i < number_of_threads; i++) {
            worker_threads.emplace_back([this] {
                while(true) {
                    function<void()> task;
                    {
                        unique_lock<mutex> lock(this -> tasks_mutex);
                        this->cv.wait(lock, [this]{ return this->tasks_available; });

                        // pops next task if there is one
                        if (!this->tasks.empty()) {
                            // Get and remove first task in list
                            task = *(this->tasks.begin());
                            this->tasks.pop_front();
                        }

                        // task list is empty
                        else {
                            lock_guard<mutex> waitLock(this -> wait_mutex);
                            tasks_available = false;
                        }
                        if(tasks.empty() && !tasks_available){
                            return;
                        }
                    }

                    // Run task if defined
                    if (task) task();
                }
            });
        }

        // wakes threads witing for cv
        cv.notify_all();
    }

    // Add task to list
    void post(function<void()> task) {
        //prepares for adding task
        lock_guard<mutex> waitLock(this -> wait_mutex);
        this->tasks_available = true;

        //locks tasks list, then adds task
        lock_guard<mutex> lock(tasks_mutex);
        tasks.emplace_back(task);

        // wakes threads witing for cv
        this->cv.notify_all();
    }

    void post_timeout(function<void()> task, int delay) {
        thread t1([this, &task, &delay] {
            cout << "Executing delayed task in: " << delay << " ms" << endl;
            this_thread::sleep_for(chrono::milliseconds(delay));
            post(task);
        });
        t1.join();
    }

    void stop() {
        for (auto &thread : worker_threads)
            thread.join();
        worker_threads.clear();
    }
};

int main() {
    Workers worker_threads(4);
    Workers event_threads(1);


    // Create worker threads
    worker_threads.start();
    event_threads.start();

    worker_threads.post([]{ cout << "Task A running..." << endl;});
    event_threads.post([]{ cout << "EVTask A running..." << endl;});
    event_threads.post([]{ cout << "EVTask B running..." << endl;});
    worker_threads.post([]{ cout << "Task B running..." << endl; });
    worker_threads.post_timeout([](){ cout << "Delayed task (C) running..." << endl; }, 4000);
    worker_threads.stop();
    event_threads.stop();
}
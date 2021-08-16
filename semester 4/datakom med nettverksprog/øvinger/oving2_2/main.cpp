#include <functional>
#include <iostream>
#include <list>
# include <condition_variable>
#include <thread>
#include <vector>
#include <chrono>

using namespace std;

class Worker {
private:
    int amount_of_threads;
    list<function<void()>> tasks;
    mutex tasks_mutex;
    vector<thread> worker_threads;

    bool hasTasks = true;
    mutex wait_mutex;
    condition_variable cv;

public:
    explicit Worker(int num_of_threads) {
        amount_of_threads = num_of_threads;
    }

    int getNum() {
        return amount_of_threads;
    }

    void start() {
        for (int i = 0; i < amount_of_threads; i++) {
            worker_threads.emplace_back([this] {
                while(this->tasks.empty()) {
                    // Temp variable
                    function<void()> task;
                    {
                        unique_lock<mutex> lock(this -> tasks_mutex);
                        this->cv.wait(lock, [this]{ return this->hasTasks; });

                        if (!this->tasks.empty()) {
                            // Get and remove first task in list
                            task = *(this->tasks.begin());
                            this->tasks.pop_front();
                        }

                        // Check if there are not tasks left
                        if (this->tasks.empty()) {
                            lock_guard<mutex> waitLock(this -> wait_mutex);
                            hasTasks = false;
                        }
                    }

                    // Run task if defined
                    if (task) task();
                }
            });
        }

        cv.notify_all(); // Awake waiting cv
    }

    // Add task to list
    void post(function<void()> task) {
        lock_guard<mutex> waitLock(this -> wait_mutex);
        this->hasTasks = true;

        lock_guard<mutex> lock(tasks_mutex);
        tasks.emplace_back(task);

        this->cv.notify_all();
    }

    void post_timeout(function<void()> task, int delay) {
        thread t1([this, &task, &delay] {
            cout << "Please wait for " << delay << " ms" <<  endl;
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
    Worker worker_threads(4);


    // Create 4 internal threads
    worker_threads.start();
    worker_threads.post([]{ cout << "Task A" << endl;});
    worker_threads.post([]{ cout << "Task B" << endl; });
    worker_threads.post_timeout([](){ cout << "Task C" << endl; }, 4000);

    worker_threads.stop();
}

# include <functional>
# include <iostream>
# include <list>
# include <mutex>
# include <thread>
# include <vector>
# include <condition_variable>

using namespace std;

// simple worker threads implementation
class Workers {

    // access specifier
private:

    // data members
    list<function<void()>> tasks;
    mutex tasks_mutex; // tasks mutex needed
    int numberOfThreads;

    bool wait = true;
    mutex wait_mutex;
    condition_variable cv;

public:
    // constructor
    explicit Workers(int numberOfThreads) {
        this->numberOfThreads = numberOfThreads;
    }

    // member functions

    // skal være trådsikker (kunne brukes problemfritt i flere tråder samtidig
    void post_tasks() {
        for (int i = 0; i < 10; i++) {
            lock_guard<mutex> lock(tasks_mutex);
            tasks.emplace_back([i] {
                cout << "task " << i
                     << " runs in thread "
                     << this_thread::get_id()
                     << endl;
            });
        }
    }

    void run_tasks_in_worker_threads() {
        vector<thread> worker_threads;
        for (int i = 0; i < 4; i++) {
            worker_threads.emplace_back([] {
                while (true) {
                    function<void()> task;
                    {
                        lock_guard<mutex> lock(tasks_mutex);

//                        cv

                        thread t([&wait, &wait_mutex, &cv] {
                            unique_lock<mutex> lock(wait_mutex);
                            while (wait)
                                cv.wait(lock); // Unlock wait_mutex and wait.
                            // When awaken, wait_mutex is locked.
                            cout << "thread: finished waiting" << endl;
                        });
                        this_thread::sleep_for(1s);
                        {
                            unique_lock<mutex> lock(wait_mutex);
                            wait = false;
                        }
                        cv.notify_one(); // Awake waiting cv
                        t.join();




                        if (!tasks.empty()) {
                            task = *tasks.begin(); // Copy task for later use
                            tasks.pop_front(); // Remove task from list
                        }
                    }
                    if (task)
                        task(); // Run task outside of mutex lock
                }
            });
        }
    }

    void stop() {
        for (auto &thread : workers) {
            thread.join();
        }
    }

    void post_timeout() {

    }

};

int main() {
    Workers workers;

    workers.post_tasks();
    workers.run_tasks_in_worker_threads();
    workers.stop()

}
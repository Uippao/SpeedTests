// count.cpp
#include <iostream>
#include <chrono>

int main() {
    auto start = std::chrono::steady_clock::now();

    for (int i = 1; i <= 10000; ++i) {
        std::cout << i << '\n';
    }

    auto end = std::chrono::steady_clock::now();
    std::chrono::duration<double> elapsed = end - start;
    std::cout << "Elapsed: " << elapsed.count() << " seconds\n";
    return 0;
}

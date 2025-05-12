import time

start = time.time()
for i in range(1, 10001):
    print(i)
elapsed = time.time() - start
print(f"Elapsed: {elapsed:.9f} seconds")

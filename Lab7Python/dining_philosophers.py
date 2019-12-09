import threading
import time

class Waiter(object):

    def __init__(self, max):
        self.value = max
        self.lock = threading.Condition(threading.Lock())

    def v(self):
        with self.lock:
            self.value += 1
            self.lock.notify()

    def p(self):
        with self.lock:
            while self.value == 0:
                self.lock.wait()
            self.value -= 1

class Fork(object):

    def __init__(self, id):
        self.id = id
        self.lock = threading.Condition(threading.Lock())
        self.taken = False
        self.philosopher = -1

    def vf(self, philosopher):
        with self.lock:
            while self.taken == True:
                self.lock.wait()
            self.philosopher = philosopher
            self.taken = True
            print(f'philosopher number {philosopher} took fork number {self.id}\n')
            self.lock.notifyAll()

    def pf(self, philosopher):
        with self.lock:
            while self.taken == False:
                self.lock.wait()
            self.philosopher = -1
            self.taken = False
            print(f'philosopher number {philosopher} dropped fork number {self.id}\n')
            self.lock.notifyAll()

class Philosopher(threading.Thread):

    def __init__(self, id, left, right, waiter):
        threading.Thread.__init__(self)
        self.id = id
        self.left = left
        self.right = right
        self.waiter = waiter

    def run(self):
        while True:
            print(f'Philosopher {self.id} is thinking')
            self.waiter.p()
            time.sleep(1)
            self.left.vf(self.id)
            time.sleep(1)
            self.right.vf(self.id)
            print(f'Philosopher {self.id} is eating')
            time.sleep(2)
            self.right.pf(self.id)
            self.left.pf(self.id)
            self.waiter.v()

def main():
    n = 5

    waiter = Waiter(n-1)

    forks = [Fork(i) for i in range(n)]

    philosophers = [Philosopher(i, forks[i], forks[(i+1)%5], waiter) for i in range(n)]

    for i in range(n):
        philosophers[i].start()

if __name__ == '__main__':
    main()

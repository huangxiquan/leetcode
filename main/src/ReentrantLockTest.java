import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) {
       new Thread(() -> test(),"线程1").start();
       new Thread(() -> test(),"线程2").start();
//       new Thread(() -> test(),"线程3").start();
//       new Thread(() -> test(),"线程4").start();
//       new Thread(() -> test(),"线程5").start();
//       new Thread(() -> test(),"线程6").start();
//       new Thread(() -> test(),"线程7").start();
//       new Thread(() -> test(),"线程8").start();
//       new Thread(() -> test(),"线程9").start();
//       new Thread(() -> test(),"线程10").start();

    }

    public static void test() {
        for(int i = 0 ; i < 5 ; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取锁" + i);
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception exception) {

            }finally {
                if(lock.isLocked()) {
                   lock.unlock();
                }
            }
        }

    }
}

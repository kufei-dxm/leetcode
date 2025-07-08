package general;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * a program that will produce deadlock
 *
 * @Author dxm
 * @Date 2022/9/20
 */
public class DeadLockTest {
    public static boolean flagB = false;
    public static boolean flagA = false;

    public static String A = "a";
    public static String B = "b";
    public static Lock lockA = new ReentrantLock();
    public static Lock lockB = new ReentrantLock();

    public static void main(String[] args) {
        //
//        new Thread(() -> new FakeDeadLockB().execute(), "thread-1").start();
//        new Thread(() -> new FakeDeadLockA().execute(), "thread-2").start();

        System.out.println("+===== true deadlock =====");

        new Thread(()->new TrueDeadLockA().exec(),"thread-3").start();
        new Thread(()->new TrueDeadLockB().exec(),"thread-4").start();
    }

    public static class TrueDeadLockA {
        public void exec() {
            lockA.lock();
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("now try to lock B ");
            lockB.lock();
        }
    }

    public static class TrueDeadLockB {
        public void exec() {
            lockB.lock();
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("now try to lock A ");
            lockA.lock();
        }
    }

    public static class FakeDeadLockB {
        private Lock lock = new ReentrantLock();

        public void execute() {
            lock.lock();
            System.out.println("start B");
            while (!flagB) {
            }
            System.out.println("here of B ");
            flagB = true;
            lock.unlock();
        }
    }

    public static class FakeDeadLockA {
        private Lock lock = new ReentrantLock();

        public void execute() {
            lock.lock();
            System.out.println("start A");
            while (!flagA) {
            }
            System.out.println("here of A ");
            flagA = true;
            lock.unlock();
        }
    }
}

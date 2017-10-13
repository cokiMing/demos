package test;

import java.util.concurrent.DelayQueue;
/**
 * Created by wuyiming on 2017/10/12.
 */
public class NetBar extends Thread{

    private DelayQueue<User> delayQueue = new DelayQueue<>();

    private volatile boolean isOperating = true;

    public NetBar() {

    }

    public void userLogin(String name,String id,int amount) {
        User user = new User(name,id,1000 * amount + System.currentTimeMillis());
        System.out.println(name + " login, paid " + amount);
        delayQueue.add(user);
    }

    public void userLogout() throws InterruptedException {
        User user = delayQueue.take();
        System.out.println(user.getName() + " logout...");
    }

    public void stopBar() {
        isOperating = false;
    }

    public int onlineNum() {
        return delayQueue.size();
    }

    @Override
    public void run() {
        try{
            while (isOperating) {
                userLogout();
            }
            System.out.println("net bar stopped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

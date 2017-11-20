package ru.enovikow.otus;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * -Xms20m
 * -Xmx20m
 * -XX:-UseParNewGC
 * -XX:+UseConcMarkSweepGC
 */

public class Main {


    public static void main(String[] args) throws InterruptedException {
        GCMonitor monitor = new GCMonitor();
        monitor.getGC();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(
                () -> getNotifications(monitor),
                1,
                1,
                TimeUnit.MINUTES
        );

        FillList list = new FillList();
    }

    static void getNotifications(GCMonitor monitor) {
        System.out.println("Checking GC activity");
        List<GCInfo> list = monitor.getMyNotificationListener().getNotifications();// notifications.getNotifications();
        for (GCInfo info : list) {
            System.out.println(info);
        }
    }
}

package ru.enovikow.otus;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * -Xms256m
 * -Xmx256m
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

        new FillList();
    }

    private static void getNotifications(GCMonitor monitor) {
        List<GCInfo> list = monitor.getMyNotificationListener().getNotifications();
        list.forEach(System.out::println);

        System.out.println("Major total cleanUp " + GCInfo.getMajorTotalCleanUp() + " Major total duration " + GCInfo.getMajorTotalDuration());
        System.out.println("Minor total cleanUp " + GCInfo.getMinorTotalCleanUp() + " Minor total duration " + GCInfo.getMinorTotalDuration());

//        list.stream().filter(art -> list.forEach())
    }
}

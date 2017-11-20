package ru.enovikow.otus;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCMonitor {
    private Notification myNotificationListener = new Notification();


    public void getGC() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean mxBean : garbageCollectorMXBeanList) {
            System.out.println(mxBean.getName());

            NotificationEmitter emitter = (NotificationEmitter) mxBean;

            emitter.addNotificationListener(myNotificationListener, null, null);
        }
    }

    Notification getMyNotificationListener() {
        return myNotificationListener;
    }
}

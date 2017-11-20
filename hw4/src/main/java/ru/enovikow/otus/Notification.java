package ru.enovikow.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.util.ArrayList;
import java.util.List;

public class Notification implements NotificationListener {

    List<GCInfo> notifications = new ArrayList<>();

    @Override
    public void handleNotification(javax.management.Notification notification, Object handback) {

        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

        notifications.add(new GCInfo(info.getGcName(), info.getGcAction(), info.getGcInfo().getStartTime(), info.getGcInfo().getEndTime(), info.getGcInfo().getDuration()));

    }

    List<GCInfo> getNotifications() {
        return notifications;
    }
}

package ru.enovikow.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.util.ArrayList;
import java.util.List;

public class Notification implements NotificationListener {

    private List<GCInfo> notifications = new ArrayList<>();

    @Override
    public void handleNotification(javax.management.Notification notification, Object handback) {

        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

        GCType gcType = info.getGcAction().equals("end of minor GC") ? GCType.MINOR_GC : GCType.MAJOR_GC;

        notifications.add(new GCInfo(gcType, info.getGcName(), info.getGcInfo().getStartTime(), info.getGcInfo().getEndTime(), info.getGcInfo().getDuration()));

    }

    List<GCInfo> getNotifications() {
        return notifications;
    }
}
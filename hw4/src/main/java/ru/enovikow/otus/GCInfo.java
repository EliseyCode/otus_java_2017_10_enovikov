package ru.enovikow.otus;

public class GCInfo {
    private String gcName;
    private long gcStartTime;
    private long gcEndTime;
    private long gcDuration;
    private GCType gcType;
    private static int minorTotalCleanUp;
    private static int majorTotalCleanUp;
    private static int minorTotalDuration;
    private static int majorTotalDuration;

    public GCInfo(GCType type, String gcName, long gcStartTime, long gcEndTime, long gcDuration) {
        this.gcType = type;
        this.gcName = gcName;
        this.gcStartTime = gcStartTime;
        this.gcEndTime = gcEndTime;
        this.gcDuration = gcDuration;

        countTotalCollector(type, gcDuration);
    }

    public static void countTotalCollector(GCType type, long gcDuration) {
        switch (type) {
            case MAJOR_GC: {
                majorTotalCleanUp++;
                majorTotalDuration += gcDuration;
                break;
            }
            case MINOR_GC: {
                minorTotalCleanUp++;
                minorTotalDuration += gcDuration;
                break;
            }
        }
    }

    public static int getMinorTotalCleanUp() {
        return minorTotalCleanUp;
    }

    public static int getMajorTotalCleanUp() {
        return majorTotalCleanUp;
    }

    public static int getMinorTotalDuration() {
        return minorTotalDuration;
    }

    public static int getMajorTotalDuration() {
        return majorTotalDuration;
    }

    @Override
    public String toString() {
        return "GCInfo{" +
                "gcName='" + gcName + '\'' +
                ", gcStartTime=" + gcStartTime +
                ", gcEndTime=" + gcEndTime +
                ", gcDuration=" + gcDuration +
                '}';
    }
}
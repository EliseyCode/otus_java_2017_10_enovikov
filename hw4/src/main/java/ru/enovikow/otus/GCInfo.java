package ru.enovikow.otus;

public class GCInfo {
    private String gcName;
    private String gcAction;
    private long gcStartTime;
    private long gcEndTime;
    private long gcDuration;
    private static int minorTotalCleanUp;
    private static int majorTotalCleanUp;

    public GCInfo(String gcName, String gcAction, long gcStartTime, long gcEndTime, long gcDuration) {
        this.gcName = gcName;
        this.gcAction = gcAction;
        this.gcStartTime = gcStartTime;
        this.gcEndTime = gcEndTime;
        this.gcDuration = gcDuration;
        if(gcAction.equals("end of minor GC")) {
            minorTotalCleanUp++;
        } else {
            majorTotalCleanUp++;
        }
    }

    @Override
    public String toString() {
        return "GCInfo{" +
                "gcName='" + gcName + '\'' +
                ", gcAction='" + gcAction + '\'' +
                ", gcStartTime=" + gcStartTime +
                ", gcEndTime=" + gcEndTime +
                ", gcDuration=" + gcDuration +
                ", minorTotalCleanUp=" + minorTotalCleanUp +
                ", majorTotalCleanUp=" + majorTotalCleanUp +
                '}';
    }
}

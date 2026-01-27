package com.worksplace.MiniPro.Tb.Tbw.AuditLogDownloader;

public class AuditCsvRow {

    long createdTime;
    String entityName;
    String entityType;
    String userName;
    String actionType;
    String actionStatus;

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    @Override
    public String toString() {
        return "AuditCsvRow{" +
                "createdTime=" + createdTime +
                ", entityName='" + entityName + '\'' +
                ", entityType='" + entityType + '\'' +
                ", userName='" + userName + '\'' +
                ", actionType='" + actionType + '\'' +
                ", actionStatus='" + actionStatus + '\'' +
                '}';
    }
}

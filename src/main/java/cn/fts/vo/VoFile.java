package cn.fts.vo;

import cn.fts.po.File;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

public class VoFile extends File {

    private Integer day;

    private Integer hour;

    private Integer minute;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTime;

    private String action;

    private CommonsMultipartFile srcFile;

    private java.io.File jFile;

    private String currentFileKind;

    private long remaining;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public CommonsMultipartFile getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(CommonsMultipartFile srcFile) {
        this.srcFile = srcFile;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public java.io.File getjFile() {
        return jFile;
    }

    public void setjFile(java.io.File jFile) {
        this.jFile = jFile;
    }

    public String getCurrentFileKind() {
        return currentFileKind;
    }

    public void setCurrentFileKind(String currentFileKind) {
        this.currentFileKind = currentFileKind;
    }

    public long getRemaining() {
        return remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "VoFile{" +
                "day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", expirationTime=" + expirationTime +
                ", action='" + action + '\'' +
                ", srcFile=" + srcFile +
                ", jFile=" + jFile +
                ", currentFileKind='" + currentFileKind + '\'' +
                ", remaining=" + remaining +
                '}';
    }
}

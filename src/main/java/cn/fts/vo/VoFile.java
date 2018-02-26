package cn.fts.vo;

import cn.fts.po.File;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VoFile extends File {
    private Integer day;

    private Integer hour;

    private Integer minute;

    private CommonsMultipartFile srcFile;

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


}

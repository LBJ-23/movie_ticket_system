package com.yyc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Movie {
    private Integer id;
    private String movieName;
    private String duration;
    private String type;
    private Date releasedTime;
    private String img;
    private String actor;
    private String director;
    private String source;
    private String introduce;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getReleasedTime() {
        return releasedTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setReleasedTime(Date releasedTime) {
        this.releasedTime = releasedTime;
    }
}

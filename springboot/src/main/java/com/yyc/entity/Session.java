package com.yyc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Session {
    private Integer id;
    private Integer movieId;
    private Integer cinemaId;
    private String hall;
    private Date start;
    private Date end;
    private String type;
    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStart() {
        return start;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setStart(Date start) {
        this.start = start;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEnd() {
        return end;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setEnd(Date end) {
        this.end = end;
    }
}

package com.andreibarroso.springionic.exceptions;

import lombok.Data;

import java.io.Serializable;

@Data
public class StandardError implements Serializable {

    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}

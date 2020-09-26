package com.wcq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Email implements Serializable{

    private String subject;
    private String from;
    private String to;
    private String content;
}

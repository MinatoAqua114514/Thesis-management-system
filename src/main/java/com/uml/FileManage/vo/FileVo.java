package com.uml.FileManage.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FileVo {

    private String userName;

    private String fileType;

    private Date createdAt;

    private Date updatedAt;
}

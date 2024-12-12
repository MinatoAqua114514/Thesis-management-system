package com.uml.FileManage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class File {

    private Integer fileId;

    private Integer userId;

    private String fileName;

    private String fileType;

    private String fileUrl;

    private Date createdAt;

    private Date updatedAt;

    private Integer deleted;
}

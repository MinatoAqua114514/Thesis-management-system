package com.uml.FileManage.dao;

import org.apache.ibatis.annotations.Mapper;
import com.uml.FileManage.entity.File;

@Mapper
public interface FileMapper {

    // 插入文件信息
    void insertFile(int userId, String fileType, String fileName, String filePath);

    // 删除文件
    void deleteFile(int fileId);

    // 根据文件路径获取文件信息
    File selectFileByFileUrl(String fileUrl);

    // 根据文件ID获取文件信息
    File selectFileByFileId(int fileId);

    // 根据文件名检查文件是否存在
    int existsFileByFileName(String fileName);

    // 更新上传文件信息
    void updateFile(int oldFileId, String newFileName, String newFilePath);
}
package com.uml.FileManage.service;

import com.uml.FileManage.dao.FileMapper;
import com.uml.FileManage.dao.FileVoMapper;
import com.uml.FileManage.entity.File;
import com.uml.FileManage.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileVoMapper fileVoMapper;
    @Autowired
    private FileMapper fileMapper;

    // 获取文件列表
    public List<FileVo> getFileList() {
        return fileVoMapper.getFileList();
    }

    // 保存文件信息
    public void saveFile(int userId, String fileType, String fileName, String filePath) {
        fileMapper.insertFile(userId, fileType, fileName, filePath);
    }

    // 删除文件
    public void deleteFile(int fileId) {
        fileMapper.deleteFile(fileId);
    }

    // 根据文件路径获取文件信息
    public File getFileByPath(String filePath) {
        return fileMapper.selectFileByFileUrl(filePath);
    }

    // 根据文件ID获取文件信息
    public File getFileById(int fileId) {
        return fileMapper.selectFileByFileId(fileId);
    }

    // 检查文件是否存在
    public int fileExists(String fileName) {
        return fileMapper.existsFileByFileName(fileName);
    }

    // 重传文件
    public void reloadFile(int oldFileId, String newFileName, String newFilePath) {
        fileMapper.updateFile(oldFileId, newFileName, newFilePath);
    }
}

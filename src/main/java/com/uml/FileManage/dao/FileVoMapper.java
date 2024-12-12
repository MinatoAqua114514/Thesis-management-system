package com.uml.FileManage.dao;

import com.uml.FileManage.vo.FileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileVoMapper {

    // 获取文件列表
    List<FileVo> getFileList();
}

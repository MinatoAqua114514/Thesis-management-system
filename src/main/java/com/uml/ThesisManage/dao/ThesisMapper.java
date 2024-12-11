package com.uml.ThesisManage.dao;

import com.uml.ThesisManage.entity.Thesis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ThesisMapper {

    // 获取论文列表
    List<Thesis> getThesisList();

    // 通过ID查找获取论文
    Thesis getThesisById(@Param("thesisId") int id);

    // 提交论文
    int submitThesis(Thesis thesis);

    // 修改论文
    int updateThesis(Thesis thesis);

    // 删除论文
    int deleteThesis(@Param("thesisId") int id);
}

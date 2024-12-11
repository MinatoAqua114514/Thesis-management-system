package com.uml.ThesisManage.dao;

import com.uml.ThesisManage.entity.Interim;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterimMapper {

    // 获取中期报告列表
    List<Interim> getInterimList();

    // 通过ID获取中期报告
    Interim getInterimById(@Param("interimReportId") int id);

    // 提交中期报告
    int submitInterim(Interim interim);

    // 修改中期报告
    int updateInterim(Interim interim);

    // 删除中期报告
    int deleteInterim(@Param("interimReportId") int id);

}

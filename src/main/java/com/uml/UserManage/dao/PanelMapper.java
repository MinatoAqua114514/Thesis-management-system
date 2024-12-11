package com.uml.UserManage.dao;

import com.uml.UserManage.entity.DefensePanelMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PanelMapper {

    // 为评阅小组添加成员
    void insertPanelMember(DefensePanelMember defensePanelMember);

    // 获取评阅小组的所有成员信息
    boolean getPanelMembers(@Param("defensePanelId") Integer defensePanelId);

    // 获取评语小组指定成员信息
    boolean getPanelMember(@Param("panelMemberId") Integer panelMemberId);

    // 删除评阅小组成员
    boolean deletePanelMember(@Param("panelMemberId") Integer panelMemberId);

    // 更细小组成员信息
    boolean updatePanelMember(@Param("panelMemberId") Integer panelMemberId,DefensePanelMember defensePanelMember);
}

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

    // 添加评阅小组
    void addDefensePanel(DefensePanelMember defensePanelMember);

    // 获取指定评阅小组信息
    DefensePanelMember getDefensePanelById(Integer defensePanelId);

    // 获取所有评阅小组信息
    List<DefensePanelMember> getAllDefensePanels();

    // 删除指定评阅小组
    void deleteDefensePanel(Integer defensePanelId);

    // 更新评阅小组信息
    void updateDefensePanel(Integer defensePanelId, DefensePanelMember defensePanelMember);
}

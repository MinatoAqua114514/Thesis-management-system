package com.uml.UserManage.dao;

import com.uml.UserManage.entity.DefensePanelMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DefensePanelMapper {

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

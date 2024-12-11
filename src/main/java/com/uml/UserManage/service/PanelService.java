package com.uml.UserManage.service;

import com.uml.UserManage.dao.DefensePanelMapper;
import com.uml.UserManage.entity.DefensePanelMember;
import org.springframework.beans.factory.annotation.Autowired;
import com.uml.UserManage.dao.PanelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanelService {

    @Autowired
    private PanelMapper panelMapper;

    @Autowired
    private DefensePanelMapper defensePanelMapper;

    // 为评阅小组添加成员
    public Optional<DefensePanelMember> insertPanelMember(DefensePanelMember defensePanelMember) {
        panelMapper.insertPanelMember(defensePanelMember);
        return Optional.of(defensePanelMember);
    }

    // 获取评阅小组的所有成员信息
    public boolean getPanelMembers(Integer defensePanelId) {
        return panelMapper.getPanelMembers(defensePanelId);
    }

    // 获取评语小组指定成员信息
    public boolean getPanelMember(Integer panelMemberId) {
        return panelMapper.getPanelMember(panelMemberId);
    }

    // 删除评阅小组成员
    public boolean deletePanelMember(Integer panelMemberId) {
        return panelMapper.deletePanelMember(panelMemberId);
    }

    // 更细小组成员信息
    public boolean updatePanelMember(Integer panelMemberId, DefensePanelMember defensePanelMember) {
        return panelMapper.updatePanelMember(panelMemberId, defensePanelMember);
    }

    // 添加评阅小组
    public Optional<DefensePanelMember> addDefensePanel(DefensePanelMember defensePanelMember) {
        defensePanelMapper.addDefensePanel(defensePanelMember);
        return Optional.of(defensePanelMember);
    }

    // 获取指定评阅小组信息
    public Optional<DefensePanelMember> getDefensePanelById(Integer defensePanelId) {
        return Optional.of(defensePanelMapper.getDefensePanelById(defensePanelId));
    }

    // 获取所有评阅小组信息
    public Optional<DefensePanelMember> getAllDefensePanels() {
        return Optional.of((DefensePanelMember) defensePanelMapper.getAllDefensePanels());
    }

    // 删除指定评阅小组
    public boolean deleteDefensePanel(Integer defensePanelId) {
        defensePanelMapper.deleteDefensePanel(defensePanelId);
        return true;
    }

}

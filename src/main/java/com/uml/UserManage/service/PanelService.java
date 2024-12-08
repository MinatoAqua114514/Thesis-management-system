package com.uml.UserManage.service;

import com.uml.UserManage.entity.DefensePanelMember;
import org.springframework.beans.factory.annotation.Autowired;
import com.uml.UserManage.dao.PanelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanelService {

    @Autowired
    private PanelMapper panelMapper;

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

}

package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.ThesisMapper;
import com.uml.ThesisManage.entity.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThesisService {

    @Autowired
    private ThesisMapper thesisMapper;

    // 获取论文列表
    public List<Thesis> getThesisList() {
        return thesisMapper.getThesisList();
    }

    // 通过ID查找获取论文
    public Optional<Thesis> getThesisById(int id) {
        Thesis thesis = thesisMapper.getThesisById(id);
        if (thesis == null || thesis.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(thesis);
    }

    // 提交论文
    public Optional<Thesis> submitThesis(Thesis thesis) {
        thesisMapper.submitThesis(thesis);
        return Optional.of(thesis);
    }

    // 修改论文
    public Optional<Thesis> updateThesis(Thesis thesis) {
        Thesis existingThesis = thesisMapper.getThesisById(thesis.getThesisId());
        if (existingThesis == null || existingThesis.getDeleted() == 1) {
            return Optional.empty();
        }
        thesisMapper.updateThesis(thesis);
        return Optional.of(thesis);
    }

    // 删除论文
    public boolean deleteThesis(int id) {
        Thesis thesis = thesisMapper.getThesisById(id);
        if (thesis == null || thesis.getDeleted() == 1) {
            return false;
        }
        thesisMapper.deleteThesis(id);
        return true;
    }
}

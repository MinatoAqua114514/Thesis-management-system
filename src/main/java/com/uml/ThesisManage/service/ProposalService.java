package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.ProposalMapper;
import com.uml.ThesisManage.entity.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    @Autowired
    private ProposalMapper proposalMapper;

    // 获取开题报告列表
    public List<Proposal> getProposalList() {
        return proposalMapper.getProposalList();
    }

    // 通过ID查找获取开题报告
    public Optional<Proposal> getProposalById(int id) {
        Proposal proposal = proposalMapper.getProposalById(id);
        if (proposal == null || proposal.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(proposal);
    }

    // 提交开题报告
    public Optional<Proposal> submitProposal(Proposal proposal) {
        proposalMapper.submitProposal(proposal);
        return Optional.of(proposal);
    }

    // 修改开题报告
    public Optional<Proposal> updateProposal(Proposal proposal) {
        Proposal existingProposal = proposalMapper.getProposalById(proposal.getProposalId());
        if (existingProposal == null || existingProposal.getDeleted() == 1) {
            return Optional.empty();
        }
        proposalMapper.updateProposal(proposal);
        return Optional.of(proposal);
    }

    // 删除开题报告
    public boolean deleteProposal(int id) {
        Proposal proposal = proposalMapper.getProposalById(id);
        if (proposal == null || proposal.getDeleted() == 1) {
            return false;
        }
        proposalMapper.deleteProposal(id);
        return true;
    }
}

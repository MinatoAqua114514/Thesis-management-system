package com.uml.ThesisManage.dao;

import com.uml.ThesisManage.entity.Proposal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProposalMapper {

    // 获取开题报告列表
    List<Proposal> getProposalList();

    // 通过ID查找获取开题报告
    Proposal getProposalById(@Param("proposalId") int id);

    // 提交开题报告
    int submitProposal(Proposal proposal);

    // 修改开题报告
    int updateProposal(Proposal proposal);

    // 删除开题报告
    int deleteProposal(@Param("proposalId") int id);

}

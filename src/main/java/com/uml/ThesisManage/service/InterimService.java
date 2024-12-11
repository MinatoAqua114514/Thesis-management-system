package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.InterimMapper;
import com.uml.ThesisManage.entity.Interim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterimService {

    @Autowired
    private InterimMapper interimMapper;

    public List<Interim> getInterimList() {
        return interimMapper.getInterimList();
    }

    public Optional<Interim> getInterimById(int id) {
        Interim interim = interimMapper.getInterimById(id);
        if (interim == null || interim.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(interim);
    }

    public Optional<Interim> submitInterim(Interim interim) {
        interimMapper.submitInterim(interim);
        return Optional.of(interim);
    }

    public Optional<Interim> updateInterim(Interim interim) {
        Interim existingInterim = interimMapper.getInterimById(interim.getInterimReportId());
        if (existingInterim == null || existingInterim.getDeleted() == 1) {
            return Optional.empty();
        }
        interimMapper.updateInterim(interim);
        return Optional.of(interim);
    }

    public boolean deleteInterim(int id) {
        Interim interim = interimMapper.getInterimById(id);
        if (interim == null || interim.getDeleted() == 1) {
            return false;
        }
        interimMapper.deleteInterim(id);
        return true;
    }
}
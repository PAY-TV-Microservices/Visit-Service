package br.ada.visitService.service;

import br.ada.visitService.model.Visit;
import br.ada.visitService.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;

    public Visit findVisitById(Long visitId){
        return visitRepository.findVisitById(visitId);
    }
}

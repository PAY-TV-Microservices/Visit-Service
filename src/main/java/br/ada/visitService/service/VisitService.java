package br.ada.visitService.service;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.model.Visit;
import br.ada.visitService.repository.VisitRepository;
import br.ada.visitService.utils.VisitConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;

    public Visit findVisitById(Long visitId){
        return visitRepository.findVisitById(visitId);
    }

    public VisitResponse saveNewVisit(VisitRequest visitRequest){
        Visit visit = VisitConvert.toEntity(visitRequest);
        visit.setVisitId(UUID.randomUUID().toString());
        visit.setActive(true);

        return VisitConvert.toResponse(visitRepository.save(visit));
    }
    
    public List<VisitResponse> getAllVisits(){
    	List<Visit> visits = visitRepository.findAll();
    	return VisitConvert.toResponseList(visits);
    }

    public void execute(List<VisitRequest> visitRequestList){
        for (VisitRequest visitRequest : visitRequestList) {
            execute(visitRequest);
        }
    }

    public void execute(VisitRequest req){
        //TODO se não for usuário novo, validar se está inadimplente

        Visit visit = VisitConvert.toEntity(req);
        visit.setVisitId(UUID.randomUUID().toString());
        visit.setVisitDate(LocalDate.now().plusDays(10));
        visit.setActive(true);
        visitRepository.save(visit);

    }
}

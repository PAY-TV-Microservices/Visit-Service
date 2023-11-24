package br.ada.visitService.service;

import br.ada.visitService.controller.dto.TechnicianRequest;
import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.model.Technician;
import br.ada.visitService.model.Visit;
import br.ada.visitService.repository.TechnicianRepository;
import br.ada.visitService.repository.VisitRepository;
import br.ada.visitService.utils.TechnicianConvert;
import br.ada.visitService.utils.VisitConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;
    
    @Autowired
    TechnicianRepository technicianRepository;

    public Visit getVisitById(String visitId){
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
    
    public void deleteVisit(String visitId) {
		Visit visit = visitRepository.findVisitById(visitId);
		visit.setActive(false);
		visitRepository.save(visit);
	}
    
    public VisitResponse assingVisit(String visitId, TechnicianRequest technicianRequest) {
    	Visit visit = visitRepository.findVisitById(visitId);
    	Technician technician = TechnicianConvert.toEntity(technicianRequest);
    	technician.setTechnicianId(UUID.randomUUID().toString());
	    visit.setTechnician(technician);
    	return VisitConvert.toResponse(visitRepository.save(visit));
    }
}

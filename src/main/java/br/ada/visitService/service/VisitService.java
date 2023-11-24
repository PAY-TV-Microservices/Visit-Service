package br.ada.visitService.service;

import br.ada.visitService.controller.dto.TechnicianRequest;
import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.exception.IdNotFoundException;
import br.ada.visitService.model.Technician;
import br.ada.visitService.model.Visit;
import br.ada.visitService.repository.TechnicianRepository;
import br.ada.visitService.repository.VisitRepository;
import br.ada.visitService.utils.TechnicianConvert;
import br.ada.visitService.utils.VisitConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;
    
    @Autowired
    TechnicianRepository technicianRepository;

    public VisitResponse getVisitById(String visitId) throws IdNotFoundException{
    	Visit visit = visitRepository.findVisitById(visitId);
    	if(visit == null) throw new IdNotFoundException("Visita não encontrada");
        return VisitConvert.toResponse(visit);
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

    public void execute(VisitRequest req) {
        //TODO se não for usuário novo, validar se está inadimplente

        Visit visit = VisitConvert.toEntity(req);
        visit.setVisitId(UUID.randomUUID().toString());
        visit.setVisitDate(LocalDate.now().plusDays(10));
        visit.setActive(true);
        visitRepository.save(visit);
    }

    
    public void deleteVisit(String visitId) throws IdNotFoundException {
		Visit visit = visitRepository.findVisitById(visitId);
		if(visit == null) throw new IdNotFoundException("Visita não encontrada");
		visit.setActive(false);
		visitRepository.save(visit);
	}
    
    public VisitResponse assingVisit(String visitId, TechnicianRequest technicianRequest) throws IdNotFoundException {
    	Visit visit = visitRepository.findVisitById(visitId);
    	if(visit == null) throw new IdNotFoundException("Visita não encontrada");
    	Technician technician = TechnicianConvert.toEntity(technicianRequest);
    	technician.setTechnicianId(UUID.randomUUID().toString());
    	technicianRepository.save(technician);
	    visit.setTechnician(technician);
    	return VisitConvert.toResponse(visitRepository.save(visit));
    }

    public List<VisitResponse> getVisitsByUserId(String userId){
        List<Visit> visits = visitRepository.findVisitsByUserId(userId);
        return VisitConvert.toResponseList(visits);
    }


}

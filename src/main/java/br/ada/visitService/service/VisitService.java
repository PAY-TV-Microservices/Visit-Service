package br.ada.visitService.service;

import br.ada.visitService.controller.dto.PaymentResponse;
import br.ada.visitService.controller.dto.TechnicianRequest;
import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.exception.IdNotFoundException;
import br.ada.visitService.exception.PendingPaymentsException;
import br.ada.visitService.exception.UserNewException;
import br.ada.visitService.model.Technician;
import br.ada.visitService.model.Visit;
import br.ada.visitService.repository.TechnicianRepository;
import br.ada.visitService.repository.VisitRepository;
import br.ada.visitService.utils.TechnicianConvert;
import br.ada.visitService.utils.VisitConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;
    @Autowired
    TechnicianRepository technicianRepository;
    @Autowired
    WebClient webClient;


    public VisitResponse getVisitById(String visitId) throws IdNotFoundException{
    	Visit visit = visitRepository.findVisitById(visitId);
    	if(visit == null) throw new IdNotFoundException("Visita não encontrada");
        return VisitConvert.toResponse(visit);
    }

    public VisitResponse saveNewVisit(VisitRequest visitRequest) throws UserNewException, PendingPaymentsException {
        PaymentResponse paymentResponse = webClient.get().uri("/pagamento/consulta/" + visitRequest.getUserId())
                .retrieve().bodyToMono(PaymentResponse.class).block();

        boolean scheduleVisit = (!visitRequest.isNewUser()) && (paymentResponse != null && paymentResponse.getPendingPayments().isEmpty());

        if (scheduleVisit) {
            Visit visit = VisitConvert.toEntity(visitRequest);
            visit.setVisitId(UUID.randomUUID().toString());
            visit.setActive(true);

            return VisitConvert.toResponse(visitRepository.save(visit));
        } else if(!visitRequest.isNewUser()){
            throw new UserNewException("Visita não pode ser realizada. Novo usuário o agendamento é realizado pela Assinatura.");
        } else{
            throw new PendingPaymentsException("Visita não pode ser realizada, usuário em débito.");
        }
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
        PaymentResponse paymentResponse = webClient.get().uri("/pagamento/consulta/" + req.getUserId())
                .retrieve().bodyToMono(PaymentResponse.class).block();

        boolean scheduleVisit = req.isNewUser() || (paymentResponse != null && paymentResponse.getPendingPayments().isEmpty());

        if (scheduleVisit){
            Visit visit = VisitConvert.toEntity(req);
            visit.setVisitId(UUID.randomUUID().toString());
            visit.setVisitDate(LocalDate.now().plusDays(10));
            visit.setActive(true);
            visitRepository.save(visit);
        }
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

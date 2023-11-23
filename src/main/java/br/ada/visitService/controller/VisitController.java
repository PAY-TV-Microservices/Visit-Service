package br.ada.visitService.controller;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.model.Visit;
import br.ada.visitService.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitResponse> saveVisit(@Valid @RequestBody VisitRequest visitRequest){
        VisitResponse visit = visitService.saveNewVisit(visitRequest);
        return ResponseEntity.created(URI.create("/visit/" + visit.getVisitId())).body(visit);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id){
        return ResponseEntity.ok(visitService.findVisitById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<VisitResponse>> getAllVisits(){
    	return ResponseEntity.ok(visitService.getAllVisits());
    }
    
    @DeleteMapping("/{id}")
    public void cancelVisit(@PathVariable Long id) {
    	visitService.deleteVisit(id);
    }
}

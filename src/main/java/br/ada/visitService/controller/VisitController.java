package br.ada.visitService.controller;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitResponse> saveVisit(VisitRequest visitRequest){
        VisitResponse visit = visitService.saveNewVisit(visitRequest);
        return ResponseEntity.created(URI.create("/visit/" + visit.getVisitId())).body(visit);
    }
}

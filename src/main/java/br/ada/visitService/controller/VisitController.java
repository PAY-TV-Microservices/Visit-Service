package br.ada.visitService.controller;

import br.ada.visitService.controller.dto.TechnicianRequest;
import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.exception.IdNotFoundException;
import br.ada.visitService.exception.PendingPaymentsException;
import br.ada.visitService.exception.UserNewException;
import br.ada.visitService.model.Visit;
import br.ada.visitService.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    VisitService visitService;
    
    @Operation(summary = "Marcar visita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Visita marcada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao marcar a visita"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VisitResponse> saveVisit(@Valid @RequestBody VisitRequest visitRequest) throws UserNewException, PendingPaymentsException {
        VisitResponse visit = visitService.saveNewVisit(visitRequest);
        return ResponseEntity.created(URI.create("/visit/" + visit.getVisitId())).body(visit);
    }
    
    @Operation(summary = "Consultar visita pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita no cadastro"),
            @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
    })
    @GetMapping("/id/{visitId}")
    public ResponseEntity<VisitResponse> getVisitById(@PathVariable String visitId) throws IdNotFoundException{
        return ResponseEntity.ok(visitService.getVisitById(visitId));
    }
    
    @Operation(summary = "Consultar visitas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita no cadastro"),
            @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
    })
    @GetMapping
    public ResponseEntity<List<VisitResponse>> getAllVisits(){
    	return ResponseEntity.ok(visitService.getAllVisits());
    }
    
    @Operation(summary = "Cancelar visita pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita cancelada"),
            @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
    })
    @DeleteMapping("/{visitId}")
    public void cancelVisit(@PathVariable String visitId) throws IdNotFoundException{
    	visitService.deleteVisit(visitId);
    }
    
    @Operation(summary = "Atribuir visita a técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita atribuida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível atribuir a visita"),
    })
    @PostMapping("/technician/{visitId}")
    public ResponseEntity<VisitResponse> assignVisit(@PathVariable String visitId, @RequestBody TechnicianRequest technicianRequest) throws IdNotFoundException{
    	return ResponseEntity.ok(visitService.assingVisit(visitId, technicianRequest));
    }
    
    @Operation(summary = "Consultar visita pelo id do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visita no cadastro"),
            @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
    })
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<VisitResponse>> getVisitByUserId(@PathVariable String userId){
        return ResponseEntity.ok(visitService.getVisitsByUserId(userId));
    }
}

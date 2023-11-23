package br.ada.visitService.utils;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.model.Visit;

public class VisitConvert {

    public static Visit toEntity(VisitRequest visitRequest){
        Visit visit = new Visit();
        visit.setVisitDate(visitRequest.getVisitDate());
        visit.setUserId(visitRequest.getUserId());

        return visit;
    }

    public static VisitResponse toResponse(Visit visit){
        VisitResponse visitResponse = new VisitResponse();

        visitResponse.setId(visit.getId());
        visitResponse.setVisitId(visit.getVisitId());
        visitResponse.setVisitDate(visit.getVisitDate());
        visitResponse.setUserId(visit.getUserId());

        return visitResponse;
    }


}

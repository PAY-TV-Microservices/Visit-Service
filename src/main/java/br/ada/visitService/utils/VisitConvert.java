package br.ada.visitService.utils;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.controller.dto.VisitResponse;
import br.ada.visitService.model.Visit;

import java.util.ArrayList;
import java.util.List;

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
        visitResponse.setTechnician(visit.getTechnician());

        return visitResponse;
    }

    public static List<VisitResponse> toResponseList(List<Visit> visits){
        List<VisitResponse> visitResponses=new ArrayList<>();
        for(Visit visit : visits){
            VisitResponse visitResponse = VisitConvert.toResponse(visit);
            visitResponses.add(visitResponse);
        }
        return visitResponses;
    }


}

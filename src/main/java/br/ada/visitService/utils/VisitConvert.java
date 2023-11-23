package br.ada.visitService.utils;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.model.Visit;

public class VisitConvert {

    public static Visit toEntity(VisitRequest visitRequest){
        Visit visit = new Visit();
        visit.setVisitDate(visitRequest.getVisitDate());
        visit.setUserId(visitRequest.getUserId());

        return visit;

    }
}

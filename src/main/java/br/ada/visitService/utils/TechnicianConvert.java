package br.ada.visitService.utils;

import br.ada.visitService.controller.dto.TechnicianRequest;
import br.ada.visitService.model.Technician;

public class TechnicianConvert {
	
	public static Technician toEntity(TechnicianRequest technicianRequest){
        Technician technician = new Technician();
        technician.setName(technicianRequest.getName());
        technician.setCpf(technicianRequest.getCpf());

        return technician;
    }

}

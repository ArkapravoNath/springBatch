package com.gok.service.mapper;


import com.gok.domain.*;
import com.gok.service.dto.AssignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assignment} and its DTO {@link AssignmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketActionMapper.class, MedicalPractitionerMapper.class})
public interface AssignmentMapper extends EntityMapper<AssignmentDTO, Assignment> {

    @Mapping(source = "ticketAction.id", target = "ticketActionId")
    @Mapping(source = "medicalPractitioner.id", target = "medicalPractitionerId")
    AssignmentDTO toDto(Assignment assignment);

    @Mapping(source = "ticketActionId", target = "ticketAction")
    @Mapping(source = "medicalPractitionerId", target = "medicalPractitioner")
    Assignment toEntity(AssignmentDTO assignmentDTO);

    default Assignment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Assignment assignment = new Assignment();
        assignment.setId(id);
        return assignment;
    }
}

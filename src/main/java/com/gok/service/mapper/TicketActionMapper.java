package com.gok.service.mapper;


import com.gok.domain.*;
import com.gok.service.dto.TicketActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TicketAction} and its DTO {@link TicketActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TicketActionMapper extends EntityMapper<TicketActionDTO, TicketAction> {


    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "removeAssignment", ignore = true)
    TicketAction toEntity(TicketActionDTO ticketActionDTO);

    default TicketAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        TicketAction ticketAction = new TicketAction();
        ticketAction.setId(id);
        return ticketAction;
    }
}

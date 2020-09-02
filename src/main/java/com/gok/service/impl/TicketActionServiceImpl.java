package com.gok.service.impl;

import com.gok.service.TicketActionService;
import com.gok.domain.TicketAction;
import com.gok.repository.TicketActionRepository;
import com.gok.service.dto.TicketActionDTO;
import com.gok.service.mapper.TicketActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TicketAction}.
 */
@Service
@Transactional
public class TicketActionServiceImpl implements TicketActionService {

    private final Logger log = LoggerFactory.getLogger(TicketActionServiceImpl.class);

    private final TicketActionRepository ticketActionRepository;

    private final TicketActionMapper ticketActionMapper;

    public TicketActionServiceImpl(TicketActionRepository ticketActionRepository, TicketActionMapper ticketActionMapper) {
        this.ticketActionRepository = ticketActionRepository;
        this.ticketActionMapper = ticketActionMapper;
    }

    @Override
    public TicketActionDTO save(TicketActionDTO ticketActionDTO) {
        log.debug("Request to save TicketAction : {}", ticketActionDTO);
        TicketAction ticketAction = ticketActionMapper.toEntity(ticketActionDTO);
        ticketAction = ticketActionRepository.save(ticketAction);
        return ticketActionMapper.toDto(ticketAction);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TicketActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TicketActions");
        return ticketActionRepository.findAll(pageable)
            .map(ticketActionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TicketActionDTO> findOne(Long id) {
        log.debug("Request to get TicketAction : {}", id);
        return ticketActionRepository.findById(id)
            .map(ticketActionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TicketAction : {}", id);
        ticketActionRepository.deleteById(id);
    }
}

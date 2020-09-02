package com.gok.service.impl;

import com.gok.service.PractitionerScheduleService;
import com.gok.domain.PractitionerSchedule;
import com.gok.repository.PractitionerScheduleRepository;
import com.gok.service.dto.PractitionerScheduleDTO;
import com.gok.service.mapper.PractitionerScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PractitionerSchedule}.
 */
@Service
@Transactional
public class PractitionerScheduleServiceImpl implements PractitionerScheduleService {

    private final Logger log = LoggerFactory.getLogger(PractitionerScheduleServiceImpl.class);

    private final PractitionerScheduleRepository practitionerScheduleRepository;

    private final PractitionerScheduleMapper practitionerScheduleMapper;

    public PractitionerScheduleServiceImpl(PractitionerScheduleRepository practitionerScheduleRepository, PractitionerScheduleMapper practitionerScheduleMapper) {
        this.practitionerScheduleRepository = practitionerScheduleRepository;
        this.practitionerScheduleMapper = practitionerScheduleMapper;
    }

    @Override
    public PractitionerScheduleDTO save(PractitionerScheduleDTO practitionerScheduleDTO) {
        log.debug("Request to save PractitionerSchedule : {}", practitionerScheduleDTO);
        PractitionerSchedule practitionerSchedule = practitionerScheduleMapper.toEntity(practitionerScheduleDTO);
        practitionerSchedule = practitionerScheduleRepository.save(practitionerSchedule);
        return practitionerScheduleMapper.toDto(practitionerSchedule);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PractitionerScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PractitionerSchedules");
        return practitionerScheduleRepository.findAll(pageable)
            .map(practitionerScheduleMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PractitionerScheduleDTO> findOne(Long id) {
        log.debug("Request to get PractitionerSchedule : {}", id);
        return practitionerScheduleRepository.findById(id)
            .map(practitionerScheduleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PractitionerSchedule : {}", id);
        practitionerScheduleRepository.deleteById(id);
    }
}

package com.gok.service.impl;

import com.gok.service.MedicalPractitionerService;
import com.gok.domain.MedicalPractitioner;
import com.gok.repository.MedicalPractitionerRepository;
import com.gok.service.dto.MedicalPractitionerDTO;
import com.gok.service.mapper.MedicalPractitionerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalPractitioner}.
 */
@Service
@Transactional
public class MedicalPractitionerServiceImpl implements MedicalPractitionerService {

    private final Logger log = LoggerFactory.getLogger(MedicalPractitionerServiceImpl.class);

    private final MedicalPractitionerRepository medicalPractitionerRepository;

    private final MedicalPractitionerMapper medicalPractitionerMapper;

    public MedicalPractitionerServiceImpl(MedicalPractitionerRepository medicalPractitionerRepository, MedicalPractitionerMapper medicalPractitionerMapper) {
        this.medicalPractitionerRepository = medicalPractitionerRepository;
        this.medicalPractitionerMapper = medicalPractitionerMapper;
    }

    @Override
    public MedicalPractitionerDTO save(MedicalPractitionerDTO medicalPractitionerDTO) {
        log.debug("Request to save MedicalPractitioner : {}", medicalPractitionerDTO);
        MedicalPractitioner medicalPractitioner = medicalPractitionerMapper.toEntity(medicalPractitionerDTO);
        medicalPractitioner = medicalPractitionerRepository.save(medicalPractitioner);
        return medicalPractitionerMapper.toDto(medicalPractitioner);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedicalPractitionerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalPractitioners");
        return medicalPractitionerRepository.findAll(pageable)
            .map(medicalPractitionerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalPractitionerDTO> findOne(Long id) {
        log.debug("Request to get MedicalPractitioner : {}", id);
        return medicalPractitionerRepository.findById(id)
            .map(medicalPractitionerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalPractitioner : {}", id);
        medicalPractitionerRepository.deleteById(id);
    }
}

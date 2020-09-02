package com.gok.service.mapper;


import com.gok.domain.*;
import com.gok.service.dto.MedicalPractitionerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalPractitioner} and its DTO {@link MedicalPractitionerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicalPractitionerMapper extends EntityMapper<MedicalPractitionerDTO, MedicalPractitioner> {


    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "removeAssignment", ignore = true)
    @Mapping(target = "practitionerSchedules", ignore = true)
    @Mapping(target = "removePractitionerSchedule", ignore = true)
    MedicalPractitioner toEntity(MedicalPractitionerDTO medicalPractitionerDTO);

    default MedicalPractitioner fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalPractitioner medicalPractitioner = new MedicalPractitioner();
        medicalPractitioner.setId(id);
        return medicalPractitioner;
    }
}

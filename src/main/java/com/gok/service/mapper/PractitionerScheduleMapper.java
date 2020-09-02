package com.gok.service.mapper;


import com.gok.domain.*;
import com.gok.service.dto.PractitionerScheduleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PractitionerSchedule} and its DTO {@link PractitionerScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalPractitionerMapper.class})
public interface PractitionerScheduleMapper extends EntityMapper<PractitionerScheduleDTO, PractitionerSchedule> {

    @Mapping(source = "medicalPractitioner.id", target = "medicalPractitionerId")
    PractitionerScheduleDTO toDto(PractitionerSchedule practitionerSchedule);

    @Mapping(source = "medicalPractitionerId", target = "medicalPractitioner")
    PractitionerSchedule toEntity(PractitionerScheduleDTO practitionerScheduleDTO);

    default PractitionerSchedule fromId(Long id) {
        if (id == null) {
            return null;
        }
        PractitionerSchedule practitionerSchedule = new PractitionerSchedule();
        practitionerSchedule.setId(id);
        return practitionerSchedule;
    }
}

package com.gok.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class MedicalPractitionerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalPractitionerDTO.class);
        MedicalPractitionerDTO medicalPractitionerDTO1 = new MedicalPractitionerDTO();
        medicalPractitionerDTO1.setId(1L);
        MedicalPractitionerDTO medicalPractitionerDTO2 = new MedicalPractitionerDTO();
        assertThat(medicalPractitionerDTO1).isNotEqualTo(medicalPractitionerDTO2);
        medicalPractitionerDTO2.setId(medicalPractitionerDTO1.getId());
        assertThat(medicalPractitionerDTO1).isEqualTo(medicalPractitionerDTO2);
        medicalPractitionerDTO2.setId(2L);
        assertThat(medicalPractitionerDTO1).isNotEqualTo(medicalPractitionerDTO2);
        medicalPractitionerDTO1.setId(null);
        assertThat(medicalPractitionerDTO1).isNotEqualTo(medicalPractitionerDTO2);
    }
}

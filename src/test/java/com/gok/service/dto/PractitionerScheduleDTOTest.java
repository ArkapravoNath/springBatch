package com.gok.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class PractitionerScheduleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PractitionerScheduleDTO.class);
        PractitionerScheduleDTO practitionerScheduleDTO1 = new PractitionerScheduleDTO();
        practitionerScheduleDTO1.setId(1L);
        PractitionerScheduleDTO practitionerScheduleDTO2 = new PractitionerScheduleDTO();
        assertThat(practitionerScheduleDTO1).isNotEqualTo(practitionerScheduleDTO2);
        practitionerScheduleDTO2.setId(practitionerScheduleDTO1.getId());
        assertThat(practitionerScheduleDTO1).isEqualTo(practitionerScheduleDTO2);
        practitionerScheduleDTO2.setId(2L);
        assertThat(practitionerScheduleDTO1).isNotEqualTo(practitionerScheduleDTO2);
        practitionerScheduleDTO1.setId(null);
        assertThat(practitionerScheduleDTO1).isNotEqualTo(practitionerScheduleDTO2);
    }
}

package com.gok.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class PractitionerScheduleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PractitionerSchedule.class);
        PractitionerSchedule practitionerSchedule1 = new PractitionerSchedule();
        practitionerSchedule1.setId(1L);
        PractitionerSchedule practitionerSchedule2 = new PractitionerSchedule();
        practitionerSchedule2.setId(practitionerSchedule1.getId());
        assertThat(practitionerSchedule1).isEqualTo(practitionerSchedule2);
        practitionerSchedule2.setId(2L);
        assertThat(practitionerSchedule1).isNotEqualTo(practitionerSchedule2);
        practitionerSchedule1.setId(null);
        assertThat(practitionerSchedule1).isNotEqualTo(practitionerSchedule2);
    }
}

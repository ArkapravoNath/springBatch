package com.gok.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class MedicalPractitionerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalPractitioner.class);
        MedicalPractitioner medicalPractitioner1 = new MedicalPractitioner();
        medicalPractitioner1.setId(1L);
        MedicalPractitioner medicalPractitioner2 = new MedicalPractitioner();
        medicalPractitioner2.setId(medicalPractitioner1.getId());
        assertThat(medicalPractitioner1).isEqualTo(medicalPractitioner2);
        medicalPractitioner2.setId(2L);
        assertThat(medicalPractitioner1).isNotEqualTo(medicalPractitioner2);
        medicalPractitioner1.setId(null);
        assertThat(medicalPractitioner1).isNotEqualTo(medicalPractitioner2);
    }
}

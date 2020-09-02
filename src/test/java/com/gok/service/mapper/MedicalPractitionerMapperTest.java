package com.gok.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalPractitionerMapperTest {

    private MedicalPractitionerMapper medicalPractitionerMapper;

    @BeforeEach
    public void setUp() {
        medicalPractitionerMapper = new MedicalPractitionerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalPractitionerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalPractitionerMapper.fromId(null)).isNull();
    }
}

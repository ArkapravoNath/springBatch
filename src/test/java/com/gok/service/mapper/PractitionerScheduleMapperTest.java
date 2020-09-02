package com.gok.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PractitionerScheduleMapperTest {

    private PractitionerScheduleMapper practitionerScheduleMapper;

    @BeforeEach
    public void setUp() {
        practitionerScheduleMapper = new PractitionerScheduleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(practitionerScheduleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(practitionerScheduleMapper.fromId(null)).isNull();
    }
}

package com.gok.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TicketActionMapperTest {

    private TicketActionMapper ticketActionMapper;

    @BeforeEach
    public void setUp() {
        ticketActionMapper = new TicketActionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ticketActionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ticketActionMapper.fromId(null)).isNull();
    }
}

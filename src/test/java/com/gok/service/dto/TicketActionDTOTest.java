package com.gok.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class TicketActionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketActionDTO.class);
        TicketActionDTO ticketActionDTO1 = new TicketActionDTO();
        ticketActionDTO1.setId(1L);
        TicketActionDTO ticketActionDTO2 = new TicketActionDTO();
        assertThat(ticketActionDTO1).isNotEqualTo(ticketActionDTO2);
        ticketActionDTO2.setId(ticketActionDTO1.getId());
        assertThat(ticketActionDTO1).isEqualTo(ticketActionDTO2);
        ticketActionDTO2.setId(2L);
        assertThat(ticketActionDTO1).isNotEqualTo(ticketActionDTO2);
        ticketActionDTO1.setId(null);
        assertThat(ticketActionDTO1).isNotEqualTo(ticketActionDTO2);
    }
}

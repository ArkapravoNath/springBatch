package com.gok.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.web.rest.TestUtil;

public class TicketActionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketAction.class);
        TicketAction ticketAction1 = new TicketAction();
        ticketAction1.setId(1L);
        TicketAction ticketAction2 = new TicketAction();
        ticketAction2.setId(ticketAction1.getId());
        assertThat(ticketAction1).isEqualTo(ticketAction2);
        ticketAction2.setId(2L);
        assertThat(ticketAction1).isNotEqualTo(ticketAction2);
        ticketAction1.setId(null);
        assertThat(ticketAction1).isNotEqualTo(ticketAction2);
    }
}

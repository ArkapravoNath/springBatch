package com.gok.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.domain.Assignment} entity.
 */
public class AssignmentDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String status;

    private String assigneeId;


    private Long ticketActionId;

    private Long medicalPractitionerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getTicketActionId() {
        return ticketActionId;
    }

    public void setTicketActionId(Long ticketActionId) {
        this.ticketActionId = ticketActionId;
    }

    public Long getMedicalPractitionerId() {
        return medicalPractitionerId;
    }

    public void setMedicalPractitionerId(Long medicalPractitionerId) {
        this.medicalPractitionerId = medicalPractitionerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssignmentDTO)) {
            return false;
        }

        return id != null && id.equals(((AssignmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssignmentDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", assigneeId='" + getAssigneeId() + "'" +
            ", ticketActionId=" + getTicketActionId() +
            ", medicalPractitionerId=" + getMedicalPractitionerId() +
            "}";
    }
}

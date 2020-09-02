package com.gok.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Assignment.
 */
@Entity
@Table(name = "assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Assignment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "assignee_id")
    private String assigneeId;

    @ManyToOne
    @JsonIgnoreProperties(value = "assignments", allowSetters = true)
    private TicketAction ticketAction;

    @ManyToOne
    @JsonIgnoreProperties(value = "assignments", allowSetters = true)
    private MedicalPractitioner medicalPractitioner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public Assignment status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public Assignment assigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
        return this;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public TicketAction getTicketAction() {
        return ticketAction;
    }

    public Assignment ticketAction(TicketAction ticketAction) {
        this.ticketAction = ticketAction;
        return this;
    }

    public void setTicketAction(TicketAction ticketAction) {
        this.ticketAction = ticketAction;
    }

    public MedicalPractitioner getMedicalPractitioner() {
        return medicalPractitioner;
    }

    public Assignment medicalPractitioner(MedicalPractitioner medicalPractitioner) {
        this.medicalPractitioner = medicalPractitioner;
        return this;
    }

    public void setMedicalPractitioner(MedicalPractitioner medicalPractitioner) {
        this.medicalPractitioner = medicalPractitioner;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assignment)) {
            return false;
        }
        return id != null && id.equals(((Assignment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Assignment{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", assigneeId='" + getAssigneeId() + "'" +
            "}";
    }
}

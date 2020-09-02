package com.gok.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TicketAction.
 */
@Entity
@Table(name = "ticket_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TicketAction extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "status")
    private String status;

    @Column(name = "actions")
    private String actions;

    @Column(name = "action_taken_by_ref")
    private Long actionTakenByRef;

    @Column(name = "category")
    private String category;

    @Column(name = "subcategory")
    private String subcategory;

    @Column(name = "idle_time")
    private Integer idleTime;

    @Column(name = "idle_time_measure_type")
    private String idleTimeMeasureType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "closure_status")
    private String closureStatus;

    @OneToMany(mappedBy = "ticketAction")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assignment> assignments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public TicketAction actionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getStatus() {
        return status;
    }

    public TicketAction status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActions() {
        return actions;
    }

    public TicketAction actions(String actions) {
        this.actions = actions;
        return this;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Long getActionTakenByRef() {
        return actionTakenByRef;
    }

    public TicketAction actionTakenByRef(Long actionTakenByRef) {
        this.actionTakenByRef = actionTakenByRef;
        return this;
    }

    public void setActionTakenByRef(Long actionTakenByRef) {
        this.actionTakenByRef = actionTakenByRef;
    }

    public String getCategory() {
        return category;
    }

    public TicketAction category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public TicketAction subcategory(String subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Integer getIdleTime() {
        return idleTime;
    }

    public TicketAction idleTime(Integer idleTime) {
        this.idleTime = idleTime;
        return this;
    }

    public void setIdleTime(Integer idleTime) {
        this.idleTime = idleTime;
    }

    public String getIdleTimeMeasureType() {
        return idleTimeMeasureType;
    }

    public TicketAction idleTimeMeasureType(String idleTimeMeasureType) {
        this.idleTimeMeasureType = idleTimeMeasureType;
        return this;
    }

    public void setIdleTimeMeasureType(String idleTimeMeasureType) {
        this.idleTimeMeasureType = idleTimeMeasureType;
    }

    public String getComment() {
        return comment;
    }

    public TicketAction comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClosureStatus() {
        return closureStatus;
    }

    public TicketAction closureStatus(String closureStatus) {
        this.closureStatus = closureStatus;
        return this;
    }

    public void setClosureStatus(String closureStatus) {
        this.closureStatus = closureStatus;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public TicketAction assignments(Set<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    public TicketAction addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        assignment.setTicketAction(this);
        return this;
    }

    public TicketAction removeAssignment(Assignment assignment) {
        this.assignments.remove(assignment);
        assignment.setTicketAction(null);
        return this;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketAction)) {
            return false;
        }
        return id != null && id.equals(((TicketAction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketAction{" +
            "id=" + getId() +
            ", actionName='" + getActionName() + "'" +
            ", status='" + getStatus() + "'" +
            ", actions='" + getActions() + "'" +
            ", actionTakenByRef=" + getActionTakenByRef() +
            ", category='" + getCategory() + "'" +
            ", subcategory='" + getSubcategory() + "'" +
            ", idleTime=" + getIdleTime() +
            ", idleTimeMeasureType='" + getIdleTimeMeasureType() + "'" +
            ", comment='" + getComment() + "'" +
            ", closureStatus='" + getClosureStatus() + "'" +
            "}";
    }
}

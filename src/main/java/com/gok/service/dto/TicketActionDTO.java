package com.gok.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.domain.TicketAction} entity.
 */
public class TicketActionDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String actionName;

    private String status;

    private String actions;

    private Long actionTakenByRef;

    private String category;

    private String subcategory;

    private Integer idleTime;

    private String idleTimeMeasureType;

    private String comment;

    private String closureStatus;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Long getActionTakenByRef() {
        return actionTakenByRef;
    }

    public void setActionTakenByRef(Long actionTakenByRef) {
        this.actionTakenByRef = actionTakenByRef;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Integer getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(Integer idleTime) {
        this.idleTime = idleTime;
    }

    public String getIdleTimeMeasureType() {
        return idleTimeMeasureType;
    }

    public void setIdleTimeMeasureType(String idleTimeMeasureType) {
        this.idleTimeMeasureType = idleTimeMeasureType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClosureStatus() {
        return closureStatus;
    }

    public void setClosureStatus(String closureStatus) {
        this.closureStatus = closureStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketActionDTO)) {
            return false;
        }

        return id != null && id.equals(((TicketActionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketActionDTO{" +
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

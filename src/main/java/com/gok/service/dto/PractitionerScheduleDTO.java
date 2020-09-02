package com.gok.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.domain.PractitionerSchedule} entity.
 */
public class PractitionerScheduleDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String typeOfSchedule;

    private Instant startDateTime;

    private Instant endDateTime;

    private String shiftType;

    private String status;

    private Instant scheduleDate;

    private Integer scheduleStartTime;

    private Integer scheduleEndTime;


    private Long medicalPractitionerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfSchedule() {
        return typeOfSchedule;
    }

    public void setTypeOfSchedule(String typeOfSchedule) {
        this.typeOfSchedule = typeOfSchedule;
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Instant startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Instant scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Integer scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Integer getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Integer scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
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
        if (!(o instanceof PractitionerScheduleDTO)) {
            return false;
        }

        return id != null && id.equals(((PractitionerScheduleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PractitionerScheduleDTO{" +
            "id=" + getId() +
            ", typeOfSchedule='" + getTypeOfSchedule() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", shiftType='" + getShiftType() + "'" +
            ", status='" + getStatus() + "'" +
            ", scheduleDate='" + getScheduleDate() + "'" +
            ", scheduleStartTime=" + getScheduleStartTime() +
            ", scheduleEndTime=" + getScheduleEndTime() +
            ", medicalPractitionerId=" + getMedicalPractitionerId() +
            "}";
    }
}

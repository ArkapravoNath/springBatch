package com.gok.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PractitionerSchedule.
 */
@Entity
@Table(name = "practitioner_schedule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PractitionerSchedule extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type_of_schedule")
    private String typeOfSchedule;

    @Column(name = "start_date_time")
    private Instant startDateTime;

    @Column(name = "end_date_time")
    private Instant endDateTime;

    @Column(name = "shift_type")
    private String shiftType;

    @Column(name = "status")
    private String status;

    @Column(name = "schedule_date")
    private Instant scheduleDate;

    @Column(name = "schedule_start_time")
    private Integer scheduleStartTime;

    @Column(name = "schedule_end_time")
    private Integer scheduleEndTime;

    @ManyToOne
    @JsonIgnoreProperties(value = "practitionerSchedules", allowSetters = true)
    private MedicalPractitioner medicalPractitioner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfSchedule() {
        return typeOfSchedule;
    }

    public PractitionerSchedule typeOfSchedule(String typeOfSchedule) {
        this.typeOfSchedule = typeOfSchedule;
        return this;
    }

    public void setTypeOfSchedule(String typeOfSchedule) {
        this.typeOfSchedule = typeOfSchedule;
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public PractitionerSchedule startDateTime(Instant startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public void setStartDateTime(Instant startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public PractitionerSchedule endDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getShiftType() {
        return shiftType;
    }

    public PractitionerSchedule shiftType(String shiftType) {
        this.shiftType = shiftType;
        return this;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public String getStatus() {
        return status;
    }

    public PractitionerSchedule status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getScheduleDate() {
        return scheduleDate;
    }

    public PractitionerSchedule scheduleDate(Instant scheduleDate) {
        this.scheduleDate = scheduleDate;
        return this;
    }

    public void setScheduleDate(Instant scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getScheduleStartTime() {
        return scheduleStartTime;
    }

    public PractitionerSchedule scheduleStartTime(Integer scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
        return this;
    }

    public void setScheduleStartTime(Integer scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Integer getScheduleEndTime() {
        return scheduleEndTime;
    }

    public PractitionerSchedule scheduleEndTime(Integer scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
        return this;
    }

    public void setScheduleEndTime(Integer scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public MedicalPractitioner getMedicalPractitioner() {
        return medicalPractitioner;
    }

    public PractitionerSchedule medicalPractitioner(MedicalPractitioner medicalPractitioner) {
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
        if (!(o instanceof PractitionerSchedule)) {
            return false;
        }
        return id != null && id.equals(((PractitionerSchedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PractitionerSchedule{" +
            "id=" + getId() +
            ", typeOfSchedule='" + getTypeOfSchedule() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", shiftType='" + getShiftType() + "'" +
            ", status='" + getStatus() + "'" +
            ", scheduleDate='" + getScheduleDate() + "'" +
            ", scheduleStartTime=" + getScheduleStartTime() +
            ", scheduleEndTime=" + getScheduleEndTime() +
            "}";
    }
}

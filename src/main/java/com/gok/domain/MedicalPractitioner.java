package com.gok.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MedicalPractitioner.
 */
@Entity
@Table(name = "medical_practitioner")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalPractitioner extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "practitioner_type")
    private String practitionerType;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "employment_mode")
    private String employmentMode;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "status")
    private String status;

    @Column(name = "person_id_ref")
    private Long personIdRef;

    @Column(name = "category")
    private String category;

    @Column(name = "position")
    private String position;

    @Column(name = "whatsapp_number")
    private String whatsappNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "language_1")
    private String language1;

    @Column(name = "language_2")
    private String language2;

    @Column(name = "vehicle_exist")
    private String vehicleExist;

    @OneToMany(mappedBy = "medicalPractitioner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assignment> assignments = new HashSet<>();

    @OneToMany(mappedBy = "medicalPractitioner")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PractitionerSchedule> practitionerSchedules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPractitionerType() {
        return practitionerType;
    }

    public MedicalPractitioner practitionerType(String practitionerType) {
        this.practitionerType = practitionerType;
        return this;
    }

    public void setPractitionerType(String practitionerType) {
        this.practitionerType = practitionerType;
    }

    public String getSpecialty() {
        return specialty;
    }

    public MedicalPractitioner specialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public MedicalPractitioner registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmploymentMode() {
        return employmentMode;
    }

    public MedicalPractitioner employmentMode(String employmentMode) {
        this.employmentMode = employmentMode;
        return this;
    }

    public void setEmploymentMode(String employmentMode) {
        this.employmentMode = employmentMode;
    }

    public String getQualification() {
        return qualification;
    }

    public MedicalPractitioner qualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getStatus() {
        return status;
    }

    public MedicalPractitioner status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPersonIdRef() {
        return personIdRef;
    }

    public MedicalPractitioner personIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
        return this;
    }

    public void setPersonIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
    }

    public String getCategory() {
        return category;
    }

    public MedicalPractitioner category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosition() {
        return position;
    }

    public MedicalPractitioner position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public MedicalPractitioner whatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
        return this;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getEmail() {
        return email;
    }

    public MedicalPractitioner email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage1() {
        return language1;
    }

    public MedicalPractitioner language1(String language1) {
        this.language1 = language1;
        return this;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public MedicalPractitioner language2(String language2) {
        this.language2 = language2;
        return this;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getVehicleExist() {
        return vehicleExist;
    }

    public MedicalPractitioner vehicleExist(String vehicleExist) {
        this.vehicleExist = vehicleExist;
        return this;
    }

    public void setVehicleExist(String vehicleExist) {
        this.vehicleExist = vehicleExist;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public MedicalPractitioner assignments(Set<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    public MedicalPractitioner addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        assignment.setMedicalPractitioner(this);
        return this;
    }

    public MedicalPractitioner removeAssignment(Assignment assignment) {
        this.assignments.remove(assignment);
        assignment.setMedicalPractitioner(null);
        return this;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Set<PractitionerSchedule> getPractitionerSchedules() {
        return practitionerSchedules;
    }

    public MedicalPractitioner practitionerSchedules(Set<PractitionerSchedule> practitionerSchedules) {
        this.practitionerSchedules = practitionerSchedules;
        return this;
    }

    public MedicalPractitioner addPractitionerSchedule(PractitionerSchedule practitionerSchedule) {
        this.practitionerSchedules.add(practitionerSchedule);
        practitionerSchedule.setMedicalPractitioner(this);
        return this;
    }

    public MedicalPractitioner removePractitionerSchedule(PractitionerSchedule practitionerSchedule) {
        this.practitionerSchedules.remove(practitionerSchedule);
        practitionerSchedule.setMedicalPractitioner(null);
        return this;
    }

    public void setPractitionerSchedules(Set<PractitionerSchedule> practitionerSchedules) {
        this.practitionerSchedules = practitionerSchedules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalPractitioner)) {
            return false;
        }
        return id != null && id.equals(((MedicalPractitioner) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalPractitioner{" +
            "id=" + getId() +
            ", practitionerType='" + getPractitionerType() + "'" +
            ", specialty='" + getSpecialty() + "'" +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", employmentMode='" + getEmploymentMode() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", status='" + getStatus() + "'" +
            ", personIdRef=" + getPersonIdRef() +
            ", category='" + getCategory() + "'" +
            ", position='" + getPosition() + "'" +
            ", whatsappNumber='" + getWhatsappNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", language1='" + getLanguage1() + "'" +
            ", language2='" + getLanguage2() + "'" +
            ", vehicleExist='" + getVehicleExist() + "'" +
            "}";
    }
}

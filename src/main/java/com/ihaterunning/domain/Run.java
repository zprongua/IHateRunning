package com.ihaterunning.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Run.
 */
@Entity
@Table(name = "run")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Run implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "run_name", nullable = false)
    private String runName;

    @NotNull
    @Column(name = "run_date", nullable = false)
    private LocalDate runDate;

    @NotNull
    @Column(name = "distance", nullable = false)
    private Double distance;

    @NotNull
    @Column(name = "time", nullable = false)
    private Double time;

    @Column(name = "pace")
    private Double pace;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Run id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRunName() {
        return this.runName;
    }

    public Run runName(String runName) {
        this.setRunName(runName);
        return this;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public LocalDate getRunDate() {
        return this.runDate;
    }

    public Run runDate(LocalDate runDate) {
        this.setRunDate(runDate);
        return this;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Run distance(Double distance) {
        this.setDistance(distance);
        return this;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTime() {
        return this.time;
    }

    public Run time(Double time) {
        this.setTime(time);
        return this;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getPace() {
        return this.pace;
    }

    public Run pace(Double pace) {
        this.setPace(pace);
        return this;
    }

    public void setPace(Double pace) {
        this.pace = pace;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Run user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Run)) {
            return false;
        }
        return id != null && id.equals(((Run) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Run{" +
            "id=" + getId() +
            ", runName='" + getRunName() + "'" +
            ", runDate='" + getRunDate() + "'" +
            ", distance=" + getDistance() +
            ", time=" + getTime() +
            ", pace=" + getPace() +
            "}";
    }
}

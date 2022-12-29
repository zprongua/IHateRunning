package com.ihaterunning.domain;

import com.ihaterunning.domain.enumeration.Distance;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Race.
 */
@Entity
@Table(name = "race")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "race_name", nullable = false)
    private String raceName;

    @Column(name = "race_date")
    private LocalDate raceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "race_distance")
    private Distance raceDistance;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Race id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaceName() {
        return this.raceName;
    }

    public Race raceName(String raceName) {
        this.setRaceName(raceName);
        return this;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public LocalDate getRaceDate() {
        return this.raceDate;
    }

    public Race raceDate(LocalDate raceDate) {
        this.setRaceDate(raceDate);
        return this;
    }

    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    public Distance getRaceDistance() {
        return this.raceDistance;
    }

    public Race raceDistance(Distance raceDistance) {
        this.setRaceDistance(raceDistance);
        return this;
    }

    public void setRaceDistance(Distance raceDistance) {
        this.raceDistance = raceDistance;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Race)) {
            return false;
        }
        return id != null && id.equals(((Race) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Race{" +
            "id=" + getId() +
            ", raceName='" + getRaceName() + "'" +
            ", raceDate='" + getRaceDate() + "'" +
            ", raceDistance='" + getRaceDistance() + "'" +
            "}";
    }
}

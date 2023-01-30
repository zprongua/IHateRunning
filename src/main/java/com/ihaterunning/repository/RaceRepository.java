package com.ihaterunning.repository;

import com.ihaterunning.domain.Race;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Race entity.
 */
@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    @Query("select race from Race race where race.user.login = ?#{principal.username} ORDER BY RACE_DATE ASC")
    List<Race> findByUserIsCurrentUser();

    default Optional<Race> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Race> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Race> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct race from Race race left join fetch race.user",
        countQuery = "select count(distinct race) from Race race"
    )
    Page<Race> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct race from Race race left join fetch race.user")
    List<Race> findAllWithToOneRelationships();

    @Query("select race from Race race left join fetch race.user where race.id =:id")
    Optional<Race> findOneWithToOneRelationships(@Param("id") Long id);
}

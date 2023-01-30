package com.ihaterunning.repository;

import com.ihaterunning.domain.Run;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Run entity.
 */
@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
    @Query("select run from Run run where run.user.login = ?#{principal.username} ORDER BY RUN_DATE DESC")
    List<Run> findByUserIsCurrentUser();

    default Optional<Run> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Run> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Run> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select distinct run from Run run left join fetch run.user", countQuery = "select count(distinct run) from Run run")
    Page<Run> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct run from Run run left join fetch run.user")
    List<Run> findAllWithToOneRelationships();

    @Query("select run from Run run left join fetch run.user where run.id =:id")
    Optional<Run> findOneWithToOneRelationships(@Param("id") Long id);
}

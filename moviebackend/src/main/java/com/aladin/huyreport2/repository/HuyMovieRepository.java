package com.aladin.huyreport2.repository;

import com.aladin.huyreport2.domain.HuyMovie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the HuyMovie entity.
 */
@Repository
public interface HuyMovieRepository extends JpaRepository<HuyMovie, Long> {

    @Query(value = "select distinct huyMovie from HuyMovie huyMovie left join fetch huyMovie.genres left join fetch huyMovie.actors",
        countQuery = "select count(distinct huyMovie) from HuyMovie huyMovie")
    Page<HuyMovie> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct huyMovie from HuyMovie huyMovie left join fetch huyMovie.genres left join fetch huyMovie.actors")
    List<HuyMovie> findAllWithEagerRelationships();

    @Query("select huyMovie from HuyMovie huyMovie left join fetch huyMovie.genres left join fetch huyMovie.actors where huyMovie.id =:id")
    Optional<HuyMovie> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "select * from huy_movie m where m.id in\n" +
        "(select r.movie_id from huy_rate r where r.id =?1)",nativeQuery = true)
    HuyMovie findHuyMovieByRate(long id);
}

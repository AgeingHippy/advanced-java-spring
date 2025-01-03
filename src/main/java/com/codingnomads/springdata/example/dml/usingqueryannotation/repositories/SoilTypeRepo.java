/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.usingqueryannotation.repositories;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {

    @Query(value = "SELECT st FROM SoilType st")
    List<SoilType> findAllJPA();

    @Query(value = "SELECT st FROM SoilType st WHERE st.name = :name")
    SoilType findByNameJPA(@Param("name") String name);

    @Query(value = "SELECT st FROM SoilType st WHERE st.ph BETWEEN ?1 AND ?2")
    List<SoilType> findAllInRangePhJPA(double lowerBound, double upperBound);

    @Query(value = "SELECT st FROM SoilType st WHERE st.ph BETWEEN ?1 AND ?2",
            countQuery = "SELECT COUNT(st.id) FROM SoilType st WHERE st.ph BETWEEN ?1 AND ?2")
    Page<SoilType> findAllInRangePhPagedJPA(double lowerBound, double upperBound, Pageable pageable);

    @Query(value = "SELECT st FROM SoilType st WHERE st.dry =:dry")
    List<SoilType> findAllByDryJPA(@Param("dry") boolean dry);
}

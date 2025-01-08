/* CodingNomads (C)2024 */
package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    public Area findByCode(String code);
}

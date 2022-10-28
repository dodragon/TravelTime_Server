package com.dod.traveltime.repo;

import com.dod.traveltime.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Page<Travel> findAllByGroup_GroupIdOrderByCreatedAtDesc(Long groupId, Pageable pageable);
}

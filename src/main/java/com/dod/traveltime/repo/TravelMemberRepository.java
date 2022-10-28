package com.dod.traveltime.repo;

import com.dod.traveltime.entity.TravelMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelMemberRepository extends JpaRepository<TravelMember, Long> {
    Optional<List<TravelMember>> findAllByTravel_TravelId(Long travelId);
}

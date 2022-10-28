package com.dod.traveltime.repo;

import com.dod.traveltime.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Page<Group> findAllByUsers_User_UserIdOrderByCreatedAtDesc(Long userIdx, Pageable pageable);
    Page<Group> findAllByUsersUserUserIdOrderByCreatedAtDesc(Long userIdx, Pageable pageable);

}

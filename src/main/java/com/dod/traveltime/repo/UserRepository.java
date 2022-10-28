package com.dod.traveltime.repo;

import com.dod.traveltime.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailAndUidAndPassword(String email, String uid, String pw);
    Optional<Users> findByEmailAndUid(String email, String uid);

    @Query(value = "select * from Users  where userId = (select gm.userId from Group_Member as gm where gm.groupId = :idx)", nativeQuery = true)
    Page<Users> findAllByGroupUsers(@Param("idx") Long idx, Pageable pageable);
}

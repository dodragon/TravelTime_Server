package com.dod.traveltime.repo;

import com.dod.traveltime.entity.Group;
import com.dod.traveltime.entity.GroupMember;
import com.dod.traveltime.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    Optional<List<GroupMember>> findAllByUser(Users users);
    boolean existsGroupMemberByGroupAndUser(Group group, Users user);
}

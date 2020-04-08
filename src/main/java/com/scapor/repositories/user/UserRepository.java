package com.scapor.repositories.user;

import com.scapor.domain.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    //JPQL
    @Query("SELECT u FROM UserEntity u WHERE u.id =:ID")
    UserEntity findUserEntityById(@Param("ID") Integer id);

}

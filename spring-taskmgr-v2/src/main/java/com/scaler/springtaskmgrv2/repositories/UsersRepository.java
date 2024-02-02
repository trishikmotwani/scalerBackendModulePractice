package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    Optional<UserEntity> findById(Integer id);

    UserEntity findByUsername(String username);
}

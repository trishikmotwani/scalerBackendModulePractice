package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    @Override
    Optional<TaskEntity> findById(Integer integer);

    @Override
    List<TaskEntity> findAll();

    @Override
    <S extends TaskEntity> S save(S entity);


    @Override
    void deleteById(Integer integer);

    // @Query(value = "select * from tasks t where t.title = $1", nativeQuery = true)
    TaskEntity findByTitle(String title);
    List<TaskEntity> findAllByTitle(String title);
    List<TaskEntity> findAllByCompleted(Boolean completed);

//    @Transactional
//    @Modifying
//    @Query("update TASKS t set t.taskId = ?1 where c.cardNumber = ?2")
//    int updateCardBalance(double balance, String cardNumber);


}

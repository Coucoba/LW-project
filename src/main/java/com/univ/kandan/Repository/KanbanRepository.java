package com.univ.kandan.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.univ.kandan.model.Kanban;

public interface KanbanRepository extends CrudRepository<Kanban, UUID> {

    Set<Kanban> findAllByIsPublic(boolean isPublic);

    @Query(value = "SELECT * FROM kanban WHERE id IN ("
            + "SELECT kanbans_id FROM user_kanbans WHERE members_id = ?1)"
            + "OR is_public = 1", nativeQuery = true)
    Set<Kanban> findAllByUser(Long userId);

    Kanban findById(Long id);
}

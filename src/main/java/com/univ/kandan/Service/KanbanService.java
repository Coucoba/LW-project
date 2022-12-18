package com.univ.kandan.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.univ.kandan.model.Kanban;
import com.univ.kandan.repository.KanbanRepository;

@Service
public class KanbanService {

    private final KanbanRepository kanbanRepostitory;

    public KanbanService(KanbanRepository kanbanRepository) {
        this.kanbanRepostitory = kanbanRepository;
    }

    public Set<Kanban> findAllIsPublic() {
        return kanbanRepostitory.findAllByIsPublic(true);
    }

    public Set<Kanban> findAllByUser(Long userId) {
        return kanbanRepostitory.findAllByUser(userId);
    }

}

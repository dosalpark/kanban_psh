package com.team8.kanban.domain.comment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryCustom {
    Page<Comment> findAllPage(Pageable pageable);
}

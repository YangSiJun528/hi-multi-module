package com.multimodule.hi.domain.persistence.post.repository;

import com.multimodule.hi.domain.persistence.post.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    boolean existsByTitle(String title);
}

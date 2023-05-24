package com.multimodule.hi.web.domain.post.dto.response;

import com.multimodule.hi.domain.persistence.post.entity.Post;

import java.time.LocalDateTime;

public record PostResDto (
        Long id, String title, String content, LocalDateTime createAt
) {
    public static PostResDto from(Post post) {
        return new PostResDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreateAt()
        );
    }
}

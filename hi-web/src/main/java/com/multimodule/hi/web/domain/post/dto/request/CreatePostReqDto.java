package com.multimodule.hi.web.domain.post.dto.request;

import com.multimodule.hi.domain.persistence.post.entity.Post;
import jakarta.validation.constraints.NotBlank;


public record CreatePostReqDto(
        @NotBlank String title, @NotBlank String content
) {
    public Post toEntity() {
        return new Post(
                null,
                this.title,
                this.content,
                null
        );
    }
}


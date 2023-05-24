package com.multimodule.hi.web.domain.post.service;

import com.multimodule.hi.domain.persistence.post.entity.Post;
import com.multimodule.hi.web.domain.post.dto.request.CreatePostReqDto;
import com.multimodule.hi.web.domain.post.dto.response.PostResDto;
import org.springframework.data.domain.Page;

public interface PostService {
    void create(CreatePostReqDto dto);

    PostResDto find(Long id);

    PostResDto find(String title);

    Page<Post> findAll(Integer page, Integer size, boolean isAsc);

    void delete(Long id);
}

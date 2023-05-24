package com.multimodule.hi.web.domain.post.service;

import com.multimodule.hi.domain.persistence.post.entity.Post;
import com.multimodule.hi.domain.persistence.post.repository.PostRepository;
import com.multimodule.hi.web.domain.post.dto.request.CreatePostReqDto;
import com.multimodule.hi.web.domain.post.dto.response.PostResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Transactional(rollbackFor = {Exception.class})
    public void create(CreatePostReqDto dto) {
        if(postRepository.existsByTitle(dto.title())) {
            throw new RuntimeException("이미 존재하는 Post 입니다");
        }
        Post savedPost = postRepository.save(dto.toEntity());
        PostResDto.from(savedPost);
    }

    public PostResDto find(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Post 입니다"));
        return PostResDto.from(post);
    }

    public PostResDto find(String title) {
        Post post = postRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Post 입니다"));
        return PostResDto.from(post);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Page<Post> findAll(Integer page, Integer size, boolean isAsc) {
        Sort sort = Sort.by("createAt");
        sort = isAsc ? sort : sort.descending();
        return postRepository.findAll(PageRequest.of(page, size, sort));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Post 입니다"));
        postRepository.delete(post);
    }
}

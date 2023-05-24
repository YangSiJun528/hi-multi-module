package com.multimodule.hi.web.domain.post.controller;

import com.multimodule.hi.domain.persistence.post.entity.Post;
import com.multimodule.hi.web.domain.post.dto.request.CreatePostReqDto;
import com.multimodule.hi.web.domain.post.dto.response.PostResDto;
import com.multimodule.hi.web.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<Map<String, String>> create(
            @RequestBody @Valid CreatePostReqDto body
    ) {
        postService.create(body);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "created"));
    }

    @GetMapping("/post/id/{postId}")
    public ResponseEntity<PostResDto> getById(
            @PathVariable Long postId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.find(postId));
    }

    @GetMapping("/post/title/{title}")
    public ResponseEntity<PostResDto> getByTitle(
            @PathVariable String title
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.find(title));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false, defaultValue = "false") boolean isAsc

    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.findAll(page, size, isAsc));
    }

    @DeleteMapping("/post/id/{postId}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long postId
    ) {
        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "ok"));
    }
}

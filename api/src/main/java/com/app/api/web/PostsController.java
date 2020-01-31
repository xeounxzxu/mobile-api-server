package com.app.api.web;

import com.app.api.service.posts.PostsService;
import com.app.api.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping("")
    public Long save(@RequestBody PostsResponseDto dto) {
        log.info("DTO TITLE >>" + dto.getTitle());
        log.info("DTO CONTENT >>" + dto.getContent());
        log.info("DTO Author >>" + dto.getAuthor());
        return postsService.save(dto);
    }

    @PutMapping("/{id}")
    public Long updatePosts(@PathVariable long id, @RequestBody PostsResponseDto dto) {
        return postsService.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public Long deletePosts(@PathVariable long id) {
        return postsService.deleteBy(id);
    }



}

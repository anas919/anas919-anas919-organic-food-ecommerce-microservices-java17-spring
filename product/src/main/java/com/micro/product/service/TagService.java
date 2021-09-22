package com.micro.product.service;

import com.micro.product.dto.TagRequest;
import com.micro.product.dto.TagResponse;
import com.micro.product.model.Tag;
import com.micro.product.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public void createTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());

        tagRepository.save(tag);
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getAllTags() {
        List<Tag> tags = (List<Tag>) tagRepository.findAll();
        return tags.stream().map(tag -> mapToTagResponse(tag)).toList();
    }

    private TagResponse mapToTagResponse(Tag tag) {
        TagResponse tagResponse = new TagResponse();
        tagResponse.setId(tag.getId());
        tagResponse.setName(tag.getName());
        return tagResponse;
    }
}

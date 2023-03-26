package ru.budharain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.budharain.controller.dto.CommentDto;
import ru.budharain.model.Comment;
import ru.budharain.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentDto(comment.getId(), comment.getTitle(),comment.getTime(), comment.getText(), comment.getGrade()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommentDto> findAll(Integer page, Integer size, String sortField) {
        return commentRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(comment -> new CommentDto(comment.getId(), comment.getTitle(),comment.getTime(), comment.getText(), comment.getGrade()));
    }

    @Override
    public Optional<CommentDto> findById(Long id) {
        return commentRepository.findById(id)
                .map(comment -> new CommentDto(comment.getId(), comment.getTitle(),comment.getTime(), comment.getText(), comment.getGrade()));
    }

    @Override
    public void save(CommentDto commentDto) {
        Comment comment = new Comment(commentDto.getId(),commentDto.getTitle(),commentDto.getTime(), commentDto.getText(), commentDto.getGrade());
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}

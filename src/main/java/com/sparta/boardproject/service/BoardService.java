package com.sparta.boardproject.service;

import com.sparta.boardproject.controller.BoardController;
import com.sparta.boardproject.dto.BoardRequestDto;
import com.sparta.boardproject.dto.BoardResponseDto;
import com.sparta.boardproject.entity.Board;
import com.sparta.boardproject.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {


	@Autowired
	private BoardRepository boardRepository;

	public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
		Board board = new Board(requestDto);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO board (username, contents) VALUES (?, ?)";
		jbdcTemplate.update( con -> {
			PreparedStatement preparedStatement = con.
		})




		board = boardRepository.save(board);
		return new BoardResponseDto(board);
	}

	public List<BoardResponseDto> getAllBoards() {
		return boardRepository.findAll().stream()
				.map(BoardResponseDto::new)
				.collect(Collectors.toList());
	}

	public BoardResponseDto getBoardById(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
		return new BoardResponseDto(board);
	}

	public BoardResponseDto getBoardsById(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
		return new BoardResponseDto(board);
	}

	public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
		Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
		board.update(requestDto);
		board = boardRepository.save(board);
		return new BoardResponseDto(board);
	}

	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}
}

//
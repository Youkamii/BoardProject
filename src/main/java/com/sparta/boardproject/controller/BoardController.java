package com.sparta.boardproject.controller;

import com.sparta.boardproject.dto.BoardRequestDto;
import com.sparta.boardproject.dto.BoardResponseDto;
import com.sparta.boardproject.entity.Board;
import com.sparta.boardproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

	private final JdbcTemplate jdbcTemplate;

	public BoardController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Autowired
	private BoardService boardService;

	// C
	@PostMapping
	public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {

		Board board = new Board(requestDto);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO board (username, contents) VALUES (?, ?)";
		jdbcTemplate.update( con -> {
					PreparedStatement preparedStatement = con.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);

					preparedStatement.setString(1, board.getUsername());
					preparedStatement.setString(2, board.getContents());
					return preparedStatement;
				},
				keyHolder);
		Long id = keyHolder.getKey().longValue();
		board.setId(id);
		return boardService.createBoard(requestDto);
	}

	// Rs
	@GetMapping("/{id}")
	public ResponseEntity<BoardResponseDto> getBoardsById(@PathVariable Long id) {
		String sql = "SELECT * FROM board WHERE id = ?";

		BoardResponseDto boardResponseDto = jdbcTemplate.queryForObject(
				sql,
				new Object[]{id},
				(rs, rowNum) -> new BoardResponseDto(
						rs.getLong("id"),
						rs.getString("username"),
						rs.getString("contents")
				)
		);

		return ResponseEntity.ok(boardResponseDto);
	}


	// R
	@GetMapping
	public BoardResponseDto getBoardById(@PathVariable Long id) {

		String sql = "SELECT * FROM board";

		return (BoardResponseDto) jdbcTemplate.query(sql, new RowMapper<BoardResponseDto>() {
			@Override
			public BoardResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id");
				String username = rs.getString("username");
				String contents = rs.getString("contents");
				return new BoardResponseDto(id, username, contents);
			}
		});
	}

	// U
	@PutMapping("/{id}")
	public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
		return boardService.updateBoard(id, requestDto);
	}

	// D
	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable Long id) {
		boardService.deleteBoard(id);
	}
}

package com.sparta.boardproject.dto;

import com.sparta.boardproject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Builder @Getter
public class BoardResponseDto {
	private Long id;
	private String username;
	private String contents;

	public BoardResponseDto(Board board) {
		this.id = board.getId();
		this.username = board.getUsername();
		this.contents = board.getContents();
	}
}
package com.sparta.boardproject.entity;

import com.sparta.boardproject.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
	private Long id;
	private String username;
	private String contents;

	public Board(BoardRequestDto requestDto) {
		this.username = requestDto.getUsername();
		this.contents = requestDto.getContents();
	}

	public void update(BoardRequestDto requestDto) {
		this.username = requestDto.getUsername();
		this.contents = requestDto.getContents();
	}
}
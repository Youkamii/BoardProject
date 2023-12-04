package com.sparta.boardproject;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.boardproject.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

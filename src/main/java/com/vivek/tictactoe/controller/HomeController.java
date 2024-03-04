package com.vivek.tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.tictactoe.board.Board;
import com.vivek.tictactoe.turns.TurnRequest;

import jakarta.validation.Valid;

@RestController
public class HomeController {
	
	private final Board board;
	
	@Autowired
	public HomeController(Board board) {
		this.board = board;
	}
	
	@GetMapping("/state")
	@ResponseStatus(HttpStatus.OK)
	public Integer[] getBoardState() {
		System.out.println("inside getboard");
		return board.getArr();
	}
	
	@PostMapping("/state")
	public void setBoard(@Valid @RequestBody TurnRequest request) {
		System.out.println("inside post state");
		board.getArr()[request.getPos()] = request.getPlayer();
		System.out.println("after post state");
		
	}
		
	@GetMapping("/winner")
	public int getWinner() {
		System.out.println("inside winner");
		int winner = board.getWinner();
		System.out.println("after getting winner");
		return winner;
	}
}

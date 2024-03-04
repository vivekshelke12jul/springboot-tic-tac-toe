package com.vivek.tictactoe.turns;

public class TurnRequest {
	
	// x => 1,
	// o => 0
	int player;
	
	//0 to 9
	int pos;
	
	public TurnRequest(int player, int pos) {
		this.player = player;
		this.pos = pos;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getPos() {
		return pos;
	}
}

package com.vivek.tictactoe.board;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class Board {
	Integer[] arr;
	Integer gameResult;
	
	Board(){
		this.arr = new Integer[9];
		Arrays.fill(arr, -1);
		this.gameResult = -1;
	}

	public Integer[] getArr() {
		return this.arr;
	}
	
	public boolean checkLine(int p1, int p2, int p3, int player) {
		if(arr[p1]==player && arr[p2]==player && arr[p3]==player) {
			return true;
		}
		return false;
	}

	public Integer getWinner() {
		if(checkLine(0,1,2, 1)) 	return 1;
		else if(checkLine(3,4,5, 1)) 	return 1;
		else if(checkLine(6,7,8, 1)) 	return 1;
		else if(checkLine(0,3,6, 1)) 	return 1;
		else if(checkLine(1,4,7, 1)) 	return 1;
		else if(checkLine(2,5,8, 1)) 	return 1;
		else if(checkLine(0,4,8, 1)) 	return 1;
		else if(checkLine(2,4,6, 1)) 	return 1;
		
		else if(checkLine(0,1,2, 0)) 	return 0;
		else if(checkLine(3,4,5, 0)) 	return 0;
		else if(checkLine(6,7,8, 0)) 	return 0;
		else if(checkLine(0,3,6, 0)) 	return 0;
		else if(checkLine(1,4,7, 0)) 	return 0;
		else if(checkLine(2,5,8, 0)) 	return 0;
		else if(checkLine(0,4,8, 0)) 	return 0;
		else if(checkLine(2,4,6, 0)) 	return 0;
		
		return -1;
	}
}

package com.github.me717.talisman.board;

import java.util.*;

import com.github.me717.talisman.*;
import com.github.me717.talisman.board.Space.Region;

public class Movement {

	public static ArrayList<Space> getMovementOptions(PChar c, int roll, Board board) {
		ArrayList<Space> returning = new ArrayList<Space>();

		Space s = c.getSpace();
		int on = s.getId();
		int upRoll = on + roll, downRoll = on - roll;
		int currentRegionSize = board.getSpacesInRegion(s.getRegion()).length;
		upRoll = (upRoll + currentRegionSize) % currentRegionSize;
		downRoll = (downRoll + currentRegionSize) % currentRegionSize;
		returning.add(board.getSpacesInRegion(s.getRegion())[upRoll]);
		returning.add(board.getSpacesInRegion(s.getRegion())[downRoll]);
		// TODO special movement cases
		//Heading to middle region via sentinel
		if (s.getRegion().equals(Region.OUTER)) {
			//the number of spaces to move in the middle region
			int remaining = 0;
			// Sentinel: coming from chapel
			if (on >= 4 && downRoll < 4) {
				remaining = roll - on + 4;
			// Sentinel: coming from village
			} else if (on <= 4 && upRoll > 4) {
				remaining = roll - 4 + on;
			}
			if(remaining==1){
				returning.add(board.getSpacesInRegion(Region.MIDDLE)[3]);
			}else if(remaining>1){
				int upRemaining = 3+(remaining-1);
				int downRemaining = 3-(remaining-1);
				upRemaining = (upRemaining + Board.MIDDLE_REGION_SIZE)%Board.MIDDLE_REGION_SIZE;
				downRemaining = (downRemaining + Board.MIDDLE_REGION_SIZE)%Board.MIDDLE_REGION_SIZE;
				returning.add(board.getSpacesInRegion(Region.MIDDLE)[upRemaining]);
				returning.add(board.getSpacesInRegion(Region.MIDDLE)[downRemaining]);
			}
		}

		return returning;
	}
}

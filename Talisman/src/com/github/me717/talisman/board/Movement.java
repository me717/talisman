package com.github.me717.talisman.board;

import java.util.*;

import com.github.me717.talisman.*;


public class Movement {

	
	public static ArrayList<Space> getMovementOptions(PChar c, int roll, Board b){
		ArrayList<Space> returning = new ArrayList<Space>();
		
		Space s = c.getSpace();
		int on = s.getId();
		int upRoll = on + roll, downRoll = on-roll;
		int currentRegionSize = b.getSpacesInRegion(s.getRegion()).length;
		upRoll = (upRoll + currentRegionSize)%currentRegionSize;
		downRoll = (downRoll + currentRegionSize)%currentRegionSize;
		returning.add(b.getSpacesInRegion(s.getRegion())[upRoll]);
		returning.add(b.getSpacesInRegion(s.getRegion())[downRoll]);
		//TODO special movement cases
		
		return null;
	}
}

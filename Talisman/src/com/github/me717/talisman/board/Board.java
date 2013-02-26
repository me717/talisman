package com.github.me717.talisman.board;

/**
 * Contains arrays for each region
 * @author Albert
 *
 */
public class Board {
	
	public static final int OUTER_REGION_SIZE = 24;
	public static final int MIDDLE_REGION_SIZE = 16;
	public static final int INNER_REGION_SIZE = 8;
	
	private Space[] outerRegion, middleRegion, innerRegion;
	
	public Board(){
		outerRegion = new Space[OUTER_REGION_SIZE];
		
		for(int i = 0; i< OUTER_REGION_SIZE;i++){
			outerRegion[i] = new Space(i, Space.Region.OUTER);
		}
		for(int i = 0; i< MIDDLE_REGION_SIZE;i++){
			middleRegion[i] = new Space(i, Space.Region.MIDDLE);
		}
		for(int i = 0; i< INNER_REGION_SIZE;i++){
			innerRegion[i] = new Space(i, Space.Region.INNER);
		}
	}
	
	public Space[] getSpacesInRegion(Space.Region r){
		if(r == Space.Region.OUTER){
			return outerRegion;
		}else if(r == Space.Region.MIDDLE ){
			return middleRegion;
		}else{
			return innerRegion;
		}
	}
}

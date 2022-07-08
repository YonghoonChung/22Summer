//gggggss
public class FourDetection {
	int[][] endPoint = new int[8][2];

	boolean[][] otherColor; 
	int count[] = new int[8];
	static boolean[] diffColor = new boolean[8];

	public void checkFourDetection(int x, int y) {
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 6; j++) {
//				otherColor[i][j] = false;
//			}
//			diffColor[i] = false;
//		}
		otherColor = new boolean [8][6];

//		if (checkHorizontal(x, y) > 2) {
//			if(GamePanel.bwMatrix[x][y] == 1) {
//				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.black2;
//				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.black2;				
//			}
//			else if(GamePanel.bwMatrix[x][y] == 2) {
//				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.white2;
//				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.white2;				
//			}
//		}
//		if (checkVertical(x, y) > 2) {
//			if(GamePanel.bwMatrix[x][y] == 1) {
//				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.black2;
//				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.black2;				
//			}
//			else if(GamePanel.bwMatrix[x][y] == 2) {
//				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.white2;
//				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.white2;				
//			}
//		}
//		if (checkDiagonal(x, y) > 2) {
//			if(GamePanel.bwMatrix[x][y] == 1) {                                              
//				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.black2;
//				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.black2;
//			}               
//			else if(GamePanel.bwMatrix[x][y] == 2) {                                         
//				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.white2;
//				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.white2;
//			}   
//		}
//		if (checkReverseDiagonal(x, y) > 2) {
//			if(GamePanel.bwMatrix[x][y] == 1) {                                              
//				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.black2;
//				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.black2;
//			}    
//			else if(GamePanel.bwMatrix[x][y] == 2) {                                         
//				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.white2;
//				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.white2;
//			}  
//		}

		if (checkHorizontal(x, y) > 3) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.black3;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.black3;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.white3;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.white3;
			}
		}
		if (checkVertical(x, y) > 3) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.black3;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.black3;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.white3;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.white3;
			}
		}
		if (checkDiagonal(x, y) > 3) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.black3;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.black3;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.white3;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.white3;
			}
		}
		if (checkReverseDiagonal(x, y) > 3) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.black3;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.black3;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.white3;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.white3;
			}
		}

		if (checkHorizontal(x, y) > 4) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.blackWeight;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.whiteWeight;
			}
		}
		if (checkVertical(x, y) > 4) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.blackWeight;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.whiteWeight;
			}
		}
		if (checkDiagonal(x, y) > 4) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.blackWeight;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.whiteWeight;
			}
		}
		if (checkReverseDiagonal(x, y) > 4) {
			if (GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.blackWeight;
			} else if (GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.whiteWeight;
			}                                                                                
		}
		
//		if(((Memory.points.size() + 1) / 2) % 2 == 0) {
//			checkSixRight(x, y, GamePanel.bwMatrix[x][y], count[2]);
//			checkSixLeft(x, y, GamePanel.bwMatrix[x][y], count[6]);
//			checkSixUp(x, y, GamePanel.bwMatrix[x][y], count[0]);
//			checkSixDown(x, y, GamePanel.bwMatrix[x][y], count[4]);
//			checkSixLRD(x, y, GamePanel.bwMatrix[x][y], count[3]);
//			checkSixULU(x, y, GamePanel.bwMatrix[x][y], count[7]);
//			checkSixURU(x, y, GamePanel.bwMatrix[x][y], count[1]);
//			checkSixLLD(x, y, GamePanel.bwMatrix[x][y], count[5]);
//		}
		
		
		
//		for (int i = 0; i < otherColor.length; i++) {
//			System.out.print(i + "방향 :" );
//			for (int j = 0; j < otherColor[0].length; j++) {
//				System.out.print(otherColor[i][j]+"\t");
//			}
//			System.out.println();
//		}
//		System.out.println("___________________________________");
//		checkSixLeft(x, y, GamePanel.bwMatrix[x][y], count[6]);
		
		
	}

	// vertical check
	private int checkVertical(int x, int y) {
		return checkUp(x, y, GamePanel.bwMatrix[x][y]) + checkDown(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkUp(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[4][0] = x;
				endPoint[4][1] = y;
				return 0;
			}
			return checkUp(x, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkDown(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[0][0] = x;
				endPoint[0][1] = y;
				return 0;
			}
			return checkDown(x, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	// Horizontal check
	private int checkHorizontal(int x, int y) {
		return checkLeft(x, y, GamePanel.bwMatrix[x][y]) + checkRight(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkRight(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[2][0] = x;
				endPoint[2][1] = y;
				return 0;
			}
			return checkRight(x + 1, y, stoneColor) + 1;

		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkLeft(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {

				endPoint[6][0] = x;
				endPoint[6][1] = y;
				return 0;
			}
			return checkLeft(x - 1, y, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkDiagonal(int x, int y) {
		return checkULU(x, y, GamePanel.bwMatrix[x][y]) + checkLRD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLRD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[3][0] = x;
				endPoint[3][1] = y;
				return 0;
			}
			return checkLRD(x + 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkULU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {

				endPoint[7][0] = x;
				endPoint[7][1] = y;
				return 0;
			}
			return checkULU(x - 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	// ReverseDiagonal check
	private int checkReverseDiagonal(int x, int y) {
		return checkURU(x, y, GamePanel.bwMatrix[x][y]) + checkLLD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLLD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[5][0] = x;
				endPoint[5][1] = y;
				return 0;
			}
			return checkLLD(x - 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkURU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[1][0] = x;
				endPoint[1][1] = y;
				return 0;
			}
			return checkURU(x + 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	/////////////////////////////////////////////////////////////////////

	private int checkSixUp(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[0][count] = true;
			diffColor[0] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixUp(x, y - 1, stoneColor, count + 1);
	}
	private int checkSixURU(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[1][count] = true;
			diffColor[1] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixURU(x + 1, y - 1, stoneColor, count + 1);
	}
	private int checkSixRight(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			System.out.println("확인 : " + x + " " + y);
			otherColor[2][count] = true;
			diffColor[2] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixRight(x + 1, y, stoneColor, count + 1);
	}


	private int checkSixLRD(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[3][count] = true;
			diffColor[3] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixLRD(x + 1, y + 1, stoneColor, count + 1);
	}

	private int checkSixDown(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			System.out.println("hello");
			otherColor[4][count] = true;
			diffColor[4] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixDown(x, y + 1, stoneColor, count + 1);
	}

	private int checkSixLLD(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[5][count] = true;
			diffColor[5] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixLLD(x - 1, y + 1, stoneColor, count + 1);
	}

	private int checkSixLeft(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[6][count] = true;
			diffColor[6] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixLeft(x - 1, y, stoneColor, count + 1);
	}
	private int checkSixULU(int x, int y, int stoneColor, int count) {
		if (GamePanel.bwMatrix[x][y] != stoneColor && GamePanel.bwMatrix[x][y] != -1) {
			otherColor[7][count] = true;
			diffColor[7] = true;
		}
		if (count > 4) {
			return 0;
		}
		return checkSixULU(x - 1, y - 1, stoneColor, count + 1);
	}

	

	
}

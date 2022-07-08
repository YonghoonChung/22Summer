import java.awt.Color;

import java.awt.Font;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Calculation {
	Memory decisionPoints = new Memory();
	WinnerDetection winDetection = new WinnerDetection();
	Stones stone;
	static int[][] weight = new int[19][19];
	static int[][] weightStatus = new int[19][19]; // 없으면 0이고,검은색 1, 흰색 2, 착수 3
	FourDetection fourDetection = new FourDetection();
	final static int blackStone = 200;
	final static int whiteStone = 300;
	final static int blockStone = 400;
	final static int blackWeight = -100;
	final static int whiteWeight = -50;
	final static int black2 = -20;
	final static int black3 = -30;
	final static int white2 = -15;
	final static int white3 = -25;
	int decisionCount;

	public Calculation() {
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				weight[i][j] = 0;
				weightStatus[i][j] = 0;
			}
	}

	public void doCalculation() 
	
	{

		int min = 100000000;
		int x = Memory.points.get(Memory.points.size() - 1).i;
		int y = Memory.points.get(Memory.points.size() - 1).j;
		boolean[] check = new boolean[8];

		if (Memory.points.size() <= Frame.blockCount) {
			weightStatus[x][y] = 3;
			weight[x][y] = blockStone;
		} else {
			try {
				fourDetection.checkFourDetection(x, y);
				if (Memory.points.get(Memory.points.size() - 1).color == Color.BLACK) { // 검은색

					System.out.println("------------------");
					for (int i = 1; i < 6; i++) {
						if (y - i >= 0) {
							if (FourDetection.diffColor[0]) {
								weight[x][y - i] = 0;
							} else {
								weight[x][y - i] += -1;
							}
						}
						if (x + i <= 18 && y - i >= 0) {
							if (FourDetection.diffColor[1]) {
								weight[x + i][y - i] = 0;
							} else {
								weight[x + i][y - i] += -1;
							}
						}
						if (x + i <= 18) {
							if (FourDetection.diffColor[2]) {
								weight[x + i][y] = 0;
							} else {
								weight[x + i][y] += -1;
							}
						}
						if (x + i <= 18 && y + i <= 18) {
							if (FourDetection.diffColor[3]) {
								weight[x + i][y + i] = 0;
							} else {
								weight[x + i][y + i] += -1;
							}
						}
						if (y + i <= 18) {
							if (FourDetection.diffColor[4]) {
								weight[x][y + i] = 0;
							} else {
								weight[x][y + i] += -1;
							}
						}

						if (x - i >= 0 && y + i <= 18) {
							if (FourDetection.diffColor[5]) {
								weight[x - i][y + i] = 0;
							} else {
								weight[x - i][y + i] += -1;
							}
						}

						if (x - i >= 0) {
							if (FourDetection.diffColor[6]) {
								weight[x - i][y] = 0;
							} else {
								weight[x - i][y] += -1;
							}
						}

						if (x - i >= 0 && y - i >= 0) {
							if (FourDetection.diffColor[7]) {
								weight[x - i][y - i] = 0;
							} else {
								weight[x - i][y - i] += -1;
							}
						}

					}
					weightStatus[x][y] = 1;
					weight[x][y] = blackStone;

				} else if (Memory.points.get(Memory.points.size() - 1).color == Color.WHITE) {// 흰색
					for (int i = 1; i < 6; i++) {
						if (y - i >= 0) {
							if (FourDetection.diffColor[0]) {
								weight[x][y - i] = 0;
							} else {
								weight[x][y - i] += 1;
							}
						}
						if (x + i <= 18 && y - i >= 0) {
							if (FourDetection.diffColor[1]) {
								weight[x + i][y - i] = 0;
							} else {
								weight[x + i][y - i] += 1;
							}
						}
						if (x + i <= 18) {
							if (FourDetection.diffColor[2]) {
								weight[x + i][y] = 0;
							} else {
								weight[x + i][y] += 1;
							}
						}
						if (x + i <= 18 && y + i <= 18) {
							if (FourDetection.diffColor[3]) {
								weight[x + i][y + i] = 0;
							} else {
								weight[x + i][y + i] += 1;
							}
						}
						if (y + i <= 18) {
							if (FourDetection.diffColor[4]) {
								weight[x][y + i] = 0;
							} else {
								weight[x][y + i] += 1;
							}
						}

						if (x - i >= 0 && y + i <= 18) {
							if (FourDetection.diffColor[5]) {
								weight[x - i][y + i] = 0;
							} else {
								weight[x - i][y + i] += 1;
							}
						}

						if (x - i >= 0) {
							if (FourDetection.diffColor[6]) {
								weight[x - i][y] = 0;
							} else {
								weight[x - i][y] += 1;
							}
						}

						if (x - i >= 0 && y - i >= 0) {
							if (FourDetection.diffColor[7]) {
								weight[x - i][y - i] = 0;
							} else {
								weight[x - i][y - i] += 1;
							}
						}
					}
					weightStatus[x][y] = 2;
					weight[x][y] = whiteStone;
				}

//				fourDetection.checkFourDetection(x, y);

				if (Memory.points.size() - Frame.blockCount > 2) {
					for (int i = 0; i < 19; i++) {
						for (int j = 0; j < 19; j++) {
							if (weightStatus[i][j] == 1) {
								weight[i][j] = blackStone;
							} else if (weightStatus[i][j] == 2) {
								weight[i][j] = whiteStone;
							} else if (weightStatus[i][j] == 3) {
								weight[i][j] = blockStone;
							}
							if (weight[i][j] <= blackWeight + 5 && weight[i][j] >= blackWeight - 5)
								weight[i][j] = blackWeight;
							if (weight[i][j] <= whiteWeight + 5 && weight[i][j] >= whiteWeight - 5)
								weight[i][j] = whiteWeight;
							if (min > weight[i][j]) {
								min = weight[i][j];
							}

						}
					}
				}

				min = weightSelect(min);
				
				
				decisionCount = 0;

				for (int i = 0; i < weight.length; i++) {
					for (int j = 0; j < weight.length; j++) {
						if (min == weight[i][j]) {
							decisionCount++;
							Memory.decisionPoints.add(new Stones(i, j, Color.BLACK, false));

//							System.out.println(decisionCount + " 가능한 곳 " + (char) (i + 65) + " " + j);
//							System.out.println((char) (i + 65) + " " + j);
							System.out.println(i + " " + j);
						}
					}
				}
				System.out.println("계산 가능한 곳 : " + decisionCount);
				if (decisionCount < 3) {
					if(Frame.swap) {
						if(decisionCount == 0) {
							Random random = new Random();
							int i = random.nextInt(18);
							int j = random.nextInt(18);
							Memory.points.add(new Stones(i, j, Color.BLACK, false));
						}else if (((Memory.points.size() + 1 - Frame.blockCount) / 2) % 2 == 1) {
//							while (!Memory.decisionPoints.isEmpty()) {
//							if(Memory.points.peek().color == Color.BLACK) {
//								System.out.println("메모리에 들어있는 값 : " + Memory.points.peek().i + " " + Memory.points.peek().j);
								x = Memory.decisionPoints.peek().i;
								y = Memory.decisionPoints.peek().j;
								weight[x][y] = blackStone;
//								
								for (int i = 1; i < 6; i++) {
									if (y - i >= 0)
										weight[x][y - i] += -1;
									if (x + i <= 18 && y - i >= 0)
										weight[x + i][y - i] += -1;
									if (x + i <= 18)
										weight[x + i][y] += -1;
									if (x + i <= 18 && y + i <= 18)
										weight[x + i][y + i] += -1;
									if (y + i <= 18)
										weight[x][y + i] += -1;
									if (x - i >= 0 && y + i <= 18)
										weight[x - i][y + i] += -1;
									if (x - i >= 0)
										weight[x - i][y] += -1;
									if (x - i >= 0 && y - i >= 0)
										weight[x - i][y - i] += -1;
								}
								GamePanel.bwMatrix[x][y] = 1;
//							}
								Memory.points.add(Memory.decisionPoints.pop());
//							}
						}
					}else {
						if (((Memory.points.size() + 1 - Frame.blockCount) / 2) % 2 == 0) {
							if(decisionCount == 0) {
								Random random = new Random();
								int i = random.nextInt(18);
								int j = random.nextInt(18);
								Memory.points.add(new Stones(i, j, Color.BLACK, false));
							}else {
								
//								while (!Memory.decisionPoints.isEmpty()) {
//							if(Memory.points.peek().color == Color.BLACK) {
//								System.out.println("메모리에 들어있는 값 : " + Memory.points.peek().i + " " + Memory.points.peek().j);
									x = Memory.decisionPoints.peek().i;
									y = Memory.decisionPoints.peek().j;
									weight[x][y] = blackStone;
//								
									for (int i = 1; i < 6; i++) {
										if (y - i >= 0)
											weight[x][y - i] += -1;
										if (x + i <= 18 && y - i >= 0)
											weight[x + i][y - i] += -1;
										if (x + i <= 18)
											weight[x + i][y] += -1;
										if (x + i <= 18 && y + i <= 18)
											weight[x + i][y + i] += -1;
										if (y + i <= 18)
											weight[x][y + i] += -1;
										if (x - i >= 0 && y + i <= 18)
											weight[x - i][y + i] += -1;
										if (x - i >= 0)
											weight[x - i][y] += -1;
										if (x - i >= 0 && y - i >= 0)
											weight[x - i][y - i] += -1;
									}
									GamePanel.bwMatrix[x][y] = 1;
									Memory.points.add(Memory.decisionPoints.pop());
								}
//							}
						}
					}

				}

				Memory.decisionPoints.clear();

//				for (int i = 0; i < Memory.points.size(); i++) {
//					System.out.println(i+1+" "+(char) (65 + Memory.points.get(i).i) + " " + Memory.points.get(i).j);
//				}			
//				int n = (int)Math.random()*(1) + (-5);

				for (int i = 0; i < 19; i++) {
					for (int j = 0; j < 19; j++) {
						System.out.print("|" + weight[j][i] + "\t");
//						System.out.print("|" + GamePanel.bwMatrix[j][i] + "\t");
					}
					System.out.println("|");
				}

				System.out.println("---------------------------------------------------");

			} catch (IndexOutOfBoundsException e) {
			}
		}

	}

	public int weightSelect(int min) {
		int x = Memory.points.get(Memory.points.size() - 1).i;
		int y = Memory.points.get(Memory.points.size() - 1).j;
		if (((Memory.points.size() + 1  - Frame.blockCount) / 2) % 2 == 0 || (Memory.points.size() - Frame.blockCount) == 3) {
			System.out.println(min);
			for (int i = 1; i < 6; i++) {

				if (weight[x][y - i] == min) {
					weight[x][y - i] = min - 10;
					return min - 10;
				}
				if (weight[x + i][y - i] == min) {
					weight[x + i][y - i] = min - 10;
					return min - 10;
				}
				if (weight[x + i][y] == min) {
					weight[x + i][y] = min - 10;
					return min - 10;
				}
				if (weight[x + i][y + i] == min) {
					weight[x + i][y + i] = min - 10;
					return min - 10;
				}
				if (weight[x][y + i] == min) {
					weight[x][y + i] = min - 10;
					return min - 10;
				}
				if (weight[x - i][y + i] == min) {
					weight[x - i][y + i] = min - 10;
					return min - 10;
				}
				if (weight[x - i][y] == min) {
					weight[x - i][y] = min - 10;
					return min - 10;
				}
				if (weight[x - i][y - i] == min) {
					weight[x - i][y - i] = min - 10;
					return min - 10;
				}
			}
			if (weight[x + 1][y - 2] == min) {
				weight[x + 1][y - 2] = min - 10;
				return min - 10;
			}
			if (weight[x + 2][y - 1] == min) {
				weight[x + 2][y - 1] = min - 10;
				return min - 10;
			}
			if (weight[x + 2][y + 1] == min) {
				weight[x + 2][y + 1] = min - 10;
				return min - 10;
			}
			if (weight[x + 1][y + 2] == min) {
				weight[x + 1][y + 2] = min - 10;
				return min - 10;
			}
			if (weight[x - 2][y + 1] == min) {
				weight[x - 2][y + 1] = min - 10;
				return min - 10;
			}
			if (weight[x - 1][y + 2] == min) {
				weight[x - 1][y + 2] = min - 10;
				return min - 10;
			}
			if (weight[x - 1][y - 2] == min) {
				weight[x - 1][y - 2] = min - 10;
				return min - 10;
			}
			if (weight[x - 2][y - 1] == min) {
				weight[x - 2][y - 1] = min - 10;
				return min - 10;
			}
		}

		return min;
	}
}

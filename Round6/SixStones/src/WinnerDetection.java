import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class WinnerDetection {

	private boolean win;
	private boolean Bwin;
	private boolean Wwin;
	private int result = 0;

	public int detectWinner(int x, int y) {

		if (checkHorizontal(x, y) > 6) {
			return GamePanel.bwMatrix[x][y];
		}
		if (checkVertical(x, y) > 6) {
			return GamePanel.bwMatrix[x][y];
		}
		if (checkDiagonal(x, y) > 6) {
			return GamePanel.bwMatrix[x][y];
		}
		if (checkReverseDiagonal(x, y) > 6) {
			return GamePanel.bwMatrix[x][y];
		}
		return 0;
	}

	private int checkHorizontal(int x, int y) {
		return checkLeft(x, y, GamePanel.bwMatrix[x][y]) + checkRight(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkRight(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkRight(x + 1, y, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkLeft(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkLeft(x - 1, y, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkVertical(int x, int y) {
		return checkUp(x, y, GamePanel.bwMatrix[x][y]) + checkDown(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkDown(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkDown(x, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkUp(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkUp(x, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkDiagonal(int x, int y) {
		return checkULU(x, y, GamePanel.bwMatrix[x][y]) + checkLRD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLRD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkLRD(x + 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkULU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkULU(x - 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkReverseDiagonal(int x, int y) {
		return checkURU(x, y, GamePanel.bwMatrix[x][y]) + checkLLD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLLD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkLLD(x - 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkURU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor)
				return 0;
			return checkURU(x + 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

}

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class myShape {
	private boolean[][] visited;
	private int[][] myShapeArray;

	// constructor
	public myShape(int[][] myShapeArray) {
		this.setMyShapeArray(myShapeArray);
		int row = myShapeArray.length;
		int column = myShapeArray[0].length;
		visited = new boolean[row][column];
	}

	public int[][] getMyShapeArray() {
		return myShapeArray;
	}

	public void setMyShapeArray(int[][] myShapeArray) {
		this.myShapeArray = myShapeArray;
	}

	public class Limit {
		private int mr, ml, md, mu;

		public Limit(ArrayList<Pixel> myPixels) {
			this.mr = findMaxRight(myPixels);
			this.ml = findMaxLeft(myPixels, mr);
			this.md = findMaxDown(myPixels);
			this.mu = findMaxUp(myPixels, md);
		}

		public int getMaxRight() {
			return mr;
		}

		public int getMaxLeft() {
			return ml;
		}

		public int getMaxUp() {
			return mu;
		}

		public int getMaxDown() {
			return md;
		}
	}

	public class Pixel {
		private int row;
		private int column;
		private char direction;	// direction 1: up, 2: right, 3: down, 4: left

		public Pixel(int row, int column, char direction) { // dir change to case
			this.row = row;
			this.column = column;
			this.direction = direction;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}

		public char getDir() {
			return direction;
		}
	}

	// displays an array of pixels, command is either 'c' or 's' 
	public void showArray(int[][] myArray, char command) {
		int row = myArray.length;
		int column = myArray[0].length;
		int x = 0, value;
		String myStringRepOfInt;

		for (x = 0; x < row; x++) {
			myStringRepOfInt = String.format("%02d", x);
			System.out.print(myStringRepOfInt + " ");
			for (int y = 0; y < column; y++) {
				value = myArray[x][y];
				if (command == 'c') {
					System.out.print(x + "/" + y + ": " + value + "\t");
				} else if (command == 's') {
					if (value == 1) {
						System.out.print("* ");
					} else {
						System.out.print("  ");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// returns the x and y coordinates and visited attribute as a Pair object 
	// for the first found black pixel
	public Pixel getFirstBlackPixel(int[][] myArray) {
		int row = myArray.length;
		int column = myArray[0].length;
		Pixel myPixel = null;
		boolean myBreak = false;

		// System.out.println("getFirstBlackPixel(): " + row + ":" + column);
		// row is y; column is x

		for (int y = 1; y < row && myBreak == false; y++) {
			for (int x = 1; x < column; x++) {
				// System.out.println(myArray[y][x]);
				if (myArray[y][x] == 1) {
					myPixel = new Pixel(y, x, 'R');
					visited[y][x] = true;
					myBreak = true;
					break;
				}
			}
		}
		// System.out.println("getFirstBlackPixel(): " + myPixel.row + ":" + myPixel.column + ":" + myPixel.direction);
		return myPixel;
	}

	// 8-cases-search
	public Pixel getNextBlackPixel(Pixel myPixel, int[][] myArray) {

		int row = myPixel.row;
		int column = myPixel.column;
		char dir = myPixel.direction;
		int up, right, down, left, up_right, down_right, down_left, up_left;

		up = myArray[row - 1][column];
		up_right = myArray[row - 1][column + 1];
		right = myArray[row][column + 1];
		down_right = myArray[row + 1][column + 1];
		down = myArray[row + 1][column];
		down_left = myArray[row + 1][column - 1];
		left = myArray[row][column - 1];
		up_left = myArray[row - 1][column - 1];
		// System.out.println("left:" + left + " upper:" + upper + " right:" + right + " down:" + down);

		switch (dir) {
		case 'D':
			// up -> up-right 'C' -> right -> down-right 'E' -> down -> down-left 'A' -> left -> up-left 'B'
			if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else {
				myPixel = null;
			}
			break;

		case 'A':	// LD
			// up-right 'C' -> right -> down-right 'E' -> down -> down-left 'A' -> left -> up-left 'B' -> up
			if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else {
				myPixel = null;
			}
			break;

		case 'L':
			// right -> down-right 'E' -> down -> down-left 'A' -> left -> up-left 'B' -> up -> up-right 'C'
			if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else {
				myPixel = null;
			}
			break;

		case 'B':	// LU
			//	down-right 'E' -> down -> down-left 'A' -> left -> up-left 'B' -> up -> up-right 'C' -> right
			if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else {
				myPixel = null;
			}
			break;

		case 'U':
			//		case 'U':
			//		down
			//		down-left 'A'
			//		left
			//		up-left 'B'
			//		up
			//		up-right 'C'
			//		right
			//		down-right 'E'
			//				System.out.println(row + ":" + (column + 1) + " " + visited[row][column + 1]);

			if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else {
				myPixel = null;
			}
			break;

		case 'C':
			//		case 'RU': 'C'
			//		down-left 'A'
			//		left
			//		up-left 'B'
			//		up
			//		up-right 'C'
			//		right
			//		down-right 'E'
			//		down
			if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else {
				myPixel = null;
			}
			break;

		case 'R':
			//		case 'R':
			//		left
			//		up-left 'B'
			//		up
			//		up-right 'C'
			//		right
			//		down-right 'E'
			//		down
			//		down-left 'A'

			if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else {
				myPixel = null;
			}
			break;

		case 'E':

			//		case 'RD': 'E'
			//		up-left 'B'
			//		up
			//		up-right 'C'
			//		right
			//		down-right 'E'
			//		down
			//		down-left 'A'
			//		left
			if ((up_left == 1) && (!visited[row - 1][column - 1])) {
				myPixel = new Pixel(row - 1, column - 1, 'B');
			} else if ((up == 1) && (!visited[row - 1][column])) {
				myPixel = new Pixel(row - 1, column, 'U');
			} else if ((up_right == 1) && (!visited[row - 1][column + 1])) {
				myPixel = new Pixel(row - 1, column + 1, 'C');
			} else if ((right == 1) && (!visited[row][column + 1])) {
				myPixel = new Pixel(row, column + 1, 'R');
			} else if ((down_right == 1) && (!visited[row + 1][column + 1])) {
				myPixel = new Pixel(row + 1, column + 1, 'E');
			} else if ((down == 1) && (!visited[row + 1][column])) {
				myPixel = new Pixel(row + 1, column, 'D');
			} else if ((down_left == 1) && (!visited[row + 1][column - 1])) {
				myPixel = new Pixel(row + 1, column - 1, 'A');
			} else if ((left == 1) && (!visited[row][column - 1])) {
				myPixel = new Pixel(row, column - 1, 'L');
			} else {
				myPixel = null;
			}
			break;

		default:
			System.out.println("no case");
			myPixel = null;
			break;
		}
		// System.out.println("getNextBlackPixel(): " + myPixel.direction + " " + myPixel.row + ":" + myPixel.column);
		if (myPixel != null) {
			visited[myPixel.row][myPixel.column] = true;
		}
		return myPixel;
	}

	public void prepare(int[][] myArray) {

		int row = myArray.length;
		int column = myArray[0].length;
		// System.out.println("prepare(): rows: " + row + " columns: " + column);
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < column; y++) {
				visited[x][y] = false;
			}
		}
	}

	// returns the column number for a '1' located on the leftest column in myArray
	private int findMaxLeft(ArrayList<Pixel> elements, int maxRight) {
		Pixel myElement;
		int column, index;
		int elSize = elements.size();
		//		int previousY = findMaxRight(elements);
		int previousY = maxRight;

		for (index = 0; index < elSize; index++) {
			myElement = elements.get(index);
			column = myElement.column;
			if (column == 0) {
				continue;
			} else {
				if (column < previousY) {
					previousY = column;		// overwrite it;	
				}
			}
		}
		return previousY;
	}

	// returns the column number for a '1' located on the rightest column in myArray
	private int findMaxRight(ArrayList<Pixel> elements) {
		Pixel myElement;
		int column, index;
		int previousY = 0;
		int elSize = elements.size();

		for (index = 0; index < elSize; index++) {
			myElement = elements.get(index);
			column = myElement.column;
			if (column == 0) {
				continue;		// skip over
			} else {
				if (column > previousY) {
					previousY = column;		// overwrite it;	
				}
			}
		}
		return previousY;
	}

	// returns the row number for a '1' located on the highest row in myArray
	private int findMaxUp(ArrayList<Pixel> elements, int maxDown) {
		Pixel myElement;
		int row, index;
		int previousX = maxDown;
		int elSize = elements.size();

		for (index = 0; index < elSize; index++) {
			myElement = elements.get(index);
			row = myElement.row;
			if (row == 0) {
				continue;
			} else {
				if (row < previousX) {
					previousX = row;		// overwrite it;	
				}
			}
		}
		return previousX;
	}

	// returns the row number for a '1' located on the lowest row in myArray	
	private int findMaxDown(ArrayList<Pixel> elements) {
		Pixel myElement;
		int row, index;
		int previousX = 0;
		int elSize = elements.size();

		for (index = 0; index < elSize; index++) {
			myElement = elements.get(index);
			row = myElement.row;
			if (row == 0) {
				continue;
			} else {
				if (row > previousX) {
					previousX = row;		// overwrite it;	
				}
			}
		}
		return previousX;
	}

	private void copyArray(int[][] fromArray, int[][] toArray, int ml, int mr, int mu, int md) {
		for (int x = mu; x <= md; x++) {
			for (int y = ml; y <= mr; y++) {
				// System.out.println(x + ":" + y);
				toArray[x - mu + 1][y - ml + 1] = fromArray[x][y];
			}
		}
	}

	private void deleteFromArray(int[][] myArray, int ml, int mu, int mr, int md) {
		for (int x = mu; x <= md; x++) {
			for (int y = ml; y <= mr; y++) {
				myArray[x][y] = 0;
			}
		}
	}

	// obs: a1 and a2 have different sizes!
	private boolean compareArrays(int[][] a1, int[][] a2) {
		int row1 = a1.length;
		int column1 = a1[0].length;
		int row2 = a2.length;
		int column2 = a2[0].length;

		// check if both size is the same
		if (((row2 == row1) && (row2 == row1)) && ((column2 == column1) && (column2 == column1))) {
			for (int x = 0; x < row1; x++) {
				for (int y = 0; y < column1; y++) {
					if (a1[x][y] != a2[x][y]) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	// here we recognize the shape
	private int[][] getShape(int[][] myArray) {
		ArrayList<Pixel> myPixels = new ArrayList<Pixel>();

		long startTime, endTime;
		startTime = System.nanoTime(); 	// The current value of the system timer, in nanoseconds.

		Pixel myPixel = getFirstBlackPixel(myArray);
		if (myPixel == null) {
//			System.out.println("myPixel is null.");
			return null;
		}
		myPixels.add(myPixel);

		Pixel myFirstPixel = myPixel;	// save the first pixel
		Pixel myNextPixel = null;

		myNextPixel = getNextBlackPixel(myPixel, myArray);

		myPixels.add(myNextPixel);
		while (myNextPixel != null) {
			myNextPixel = getNextBlackPixel(myNextPixel, myArray);

			if (myNextPixel != null) {
				myPixels.add(myNextPixel);
				// test up
				if ((myNextPixel.row - 1) == myFirstPixel.row && (myNextPixel.column == myFirstPixel.column)) {
					break;
				}
				// test up-right
				if ((myNextPixel.row - 1) == myFirstPixel.row && ((myNextPixel.column + 1) == myFirstPixel.column)) {
					break;
				}
			}
		}
		// end of shape recognizing
		endTime = System.nanoTime();
		System.out.println(endTime - startTime + " ns");	// in nanoseconds.

		// find the limits and make a copy of array
		Limit myLimits = findLimits(myPixels);
		int ml = myLimits.getMaxLeft();
		int mr = myLimits.getMaxRight();
		int mu = myLimits.getMaxUp();
		int md = myLimits.getMaxDown();

		int[][] oneShapeArray = new int[md - mu + 3][mr - ml + 3];		// add surrounded empty pixels
		//		System.out.println("row/column: " + oneShapeArray.length + "/" + oneShapeArray[0].length);

		copyArray(myArray, oneShapeArray, ml, mr, mu, md);
		//		System.out.println("black pixels: " + myPixels.size());
		deleteFromArray(myArray, ml, mu, mr, md);
		//		showArray(myArray, 's');

		return oneShapeArray;
	}

	private Limit findLimits(ArrayList<Pixel> myPixels) {
		Limit myLimits = new Limit(myPixels);

		return myLimits;
	}

	public static void main(String[] args) throws IOException {

		BufferedImage img = ImageIO.read(new File("c:/abbaba.bmp"));
		int width = img.getWidth();
		int height = img.getHeight();
		System.out.println(width + ":" + height);

		int[][] myArray = new int[height][width];

		for (int xPixel = 0; xPixel < img.getHeight(); xPixel++) {
			for (int yPixel = 0; yPixel < img.getWidth(); yPixel++) {
				int color = img.getRGB(yPixel, xPixel);
				if (color != Color.WHITE.getRGB()) {
					myArray[xPixel][yPixel] = 1;
				} else {
					myArray[xPixel][yPixel] = 0;
				}
			}
		}

		myShape shape = new myShape(myArray);
		shape.prepare(myArray);
		System.out.println("all at once:");
		// shape.showArray(myArray, 's');

		// the listOfShapes contains all characters each as a 2-dim-array.
		ArrayList<int[][]> listOfShapes = new ArrayList<int[][]>();
		int[][] myShape = null;

		do {
			myShape = shape.getShape(myArray);
			if (myShape != null) {
				listOfShapes.add(myShape);
				shape.showArray(myShape, 's');
			} else {
				break;
			}
		} while (true);

		int shapes = listOfShapes.size();
		System.out.println("listOfShapes: " + shapes);

		// here we compare the shapes with each other
		boolean rs;
		for (int x = 0; x < shapes; x++) {
			for (int y = x; y < shapes; y++) {
				if (x != y) {
					rs = shape.compareArrays(listOfShapes.get(x), listOfShapes.get(y));
					System.out.println("shape" + x + " && shape " + y + ": " + rs);
				}
			}
		}

		System.out.println("\n\nend of program.");
	}
}

// 0.8ms * 400 shapes = 320ms 

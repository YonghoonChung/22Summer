import java.awt.image.BufferedImage;
import java.awt.Color;

public class Blur{
	DeepCopy dCopy = new DeepCopy();
	private BufferedImage manipulatedImage;
	private BufferedImage resultImage;

	public static final String HORIZONTAL_FILTER = "Horizontal Filter";
	private static final double[][] FILTER_HORIZONTAL = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
	
	//{{ 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };



	public void doBlur() {
		manipulatedImage = dCopy.deepCopy(ImagePanel.image);
		if(ProcessedPanel.image == null) {
			resultImage = dCopy.deepCopy(ImagePanel.image);
		}else {
			resultImage = dCopy.deepCopy(ProcessedPanel.image);
		}

		double[][][] image = transformImageToArray(manipulatedImage);
		applyConvolution(manipulatedImage.getWidth(), manipulatedImage.getHeight(), image, FILTER_HORIZONTAL);
//		applyConvolution(manipulatedImage.getWidth(), manipulatedImage.getHeight(), image, FILTER_HORIZONTAL);
//		convertToImage(convolutedPixels);
	}

	private double[][][] transformImageToArray(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double[][][] image = new double[3][height][width];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color color = new Color(bufferedImage.getRGB(i, j));
				image[0][j][i] = color.getRed();
				image[1][j][i] = color.getGreen();
				image[2][j][i] = color.getBlue();
			}
		}
		return image;
	}

	private void applyConvolution(int width, int height, double[][][] image, double[][] filter) {
		double[][] redConv = convolution(image[0], filter);
		double[][] greenConv = convolution(image[1], filter);
		double[][] blueConv = convolution(image[2], filter);
		for (int i = 0; i < redConv.length; i++) {
			for (int j = 0; j < redConv[i].length; j++) {
				Color color = new Color(fixOutOfRangeRGBValues(redConv[i][j]),
						fixOutOfRangeRGBValues(greenConv[i][j]), fixOutOfRangeRGBValues(blueConv[i][j]));										
			resultImage.setRGB(j, i, color.getRGB());
			}
		}
		Frame.changedImage = resultImage;
		ProcessedPanel.image = resultImage;
		Frame.processedPanel.repaint();
	}

	private int fixOutOfRangeRGBValues(double value) {
		if (value < 0.0) {
//			value = -value;
			return 0;
		}
		if (value > 255) {
			return 255;
		} else {
			return (int) value;
		}
	}

	public static double[][] convolution(double[][] map, double[][] filter) {
		double[][] output = new double[map.length][map[0].length];
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				for (int i = 0; i < filter.length; i++) {
					for (int j = 0; j < filter[i].length; j++) {
						try {
							output[y][x] += map[y - i + 1][x - j + 1] * filter[j][i];
						} catch (ArrayIndexOutOfBoundsException e) {

						}
					}
				}
				output[y][x] = output[y][x]/9;
			}
		}
		return output;
//		}
	}

	private void convertToImage(double[][] convolutedPixels) {
		// TODO Auto-generated method stub
		for (int i = 0; i < convolutedPixels.length - 1; i++) {
			for (int j = 0; j < convolutedPixels[i].length - 1; j++) {
				Color color = new Color(fixOutOfRangeRGBValues(convolutedPixels[i][j]),
							fixOutOfRangeRGBValues(convolutedPixels[i][j]), fixOutOfRangeRGBValues(convolutedPixels[i][j]));										
				resultImage.setRGB(j, i, color.getRGB());
			}
		}
		Frame.changedImage = resultImage;
		ProcessedPanel.image = resultImage;
		Frame.processedPanel.repaint();
	}

}

//reference : https://medium.com/javarevisited/building-a-java-edge-detection-application-6147b68e5d79
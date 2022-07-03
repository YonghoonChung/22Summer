import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.swing.JPanel;

public class Convolution extends JPanel {
	DeepCopy dCopy = new DeepCopy();
	private BufferedImage manipulatedImage;
	private BufferedImage resultImage;
	private boolean edging = false;
	private boolean masking = false;

	public void setEdging(boolean edging) {
		this.edging = edging;
	}

	public void setMasking(boolean masking) {
		this.masking = masking;
	}

	public static final String HORIZONTAL_FILTER = "Horizontal Filter";
	private static final double[][] FILTER_HORIZONTAL = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
	
	//{{ 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };

	double[][] filter;

	public void doConvolute() {
		filter = FILTER_HORIZONTAL;
		manipulatedImage = dCopy.deepCopy(ImagePanel.image);
		resultImage = dCopy.deepCopy(ImagePanel.image);

		double[][][] image = transformImageToArray(manipulatedImage);
		double[][] convolutedPixels = applyConvolution(manipulatedImage.getWidth(), manipulatedImage.getHeight(), image,
				FILTER_HORIZONTAL);
		convertToImage(convolutedPixels);
	}

	private double[][][] transformImageToArray(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double[][][] image = new double[3][height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Color color = new Color(bufferedImage.getRGB(j, i));
				image[0][i][j] = color.getRed();
				image[1][i][j] = color.getGreen();
				image[2][i][j] = color.getBlue();
			}
		}
		return image;
	}

	private double[][] applyConvolution(int width, int height, double[][][] image, double[][] filter) {
		double[][] redConv = convolution(image[0], filter);
		double[][] greenConv = convolution(image[1], filter);
		double[][] blueConv = convolution(image[2], filter);
		double[][] finalConv = new double[redConv.length][redConv[0].length];
		for (int i = 0; i < redConv.length; i++) {
			for (int j = 0; j < redConv[i].length; j++) {
				finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
			}
		}
		return finalConv;
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
		int c = 0;
		double[][] output = new double[map.length][map[0].length];
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				for (int i = 0; i < filter.length; i++) {
					for (int j = 0; j < filter[i].length; j++) {
						try {
							output[y][x] += map[y - i + 1][x - j + 1] * filter[i][j];
							c++;
						} catch (ArrayIndexOutOfBoundsException e) {

						}
					}
				}
			}
		}
		return output;
//		}
	}

	private void convertToImage(double[][] convolutedPixels) {
		// TODO Auto-generated method stub
		for (int i = 0; i < convolutedPixels.length - 1; i++) {
			for (int j = 0; j < convolutedPixels[i].length - 1; j++) {
				Color color;
				if(masking) {
					color = new Color(fixOutOfRangeRGBValues(convolutedPixels[i][j]),
							fixOutOfRangeRGBValues(convolutedPixels[i][j]), fixOutOfRangeRGBValues(convolutedPixels[i][j]));										
				}else {
					color = new Color(255- fixOutOfRangeRGBValues(convolutedPixels[i][j]),
							255- fixOutOfRangeRGBValues(convolutedPixels[i][j]), 255 - fixOutOfRangeRGBValues(convolutedPixels[i][j]));					
				}
				resultImage.setRGB(j, i, color.getRGB());
			}
		}
		ProcessedPanel.image = resultImage;
		Frame.processedPanel.repaint();
	}

}

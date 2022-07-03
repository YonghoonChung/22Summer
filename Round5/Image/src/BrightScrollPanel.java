import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class BrightScrollPanel extends JPanel {
	JSlider slider = new JSlider();
	JButton confirmButton = new JButton("Confirm");
	private int imageHeight;
	private int imageWidth;
	public BufferedImage manipulatedImage;
	
	Graphics g;

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public BufferedImage deepCopy(BufferedImage image) {
		ColorModel colorModel = image.getColorModel();
		boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(image.getRaster().createCompatibleWritableRaster());

		return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
	}

	public void doFrame() {
		setBounds(0, 160, 160, 70);
		setBackground(Color.PINK);
		setOpaque(true);
		setLayout(null);
		setVisible(false);

		slider.setBounds(0, 3, 160, 30);
		slider.setMaximum(100);
		slider.setMinimum(-100);
		slider.setValue(0);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setFont(new Font("Arial", Font.BOLD, 10));

		confirmButton.setBounds(40, 35, 80, 30);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ImagePanel.image == null) {
					return;
				}
				System.out.println("test : 1");
				DeepCopy dCopy = new DeepCopy();
				if(ProcessedPanel.image != null) {
					manipulatedImage = dCopy.deepCopy(ProcessedPanel.image);					
				}else {					
					manipulatedImage = dCopy.deepCopy(ImagePanel.image);
				}
//				ProcessedPanel.image = ImagePanel.image;
				System.out.println("BSP : " + imageHeight + " " + imageWidth);
				Frame.processedPanel.setImageHeight(imageHeight);
				Frame.processedPanel.setImageWidth(imageWidth);
				System.out.println("test : 2");
				for (int y = 0; y < imageHeight; y++) {
					for (int x = 0; x < imageWidth; x++) {
						Color color = new Color(manipulatedImage.getRGB(x, y));

						int red = (int) (color.getRed() + slider.getValue());
						if (red > 255)
							red = 255;
						if (red < 0)
							red = 0;

						int green = (int) (color.getGreen() + slider.getValue());
						if (green > 255)
							green = 255;
						if (green < 0)
							green = 0;

						int blue = (int) (color.getBlue() + slider.getValue());
						if (blue > 255)
							blue = 255;
						if (blue < 0)
							blue = 0;

						manipulatedImage.setRGB(x, y, new Color(red, green, blue).getRGB());
					}
				}
				ProcessedPanel.image = manipulatedImage;
				Frame.processedPanel.repaint();
				System.out.println("test : 3");
			}

		});

		this.add(confirmButton);
		this.add(slider);
	}

}

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame {
	JFrame fr = new JFrame();
	ImagePanel imagePanel = new ImagePanel();
	static ProcessedPanel processedPanel = new ProcessedPanel();
	BrightScrollPanel brightScrollPanel = new BrightScrollPanel();
	Convolution convolution = new Convolution();
	DeepCopy dCopy = new DeepCopy();

	JPanel buttonPanel = new JPanel();
	JPanel convolutionPanel = new JPanel();
	JButton loadButton = new JButton("Load");
	JButton edgingButton = new JButton("Edging");
	JButton maskingButton = new JButton("masking");
	JButton grayScaleButton = new JButton("GrayScale");
	JButton brightButton = new JButton("BrightScale");
	JButton scopeButton = new JButton("Scope");
	JFileChooser fileChooser = new JFileChooser();
	BufferedImage img = null;

	public BufferedImage manipulatedImage;
	public static BufferedImage changedImage;

	int frameWidth = 1300;
	int frameHeight = 800;
	static int imageProcessSize = 550;
	int buttonPanelWidth = 80;
	int buttonWidth = 138;
	int buttonHeight = 70;
	int imageHeight;
	int imageWidth;

	private boolean brightShow = false;
	private boolean grayScale = false;
	private boolean edging = false;
	private boolean masking = false;
	private boolean scope = false;

	public void doFrame() {
		fr.setSize(frameWidth, frameHeight);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.LIGHT_GRAY);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);

		fr.setLayout(null);

		imagePanel.setFrameHeight(frameHeight);
		imagePanel.doFrame();

		processedPanel.setFrameHeight(frameHeight);
		processedPanel.setFrameWidth(frameWidth);
		processedPanel.setButtonPanelWidth(buttonPanelWidth);
		processedPanel.doFrame();

		fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "gif", "jpeg"));
		fileChooser.setMultiSelectionEnabled(false);
		loadButton.setBounds(20, 20, buttonWidth, buttonHeight);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brightScrollPanel.setVisible(false);
				brightShow = false;
				int ret = fileChooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					System.out.println(fileChooser.getSelectedFile());
					ImagePanel.image = ImageIO.read(fileChooser.getSelectedFile());
					imageHeight = ImagePanel.image.getHeight(null);
					imageWidth = ImagePanel.image.getWidth(null);
					imagePanel.setImageHeight(imageHeight);
					imagePanel.setImageWidth(imageWidth);
					imagePanel.repaint();
					brightScrollPanel.slider.setValue(0);
					processedPanel.repaint();
					changedImage = ImagePanel.image;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				imagePanel.imageColor = new Color[imageWidth][imageHeight];
//				Color color[][] = new Color[imageWidth][imageHeight];
//
//				for (int i = 0; i < imageHeight; i++)
//					for (int j = 0; j < imageWidth; j++)
//						color[j][i] = new Color(imagePanel.image.getRGB(j, i));
			}
		});

		buttonPanel.setBounds(frameWidth / 2 - buttonPanelWidth - 5, 100, buttonPanelWidth * 2, 450);
		buttonPanel.setBackground(Color.RED);
		buttonPanel.setOpaque(true);
		buttonPanel.setLayout(null);

		grayScaleButton.setBounds(10, 10, buttonWidth, buttonHeight);
		grayScaleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ImagePanel.image == null) {
					return;
				}
				grayScale = !grayScale;
				ProcessedPanel.image = ImagePanel.image;
				processedPanel.setImageHeight(imageHeight);
				processedPanel.setImageWidth(imageWidth);
				if (grayScale) {
					manipulatedImage = dCopy.deepCopy(ImagePanel.image);
					for (int y = 0; y < imageHeight; y++) {
						for (int x = 0; x < imageWidth; x++) {
							Color colour = new Color(manipulatedImage.getRGB(x, y));
							int Y = (int) (0.3 * colour.getRed() + 0.6 * colour.getGreen() + 0.1 * colour.getBlue());
							manipulatedImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
						}
					}
					changedImage = manipulatedImage;
					ProcessedPanel.image = manipulatedImage;
				} else {
					changedImage = ImagePanel.image;
				}
				processedPanel.repaint();
			}
		});
		brightButton.setBounds(10, 90, buttonWidth, buttonHeight);
		brightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brightScrollPanel.setImageHeight(imageHeight);
				brightScrollPanel.setImageWidth(imageWidth);
				if (!brightShow) {
					brightScrollPanel.setVisible(true);
					brightShow = true;
				} else {
					brightScrollPanel.setVisible(false);
					brightShow = false;
				}
			}
		});
		brightScrollPanel.setImageHeight(imageHeight);
		brightScrollPanel.setImageWidth(imageWidth);
		brightScrollPanel.doFrame();

		convolutionPanel.setBounds(10, 230, buttonWidth, buttonHeight);
		convolutionPanel.setLayout(new GridLayout(2, 1, 0, 0));
		edgingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ImagePanel.image == null) {
					return;
				}
				grayScale = false;
				edging = true;
				masking = false;
				convolution.setEdging(edging);
				convolution.setMasking(masking);
				processedPanel.setImageHeight(imageHeight);
				processedPanel.setImageWidth(imageWidth);
				convolution.doConvolute();
			}
		});
		maskingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ImagePanel.image == null) {
					return;
				}
				grayScale = false;
				edging = false;
				masking = true;
				convolution.setEdging(edging);
				convolution.setMasking(masking);
				processedPanel.setImageHeight(imageHeight);
				processedPanel.setImageWidth(imageWidth);
				convolution.doConvolute();
			}
		});
		scopeButton.setBounds(10, 370, buttonWidth, buttonHeight);
		scopeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!scope) {
					ProcessedPanel.image = ImagePanel.image;
					processedPanel.magnifiedPanel.setVisible(true);
					scope = true;
					processedPanel.setScope(scope);
					processedPanel.setImageHeight(imageHeight);
					processedPanel.setImageWidth(imageWidth);
				} else {
					ProcessedPanel.image = null;
					processedPanel.magnifiedPanel.setVisible(false);
					scope = false;
					processedPanel.setScope(scope);
				}
				processedPanel.repaint();
			}
		});

		convolutionPanel.add(edgingButton);
		convolutionPanel.add(maskingButton);
		buttonPanel.add(brightScrollPanel);
		buttonPanel.add(scopeButton);
		buttonPanel.add(brightButton);
		buttonPanel.add(grayScaleButton);
		buttonPanel.add(convolutionPanel);

		fr.add(loadButton);
		fr.add(imagePanel);
		fr.add(buttonPanel);
		fr.add(processedPanel);

		fr.setVisible(true);

	}
}

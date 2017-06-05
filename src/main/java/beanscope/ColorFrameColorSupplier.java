package beanscope;


import java.awt.Color;
import java.util.Random;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;

public class ColorFrameColorSupplier extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Supplier<Color> colorSupplier;
	public ColorFrameColorSupplier() {
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void showOnRandomPlace() {
		Random random = new Random();
		setLocation(random.nextInt(1200), random.nextInt(700));
		getContentPane().setBackground(colorSupplier.get());
		repaint();
	}
	
	
	
	

}

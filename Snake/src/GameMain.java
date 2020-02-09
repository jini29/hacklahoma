import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GameMain extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//size of the map
	protected static final int HEIGHT = 50;
	protected static final int WIDTH = 50;
	
	//size of the squares
	protected static final int HEIGHT_SQUARE = 20;
	protected static final int WIDE_SQUARE = 20;
	
	private JPanel panel1;
	
	private JButton startB = new JButton("START");
	private JButton diffB = new JButton("DIFFICULTY");
	private JButton exitB = new JButton("QUIT");
	
	private BufferedImage picture;
	
	private JLabel pic;
	
	private JLabel diffL = new JLabel("Choose Level 1-5");
	
	private JTextField diff = new JTextField("1");
	
	public GameMain() throws IOException{
	
		setFrame();
		
		setImage();
		
		setButton();
		
		setMusic();
		
		diffL.setBounds(190,320,115,25);
		diffL.setVisible(false);
		
		diff.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		diff.setBounds(195,350,90,18);
		diff.setVisible(false);
		
		panel1.add(startB);
		panel1.add(diffB);
		panel1.add(exitB);
		panel1.add(diffL);
		panel1.add(diff);
		
		panel1.add(pic);
		
		
		
		this.add(panel1);
		
		
	}


	private void setButton() {
		startB.setBounds(40,380,115,25);
		diffB.setBounds(180,380,115,25);
		exitB.setBounds(320,380,115,25);
		
		startB.setBackground(Color.WHITE);
		diffB.setBackground(Color.WHITE);
		exitB.setBackground(Color.WHITE);
		
		startB.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(diff.getText()) > 5 || Integer.valueOf(diff.getText()) < 1) {
				     JOptionPane.showMessageDialog(panel1, "Please Enter a Value From 1-5", "WARNING", JOptionPane.ERROR_MESSAGE);
				    }
				new Snake(Integer.valueOf(diff.getText()));
				
				
			
			} 
		 });
		
		diffB.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) { 
				diffL.setVisible(true);
				diff.setVisible(true);
				
				
				
			} 
		 });
		
		exitB.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
					System.exit(0);
			
			} 
		 });
		
	}

	private void setImage() throws IOException{
		picture = ImageIO.read(new File("snake.png"));
		ImageIcon icon = new ImageIcon(picture);
		pic = new JLabel(icon);
		pic.setBounds(0,0,500,500);
		
	}

	private void setFrame() {
		setTitle("Game Interface");
		setSize(500,500);
		panel1 = new JPanel(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public static void setMusic() {
		  try {
		  File musicFile = new File("oo.wav");
		  AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFile);
		   Clip clip = AudioSystem.getClip();
		   clip.open(audioIn);
		   clip.loop(Clip.LOOP_CONTINUOUSLY); 
		   

		  } catch (UnsupportedAudioFileException e) {
		          e.printStackTrace(); 
		     } catch (IOException e) {
		          e.printStackTrace();
		     } catch (LineUnavailableException e) {
		          e.printStackTrace();
		     }
		  
		 }

	public static void main(String[] args) throws IOException {
		GameMain game = new GameMain();
		
		game.revalidate();
	}
	
	
}





package com.betaben.collegeapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import nebulous.tools.DataHandler;
import nebulous.tools.ImageUtils;
import javax.swing.JTabbedPane;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel profileImageLabel;
	private JLabel familyImageLabel;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JLabel profileDisplayLbl = new JLabel("");
	JLabel familyDisplayLbl = new JLabel("");
	JLabel profileFirstNameLbl = new JLabel("First Name:");
	JLabel profileLastNameLbl = new JLabel("Last Name:");
	JLabel familyFirstNameLbl = new JLabel("First Name:");
	JLabel familyLastNameLbl = new JLabel("Last Name:");
	JLabel profileAgeLbl = new JLabel("Age:");
	JLabel familyAgeLbl = new JLabel("Age:");
	private JTextField firstNameTxt;
	private JTextField lastNameTxt;
	private JTextField ageTxt;
	private JTextField GPATxt;
	JPanel profilePanel = new JPanel();
	JPanel familyPanel = new JPanel();
	JScrollPane scrollPane = new JScrollPane();
	JLabel GPALbl = new JLabel("GPA:");
	
	public String firstName;
	public String lastName;
	public String age;
	public String GPA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		familyPanel.setLayout(null);
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.setPreferredSize(new Dimension(188, 1000));
		scrollPanel.setLayout(null);
		
		scrollPane.setBounds(205, 5, 207, 1004);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(scrollPanel);
		familyPanel.add(scrollPane);
		
		Main box = this;
		
		tabbedPane.setBounds(6, 6, 638, 572);
		profilePanel.setLayout(null);
		
		firstNameTxt = new JTextField();
		firstNameTxt.setBounds(74, 21, 130, 26);
		firstNameTxt.setColumns(10);
		profilePanel.add(firstNameTxt);
		
		lastNameTxt = new JTextField();
		lastNameTxt.setBounds(74, 59, 130, 26);
		lastNameTxt.setColumns(10);
		profilePanel.add(lastNameTxt);
		
		ageTxt = new JTextField();
		ageTxt.setBounds(74, 97, 130, 26);
		ageTxt.setColumns(10);
		profilePanel.add(ageTxt);
		
		GPATxt = new JTextField();
		GPATxt.setBounds(74, 135, 130, 26);
		GPATxt.setColumns(10);
		profilePanel.add(GPATxt);
		
		profileFirstNameLbl.setBounds(6, 6, 217, 40);
		profilePanel.add(profileFirstNameLbl);
		
		profileLastNameLbl.setBounds(6, 58, 217, 26);
		profilePanel.add(profileLastNameLbl);
		
		profileAgeLbl.setBounds(33, 101, 28, 16);
		profilePanel.add(profileAgeLbl);
		
		GPALbl.setBounds(33, 140, 29, 16);
		profilePanel.add(GPALbl);
		
		profileDisplayLbl.setBounds(227, 407, 390, 35);
		profilePanel.add(profileDisplayLbl);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(6, 448, 605, 72);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				profileDisplayLbl.setText("Your information was saved.");
				FamilyMemberPanel panel = new FamilyMemberPanel(firstNameTxt.getText(), lastNameTxt.getText(), ageTxt.getText(), GPATxt.getText());
				panel.setForeground(Color.black);
				scrollPanel.add(panel);
				FamilyMemberPanel.yPos += panel.getHeight()+5;
				scrollPanel.repaint();
				scrollPanel.revalidate();
				scrollPane.repaint();
				scrollPane.revalidate();
				contentPane.repaint();
				contentPane.revalidate();
				
			}
		});
		profilePanel.add(submitBtn);
		
		contentPane.getRootPane().setDefaultButton(submitBtn);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		imagePanel.setBounds(33, 185, 153, 192);
		profilePanel.add(imagePanel);
		
		JButton btnOpenImage = new JButton("Open Image");
		btnOpenImage.setBounds(33, 407, 119, 29);
		btnOpenImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setVisible(true);
				chooser.showOpenDialog(box);
				java.io.File file = chooser.getSelectedFile();
				try{
					BufferedImage image = ImageIO.read(file);
					image = ImageUtils.resizeImage(image, imagePanel.getWidth()-4, imagePanel.getHeight()-4);
					ImageIcon icon = new ImageIcon(image);
					profileImageLabel = new JLabel(icon);
					imagePanel.add(profileImageLabel);
					imagePanel.revalidate();
					imagePanel.repaint();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		profilePanel.add(btnOpenImage);
		tabbedPane.addTab("Profile", profilePanel);
		tabbedPane.addTab("Family", familyPanel);
		contentPane.add(tabbedPane);
		
		load();
		
	}
	
	public void submit(){
		firstName = firstNameTxt.getText();
		lastName = lastNameTxt.getText();
		age = ageTxt.getText();
		GPA = GPATxt.getText();
		profileDisplayLbl.setText("Your information was successfully saved!");
		DataHandler.save("First Name", "./res/text.txt", firstName);
		DataHandler.save("Last Name", "./res/text.txt", lastName);
		DataHandler.save("Age", "./res/text.txt", age);
		DataHandler.save("GPA", "./res/text.txt", GPA);
	}
	
	public void load(){
		firstNameTxt.setText((String)DataHandler.read("First Name", "./res/text.txt"));
		lastNameTxt.setText((String)DataHandler.read("Last Name", "./res/text.txt"));
		ageTxt.setText((String)DataHandler.read("Age", "./res/text.txt"));
		GPATxt.setText((String)DataHandler.read("GPA", "./res/text.txt"));
	}
}

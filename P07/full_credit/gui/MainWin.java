package gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;
import cli.*;
import store.*;
import java.util.ArrayList;

public class MainWin extends JFrame {// implements ActionListener {
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");
		JMenu     create       = new JMenu("Create");
		JMenuItem java       = new JMenuItem("Java");
		JMenuItem donut       = new JMenuItem("Donut");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");
        
        quit.addActionListener(event -> onQuitClick());
		java.addActionListener(event -> onJavaClick());
		donut.addActionListener(event -> onDonutClick());
        about.addActionListener(event -> onAboutClick());

        
        file.add(quit);
		create.add(java);
		create.add(donut);
        help.add(about);
        
        menubar.add(file);
		menubar.add(create);
        menubar.add(help);
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Jade Controls");
		
		toolbar.add(Box.createHorizontalStrut(25));
        // Create the 3 buttons using the icons provided
          button1  = new JButton(new ImageIcon("gui/resources/java.png"));
          button1.setActionCommand("Create new Java");
          button1.setToolTipText("Create new Java");
          toolbar.add(button1);
          button1.addActionListener(event -> onJavaClick());

	      button2 = new JButton(new ImageIcon("gui/resources/donut.png"));
	        button2.setActionCommand("Create new Donut");
	        button2.setToolTipText("Create new Donut");
	        toolbar.add(button2);
          button2.addActionListener(event -> onDonutClick());

        button3 = new JButton(new ImageIcon("gui/resources/help.png"));
          button3.setActionCommand("Get Help");
          button3.setToolTipText("Get Help");
          toolbar.add(button3);
          button3.addActionListener(event -> onAboutClick());
		  
    	toolbar.add(Box.createHorizontalStrut(25));

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        // /////////////////////////// ////////////////////////////////////////////
        // S T I C K S   D I S P L A Y
        // Provide a text entry box to show the remaining sticks
        menu = new JLabel();
        menu.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(menu, BorderLayout.CENTER);
		

        setVisible(true);
		
		onNewStoreClick();
		
    }
    
    // Listeners
	
    protected void onNewStoreClick() {        
		String name = "JADE";
	    store = new Store(name);
        setStore();
       }
	   
    protected void onJavaClick() {
        String javaName = JOptionPane.showInputDialog(this,"Java Name?");
		if(javaName != null)
		{
			String str = JOptionPane.showInputDialog(this,"Price?");
			if(str != null)
			{
				double price = Double.parseDouble(str);
				str = JOptionPane.showInputDialog(this,"Cost?");
				if(str != null)
				{
					double cost = Double.parseDouble(str);
					String[] optionsd = new String[Darkness.values().length];
					int i = 0;
					for (Darkness d: Darkness.values())
					{
						str = d.name();
						optionsd[i] = str;
						i++;
					}
					darknessCombo = new JComboBox<String>(optionsd);
					JOptionPane.showMessageDialog(this,darknessCombo,"Darkness?",3);
					darknessCombo.addActionListener(
							    event -> JOptionPane.showMessageDialog(this, (String)darknessCombo.getSelectedItem()));
					i = 0;
					while(optionsd[i] != (String)darknessCombo.getSelectedItem())
					{
						i++;
					}
					int darkness = i;
					String[] optionss = new String[Shot.values().length];
					i = 0;
					for (Shot s: Shot.values())
					{
						str = s.name();
						optionss[i] = str;
						i++;
					}
					java = new Java(javaName,price,cost,Darkness.values()[darkness]);
					int shot = 1;
					while (Shot.values()[shot] != Shot.None)
					{
						shotCombo = new JComboBox<String>(optionss);
						JOptionPane.showMessageDialog(this,shotCombo,"Shots? None when done",3);
						shotCombo.addActionListener(
								    event -> JOptionPane.showMessageDialog(this, (String)shotCombo.getSelectedItem()));
						i = 0;
						while(optionss[i] != (String)shotCombo.getSelectedItem())
						{
							i++;
						}
						shot = i;
						java.addShot(Shot.values()[shot]);
					}
					Product product = java;
					store.addProduct(product);
		
					setStore();
				}
			}
			
		}
		
    }
	
	protected void onDonutClick(){
        String donutName = JOptionPane.showInputDialog(this,"Donut Name?");
		if(donutName != null)
		{
			String str = JOptionPane.showInputDialog(this,"Price?");
			if(str != null)
			{
				double price = Double.parseDouble(str);
				str = JOptionPane.showInputDialog(this,"Cost?");
				if(str != null)
				{
					double cost = Double.parseDouble(str);
		
					String[] optionsf = new String[Frosting.values().length];
					int i = 0;
					for (Frosting d: Frosting.values())
					{
						str = d.name();
						optionsf[i] = str;
						i++;
					}
					frostingCombo = new JComboBox<String>(optionsf);
					JOptionPane.showMessageDialog(this,frostingCombo,"Frosting?",3);
					frostingCombo.addActionListener(
							    event -> JOptionPane.showMessageDialog(this, (String)frostingCombo.getSelectedItem()));
					i = 0;
					while(optionsf[i] != (String)frostingCombo.getSelectedItem())
					{
						i++;
					}
					int frosting = i;
		
					String[] optionsff = new String[Filling.values().length];
					i = 0;
					for (Filling a: Filling.values())
					{
						str = a.name();
						optionsff[i] = str;
						i++;
					}
					fillingCombo = new JComboBox<String>(optionsff);
					JOptionPane.showMessageDialog(this,fillingCombo,"Filling?",3);
					fillingCombo.addActionListener(
							    event -> JOptionPane.showMessageDialog(this, (String)fillingCombo.getSelectedItem()));
					i = 0;
					while(optionsff[i] != (String)fillingCombo.getSelectedItem())
					{
						if(optionsff[0] == (String)fillingCombo.getSelectedItem())
						{
							break;
						}
						i++;
					}
					int filling = i;
		
					boolean sprinkles = false;
					if(optionsf[0] == (String)frostingCombo.getSelectedItem())
					{
						sprinkles = false;
					}
					else
					{	
						String[] ynOptions = {"Yes","No"};
						sprinklesCombo = new JComboBox<String>(ynOptions);
						JOptionPane.showMessageDialog(this,sprinklesCombo,"Sprinkles?",3);
						sprinklesCombo.addActionListener(
								    event -> JOptionPane.showMessageDialog(this, (String)sprinklesCombo.getSelectedItem()));
						if(ynOptions[0] == (String)sprinklesCombo.getSelectedItem())
						{
							sprinkles = true;
						}
					}
					donut = new Donut(donutName,price,cost,Frosting.values()[frosting],sprinkles,Filling.values()[filling]);
					Product product = donut;
					store.addProduct(product);
		
					setStore();
				}
			}
		}
		
	}
      
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("Java and Donut Express");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("gui/resources/logo.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>Java and Donut Express</font></p>"
          + "</html>");
        about.add(title);

        JLabel artists = new JLabel("<html>"
          + "<p>Version 1.0</p>"
          + "<p>Copyright 2021-2024 by Angel D. Montelongo</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Royalty, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://www.pinclipart.com%2Fmaxpin%2FToRoh</font></p>"
          + "<p>Coffee Cup Icon by pinclipart.com, public domain</p>"
          + "<p><font size=-2>https://www.pinclipart.com/Fpindetail/FbRmbmx</font></p>"
          + "<p>Donut Icon by Fclipart.com, public domain</p>"
          + "<p><font size=-2>https://www.Fclipart.world/Fdonut-clipart</font></p>"
          + "<p>Help Cup Icon by pngitem.com, public domain</p>"
          + "<p><font size=-2>https://www.pngitem.com/Fmiddle/FiTRRxTx</font></p>"
          + "</html>");
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.setSize(450,400);
        about.setVisible(true);
     }
    protected void onQuitClick() {System.exit(0);}   // Exit the game
	
    public static void main(String[] args) {
        MainWin myApp = new MainWin("JADE");
        myApp.setVisible(true);
    }
	
   private void setStore() {              // Update display, robot move
        // s collects the status message
        String s = "";
        
        s += "Welcome to " + store.storeName();
		int num = store.numberOfProducts();
		if ( num != 0 )
		{
			s += "\n\n" + store.toString();
		}
		String htmlString = "<html>" + s.toString()                                          
			.replaceAll("<","&lt;")                                          
				.replaceAll(">", "&gt;")                                          
					.replaceAll("\n", "<br/>")                  
						+ "</html>";
        menu.setText(htmlString);


    }
	
	private Store store;
	private Java java;
	private Product product;
	private Donut donut;
    
    private JLabel menu;                 
    private JButton button1;                // Button to select 1 stick
    private JButton button2;                // Button to select 2 sticks
    private JButton button3;                // Button to select 3 sticks
    private JComboBox darknessCombo;
	private JComboBox shotCombo;
	private JComboBox frostingCombo;
	private JComboBox fillingCombo;
	private JComboBox sprinklesCombo;

}
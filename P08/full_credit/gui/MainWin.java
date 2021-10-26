package gui;
import cli.*;
import store.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.UIManager;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainWin extends JFrame {// implements ActionListener {
	
	private String NAME = "JADE";
    private String VERSION = "1.3J";
    private String FILE_VERSION = "1.0";
    private String MAGIC_COOKIE = "JADE𓎯";
		
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
		filename = new File("untitled.jade");
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem anew       = new JMenuItem("New");
        JMenuItem open       = new JMenuItem("Open");
        JMenuItem save       = new JMenuItem("Save");
        JMenuItem saveas     = new JMenuItem("Save As");
        JMenuItem quit       = new JMenuItem("Quit");
		JMenu     create       = new JMenu("Create");
		JMenuItem java       = new JMenuItem("Java");
		JMenuItem donut       = new JMenuItem("Donut");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");
        
		anew.addActionListener(event -> onNewStoreClick());
		open.addActionListener(event -> onOpenStoreClick());
		save.addActionListener(event -> onSaveStoreClick());
		saveas.addActionListener(event -> onSaveStoreAsClick());
        quit.addActionListener(event -> onQuitClick());
		java.addActionListener(event -> onJavaClick());
		donut.addActionListener(event -> onDonutClick());
        about.addActionListener(event -> onAboutClick());

        file.add(anew);
		file.add(open);
		file.add(save);
		file.add(saveas);
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
		
        buttonnew  = new JButton(new ImageIcon("gui/resources/new.png"));
        buttonnew.setActionCommand("Create new Store");
        buttonnew.setToolTipText("Create new Store");
        toolbar.add(buttonnew);
        buttonnew.addActionListener(event -> onNewStoreClick());
		
        buttonopen  = new JButton(new ImageIcon("gui/resources/open.png"));
        buttonopen.setActionCommand("Open Store");
        buttonopen.setToolTipText("Open Store");
        toolbar.add(buttonopen);
        buttonopen.addActionListener(event -> onOpenStoreClick());
		
        buttonsave  = new JButton(new ImageIcon("gui/resources/save.png"));
        buttonsave.setActionCommand("Save Store");
        buttonsave.setToolTipText("Save Store");
        toolbar.add(buttonsave);
        buttonsave.addActionListener(event -> onSaveStoreClick());
		
        buttonsaveas  = new JButton(new ImageIcon("gui/resources/saveas.png"));
        buttonsaveas.setActionCommand("Save Store As");
        buttonsaveas.setToolTipText("Save Store As");
        toolbar.add(buttonsaveas);
        buttonsaveas.addActionListener(event -> onSaveStoreAsClick());
		
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
		  
		 toolbar.add(Box.createHorizontalStrut(25));

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
		String storeName = JOptionPane.showInputDialog(this,"Store Name?");
		if(storeName != null)
		{
		    store = new Store(storeName);
	        setStore();
		}
       }
	   
	protected void onOpenStoreClick() {         // Create a new game
	    final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
	    FileFilter jadeFiles = new FileNameExtensionFilter("Jade files", "jade");
	    fc.addChoosableFileFilter(jadeFiles);         
	    fc.setFileFilter(jadeFiles);                  
    
	    int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
	    if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
	        filename = fc.getSelectedFile();        // Obtain the selected File object
        
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String magicCookie = br.readLine();
	            if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a Jade file");
	            String fileVersion = br.readLine();
	            if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible Jade file format");
            
	            store = new Store(br);
				                   
	            setStore();                        
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
	            "Failed", JOptionPane.ERROR_MESSAGE); 
	        }
	    }
	}
	
	protected void onSaveStoreClick() {         // Create a new game
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            store.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }
	
	protected void onSaveStoreAsClick() {         // Create a new game
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter jadeFiles = new FileNameExtensionFilter("Jade files", "jade");
        fc.addChoosableFileFilter(jadeFiles);         // Add "Nim file" filter
        fc.setFileFilter(jadeFiles);                  // Show Nim files only by default
    
        int result = fc.showSaveDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();         // Obtain the selected File object
            if(!filename.getAbsolutePath().endsWith(".jade"))  // Ensure it ends with ".nim"
                filename = new File(filename.getAbsolutePath() + ".jade");
            onSaveStoreClick();                       // Delegate to Save method
        }
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
          + "<p>NewFile Icon by pngitem.com, public domain</p>"
          + "<p><font size=-2>https://www.pngitem.com/F13/F16</font></p>"
          + "<p>OpenFile Icon by pngitem.com, public domain</p>"
          + "<p><font size=-2>https://www.pngitem.com/F255086/Fagafjhku</font></p>"
          + "<p>SaveFile Icon by pngitem.com, public domain</p>"
          + "<p><font size=-2>https://www.pngitem.com/F1dda/FbRbAA</font></p>"
          + "<p>SaveAsFile Icon by pngitem.com, public domain</p>"
          + "<p><font size=-2>https://www.pngitem.com/F98kll/FLLeiw</font></p>"
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
	private File filename;
    
    private JLabel menu;   
	private JButton buttonnew;
	private JButton buttonopen;    
	private JButton buttonsave;    
	private JButton buttonsaveas;                      
    private JButton button1;                // Button to select 1 stick
    private JButton button2;                // Button to select 2 sticks
    private JButton button3;                // Button to select 3 sticks
    private JComboBox darknessCombo;
	private JComboBox shotCombo;
	private JComboBox frostingCombo;
	private JComboBox fillingCombo;
	private JComboBox sprinklesCombo;

}
package gui;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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

import store.Store;
import store.Product;
import store.Person;
import store.Customer;
import store.Donut;
import store.Frosting;
import store.Filling;
import store.Java;
import store.Darkness;
import store.Shot;

public class MainWin extends JFrame {
    private final String NAME = "JADE";
    private final String EXT = "jade";
    private final String VERSION = "0.4";
    private final String FILE_VERSION = "1.0";
    private final String MAGIC_COOKIE = "ʝąɗ玉";

    // Thrown if the Cancel button on a dialog is clicked
    protected class CancelDialogException extends Exception {
        public CancelDialogException() {
            super("Dialog canceled");
        }
    }

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 480);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     mFile    = new JMenu("File");
        JMenuItem mNew     = new JMenuItem("New");
        JMenuItem mOpen    = new JMenuItem("Open");
        JMenuItem mSave    = new JMenuItem("Save");
        JMenuItem mSaveAs  = new JMenuItem("Save As");
        JMenuItem mQuit    = new JMenuItem("Quit");
        JMenu     mCreate  = new JMenu("Create");
        JMenuItem mJava    = new JMenuItem("Java");
        JMenuItem mDonut   = new JMenuItem("Donut");
		JMenuItem mCustomer = new JMenuItem("Customer");
        JMenu     mHelp    = new JMenu("Help");
        JMenuItem mAbout   = new JMenuItem("About");
        
        mNew   .addActionListener(event -> onNewClick());
        mOpen  .addActionListener(event -> onOpenClick());
        mSave  .addActionListener(event -> onSaveClick());
        mSaveAs.addActionListener(event -> onSaveAsClick());
        mQuit  .addActionListener(event -> onQuitClick());
        mJava  .addActionListener(event -> onCreateJavaClick());
        mDonut .addActionListener(event -> onCreateDonutClick());
		mCustomer .addActionListener(event -> onCreateCustomerClick());
        mAbout .addActionListener(event -> onAboutClick());

        
        mFile  .add(mNew);
        mFile  .add(mOpen);
        mFile  .add(mSave);
        mFile  .add(mSaveAs);
        mFile  .add(mQuit);
        mCreate.add(mJava);
        mCreate.add(mDonut);
		mCreate.add(mCustomer);
        mHelp  .add(mAbout);
        
        menubar.add(mFile);
        menubar.add(mCreate);
        menubar.add(mHelp);
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("JADE Controls");

       JButton bNew = new JButton(new ImageIcon("gui/resources/file_new.png"));
          bNew.setActionCommand("Create new JADE store");
          bNew.setToolTipText("Create new JADE store");
          toolbar.add(bNew);
          bNew.addActionListener(event -> onNewClick());

        JButton bOpen = new JButton(new ImageIcon("gui/resources/file_open.png"));
          bOpen.setActionCommand("Open JADE file");
          bOpen.setToolTipText("Open JADE file");
          toolbar.add(bOpen);
          bOpen.addActionListener(event -> onOpenClick());

        JButton bSave = new JButton(new ImageIcon("gui/resources/file_save.png"));
          bSave.setActionCommand("Save JADE file");
          bSave.setToolTipText("Save JADE file");
          toolbar.add(bSave);
          bSave.addActionListener(event -> onSaveClick());

        JButton bSaveAs = new JButton(new ImageIcon("gui/resources/file_save_as.png"));
          bSaveAs.setActionCommand("Save JADE as new file");
          bSaveAs.setToolTipText("Save JADE as new file");
          toolbar.add(bSaveAs);
          bSaveAs.addActionListener(event -> onSaveAsClick());

        toolbar.add(Box.createHorizontalStrut(25));
        
        // Create the 3 buttons using the icons provided
        bJava  = new JButton(new ImageIcon("gui/resources/new_java.png"));
          bJava.setActionCommand("Create new Java");
          bJava.setToolTipText("Create a new coffee selection");
          toolbar.add(bJava);
          bJava.addActionListener(event -> onCreateJavaClick());

        bDonut = new JButton(new ImageIcon("gui/resources/new_donut.png"));
          bDonut.setActionCommand("Create new donut");
          bDonut.setToolTipText("Create a new donut selection");
          toolbar.add(bDonut);
          bDonut.addActionListener(event -> onCreateDonutClick());
		  
	    bCustomer = new JButton(new ImageIcon("gui/resources/person.png"));
	      bCustomer.setActionCommand("Create new Customer");
	      bCustomer.setToolTipText("Create a new Customer");
	      toolbar.add(bCustomer);
	      bCustomer.addActionListener(event -> onCreateCustomerClick());

        toolbar.add(Box.createHorizontalStrut(25));
		
	    bProductList = new JButton(new ImageIcon("gui/resources/products.png"));
	      bProductList.setActionCommand("List Products");
	      bProductList.setToolTipText("List Products");
	      toolbar.add(bProductList);
	      bProductList.addActionListener(event -> updateDisplay());
		  
  	    bPersonList = new JButton(new ImageIcon("gui/resources/personlist.png"));
  	      bPersonList.setActionCommand("List Customers");
  	      bPersonList.setToolTipText("List Customers");
  	      toolbar.add(bPersonList);
  	      bPersonList.addActionListener(event -> updateDisplayCusto());
		  
		toolbar.add(Box.createHorizontalStrut(25));
        
        JButton bAbout = new JButton(new ImageIcon("gui/resources/about.png"));
          bAbout.setActionCommand("About JADE Manager");
          bAbout.setToolTipText("About JADE Manager");
          toolbar.add(bAbout);
          bAbout.addActionListener(event -> onAboutClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        
        // /////////////////////////// ////////////////////////////////////////////
        // D A T A   D I S P L A Y
        // Provide a text entry box to show output
        data = new JLabel();
        data.setFont(new Font("SansSerif", Font.BOLD, 12));
        data.setVerticalAlignment(JLabel.TOP);
        add(data, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);
        
        // Create a default store
        onNewClick();
        
        // Make everything in the JFrame visible
        setVisible(true);        
    }
    
    // Listeners
    
    protected void onNewClick() {
        String storeName = "JADE";
        if(store != null) {
            storeName = JOptionPane.showInputDialog(this, "New store name?");
        }
        if(storeName != null) {
            store = new Store(storeName);
            filename = new File("Untitled.jade");
        }
        updateDisplay();
    }

    protected void onOpenClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter jadeFiles = new FileNameExtensionFilter(NAME + " files", EXT);
        fc.addChoosableFileFilter(jadeFiles);
        fc.setFileFilter(jadeFiles);
        
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fc.getSelectedFile();
             
            try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) 
                    throw new RuntimeException("Not a " + NAME + " file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) 
                    throw new RuntimeException("Incompatible " + NAME + " file format");
                
                // Try to read the new store
                // If it fails, old store remains
                // If it succeeds, replace old store with new store
                
                Store newStore = new Store(br);
                store = newStore;
                filename = fname;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(
                    this, 
                    "Unable to open " + filename + '\n' + e, "Failed",
                    JOptionPane.ERROR_MESSAGE); 
            }
            updateDisplay();
        }
    }
    protected void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            store.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this, 
                "Unable to save " + filename + '\n' + e, "Failed",
                JOptionPane.ERROR_MESSAGE); 
        }
    }

    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter jadeFiles = new FileNameExtensionFilter(NAME + " files", EXT);
        fc.addChoosableFileFilter(jadeFiles);
        fc.setFileFilter(jadeFiles);
        
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith("." + EXT))
                filename = new File(filename.getAbsolutePath() + "." + EXT);
            onSaveClick();
        }
    }

    protected void onQuitClick() {
        dispose();
    }

    protected void onCreateJavaClick() {  // Create a new Java product
		JLabel nameJava = new JLabel("<HTML><br/>Java Name?</HTML>");
		nameJavas = new JTextField(30);
		JLabel price = new JLabel("<HTML><br/>Price?</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
        prices2 = new JSpinner(range); 
		JLabel cost = new JLabel("<HTML><br/>Cost?</HTML>");
		SpinnerModel range2 = new SpinnerNumberModel(0, 0, 100, 1);
		costs2 = new JSpinner(range2); 
		JLabel darkness = new JLabel("Darkness?");
		String[] options = new String[Darkness.values().length];
		Darkness[] f = Darkness.values();
		int i = 0;
		for ( Darkness fs : f)
		{
			options[i] = fs.name();
			i++;
		}
        darknesse = new JComboBox<String>(options);
		Object[] objects = {
			nameJava,	nameJavas,
			price, 	prices2,
			cost,	costs2,
			darkness,	darknesse};
		int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Java",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
		if( button == JOptionPane.OK_OPTION){
		int int1 = (int)prices2.getValue();
		int int2 = (int)costs2.getValue();
		String str1 = (String)darknesse.getSelectedItem();
		Java java = new Java(nameJavas.getText(), (double)int1, (double)int2, Darkness.valueOf(str1));
		try {
		while(true) {
		                Shot shot = (Shot) selectFromArray("Shot?", Shot.values());
		                if(shot.equals(Shot.None)) break;
		                java.addShot(shot);
		            }
		store.addProduct(java);
		updateDisplay();
		} catch (CancelDialogException e) { // ignore a Cancel
	        } catch(Exception e) {
	            msg.setText("Failed to create new Java: " + e.getMessage());
	        }
		}
    }
    
    protected void onCreateDonutClick() {  // Create a new Java product
		JLabel nameDonut = new JLabel("<HTML><br/>Donut Name?</HTML>");
		nameDonuts = new JTextField(30);
		JLabel price = new JLabel("<HTML><br/>Price?</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
        prices = new JSpinner(range); 
		JLabel cost = new JLabel("<HTML><br/>Cost?</HTML>");
		SpinnerModel range2 = new SpinnerNumberModel(0, 0, 100, 1);
		costs = new JSpinner(range2); 
		JLabel frosting = new JLabel("Frosting?");
		String[] options = new String[Frosting.values().length];
		Frosting[] f = Frosting.values();
		int i = 0;
		for ( Frosting fs : f)
		{
			options[i] = fs.name();
			i++;
		}
        frostings = new JComboBox<String>(options);
		JLabel filling = new JLabel("Filling?");
        String[] options2 = new String[Filling.values().length];
		Filling[] fl = Filling.values();
		i = 0;
		for(Filling fls : fl)
		{
			options2[i] = fls.name();
			i++;
		}
        fillings = new JComboBox<String>(options2);
		JLabel sprinkle = new JLabel("Sprinkles?");
        String[] options3 = {"No Sprinkles", "Sprinkles"};
        sprinkles = new JComboBox<String>(options3);
		Object[] objects = {
			nameDonut,	nameDonuts,
			price, 	prices,
			cost,	costs,
			frosting,	frostings,
			filling,	fillings,
			sprinkle,	sprinkles};
		int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Donut",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
		if( button == JOptionPane.OK_OPTION){
		boolean bsprink = false;
		if(sprinkles.getSelectedItem() == "Sprinkles")
		{
			bsprink = true;
		}
		int int1 = (int)prices.getValue();
		int int2 = (int)costs.getValue();
		String str1 = (String)frostings.getSelectedItem();
		String str2 = (String)fillings.getSelectedItem();
		store.addProduct(new Donut(nameDonuts.getText(), (double)int1, (double)int2,Frosting.valueOf(str1),Filling.valueOf(str2),bsprink));
		updateDisplay();}
    }
	
	protected void onCreateCustomerClick() {
			JLabel name = new JLabel("<HTML><br/>Name</HTML>");
			names = new JTextField(30); 
			JLabel phone = new JLabel("<HTML><br/>Phone (Example 123-456-7890)</HTML>");
			phones = new JTextField(30);
			Object[] objects = {  // Array of widgets to display
	            name,   names, 
	            phone,	phones};
			int button = JOptionPane.showConfirmDialog( // Show the dialog
	            this,
	            objects,
	            "New Customer",
	            JOptionPane.OK_CANCEL_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
			if( button == JOptionPane.OK_OPTION){
			store.addPerson(new Customer(names.getText(), phones.getText()));
			updateDisplayCusto();}
	}
            
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.getContentPane().setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        about.setTitle("Java and Donut Express");
        about.setSize(640,600);
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("gui/resources/logo.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+3>Java and Donut Express</font></p>"
          + "</html>");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(title);

        JLabel copyright = new JLabel("<html>"
          + "<p>Version 0.4</p>"
          + "<p>Copyright 2021 by George F. Rice & Angel Montelongo</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<br/>"
          + "</html>");
        copyright.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(copyright);
                    
       JLabel artists = new JLabel("<html>"
          + "<p>JADE Logo by SaxDeux, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Logo_JADE.png</p>"
          + "<p>Flat Coffee Cup Icon by superawesomevectors, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>http://fav.me/dbf6otc</p>"
          + "<p><font size=-2>https://creativecommons.org/licenses/by-sa/3.0/us/</p>"
          + "<p>Donut Icon by Hazmat2 via Hyju, public domain</p>"
          + "<p><font size=-2>https://en.wikipedia.org/wiki/File:Simpsons_Donut.svg</p>"
          + "<p>Help Icon by Vector Stall via the Flat Icon license</p>"
          + "<p><font size=-2>https://www.flaticon.com/premium-icon/question-mark_3444393</p>"
          + "<p>File icons made by Freepik</p>"
          + "<p><font size=-2>https://www.freepik.com</p>"     
          + "<p>Person icon made by Freepik</p>"
          + "<p><font size=-2>https://www.freepik.com/person-213411</p>"   
          + "<p>ProductList icon made by Freepik</p>"
          + "<p><font size=-2>https://www.freepik.com/coffeedonut-2123121</p>"    
          + "<p>PersonList icon made by Freepik</p>"
          + "<p><font size=-2>https://www.freepik.com/people-clipart-78974</p>"  
          + "<br/>"
          + "</html>");
        artists.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.pack();
        about.setVisible(true);
     }

    public static void main(String[] args) {
        MainWin myApp = new MainWin("JADE");
        myApp.setVisible(true);
    }
    
    private void updateDisplay() {
        data.setText("<html>" + store.toString()
                                     .replaceAll("<","&lt;")
                                     .replaceAll(">", "&gt;")
                                     .replaceAll("\n", "<br/>")
                              + "</html>");
    }
	
    private void updateDisplayCusto() {
        data.setText("<html>" + store.peopleToString()
                                     .replaceAll("<","&lt;")
                                     .replaceAll(">", "&gt;")
                                     .replaceAll("\n", "<br/>")
                              + "</html>");
    }
	
	private Object selectFromArray(String prompt, Object[] options) throws CancelDialogException {
	        JComboBox<Object> comboEnum = new JComboBox<>();
	        comboEnum.setModel(new DefaultComboBoxModel<Object>(options));
	        int button = JOptionPane.showConfirmDialog(this, comboEnum, prompt, 
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        if(button == JOptionPane.CANCEL_OPTION) throw new CancelDialogException();
	        return comboEnum.getSelectedItem();
	    }

    
    private Store store;
    private File filename;
    
    private JLabel data;                    // Display of output in main window
    private JLabel msg;                     // Status message display
    private JButton bJava;                  // Button to select 1 stick
    private JButton bDonut;                 // Button to select 2 sticks
	private JButton bCustomer;
	private JButton bProductList;
	private JButton bPersonList;
	private JTextField names;
	private JTextField phones;
	private JTextField nameDonuts, nameJavas;
	private JSpinner prices, prices2;
	private JSpinner costs, costs2;
	private JComboBox frostings, darknesse;
	private JComboBox fillings;
	private JComboBox sprinkles;
}

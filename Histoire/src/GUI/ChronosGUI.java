package GUI;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUIpan.*;
import data.Tribes;
import temporality.Age;
import temporality.Chronos;

public class ChronosGUI extends JFrame implements Runnable {
	@SuppressWarnings("unused")
	private static final Dimension IDEAL_MAIN_DIMENSION = new Dimension(1500, 900);
	private static final Dimension IDEAL_DASHBOARD_DIMENSION = new Dimension(GParameters.FRAME_WIDTH, 1000);
	private static Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
	private static final long CHRONO_SPEED = 50;

	private static final long serialVersionUID = 1L;
	


	private JPanel introPan = new IntroPan();
	private JPanel prePann = new PrehistoirePan();
	private JPanel antPan = new AntiquitePan();
	private JPanel maPan = new	MaPan();
	private JPanel rePan = new RePan();
	private JPanel modPan = new ModernePan();


    
	

	/**
	 * The core functional part : the chronometer and the printing of the ingos.
	 */
	private Chronos chronometer=new Chronos(-1598000);

	private Age age =new Age("0");

	private JButton startButton = new JButton(" Continuer ");
	private JButton clearButton = new JButton(" Recommencer ");

	private JLabel dateLabel = new JLabel("Date :");
	private JLabel dateValue = new JLabel("");
	private MousePopUp me =new MousePopUp();
	
	//Panel de la carte 
	private GrapheTemps control = new GrapheTemps();
	//Panel qui contiendras les 3 panels principaux
	private JPanel container = new JPanel();
	//Panel de la Frise
	private JPanel frise= new Frise();
	//Panel des évènements
    private Panelevents events= new Panelevents(age);
    /**
     * The buttons that will change the panels
     */
    private JButton bChangerPanel = new JButton("Prehistoire");
    private JButton anti = new JButton("Antiquité");
    private JButton moyen = new JButton("moyen age");
    private JButton cont = new JButton("Renaissance");
    private JButton moderne = new JButton("Moderne");
    private JButton retour = new JButton("previous");
    private JButton play = new JButton("Commencer");
    private JButton re = new JButton("Retour");
    private JButton pla = new JButton("Commencer");
    
	/**
	 * This instance is used in the inner classes for different action listeners.
	 */
	private ChronosGUI instance = this;

	/**
	 * Initial status of for the start button.
	 */
	private boolean stop = true;
	private int path = 0;
	private boolean passage =false;
	String csvFile = System.getProperty("user.dir")+"//src//data//donnee//"+"changement.csv";
	
	
	/////
	public ChronosGUI(String title) {
		super(title);
		init();
	}
	///////
	private void init() {
		updateValues();

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		container.setLayout(new FlowLayout(FlowLayout.LEFT));

		container.add(modPan);
		
		control.addMouseListener(me);
		
		introPan.setPreferredSize(IDEAL_DASHBOARD_DIMENSION);
		contentPane.add(BorderLayout.SOUTH, introPan);
		
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(true);
		
		
	   	
	   	bChangerPanel.setBounds(600,280,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	     anti.setBounds(600,340,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	     moyen.setBounds(600,400,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	     cont.setBounds(600,460,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT); 
	     moderne.setBounds(600,520,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT); 
	     retour.setBounds(600,500,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	     play.setBounds(600,400,GParameters.BUTTON_WIDTH,GParameters.BUTTON_HEIGHT);
	     
	        /**
	         * Listener on buttons
	         */
	        this.bChangerPanel.addActionListener(new PreHist());
	        this.anti.addActionListener(new Antic());
	        this.moyen.addActionListener(new MO());
	        this.cont.addActionListener(new Contemp());
	        this.moderne.addActionListener(new Moderne());
	        this.play.addActionListener(new Play());
	        this.retour.addActionListener(new Retour());
	        this.re.addActionListener(new Playy());
	        this.pla.addActionListener(new Retourr());
	        
	        this.introPan.add(this.bChangerPanel);
	        this.introPan.add(this.anti);
	        this.introPan.add(this.moyen);
	        this.introPan.add(this.cont);
	        this.introPan.add(this.moderne);
	       
	        this.setContentPane(this.introPan);
	}

	private void updateValues() {
		if(chronometer.getAnnee()<chronometer.getFin()) {
			//Changement de l'affichage de l'année
			long date = chronometer.getAnnee();
			dateValue.setText(date + " ");
			//mise à jour des informations 
			age.setYear(chronometer.getAnnee());
			age.update();
			//Vérification si changement de temporalité il y a
			changement();
			//Mise à jour du panneau evenements
			events.setTexte(age);
			events.repaint();
			//Mise à jour de la carte
			control.up(age);
			control.repaint();
			//Mise à jour de la frise
			((Frise) frise).setChronos();
			frise.repaint();
		}
		else{
			stop = true;
			startButton.setText(" Start ");
			JDialog.setDefaultLookAndFeelDecorated(true);
	
	        int input = JOptionPane.showConfirmDialog(null, 
	                "Vous avez fini la simulation!", "Ok!", JOptionPane.DEFAULT_OPTION);
	        dispose();
			
		}
	}
	/*
	 * Methode pour vérifié si il est temps de proposer un changement
	 */

	private void changement() {
		//on vï¿½rifie d'abord si on est pas dï¿½ja dans une autre branche que celle normale
				if(passage ==false) {
					if(passage ==true) {
						
					}
					else {
						String line = "";
				        String cvsSplitBy = ";";
				        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
				        while (((line = br.readLine()) != null)) {
				        		 String[]change = line.split(cvsSplitBy);
				                 try {
				                    long l = Long.parseLong(change[0]);
				                    long fin = Long.parseLong(change[3]);
				                    if((chronometer.getAnnee()==l)&&(path==0)) {
				            			stop = true;
				            			startButton.setText(" Start ");
				            			JDialog.setDefaultLookAndFeelDecorated(true);
				            		    int response = JOptionPane.showConfirmDialog(null, change[2], "Choix important",
				            		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				            		    	
				            		    if (response == JOptionPane.NO_OPTION) {
				            		    } 
				            		    else if (response == JOptionPane.YES_OPTION) {
				            		    	long p = Long.parseLong(change[1]);
				            		    	path=(int) p;
				            		    	chronometer.SetChronos(l);
											chronometer.setFin(fin);
				            		    	stop = false;
				        					startButton.setText(" Pause ");
				        					Thread chronoThread = new Thread(instance);
				        					chronoThread.start();
				        					age = new Age(Integer.toString(path));
				            		    	
				            		    	
				            		      
				            		    } 
				            		    else if (response == JOptionPane.CLOSED_OPTION) {
				            		    	
				            		    }
				            		    stop = false;
				    					startButton.setText(" Pause ");
				    					Thread chronoThread = new Thread(instance);
				    					chronoThread.start();
					        		}
				                    
				                    
				                 } catch (NumberFormatException nfe) {
				                    System.out.println("NumberFormatException: " + nfe.getMessage());
				                 }
				            }

				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						

						
					}
				}
			}
	/**
	 * Defines what to do : it increments the chronometer and actualise informations
	 */
	///////
	public void run() {
		while (!stop) {
			try {
				
				Thread.sleep(CHRONO_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			chronometer.increment();
			
			/**
			 *  Ensure that the chronometer is not stopped during the iteration.
			 */
			if (!stop) {
				updateValues();
			}
		}
	}
	   /**
     * @author Etienne 
     * Listener of click, display of info about tribes
     */
	//////
	private class MousePopUp extends MouseAdapter {
		 @Override
        public void mouseClicked(MouseEvent me) {
           super.mouseClicked(me);
           for(Iterator<Zones> it=control.getGrid().getZones().values().iterator();it.hasNext();) {
        	   Zones z =it.next();
        	   String name =z.getName();
        	   for(Iterator<Case> at =z.getCases().iterator();at.hasNext();) {
        		   if(at.next().getRectangle().contains(me.getX(), me.getY())) {
        			   for(Iterator<Tribes> tribIt= age.getInfo().getCulture().values().iterator();tribIt.hasNext();) {
        				Tribes t = tribIt.next();
           				if(t.getLieu().contains(name)) {
           					if (!stop) {
                   				stop = true;
                   				startButton.setText(" Continuer ");
           					}
           					JOptionPane.showMessageDialog(control, t.toString() , t.getName(), JOptionPane.INFORMATION_MESSAGE);
           				}
           				stop = false;
            			startButton.setText(" Pause ");
            			Thread chronoThread = new Thread(instance);
            			chronoThread.start();
           				}
           			}
        	   }
           }
       }
	}
	
	//////
	private class StartStopAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!stop) {
				stop = true;
				startButton.setText(" Continuer ");
			} else {
				stop = false;
				startButton.setText(" Pause ");
				Thread chronoThread = new Thread(instance);
				chronoThread.start();
			}
		}
	}
	
	
	///////
	private class ClearAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			stop = true;
			startButton.setText(" Continuer ");
			chronometer.init();
			age.setYear(chronometer.getAnnee());
			age.update();
			control.reset();
			updateValues();
		}

	}
	
	/**
	 * Methods that changes window panels
	 */
	///////
    public void switchPrehistPan(){
    	/**
    	 * panel that contains the buttons on the lap times
    	 */
    	this.prePann.add(this.retour);
        this.prePann.add(this.play);
        this.setContentPane(this.prePann);
        this.revalidate();
        
    }
    ///////
    public void switchAnticPan(){
    	this.maPan.add(this.retour);
        this.maPan.add(this.play);
        this.setContentPane(this.maPan);
        this.revalidate();
        
    }
    ///////
    public void switchMOPan(){
    	this.antPan.add(this.retour);
        this.antPan.add(this.play);
        this.setContentPane(this.antPan);
        this.revalidate();
        
    }
    ///////
    public void retourPan(){
        this.setContentPane(this.introPan);
        this.revalidate();
        
    }
    ///////
    public void play(){
    	dateValue.setText(chronometer.getAnnee() + " ");
    	
    	this.setContentPane(this.container);
    	start();
      
    }
    //////
    public void switchRenPan(){
    	this.rePan.add(this.retour);
        this.rePan.add(this.play);
        this.setContentPane(this.rePan);
        this.revalidate();
        
    }
    //////
    public void switchModernPan(){
    	this.modPan.add(this.retour);
        this.modPan.add(this.play);
        this.setContentPane(this.modPan);
        this.revalidate();
        
    }
     
    /**
     * @author Seruche 
     * Listener of buttons
     */
    /////
    private class PreHist implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	chronometer = new Chronos(-1598000);
        	ChronosGUI.this.switchPrehistPan();
        }
    }
    /////
    private class MO implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	chronometer = new Chronos(250);
        	ChronosGUI.this.switchAnticPan();
        }
    }
    /////
    private class Antic implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	chronometer = new Chronos(-3000);
        	ChronosGUI.this.switchMOPan();
        }
    }
    /////
    private class Contemp implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	chronometer = new Chronos(1400);
        	ChronosGUI.this.switchRenPan();
        }
    }
    /////
    private class Moderne implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	chronometer = new Chronos(1789);
        	ChronosGUI.this.switchModernPan();
        }
    }
    //////
    private class Retour implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	ChronosGUI.this.retourPan();
        }
    }
    //////
    private class Play implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	ChronosGUI.this.play();
        }
    }
    ///////
    private class Retourr implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	ChronosGUI.this.retourPan();
        }
    }
    /////
    private class Playy implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
        	ChronosGUI.this.play();
        }
    }
    private void start() {
    	  dateLabel.setFont(font);
  		frise.add(dateLabel);
  		dateValue.setFont(font);
  		frise.add(dateValue);
  		startButton.setFont(font);
  		startButton.addActionListener(new StartStopAction());
  		frise.add(startButton, BorderLayout.CENTER);

  		clearButton.setFont(font);
  		clearButton.addActionListener(new ClearAction());
  		frise.add(clearButton);
          container.add(control, BorderLayout.CENTER);	
      
          events.setPreferredSize( new Dimension(850,500) ) ;
          container.add(events, BorderLayout.EAST);
          this.revalidate();

          frise.setPreferredSize( new Dimension(1500,300) ) ;
          container.add(frise, BorderLayout.SOUTH);
          this.revalidate();
    }
	
	

	public static void main(String[] args) {
		new ChronosGUI("SIMULATION DE L'HISTOIRE DES PEUPLES");
	}

}

/*
Josh Riddle
CS 331 - 001
Tue, Thurs 9:15 - 10:45
Fall 2022
*/
package AirportRunwayTester;

import java.awt.Color;
import java.awt.Font;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
//JOptionPane displays another window with message
//import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirTrafficControl extends JFrame {
    
    Queue<Airplane> landingQueue = new LinkedList<>();
    Queue<Airplane> liftOffQueue = new LinkedList<>();
    Queue<Airplane> decodingQueue = new LinkedList<>();
    Queue<Airplane> temp1 = new LinkedList<>();
    Queue<Airplane> temp2 = new LinkedList<>();
    Queue<Airplane> temp3 = new LinkedList<>();
    
    private JButton startBtn;
    private String planeNum;
    private boolean running = false;
    private int broke;
    private int fuel;
    Airplane plane;
    
    private Timer planeTime;
    private int planeTimeLeft = 0;
    private final int planeTimeBase = 50;
    QueueHandler landing = new QueueHandler();
    QueueHandler takingOff = new QueueHandler();
//    private JLabel[] upPlanes = new JLabel[5];
//    private JLabel[] downPlanes = new JLabel[5];
//    private int[] board = new int[5];
    
    public AirTrafficControl() {
        // Setup main screen
        setupScreen();
        // Run function to clear any existing list data
        startupProcess();
        // Start Timer to land planes
        startEvents();
    }
    
    public static void main(String[] args) {
        AirTrafficControl frame = new AirTrafficControl();
        frame.setVisible(true);
    }
    
    public void setupScreen() {
        // Creates main window
        setTitle("Air Traffic Control Screen");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 250);
        
        // Creates a smaller window inside main window
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 75));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        
        // Display App title
        JLabel appTitle = new JLabel("Air Traffice Control");
        appTitle.setForeground(new Color(153,204,0));
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        appTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        appTitle.setBounds(0, 0, 500, 47);
        contentPane.add(appTitle);
        
        // Plane landing
//        JLabel land = new JLabel("Landing");
//        land.setForeground(new Color(153, 204, 0));
//        land.setHorizontalAlignment(SwingConstants.LEADING);
//        land.setFont(new Font("Calibri", Font.BOLD, 16));
//        land.setBounds(25, 45, 712, 47);
//        contentPane.add(land);
        
        // Planes waiting to land
//        JLabel inAir = new JLabel("Waiting to Land");
//        inAir.setForeground(new Color(153, 204, 0));
//        inAir.setHorizontalAlignment(SwingConstants.LEADING);
//        inAir.setFont(new Font("Calibri", Font.BOLD, 16));
//        inAir.setBounds(25, 220, 712, 47);
//        contentPane.add(inAir);
        
        // Planes waiting to take-off
//        JLabel onLand = new JLabel("Waiting to Take-off");
//        onLand.setForeground(new Color(153,204,0));
//        onLand.setHorizontalAlignment(SwingConstants.LEADING);
//        onLand.setFont(new Font("Calibri", Font.BOLD, 16));
//        onLand.setBounds(25, 395, 712, 47);
//        contentPane.add(onLand);
        
        // Plane display section
//        planeWindow = new JPanel();
//        planeWindow.setBackground(Color.DARK_GRAY);
//        planeWindow.setBounds(16, 50, 700, 495);
//        planeWindow.setLayout(null);
//        contentPane.add(planeWindow);
        
        // Button to start and stop the program
        startBtn = new JButton("Start");
        startBtn.setBackground(Color.white);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);
        startBtn.setBounds(200, 100, 110, 33);
        contentPane.add(startBtn);
        
//        for (int i = 0; i < 5; i++) {
//            int incrX = i % width;
//            int incrY = i / height;
//            upPlanes[i] = new JLabel(Integer.toString(i));
//            upPlanes[i].setName(Integer.toString(i));
//            upPlanes[i].setBounds((0 + (incrX * offset)), (396 - (incrY * offset)), 132, 132);
//            planeWindow.add(upPlanes[i]);
//        }
        
        setContentPane(contentPane);
    }
    
    public void startupProcess() {
        startBtn.addActionListener((ActionEvent e) -> {
//      If running is NOT TRUE
            if (!running) {
                landingQueue.clear();
                liftOffQueue.clear();
                running = true;
                planeTime.start();
                startBtn.setText("Stop");
//                planeTimeLeft = planeTimeBase;
                trafficControl(running);
            } else {
                landingQueue.clear();
                liftOffQueue.clear();
                running = false;
                startBtn.setText("Start");
                planeTime.stop();
            }
        });
        
        // Timer to keep all the planes from landing at the same time
        planeTime = new Timer(100, (ActionEvent evt) -> {
            if (planeTimeLeft == 0){
                planeTime.stop();
            }
            planeTimeLeft--;
        });
    }
    
    public void trafficControl(boolean running) {
        
        temp1.clear();
        temp2.clear();
        temp3.clear();
        // Generate planes waiting to land
        landingQueue.clear();
        if (running) {
            for (int i = 0; i < 5; i++){
                planeNum = setAirplaneNumString();
                fuel = (int)((Math.random() * (100 - 1)) + 1);
                broke = (int)((Math.random() * (100 - 1)) + 1);
                plane = new Airplane(planeNum, "Landing", fuel, (fuel < 30), (broke < 25));
                
                // Add planes to queues to condense after all 5 have been created
                if (broke < 25) {
                    temp1.add(plane);
                } else if (fuel < 30){
                    temp2.add(plane);
                } else {
                    temp3.add(plane);
                }
            }
            
            // Generate planes waiting for take off
            for (int i = 0; i < 5; i++){
                planeNum = setAirplaneNumString();
                fuel = 100;
                broke = 100;
                plane = new Airplane(planeNum, "Landing", fuel, (fuel < 30), (broke < 25));
                liftOffQueue.add(plane);
            }
            
            addToQueue();
        }
        
        System.out.println("Planes waiting to land");
        String ANSI_RED = "\u001B[31m";
        String ANSI_YELLOW = "\033[0;33m";
        String ANSI_RESET = "\u001B[0m";
        for (Airplane landingPlane : landingQueue) {
            // Display loop
            if(landingPlane.MechanicalIssue()) {
                System.out.println(ANSI_RED + "Plane num: " + landingPlane.getAirplaneNumber()
                        + ", Low Fuel: " + landingPlane.LowFuel()
                        + ", Broken: " + landingPlane.MechanicalIssue()+ANSI_RESET);
            } else if (landingPlane.LowFuel()) {
                System.out.println(ANSI_YELLOW + "Plane num: " + landingPlane.getAirplaneNumber()
                        + ", Low Fuel: " + landingPlane.LowFuel()
                        + ", Broken: " + landingPlane.MechanicalIssue()+ANSI_RESET);
            } else {
                System.out.println("Plane num: " + landingPlane.getAirplaneNumber()
                        + ", Low Fuel: " + landingPlane.LowFuel()
                        + ", Broken: " + landingPlane.MechanicalIssue());
            }
            
        }
        System.out.println("Planes waiting to take off");
        for (Airplane takingOff : liftOffQueue) {
            // Display loop
            System.out.println("Plane num: " + takingOff.getAirplaneNumber() + ", Low Fuel: "
                    + takingOff.LowFuel() + ", Broken: " + takingOff.MechanicalIssue());
        }
    }
    
    public void startEvents() {
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                planeTimeLeft = planeTimeBase;
                planeTime.start();
            }
        });
        
        planeTime = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (planeTimeLeft == 0) {
                    if(landingQueue.size() > 0) {
                        Airplane landing = landingQueue.remove();
                        if (landing.MechanicalIssue()) {
                            System.out.println("\nPlane: " + landing.getAirplaneNumber()
                            + " has a mechanical issue and has priority to land.");
                        } else if (landing.LowFuel()) {
                            System.out.println("\nPlane: " + landing.getAirplaneNumber()
                            + " is low on fuel and has priority to land.");
                        } else {
                            System.out.println("\nPlane: " + landing.getAirplaneNumber()
                            + " is now landing.");
                        }
                    }
                    if((landingQueue.size() == 0) && (liftOffQueue.size() > 0)) {
                        Airplane liftOff = liftOffQueue.remove();
                        System.out.println("\nPlane: " + liftOff.getAirplaneNumber() +
                                " is taking off.");
                    }
                    if ((landingQueue.size() == 0) && (liftOffQueue.size() == 0)) {
                        System.out.println("\nThere are no more planes to land or take off.");
                        landingQueue.clear();
                        liftOffQueue.clear();
                        running = false;
                        startBtn.setText("Start");
                        planeTime.stop();
                    }
                    planeTimeLeft = planeTimeBase;
                }
                planeTimeLeft--;
            }
        });
    }
    
    private void addToQueue() {
        if (temp1.size() > 0) {
            for (Airplane tempPlane1 : temp1) {
                landingQueue.add(tempPlane1);
            }
        }
        if (temp2.size() > 0) {
            for (Airplane tempPlane2 : temp2) {
                landingQueue.add(tempPlane2);
            }
        }
        if (temp3.size() > 0) {
            for (Airplane tempPlane3 : temp3) {
                landingQueue.add(tempPlane3);
            }
        }
    }
    
    public String setAirplaneNumString() {
        int numMax = 9999;
        int numMin = 1000;
        int number;
        String tempPlaneNum = "";
        
        number = (int)((Math.random() * (numMax - numMin)) + numMin);
        tempPlaneNum += Integer.toString(number);
        
        return tempPlaneNum;
    }
}

// *************************************************************** \\
//    Old and unused code
//
//    private void addToQueue(Airplane plane, Queue landingQueue) {
//        // Creates temporary array for comparison
//        for (Airplane temp : landingQueue) {
//            tempQueue.add(temp);
//        }
//        // Clears old queue for reordering
//        landingQueue.clear();
//        
//        // If plane has a mechanical issue put it at the head of the queue
//        if (plane.MechanicalIssue()) {
//            landingQueue.add(plane);
//            for (Airplane temp : tempQueue) {
//                landingQueue.add(temp);
//            }
//        } else if (plane.LowFuel()) {
//            int count = tempQueue.size();
//            for (Airplane tempPlane : tempQueue) {
//                if (tempPlane.MechanicalIssue()) {
//                    landingQueue.add(tempPlane);
//                } else if (plane != null) {
//                    landingQueue.add(plane);
//                    landingQueue.add(tempPlane);
//                    plane = null;
//                } else {
//                    landingQueue.add(tempPlane);
//                }
//            }
//        }
//        
//    }
//    
//    private ImageIcon loadImage(String path) {
//        System.out.println("1");
//        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
//        System.out.println("2");
//        Image scaledImage = image.getScaledInstance(132, 132, java.awt.Image.SCALE_SMOOTH);
//        System.out.println("3");
//        return new ImageIcon(scaledImage);
//    }
//    
package airline;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mainframe extends JFrame {

    public static void main(String[] args) {
        new Mainframe().setVisible(true);
    }

    public Mainframe() {
        super("AIR INDIA AIRLINES - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(10, 61, 98));
        header.setPreferredSize(new Dimension(1500, 80));
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(ClassLoader.getSystemResource("icon/front.jpg")));
        logo.setPreferredSize(new Dimension(80, 80));
        header.add(logo, BorderLayout.WEST);
        JLabel title = new JLabel("AIR INDIA AIRLINES", SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.CENTER);
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 25));
        navPanel.setOpaque(false);
        String[] navs = {"Home", "Flights", "Bookings", "Payments", "Cancellations", "Logout"};
        for (String nav : navs) {
            JButton btn = new JButton(nav);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btn.setBackground(new Color(255,255,255,220));
            btn.setForeground(new Color(10, 61, 98));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            navPanel.add(btn);
            if (nav.equals("Logout")) {
                btn.setForeground(Color.RED);
                btn.addActionListener(e -> {
                    setVisible(false);
                    new Login().setVisible(true);
                });
            }
        }
        header.add(navPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Main Content with Background Image
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1100, 540));

        // Background image panel
        JPanel bgPanel = new JPanel() {
            Image bg = new ImageIcon(ClassLoader.getSystemResource("icon/front.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setBounds(0, 0, 1100, 540);
        layeredPane.add(bgPanel, Integer.valueOf(0));


        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBounds(0, 0, 1100, 540);

        JLabel welcome = new JLabel("Welcome to Air India Airlines!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setForeground(new Color(10, 61, 98));
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(welcome);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setOpaque(false);
        String[] actions = {"Book Flight", "View Flights", "My Bookings", "Payments", "Cancellations"};
        for (String action : actions) {
            JButton btn = new JButton(action);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setBackground(new Color(10, 61, 98));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(180, 50));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttonPanel.add(btn);

            switch (action) {
                case "View Flights":
                    btn.addActionListener(e -> new Flight_Info());
                    break;
                case "Book Flight":
                    btn.addActionListener(e -> {
                        try { new Add_Customer(); } catch (Exception ex) { ex.printStackTrace(); }
                    });
                    break;
                case "My Bookings":
                    btn.addActionListener(e -> {
                        try { new Journey_Details(); } catch (Exception ex) { ex.printStackTrace(); }
                    });
                    break;
                case "Payments":
                    btn.addActionListener(e -> {
                        try { new Payment_Details(); } catch (Exception ex) { ex.printStackTrace(); }
                    });
                    break;
                case "Cancellations":
                    btn.addActionListener(e -> new Cancel());
                    break;
            }
        }
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        layeredPane.add(mainPanel, Integer.valueOf(1));
        add(layeredPane, BorderLayout.CENTER);


        JPanel footer = new JPanel();
        footer.setBackground(new Color(10, 61, 98));
        JLabel copyright = new JLabel("© 2024 Air India Airlines. All rights reserved. | Contact: support@airindia.com");
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        copyright.setForeground(Color.WHITE);
        footer.add(copyright);
        add(footer, BorderLayout.SOUTH);
    }
}


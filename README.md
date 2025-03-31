package photos;

import photos.Photograph;
import photos.PhotographContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PhotoViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private PhotographContainer imageLibrary;
    private int albumPosition;

    private JMenuItem dateSortMenuItem;
    private JMenuItem ratingSortMenuItem;

    private ButtonGroup ratingButtonGroup;
    private JRadioButton rb1, rb2, rb3, rb4, rb5;

    private JLabel imageDisplayLabel;
    private JPanel controlPanel, displayPanel, thumbnailPanel, bottomPanel, panelPane;

    // Create the Menu
    public JMenuBar createMenu() {
        JMenuBar menuBar;
        JMenu menu;

        menuBar = new JMenuBar();
        menu = new JMenu("Sort");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription("Sort the photographs");
        menuBar.add(menu);

        // Adding Sort by Date and Sort by Rating to the menu
        dateSortMenuItem = new JMenuItem("Sort By Date", KeyEvent.VK_D);
        ratingSortMenuItem = new JMenuItem("Sort By Rating", KeyEvent.VK_R);

        menu.add(dateSortMenuItem);
        menu.add(ratingSortMenuItem);

        // Adding listeners for the sort menu items
        SortNavigationListener sortListener = new SortNavigationListener();
        dateSortMenuItem.addActionListener(sortListener);
        ratingSortMenuItem.addActionListener(sortListener);

        return menuBar;
    }

    // Getter and Setter for imageLibrary
    public PhotographContainer getImageLibrary() {
        return imageLibrary;
    }

    public void setImageLibrary(PhotographContainer imageLibrary) {
        this.imageLibrary = imageLibrary;
    }

    // Sort Navigation Listener for handling the Sort options
    class SortNavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == dateSortMenuItem) {
                getImageLibrary().getPhotos().sort(Photograph::compareTo);  // Sort by Date
            } else if (e.getSource() == ratingSortMenuItem) {
                getImageLibrary().getPhotos().sort(new CompareByRating());  // Sort by Rating
            }

            drawThumbnails();  // Redraw thumbnails to reflect sorting
            albumPosition = 0;  // Reset to first image after sorting
            displayPhoto(albumPosition);  // Display the first photo
        }
    }

    // Main method to initialize the PhotoViewer
    public static void main(String[] args) {
        PhotoViewer myViewer = new PhotoViewer();
        myViewer.setImageLibrary(new PhotoLibrary("Famous CS People", 1));

        Path p = Paths.get("src", "main", "resources", "Ada_Lovelace.jpg");
        String s = p.toAbsolutePath().toString();
        myViewer.getImageLibrary().addPhoto(new Photograph(s, "Ada Lovelace, often considered the first programmer", "1924-01-01", 5));

        p = Paths.get("src", "main", "resources", "Alan_Turing_Aged_16.jpg");
        s = p.toAbsolutePath().toString();
        myViewer.getImageLibrary().addPhoto(new Photograph(s, "Alan Turing, widely considered to the be the father of Artificial Intelligence and Theoretical Computer Science", "1928-01-01", 4));

        p = Paths.get("src", "main", "resources", "Dorothy_Vaughan.jpg");
        s = p.toAbsolutePath().toString();
        myViewer.getImageLibrary().addPhoto(new Photograph(s, "Dorthy Vaughn, leader in transition from human computers to digital computers.", "unknown", 5));

        p = Paths.get("src", "main", "resources", "ae.jpg");
        s = p.toAbsolutePath().toString();
        myViewer.getImageLibrary().addPhoto(new Photograph(s, "Annie Easley at NASA", "1955-01-01", 5));

        p = Paths.get("src", "main", "resources", "Grace_Hopper_and_UNIVAC.jpg");
        s = p.toAbsolutePath().toString();
        myViewer.getImageLibrary().addPhoto(new Photograph(s, "Grace Hopper, early creator of high-level programming languages", "1960-01-01", 4));

        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    // Initialize the GUI
    private void initialize() {
        setJMenuBar(createMenu());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(this.imageLibrary.getName());

        controlPanel = new JPanel();
        displayPanel = new JPanel();
        thumbnailPanel = new JPanel();
        bottomPanel = new JPanel();

        panelPane = new JPanel(new BorderLayout());

        imageDisplayLabel = new JLabel();
        imageDisplayLabel.addKeyListener(new NavigationKeyListener());  // Correct key listener
        imageDisplayLabel.setFocusable(true);
        imageDisplayLabel.requestFocusInWindow();

        displayPanel.add(imageDisplayLabel);

        ratingButtonGroup = new ButtonGroup();
        rb1 = new JRadioButton("1");
        rb2 = new JRadioButton("2");
        rb3 = new JRadioButton("3");
        rb4 = new JRadioButton("4");
        rb5 = new JRadioButton("5");

        bottomPanel.add(new JLabel("Rating"));
        ratingButtonGroup.add(rb1); bottomPanel.add(rb1);
        ratingButtonGroup.add(rb2); bottomPanel.add(rb2);
        ratingButtonGroup.add(rb3); bottomPanel.add(rb3);
        ratingButtonGroup.add(rb4); bottomPanel.add(rb4);
        ratingButtonGroup.add(rb5); bottomPanel.add(rb5);

        displayPhoto(albumPosition);

        panelPane.add(controlPanel, BorderLayout.PAGE_START);
        panelPane.add(displayPanel, BorderLayout.CENTER);
        panelPane.add(thumbnailPanel, BorderLayout.WEST);
        panelPane.add(bottomPanel, BorderLayout.SOUTH);
        this.getContentPane().add(panelPane);

        this.pack();
        this.setVisible(true);
    }

    // Redraw thumbnails after sorting
    private void drawThumbnails() {
        thumbnailPanel.removeAll();

        for (int i = 0; i < imageLibrary.numPhotographs(); i++) {
            final int imagePosition = i;
            Photograph p = imageLibrary.getPhotos().get(i);

            JLabel thumbnailLabel = new JLabel();
            thumbnailLabel.setToolTipText("<html><body width='300'>"
                    + p.getCaption() + "<br>(" + p.getDateTaken() + ")<br>"
                    + "rated: " + p.getRating() + "</body></html>");

            thumbnailLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    displayPhoto(imagePosition);
                }
            });

            try {
                BufferedImage originalImage = p.getImageData();
                int originalWidth = originalImage.getWidth();
                int originalHeight = originalImage.getHeight();

                // Calculate new height to maintain aspect ratio
                int newWidth = 102;
                int newHeight = (originalHeight * newWidth) / originalWidth;

                Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                // Convert the Image to a BufferedImage
                BufferedImage bufferedScaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedScaledImage.createGraphics();
                g2d.drawImage(scaledImage, 0, 0, null);
                g2d.dispose();

                if (newHeight > 77) {
                    // Crop the image to 102x77 by taking the center portion
                    int yOffset = (newHeight - 77) / 2;
                    BufferedImage croppedImage = bufferedScaledImage.getSubimage(0, yOffset, 102, 77);
                    thumbnailLabel.setIcon(new ImageIcon(croppedImage));
                } else {
                    thumbnailLabel.setIcon(new ImageIcon(bufferedScaledImage));
                }

            } catch (Exception e) {
                System.err.println("Could not scale the image: " + e.getMessage());
            }

            thumbnailPanel.add(thumbnailLabel);
        }

        thumbnailPanel.revalidate();
    }


    // Display the photo at the specified position
    private void displayPhoto(int position) {
        albumPosition = position;

        Photograph photograph = imageLibrary.getPhotos().get(position);
        BufferedImage img = photograph.getImageData();
        int rating = photograph.getRating();

        imageDisplayLabel.setIcon(new ImageIcon(img));
        ratingButtonGroup.clearSelection();

        switch (rating) {
            case 1: rb1.setSelected(true); break;
            case 2: rb2.setSelected(true); break;
            case 3: rb3.setSelected(true); break;
            case 4: rb4.setSelected(true); break;
            case 5: rb5.setSelected(true); break;
            default: assert(false);
        }

        displayPanel.repaint();
    }

    // Key listener for navigating through images
    class NavigationKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                albumPosition = (albumPosition + 1) % imageLibrary.numPhotographs();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                albumPosition = (albumPosition - 1);
                if (albumPosition < 0) {
                    albumPosition += imageLibrary.numPhotographs();
                }
            }
            displayPhoto(albumPosition);  // Update the displayed photo after navigating
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        @Override
        public void keyTyped(KeyEvent e) {}
    }
}

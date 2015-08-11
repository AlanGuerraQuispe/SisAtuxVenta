package com.aw.swing;

//import com.sun.java.swing.Painter;

/**
 * User: Juan Carlos Vergara
 * Date: 12/05/2009
 */
public class SliderSkinDemo {
	/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {




                for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(laf.getName())) {
                        try {
                            UIManager.setLookAndFeel(laf.getClassName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                UIManager.put("Nimbus.Overrides.InheritDefaults", false);
                UIManager.put("Button[Enabled].background", new Color(252, 240, 178));


//                for (Map.Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
//                    if (((String) entry.getKey()).startsWith("Slider")) {
//                        System.out.println(entry.getKey() + " = " + entry.getValue());
//                    }
//                }

                JFrame frame = new JFrame("Slider Skining Demo");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                JPanel panel = new JPanel(new GridLayout(0, 1, 20, 20));
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel.setBackground(Color.darkGray);

                UIDefaults sliderDefaults = new UIDefaults();

                sliderDefaults.put("Slider.thumbWidth", 20);
                sliderDefaults.put("Slider.thumbHeight", 20);
                sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter<JComponent>() {
                    public void paint(Graphics2D g, JComponent c, int w, int h) {
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setStroke(new BasicStroke(2f));
                        g.setColor(Color.RED);
                        g.fillOval(1, 1, w - 3, h - 3);
                        g.setColor(Color.WHITE);
                        g.drawOval(1, 1, w - 3, h - 3);
                    }
                });
                sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
                    public void paint(Graphics2D g, JComponent c, int w, int h) {
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setStroke(new BasicStroke(2f));
                        g.setColor(Color.GRAY);
                        g.fillRoundRect(0, 6, w - 1, 8, 8, 8);
                        g.setColor(Color.WHITE);
                        g.drawRoundRect(0, 6, w - 1, 8, 8, 8);
                    }
                });

                JSlider slider = new JSlider(0, 100, 50);
                panel.add(slider);
                slider.putClientProperty("Nimbus.Overrides", sliderDefaults);
                slider.putClientProperty("Nimbus.Overrides.InheritDefaults", false);

                // Add a normal themed slider for comparison
                JSlider normalSlider = new JSlider(0, 100, 50);
                panel.add(normalSlider);

                JButton jb = new JButton("My button");
                panel.add(jb);

                frame.getContentPane().add(panel, BorderLayout.CENTER);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
*/
}

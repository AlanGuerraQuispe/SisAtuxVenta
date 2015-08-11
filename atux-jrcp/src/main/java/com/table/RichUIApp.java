package com.table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RichUIApp extends JFrame
{
    RichUIApp ()
    {
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        setContentPane (new LoginScreen (this));
        pack ();
        setResizable (false);
        setLocationRelativeTo (null);
        setVisible (true);
    }

    public static void main (String [] args)
    {
        Runnable r = new Runnable ()
        {
            public void run ()
            {
                new RichUIApp ();
            }
        };
        EventQueue.invokeLater (r);
    }
}

class LoginScreen extends JPanel
{
    final static Font FNTLBL = new Font ("Arial", Font.BOLD, 14);
    final static Font FNTTXT = new Font ("Tahoma", Font.BOLD, 12);

    private JFrame frame;
    private JComponent initComp;
    private GradientPaint gp;

    LoginScreen (JFrame frame)
    {
        this.frame = frame;
        UIManager.put ("Button.focus", Color.BLACK);
        java.util.List gradients = new ArrayList(5);
        gradients.add (0.63f);
        gradients.add (0.12f);
        gradients.add (new Color (0xFF6600));
        gradients.add (new Color (0xFFCC33));
        gradients.add (new Color (0xFF9933));
        UIManager.put ("Button.gradient", gradients);
        UIManager.put ("Button.select", new Color (0xff, 0xcc, 0x33));
        createUI ();
    }

    void initFocus ()
    {
        initComp.requestFocusInWindow ();
    }

    private void createUI ()
    {
        setBorder (new EmptyBorder(10, 10, 10, 10));

        setLayout (new GridLayout (3, 1));

        JPanel panelRow = new JPanel ();
        panelRow.setOpaque (false);
        JLabel lblUserID = new JLabel ("UserID: ");
        lblUserID.setFont (FNTLBL);
        lblUserID.setForeground (Color.WHITE);
        panelRow.add (lblUserID);
        JTextField txtUserID = new JTextField (10);
        txtUserID.setFont (FNTTXT);
        Border bdText = new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY,
                                         Color.GRAY);
        Border bdMargin = new EmptyBorder (0, 1, 0, 1);
        Border bdCompound = new CompoundBorder(bdText, bdMargin);
        txtUserID.setBorder (bdCompound);
        panelRow.add (txtUserID);
        add (panelRow);

        panelRow = new JPanel ();
        panelRow.setOpaque (false);
        JLabel lblPassword = new JLabel ("Password: ");
        lblPassword.setFont (FNTLBL);
        lblPassword.setForeground (Color.WHITE);
        panelRow.add (lblPassword);
        JPasswordField txtPassword = new JPasswordField (10);
        txtPassword.setFont (FNTTXT);
        txtPassword.setBorder (bdCompound);
        panelRow.add (txtPassword);
        add (panelRow);

        lblUserID.setPreferredSize (lblPassword.getPreferredSize ());

        panelRow = new JPanel ();
        panelRow.setLayout (new FlowLayout (FlowLayout.CENTER, 30, 0));
        panelRow.setOpaque (false);
        ImageIcon ii = new ImageIcon ("key1.gif");
//        ii.setImage (ImageUtils.makeReflectedImage (ii.getImage (), 5, 0, 0.5f));
        panelRow.add (new JLabel (ii));
        JButton btnLogin = new JButton ("LOGIN");
        Border bdButton = new BevelBorder (BevelBorder.RAISED, Color.WHITE,
                                           Color.BLACK);
        Insets insets = btnLogin.getMargin ();
        bdMargin = new EmptyBorder (insets.top+1, insets.left+1,
                                    insets.bottom+1, insets.right+1);
        btnLogin.setBorder (new CompoundBorder (bdButton, bdMargin));
        ActionListener alLogin;
        alLogin = new ActionListener ()
        {
            public void actionPerformed (ActionEvent ae)
            {
                // Validate txtUserID's entered user ID and txtPassword's
                // entered password, perhaps by connecting to a database and
                // scanning an authentication table for the presence of the
                // entered ID and password.

                // Assuming successful validation, move to the next screen,
                // which happens to be the logout screen (for simplicity).

                LogoutScreen los = new LogoutScreen (frame);
                frame.setContentPane (los);
                frame.pack ();
                los.initFocus ();
                frame.setLocationRelativeTo (null);
                frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
            }
        };
        btnLogin.addActionListener (alLogin);
        panelRow.add (btnLogin);
        ii = new ImageIcon ("key2.gif");
//        ii.setImage (ImageUtils.makeReflectedImage (ii.getImage (), 5, 0, 0.5f));
        panelRow.add (new JLabel (ii));
        add (panelRow);

        initComp = txtUserID;
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        if (gp == null)
            gp = new GradientPaint (0, 0, Color.BLACK, 0, getHeight (),
                                    new Color (96, 96, 96));
        ((Graphics2D) g).setPaint (gp);
        g.fillRect (0, 0, getWidth (), getHeight ());
    }
}

class LogoutScreen extends JPanel
{
    private JFrame frame;
    private JComponent initComp;
    private GradientPaint gp;

    LogoutScreen (JFrame frame)
    {
        this.frame = frame;
        createUI ();
    }

    void initFocus ()
    {
        initComp.requestFocusInWindow ();
    }

    private void createUI ()
    {
        setPreferredSize (new Dimension (400, 400));

        JButton btnLogout = new JButton ("LOGOUT");
        Border bdButton = new BevelBorder (BevelBorder.RAISED, Color.WHITE,
                                           Color.BLACK);
        Insets insets = btnLogout.getMargin ();
        Border bdMargin = new EmptyBorder (insets.top+1, insets.left+1,
                                           insets.bottom+1, insets.right+1);
        btnLogout.setBorder (new CompoundBorder (bdButton, bdMargin));
        ActionListener alLogout;
        alLogout = new ActionListener ()
        {
            public void actionPerformed (ActionEvent ae)
            {
                LoginScreen lis = new LoginScreen (frame);
                frame.setContentPane (lis);
                frame.pack ();
                lis.initFocus ();
                frame.setLocationRelativeTo (null);
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            }
        };
        btnLogout.addActionListener (alLogout);
        add (btnLogout);

        initComp = btnLogout;
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        if (gp == null)
            gp = new GradientPaint (0, 0, Color.BLACK, 0, getHeight (),
                                    new Color (96, 96, 96));
        ((Graphics2D) g).setPaint (gp);
        g.fillRect (0, 0, getWidth (), getHeight ());
    }
}

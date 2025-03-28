package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    Score(String name, int score) {
        setBounds(400, 150, 1080, 720);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(i1.getIconWidth(), i1.getIconHeight(), Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, i1.getIconWidth(), i1.getIconHeight());
        add(image);

        JLabel heading = new JLabel("Thank You " + name + "! Autobots defend them.");
        heading.setBounds(i1.getIconWidth()+50, 30, 1200, 30);
        heading.setForeground(new Color(30, 144, 254));
        heading.setFont(new Font("Consolas", Font.PLAIN, 26));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(i1.getIconWidth()+175, 200, 300, 30);
        lblscore.setForeground(new Color(30, 144, 254));
        lblscore.setFont(new Font("Consolas", Font.BOLD, 26));
        add(lblscore);

        JButton submit = new JButton("Play Again");
        submit.setBounds(i1.getIconWidth()+225, 270, 120, 30);
        submit.setBackground(Color.black);
        submit.setForeground(new Color(30, 144, 254));

        submit.addActionListener(this);
        add(submit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

//    public static void main(String[] args) {
//        new Score("User", 0);
//    }
}
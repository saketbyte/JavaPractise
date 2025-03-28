package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton back, start;
    String name, numQuestions, difficulty, category;
    Rules(String name, String numQuestions, String difficulty, String category){
        this.name = name;
        this.numQuestions=numQuestions;
        this.difficulty=difficulty;
        this.category=category;

        getContentPane().setBackground(Color.black);
        setLayout(null);

        Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenRes.width;
        int height = screenRes.height;
        int windowWidth =  (int)(0.75* width);
        int windowHeight =  (int)(0.75* height);

        // Label
        // Heading: Quizverse
        JLabel heading = new JLabel("Welcome " + this.name + " to Quizverse:");
        heading.setBounds( 40, 40, 720, 45); // Place it on the right side
        heading.setFont(new Font("Consolas", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        // Writing Rules
        String rulePage = "<html>" +
                "1. Each question has only one correct answer, choose wisely." + "<br><br>" +
                "2. You have 25 seconds to answer each question, time waits for no one." + "<br><br>" +
                "3. Once submitted, your answer is final—there’s no turning back." + "<br><br>" +
                "4. Correct answers give you +10 points, wrong or unanswered questions give you nothing." + "<br><br>" +
                "5. 1 lifeline Orion (50-50) where Orian Pax /Optimus Prime defeats two wrong options for you!" + "<br><br>" +
                "6. Choose your best realm! " + "<br><br>" +
                "7. Freedom is the right of all Sentient Beings! - Optimus, respect that!" + "<br><br>" +
                "8. Have fun, learn, and may your knowledge be greater than any Sentient Being!" + "<br><br>" +
                "</html>";

        JLabel ruleBook = new JLabel();
        ruleBook.setBounds(40, 100, 720, 3*windowHeight/4);
        ruleBook.setFont(new Font("Consolas", Font.PLAIN, 20));
        ruleBook.setForeground(new  Color(30, 144, 254));
        ruleBook.setText(rulePage);
        add(ruleBook);


        start = new JButton("Begin!");
        start.setBounds(windowWidth-512,windowHeight-150, 180, 45); // Place it on the right side
        start.setFont(new Font("Consolas", Font.BOLD, 30));
        start.setForeground(new  Color(30, 144, 254));
        start.setBackground(new Color(0, 0, 0));
        start.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // adding action listener
        start.addActionListener(this);
        add(start);




        back = new JButton("Back");
        back.setBounds(windowWidth-256,windowHeight-150, 180, 45); // Place it on the right side
        back.setFont(new Font("Consolas", Font.BOLD, 30));
        back.setForeground(new  Color(30, 144, 254));
        back.setBackground(new Color(0, 0, 0));
        back.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // adding action listener
        back.addActionListener(this);
        add(back);


        setSize(windowWidth, windowHeight);
        setLocation(100,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == start) {
            setVisible(false);
            if(numQuestions.equals(""))
            {
                numQuestions="10";
            }
            if(category.equals(""))
            {
                category="science";
            }

            if(difficulty.equals(""))
            {
                difficulty="easy";
            }
            new Quiz(name, numQuestions,difficulty,category);
        } else {
            setVisible(false);
            new Login();
        }
    }

//    public static void main(String[] args) {
//        new Rules("User", "10", "easy","science");
//    }

}

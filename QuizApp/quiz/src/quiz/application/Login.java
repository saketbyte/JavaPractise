package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//Extending JFrame for UI and ActionListener to capture events on the frame.
// Interfaces will have an unimplemented method in it, which we need to write in the class which is implementing it.

public class Login extends JFrame implements ActionListener {

    JButton goToRules, back;
    JTextField nameTextField, numberOfQuestionsTextField;
    JComboBox categoryDropDown, difficultyDropDown;
    String[] levels = {"","easy", "medium", "hard"};
    String[] categories = {"","music", "sport_and_leisure", "film_and_tv", "arts_and_literature", "history", "society_and_culture", "science", "geography", "food_and_drink", "general_knowledge"};
    String seletedNumberOfQuestions="",selectedCategory="", selectedDifficulty="";
    Login() {
        Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenRes.width;
        int height = screenRes.height;
        int windowWidth =  (int)(0.75* width);
        int windowHeight =  (int)(0.75* height);

        setSize(windowWidth, windowHeight);
        setLocation(100, 100);
        getContentPane().setBackground(Color.BLACK);
        // Important to setLayout to null otherwise it does not function properly. (read more later)
        setLayout(null);

        // Load ImageIcon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));

        JLabel image = new JLabel(i1);

        // Set image to cover the left half of the window
        image.setBounds(0, 0, i1.getIconWidth(),  windowHeight);
        add(image);

        // Heading: Quizverse
        JLabel heading = new JLabel("Quizverse");
        heading.setBounds(windowWidth/2-100, 60, 300, 45); // Place it on the right side
        heading.setFont(new Font("Consolas", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        // Enter Candidate name component
        JLabel name = new JLabel("Enter Candidate Name:");
        name.setBounds(windowWidth/2-100, 115, 300, 45); // Place it on the right side
        name.setFont(new Font("Consolas", Font.BOLD, 24));
        name.setForeground(new Color(255, 255, 255));
        add(name);

        //TextField
        nameTextField = new JTextField();
        nameTextField.setBounds(windowWidth/2+190, 115, 300, 40); // Place it on the right side
        nameTextField.setFont(new Font("Consolas", Font.BOLD, 30));
        nameTextField.setBackground(new Color(215, 214, 214));
        nameTextField.setForeground(new Color(0, 0, 0));
        add(nameTextField);

        // number of questions, difficulty, category
        JLabel qNum = new JLabel("Number of questions:");
        qNum.setBounds(windowWidth/2-100, 165, 400, 45); // Place it on the right side
        qNum.setFont(new Font("Consolas", Font.BOLD, 24));
        qNum.setForeground(new Color(255, 255, 255));
        add(qNum);

        //TextField
        numberOfQuestionsTextField = new JTextField();
        numberOfQuestionsTextField.setBounds(windowWidth/2+190, 165, 300, 40); // Place it on the right side
        numberOfQuestionsTextField.setFont(new Font("Consolas", Font.BOLD, 30));
        numberOfQuestionsTextField.setBackground(new Color(215, 214, 214));
        numberOfQuestionsTextField.setForeground(new Color(0, 0, 0));
        add(numberOfQuestionsTextField);

        // Enter Category
        JLabel category = new JLabel("Pick the Realm:");
        category.setBounds(windowWidth/2-100, 215, 360, 45); // Place it on the right side
        category.setFont(new Font("Consolas", Font.BOLD, 24));
        category.setForeground(new Color(255, 255, 255));
        add(category);

        //TextField
        categoryDropDown = new JComboBox(categories);
        // Place it on the right side
        categoryDropDown.setBounds(windowWidth/2+190, 215, 360, 45); // Place it on the right side
        categoryDropDown.setFont(new Font("Consolas", Font.BOLD, 30));
        categoryDropDown.setBackground(new Color(215, 214, 214));
        categoryDropDown.setForeground(new Color(0, 0, 0));
        categoryDropDown.addActionListener(this);
        add(categoryDropDown);


        JLabel difficulty = new JLabel("Difficulty Level:");
        difficulty.setBounds(windowWidth/2-100, 265, 300, 45); // Place it on the right side
        difficulty.setFont(new Font("Consolas", Font.BOLD, 24));
        difficulty.setForeground(new Color(255, 255, 255));
        add(difficulty);

        //TextField
        difficultyDropDown = new JComboBox(levels);
        // Place it on the right side
        difficultyDropDown.setBounds(windowWidth/2+190, 265, 300, 45); // Place it on the right side
        difficultyDropDown.setFont(new Font("Consolas", Font.BOLD, 30));
        difficultyDropDown.setBackground(new Color(215, 214, 214));
        difficultyDropDown.setForeground(new Color(0, 0, 0));
        difficultyDropDown.addActionListener(this);
        add(difficultyDropDown);


        // goToRules Button

        goToRules = new JButton("Start");
        goToRules.setBounds(windowWidth/2-110, windowHeight-150, 196, 45); // Place it on the right side
        goToRules.setFont(new Font("Consolas", Font.BOLD, 30));
        goToRules.setForeground(new  Color(30, 144, 254));
        goToRules.setBackground(new Color(0, 0, 0));
            // adding action listener
        goToRules.addActionListener(this);
        add(goToRules);

        // Back Button
        back = new JButton("Back");
        back.setBounds(windowWidth/2+380, windowHeight-150, 180, 45); // Place it on the right side
        back.setFont(new Font("Consolas", Font.BOLD, 30));
        back.setForeground(new  Color(30, 144, 254));
        back.setBackground(new Color(0, 0, 0));
        // adding action listener
        back.addActionListener(this);
        add(back);




        setVisible(true);
        setTitle("Quizverse");
    }

    public void actionPerformed(ActionEvent ae){
        // we can differentiate which button has been clicked by checking the source.
        if(ae.getSource()==goToRules){
            String name = nameTextField.getText();
            seletedNumberOfQuestions = numberOfQuestionsTextField.getText();

            setVisible(false);
            // Start
            new Rules(name,seletedNumberOfQuestions, selectedDifficulty,selectedCategory);
        }
        else if(ae.getSource()==back){
            setVisible(false);
        }
        else if(ae.getSource()==categoryDropDown){
            selectedCategory=categoryDropDown.getSelectedItem().toString();
            System.out.println(selectedCategory);

        }
        else if(ae.getSource()==difficultyDropDown){
            selectedDifficulty=difficultyDropDown.getSelectedItem().toString();
            System.out.println(selectedDifficulty);

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

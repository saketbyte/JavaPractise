package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {

//    String questions[][] = new String[10][5];
//    String answers [][] = new String[10][2];
    String userAnswers[][] = new String[10][2];


    String questions [][];
    String answers [][];



    JLabel qno, questionText;
    JRadioButton opt1,opt2,opt3,opt4;
    ButtonGroup groupOptions;
    JButton nextButton, lifeLine, submit;
    int count =0;
    boolean lifeLineFlag = false;

    public static int timer = 25;
    int score=0;

    String name;

    Quiz(String name, String numQuestions, String difficulty, String category){

        String[][][] data = api.fetchTriviaQuestions(Integer.valueOf(numQuestions), difficulty,category);
        this.questions = data[0]; // Assign questions array
        this.answers = data[1];   // Assign answers array
        this.userAnswers = new String[Integer.valueOf(numQuestions)][2];

        Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenRes.width;
        int height = screenRes.height;
        int windowWidth =  (int)(0.75* width);
        int windowHeight =  (int)(0.75* height);
        System.out.println(windowHeight + " " + windowWidth);
        setSize(windowWidth,windowHeight);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        this.name = name;
        // Load original image
        ImageIcon q1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpeg"));

        // Scale the image to fit full width
        Image scaledImage = q1.getImage().getScaledInstance(windowWidth, q1.getIconHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Set image in JLabel
        JLabel quizImage = new JLabel(scaledIcon);
        quizImage.setBounds(0, 0, windowWidth, q1.getIconHeight());
        add(quizImage);

        // Question Number
        qno = new JLabel(Integer.toString(count+1));
        qno.setBounds(64, 256, 40,50);
        qno.setFont(new Font("Consolas", Font.BOLD, 24));
        qno.setForeground(new Color(30, 144, 254));
        add(qno);

        questionText = new JLabel();
        questionText.setBounds(100, 256, 1200,60);
        questionText.setFont(new Font("Consolas", Font.BOLD, 28));
        questionText.setForeground(new Color(30, 144, 254));
        add(questionText);

        // Options
        // Buttons



        opt1 = new JRadioButton();
        opt1.setBounds(100,350,1200,20);
        opt1.setBackground(Color.black);
        opt1.setForeground(new Color(30, 144, 254));
        opt1.setFont(new Font("Consolas", Font.ITALIC,20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(100,380,1200,20);
        opt2.setBackground(Color.black);
        opt2.setForeground(new Color(30, 144, 254));
        opt2.setFont(new Font("Consolas", Font.ITALIC,20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(100,410,1200,20);
        opt3.setBackground(Color.black);
        opt3.setForeground(new Color(30, 144, 254));
        opt3.setFont(new Font("Consolas", Font.ITALIC,20));
        add(opt3);
        opt4 = new JRadioButton();
        opt4.setBounds(100,440,1200,20);
        opt4.setBackground(Color.black);
        opt4.setForeground(new Color(30, 144, 254));
        opt4.setFont(new Font("Consolas", Font.ITALIC,20));
        add(opt4);


        groupOptions = new ButtonGroup();

        groupOptions.add(opt1);
        groupOptions.add(opt2);
        groupOptions.add(opt3);
        groupOptions.add(opt4);

        nextButton = new JButton("NEXT");
        nextButton.setBounds(1100,666,200,40);
        nextButton.setFont(new Font("Consolas", Font.BOLD, 30));
        nextButton.setForeground(new  Color(30, 144, 254));
        nextButton.setBackground(new Color(0, 0, 0));
        nextButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        nextButton.addActionListener(this);
        add(nextButton);

        lifeLine = new JButton("Orion");
        lifeLine.setBounds(800,666,200,40);
        lifeLine.setFont(new Font("Consolas", Font.BOLD, 30));
        lifeLine.setForeground(new  Color(30, 144, 254));
        lifeLine.setBackground(new Color(0, 0, 0));
        lifeLine.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lifeLine.addActionListener(this);
        add(lifeLine);

        submit = new JButton("Submit");
        submit.setBounds(100,666,200,40);
        submit.setFont(new Font("Consolas", Font.BOLD, 30));
        submit.setForeground(new  Color(30, 144, 254));
        submit.setBackground(new Color(0, 0, 0));
        submit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        submit.setEnabled(false);
        submit.addActionListener(this);
        add(submit);


        JLabel timePrompt = new JLabel("Seconds Left:");
        timePrompt.setBounds(1100, 220, 800,50);
        timePrompt.setFont(new Font("Consolas", Font.BOLD, 18));
        timePrompt.setForeground(Color.green);
        add(timePrompt);

        progress(0);
        setVisible(true);
    }

    public void progress(int c){

        if(lifeLineFlag) {
            clearLifeLine();
            lifeLineFlag = false;
    }

        qno.setText(Integer.toString(c + 1));
        questionText.setText("<html><p>" +questions[c][0]+"</p></html>");
        opt1.setText(questions[c][1]);
        opt1.setActionCommand(questions[c][1]);

        opt2.setText(questions[c][2]);
        opt2.setActionCommand(questions[c][2]);

        opt3.setText(questions[c][3]);
        opt3.setActionCommand(questions[c][3]);

        opt4.setText(questions[c][4]);
        opt4.setActionCommand(questions[c][4]);

        groupOptions.clearSelection();
        timer = 25;

        // Disable NEXT and enable SUBMIT on the last question
        if (c == questions.length - 1) {
            nextButton.setEnabled(false);
            submit.setEnabled(true);
        }
    }



    public void paint(Graphics g){
        super.paint(g); // call super class's paint to paint fresh paint
        String time = Integer.toString(timer);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Consolas", Font.BOLD, 18));

        if(timer>0){
            if(timer<=5) g.setColor(Color.RED);
            g.drawString(time, 1250, 280);
        }
        else{
            g.drawString("Time up!", 1250, 280);

            if(groupOptions.getSelection() == null)
                userAnswers[count][0] = "";
            else{
                userAnswers[count][0] = groupOptions.getSelection().getActionCommand();
            }
            try {
                Thread.sleep(1000);
                if(count+1<questions.length) {
                    count += 1;
                    progress(count);
                }
                else{
                    if (count+1 >= questions.length) {
                        nextButton.setEnabled(false);
                        submit.setEnabled(true);
                        if(timer<=0 && count+1 == questions.length){
                            for(int i =0;i<userAnswers.length;i++){
                                if (userAnswers[i][0] != null && userAnswers[i][0].equals(answers[i][0])) {

                                    score+=10;
                                }
                                setVisible(false);
                                new Score(name, score);
                            }
                        }
                    }


                }
            } catch(Exception e){
                e.printStackTrace();
            }

        }
        if(timer<=0 && count==questions.length){
            for(int i =0;i<userAnswers.length;i++){
                if (userAnswers[i][0] != null && userAnswers[i][0].equals(answers[i][0])) {

                    score+=10;
                }
                setVisible(false);
                new Score(name, score);
            }
        }
        timer--;

        try {
            Thread.sleep(1000);
            repaint();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearLifeLine(){
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
    }

    public void applyLifeLine(){
        lifeLineFlag =true;
        lifeLine.setEnabled(false);
        int n=2;
        for(int i =1;i<=4 && n>0 ;i++){
            if(!questions[count][i].equals(answers[count][0])){
                {
                    if(i==1){
                        opt1.setEnabled(false);
                    }
                    else if(i==2){
                        opt2.setEnabled(false);
                    }
                    else if(i==3){
                        opt3.setEnabled(false);
                    }
                    else if(i==4){
                        opt4.setEnabled(false);
                    }
                }
                n--;
            }
        }

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {

            if(groupOptions.getSelection() == null) {
                userAnswers[count][0] = "";
            } else {
                userAnswers[count][0] = groupOptions.getSelection().getActionCommand();
            }

            count += 1;

            if (count == questions.length - 1) {
                nextButton.setEnabled(false);
                submit.setEnabled(true);
            }

            progress(count);
        } else if (ae.getSource() == lifeLine) {
            applyLifeLine();
        }
        else if (ae.getSource() == submit){
            for(int i =0;i<userAnswers.length;i++){
                if (userAnswers[i][0] != null && userAnswers[i][0].equals(answers[i][0])) {

                    score+=10;
                }
                setVisible(false);
                new Score(name, score);
            }

        }
    }

//    public static void main(String[] args) {
//        new Quiz("User","10","easy","science");
//    }
}

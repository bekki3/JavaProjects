import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.function.Function;
import java.lang.Thread;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.lang.Integer;
class Frame extends JFrame implements ActionListener
{
    String answer1;
    String name="NoName";
    Random rand=new Random();
    File file;
    int operand1=0;
    int operand2=0;
    int operator;
    int result;
    int range=2;
    int score=0;
    int time=200;
    boolean timeRunning=false;
    JFrame difficultyFrame;
    JTextField answer;
    JLabel problemDisplay;
    JLabel scoreDisplay;
    JLabel correctDisplay;
    JLabel timeDisplay;
    JButton difEasy;
    JButton difNormal;
    JButton difHard;
    JButton difInsane;
    JTextField nameScoreInput;
    JLabel recordDisplayText;
    Frame()
    {
        JLabel mathGameTxt=new JLabel(" Math Game!");
        JLabel copyright=new JLabel("(c)Behzod all rights reserved.");
        setDifficulty();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocation(450, 150);
        problemDisplay=new JLabel();
        correctDisplay=new JLabel();
        answer=new JTextField();
        scoreDisplay=new JLabel("Score: 0");
        timeDisplay=new JLabel();
        timeDisplay.setBorder(BorderFactory.createLineBorder(Color.green, 5));
        scoreDisplay.setBorder(BorderFactory.createLineBorder(new Color(0xACDDDE), 3));
        problemDisplay.setBorder(BorderFactory.createLineBorder(new Color(0x9EA0A3), 3));
        answer.setBorder(BorderFactory.createLineBorder(new Color(0x92B1B6), 3));
        mathGameTxt.setBounds(100,10,200,30);
        problemDisplay.setBounds(100,60, 200,30);
        answer.setBounds(100,100,200,30);
        scoreDisplay.setBounds(100,140,200,30);
        timeDisplay.setBounds(100, 190, time, 3);
        correctDisplay.setBounds(100, 230, 200,30);
        copyright.setBounds(250,350,200,20);
        mathGameTxt.setFont(new Font("Serif", Font.ITALIC, 36));
        copyright.setFont(new Font("Serif", Font.PLAIN, 12));
        scoreDisplay.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        problemDisplay.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        answer.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        correctDisplay.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
        answer.addActionListener(this);
        this.add(problemDisplay);
        this.add(answer);
        this.add(scoreDisplay);
        this.add(correctDisplay);
        this.add(timeDisplay);
        this.add(mathGameTxt);
        this.setResizable(false);
        this.setSize(420,420);
        this.add(copyright);
        setQuestion();
    }
    public void setRecord()
    {
        file=new File("record.txt");
        try 
        {
            if (file.createNewFile()) 
            {
                //System.out.println("File created: " + file.getName());
            } 
            else 
            {
               // System.out.println("File already exists.");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String recordedData="";
        int recordedScore=0;
        try 
        {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              recordedData+=data;
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String temp="0";
        char[] array = recordedData.toCharArray();
        for(int i=0; i<=(recordedData.length()-1); i++)
        {
            if (Integer.valueOf(array[i])>=48&&Integer.valueOf(array[i])<=57) 
            {
                temp+=array[i];
            }
        }
        recordedScore=Integer.parseInt(temp);
        if(recordedData==""||recordedScore<score)
        {
            correctDisplay.setText("Record Updated!");
            try {
                FileWriter write=new FileWriter("record.txt");
                write.write(name+" "+score);
                write.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
    }
    public void setDifficulty()
    {
        String tmpRecordedData="";
        File tmpFile=new File("record.txt");
        recordDisplayText=new JLabel();
        try 
        {
            if (tmpFile.createNewFile()) 
            {
                recordDisplayText.setText("Record: Not Played!");
            } 
            else 
            {
                try 
                {
                    Scanner myReader = new Scanner(tmpFile);
                    while (myReader.hasNextLine()) 
                    {
                    String data = myReader.nextLine();
                    tmpRecordedData+=data;
                    }
                    myReader.close();
                } 
                catch (FileNotFoundException e) 
                {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                recordDisplayText.setText("Record: "+tmpRecordedData);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        JLabel askForNameTxt=new JLabel("Your name here:");
        nameScoreInput=new JTextField();
        difficultyFrame=new JFrame("Choose the difficulty!");
        difEasy=new JButton("Easy");
        difNormal=new JButton("Normal");
        difHard=new JButton("Hard");
        difInsane=new JButton("Insane");
        difEasy.setBounds(50,20,200,30);
        difNormal.setBounds(50,60,200,30);
        difHard.setBounds(50,100,200,30);
        difInsane.setBounds(50,140,200,30);
        askForNameTxt.setBounds(50,180,100,20);
        nameScoreInput.setBounds(150,180,100,20);
        recordDisplayText.setBounds(100,210,200,20);
        askForNameTxt.setFont(new Font("Serif", Font.BOLD, 12));
        nameScoreInput.addActionListener(this);
        difEasy.addActionListener(this);
        difNormal.addActionListener(this);
        difHard.addActionListener(this);
        difInsane.addActionListener(this);
        difficultyFrame.setLayout(null);
        difficultyFrame.setLocation(500,210);
        difficultyFrame.setVisible(true);
        difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        difficultyFrame.add(difEasy);
        difficultyFrame.add(difNormal);
        difficultyFrame.add(difHard);
        difficultyFrame.add(difInsane);
        difficultyFrame.add(nameScoreInput);
        difficultyFrame.add(recordDisplayText);
        difficultyFrame.add(askForNameTxt);
        difficultyFrame.setResizable(false);
        difficultyFrame.setSize(300,300);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==answer) 
        {
            answer1=answer.getText();
            answer.setText("");
            setAnswer();
            if(timeRunning==false)
            {
                setTimeout();
                timeRunning=true;
            }
        }
        else if(e.getSource()==difEasy)
        {
            range=2;
        }
        else if(e.getSource()==difNormal)
        {
            range=15;
        }
        else if(e.getSource()==difHard)
        {
            range=30;
        }
        else if(e.getSource()==difInsane)
        {
            range=50;
        }
        else if(e.getSource()==nameScoreInput)
        {
            name=nameScoreInput.getText();
        }
        if(e.getSource()==difHard||e.getSource()==difNormal||e.getSource()==difEasy||e.getSource()==difInsane)
        {
            difficultyFrame.setVisible(false);
            this.setVisible(true);
        }
        
    }
    public void setTimeout()
    {
        new Thread(() -> {
            try {
                while (time>0) {
                    Thread.sleep(100);
                time-=1;
                timeDisplay.setBounds(100, 190, time, 3);
                if (time==100) {
                    timeDisplay.setBorder(BorderFactory.createLineBorder(Color.orange, 6));
                }
                else if(time==50)
                {
                    timeDisplay.setBorder(BorderFactory.createLineBorder(Color.red, 7));
                }
                }
                gameOver();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    public void setAnswer()
    {
        correctDisplay.setVisible(true);
        if(result==Integer.valueOf(answer1)) 
        {
            correctDisplay.setText("Correct!");
            time+=20;
            score++;
            range++;
        }
        else{
            correctDisplay.setText("Wrong!");
            time-=10;
            score--;
        }
        if(range%5==0)
        {
            correctDisplay.setText("Level Increased!");
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                correctDisplay.setVisible(false);
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
        setQuestion();
    }
    public void setQuestion()
    {
        scoreDisplay.setText("Score: "+String.valueOf(score));
        operand1= rand.nextInt(range);
        operand2=(rand.nextInt(range))+1;
        operator=rand.nextInt(5);
        switch (operator) 
        {
            case 0:
            {
                problemDisplay.setText(operand1+"*"+operand2);
                result=operand1*operand2;
                break;
            }
            case 1:
            {
                problemDisplay.setText(operand1+"/"+operand2);
                result=operand1/operand2;
                break;
            }
            case 2:
            {
                problemDisplay.setText(operand1+"+"+operand2);
                result=operand1+operand2;
                break;
            }
            case 3:
            {
                problemDisplay.setText(operand1+"-"+operand2);
                result=operand1-operand2;
                break;
            }
            case 4:
            {
                if(operand1<operand2)
                {
                    problemDisplay.setText(operand2+"%"+(operand1+1));
                    result=operand2%(operand1+1);
                }
                else
                {
                    problemDisplay.setText(operand1+"%"+operand2);
                    result=operand1%operand2;
                }
                break;
            }
        }
    }
    public void gameOver()
    {
        correctDisplay.setVisible(true);
        answer.setVisible(false);
        problemDisplay.setVisible(false);
        correctDisplay.setText("GAME OVER!");
        setRecord();
    }
}
public class MathGame
{
    public static void main(String[] args) 
    {
        new Frame();

    }
}
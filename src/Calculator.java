import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import calculate.*;

import static java.awt.SystemColor.text;

public class Calculator extends JFrame {
    static JLabel label;
    static JLabel info;
    double result = 0.0;
    String math = "";
    double num = 0.0;
    String lastPressedButtonText = "";


    public Calculator() {
        //레이아웃
        this.setTitle("계산기");
        this.setDefaultCloseOperation(3);
        Container MyCon = this.getContentPane();
        MyCon.setLayout(new BorderLayout(5, 5));
        MyCon.setBackground(new Color(238, 238, 238));

        //상단 패널
        UpPanel UP = new UpPanel();
        MyCon.add(UP,  "East");

        //하단 패널
        DownPanel DP = new DownPanel();
        MyCon.add(DP, "South");
        this.setSize(350, 600);
        this.setVisible(true);
    }

    private void setBackSpace(String bs) {
        label.setText(bs);
    }

    private String getBackSpace() {
        return label.getText();
    }

    class DownPanel extends JPanel { //버튼
        public DownPanel() {
            JButton[] bt = new JButton[24];
            this.setLayout(new GridLayout(6, 4, 5, 5));
            this.setBackground(new Color(238, 238, 238));
            bt[0] = new JButton("%");
            bt[1] = new JButton("CE");
            bt[2] = new JButton("C");
            bt[3] = new JButton("<<");
            bt[4] = new JButton("1/x");
            bt[5] = new JButton("x²");
            bt[6] = new JButton("2√x");
            bt[7] = new JButton("÷");
            bt[8] = new JButton("7");
            bt[9] = new JButton("8");
            bt[10] = new JButton("9");
            bt[11] = new JButton("×");
            bt[12] = new JButton("4");
            bt[13] = new JButton("5");
            bt[14] = new JButton("6");
            bt[15] = new JButton("-");
            bt[16] = new JButton("1");
            bt[17] = new JButton("2");
            bt[18] = new JButton("3");
            bt[19] = new JButton("+");
            bt[20] = new JButton("+/-");
            bt[21] = new JButton("0");
            bt[22] = new JButton(".");
            bt[23] = new JButton("=");

            for(int i = 0; i <= 23; i++) { //모든 버튼 레이아웃
                bt[i].setPreferredSize(new Dimension(0, 65));
                bt[i].setFont(new Font("맑은 고딕", 0, 20));
                bt[i].setForeground(Color.BLACK);
                bt[i].setBackground(Color.white);

                if (7 < i && i < 19 && i%4 != 3) { //1-9, 0을 제외한 숫자
                    bt[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JButton b = (JButton)e.getSource(); //숫자 누르면 b = 숫자

                            String labelText = Calculator.label.getText();

                            String text = b.getText();
                            String newText = text;
                            int n = newText.length();

                            System.out.println("숫자=======================================================================");
                            System.out.println("labelText: " + labelText);
                            System.out.println("text: " + text);
                            System.out.println("result: " + result);
                            System.out.println("newText: " + newText);


                            if (Calculator.label.getText().equals("0")) {
                                Calculator.label.setText("");
                            }

                            if (labelText == "") {
                                labelText = "0";
                            }

                            //String newText = String.valueOf(Integer.parseInt(labelText) + Integer.parseInt(text));

                            if (n <= 10) {
                                Calculator.label.setText(newText);
                            }
                            if (Calculator.info.getText().contains("=")) {
                                Calculator.info.setText("");
                                Calculator.label.setText(b.getText());
                                Calculator.this.result = 0.0;
                                Calculator.this.num = 0.0;
                            }
                        }
                    });
                } else if (i == 21) { //0
                    bt[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JButton b = (JButton)e.getSource();
                            String labelText = Calculator.label.getText();
                            String text = b.getText();
                            String newText = labelText + text;
                            if (labelText.equals("0")) {
                                Calculator.label.setText(text);
                            } else {
                                Calculator.label.setText(newText);
                            }
                        }
                    });
                } else if (i == 23) { //=
                    bt[i].setBackground(new Color(0, 103, 192));
                    bt[i].setForeground(Color.WHITE);
                    bt[i].addActionListener(Calculator.this.new Result());

                } else if (i % 4 == 3 || i < 7 || 19 < i) {
                    bt[i].setBackground(new Color(238, 238, 238));
                    if (i == 2) { //c
                        bt[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // int n = Calculator.label.getText().length();
                                Calculator.label.setText("0");
                                Calculator.info.setText("");
                                Calculator.this.result = 0.0;
                            }
                        });
                    } else if(i == 3) { //<<
                        bt[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String labelText = Calculator.label.getText();
                                int n = labelText.length();

                                if (n > 0) {
                                    Calculator.label.setText(labelText.substring(0, n - 1));
                                }
                                if (Calculator.label.getText().isEmpty()) {
                                    Calculator.label.setText("0");
                                }
                            }
                        });
                    } else if (i == 1) { //ce
                        bt[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {

                                System.out.println("==========================================================================");
                                System.out.println("text: " + text);

                                int n = Calculator.label.getText().length();
                                if (n > 0) {  //if (Calculator.label.getText() != "") //빈 문자열인지 체크하는 코드, 공백도 빈 문자로 인식
                                    Calculator.this.setBackSpace(Calculator.this.getBackSpace().substring(0, Calculator.this.getBackSpace().length() - 1));
                                }
                                //ce누르고 빈 창일때, label에 0
                                if (Calculator.label.getText().equals("")) {
                                    Calculator.label.setText("0");
                                } //=누르고 ce 누르면 info 초기화, label 0
                                if (lastPressedButtonText.equals("=")) {
                                    Calculator.label.setText("0");
                                    Calculator.info.setText("");
                                    Calculator.this.result = 0.0;
                                }
                            }
                        });
                    } else if (i == 22) { //.
                        bt[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JButton b = (JButton)e.getSource();
                                String labelText = Calculator.label.getText();
                                String text = b.getText();

                                if (!labelText.contains(".")) {
                                    String newText = labelText + text;
                                    Calculator.label.setText(newText);
                                }
                            }
                        });
                    } else { //특수기호4개, 사칙연산, +/-, =
                        bt[i].addActionListener(Calculator.this.new Result());
                    }
                }
                this.add(bt[i]);
            }
        }
    }

    public class Result implements ActionListener {
        public Result() {
        }

        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            String labelText = Calculator.label.getText();
            String text = b.getText();

            String newText = labelText + text;
            lastPressedButtonText = text;
            //String newText = (Integer.parseInt(labelText) + Integer.parseInt(text)) + "";

            int n = newText.length();

            if (!text.equals("x²") && !text.equals("1/x") && !text.equals("2√x") && (!text.equals("+/-")) && !text.equals("%")) {
                num = Double.parseDouble(label.getText().substring(0, n - 1));
            }


            if (Calculator.this.math.equals("+")) {
                result += Calculator.this.num;
            } else if (Calculator.this.math.equals("-")) {
                result -= Calculator.this.num;
            } else if (Calculator.this.math.equals("×")) {
                result *= Calculator.this.num;
            } else if (Calculator.this.math.equals("÷")) {
                result /= Calculator.this.num;
            }

            Calculator.this.math = "";

            if (Calculator.this.math.equals("")) { //math 빈 값이면
                Calculator.this.math = b.getText(); //math = 버튼을 누른 값
            }

            if (Calculator.info.getText() == "" && !text.equals("=") && !text.equals("x²") && !text.equals("1/x")
                    && !text.equals("2√x") && (!text.equals("+/-")) && !text.equals("%")) { //info 빈 창
                Calculator.info.setText(newText);
                Calculator.this.result = Calculator.this.num;
                //Calculator.label.setText("0");

                System.out.println("연산 빈창======================================================================");
                System.out.println("labelText: " + labelText);
                System.out.println("text: " + text);
                System.out.println("result: " + result);
                System.out.println("newText: " + newText);



            } else if (Calculator.info.getText() != "" && !text.equals("=") && !text.equals("x²") && !text.equals("1/x")
                    && !text.equals("2√x") && (!text.equals("+/-")) && !text.equals("%")) { //info 빈 창 아님
                Calculator.this.result = (double)Math.round(Calculator.this.result * 1_000_000_000) / 1_000_000_000.0;

                System.out.println("연산 있는창======================================================================");
                System.out.println("labelText: " + labelText);
                System.out.println("text: " + text);
                System.out.println("result: " + result);
                System.out.println("newText: " + newText);


                if (Calculator.this.result % 1.0 == 0.0) {
                    int var10001 = (int) Calculator.this.result; //result를 정수로 변환
                    Calculator.info.setText( var10001 + text); //info = result+text

                    //Calculator.label.setText("0");
                } else {
                    Calculator.info.setText(Calculator.this.result + text);
                    //Calculator.label.setText("0");
                }
            }
            if (text.equals("x²")) {
                CalculateSquare.calculateSquare(label, info, result, e);
            }
            else if (text.equals("2√x")) {
                CalculateRoot.calculateRoot(label, info, result);
            }
            else if (text.equals("1/x")) {
                CalculateInverse.calculateInverse(label, info, result);
            }
            else if (text.equals("+/-")) {
                CalculateNegation.calculateNegation(label, info, result);
            }
            else if (text.equals("%")) {
                CalculatePercentage.calculatePercentage(label, info, result);
            }
            else if (text.equals("=")) {
                CalculateEquals.CalculateEquals(label, info, result, e);
                System.out.println("는======================================================================");
                System.out.println("labelText: " + labelText);
                System.out.println("text: " + text);
                System.out.println("result: " + result);
                System.out.println("newText: " + newText);
            }
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}

/*
System.out.println("---------------------");
        System.out.println("*********************************************************************");
        System.out.println("labelText: " + labelText);
        System.out.println("text: " + text);
        System.out.println("*********************************************************************");

System.out.println("==========================================================================");
                            System.out.println("labelText: " + labelText);
                            System.out.println("text: " + text);
                            System.out.println("newText: " + newText);
 */
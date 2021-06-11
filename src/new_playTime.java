import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//1714098 미디어학부 박예원

public class new_playTime extends JPanel {

    JButton pause;
    Thread p_display;
    //쓰레드 미리 선언해 이벤트 등으로 조절
    JLabel w1, w2, w3;
    int mm, ss, ms, t = 0;
    // 시간 나타내는 값

    public new_playTime() {

        buildGUI();

        p_display = new Thread(new Runnable() {

            @Override
            public void run() {
                mm = Integer.parseInt(w1.getText());
                ss = Integer.parseInt(w2.getText());
                ms = Integer.parseInt(w3.getText());


                while (p_display == Thread.currentThread()) {

                    mm = t % (1000 * 60) / 100 / 60;
                    ss = t % (1000 * 60) / 100 % 60;
                    ms = t % 100;
                    //분, 초를 단위에 맞게 변환

                    try {
                        Thread.sleep(10);

                        w1.setText(String.format("%02d", mm));
                        w2.setText(String.format("%02d", ss));
                        w3.setText(String.format("%02d", ms));

                        t++;

                    } catch (InterruptedException e1) {
                    }
                }
            }

        });
        p_display.start();
        //생성되면 바로 쓰레드 시작(타임워치 이므로)


    }

    private void buildGUI() { //타이머 화면 설정
        JPanel p = new JPanel(new BorderLayout());
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel wp = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel c1 = new JLabel(" : ");
        JLabel c2 = new JLabel(" : ");
        w1 = new JLabel("00");
        w2 = new JLabel("00");
        w3 = new JLabel("00");


        pause = new JButton("PAUSE");
        bp.add(pause);


        wp.add(w1);
        wp.add(c1);
        wp.add(w2);
        wp.add(c2);
        wp.add(w3);

        p.add(wp, BorderLayout.CENTER);
        p.add(bp, BorderLayout.SOUTH);
        add(p);


        pause.setFont(new Font("serif", Font.BOLD, 10));


        w1.setFont(new Font("courier", Font.BOLD, 15));
        w2.setFont(new Font("courier", Font.BOLD, 15));
        w3.setFont(new Font("courier", Font.BOLD, 15));

        c1.setFont(new Font("courier", Font.BOLD, 15));
        c2.setFont(new Font("courier", Font.BOLD, 15));

        pause.setEnabled(true);
        //처음에는 pause 버튼 활성


        pause.addActionListener(new ButtonListener());

    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();

            if (s.equals("PAUSE")) {

                pause.setEnabled(false);
                p_display.suspend();
                //pause 버튼 누르면 일시정지 버튼 비활성화, 쓰레드 일시정지
                //pause 윈도우 뜰 수 있도록 이벤트 추가해야함


            }


        }
    }
}


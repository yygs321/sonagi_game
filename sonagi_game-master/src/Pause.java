//1910391 시각영상디자인과 도하은

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pause extends JDialog {

    JButton resumeBt, exitBt;

    public Pause(Window parent){
        super(parent,"Pause", ModalityType.APPLICATION_MODAL);
        setSize(300,250);
        setLayout(null);

        resumeBt = new JButton("계속하기");
        exitBt = new JButton("그만두기");
        resumeBt.setBounds(100,125,80,30);
        exitBt.setBounds(200,125,80,30);

        resumeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notify();
                dispose();
            }
        });

        exitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameMainView(); // 첫 화면으로 돌아감
            }
        });


        add(resumeBt);
        add(exitBt);
    }
}

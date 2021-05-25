//1910391 시각영상디자인과 도하은

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// if (hp < 1){
//
public class GameOver extends JDialog{
    JButton exitBt;
    JFrame jf;

    public GameOver(Window parent){
        super(parent,"Game Over!!", ModalityType.APPLICATION_MODAL);
        setSize(300,250);
        setLayout(null);

        exitBt = new JButton("처음으로");
        exitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameMainView(); // 첫 화면으로 돌아감
            }
        });

        add(exitBt);
    }
}

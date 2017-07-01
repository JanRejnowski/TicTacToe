package com.sda.kik;

import kik.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-01.
 */
public class KikJFrameBoard extends JFrame {

    private Board board;

    private JPanel panel;

    private String currentSign;

    private List<JButton> buttons;

    public String getCurrentSign() {
        return currentSign;
    }

    public void setCurrentSign(String currentSign) {
        this.currentSign = currentSign;
    }

    public KikJFrameBoard() throws HeadlessException {
        super("Kolko i krzyzyk");
        this.buttons = new ArrayList<>();
        this.board = new Board();
        this.panel = new JPanel(new GridLayout(3, 3));
        this.currentSign = "X";
        initPanel();
        this.getContentPane().add(panel);
    }

    private void initPanel() {
        for (int i = 0; i < 9; i++) {
            int position = i;
            JButton jButton = new JButton();
            jButton.addActionListener(new KikButtonActionListener(board, position, jButton, panel, this));
            panel.add(jButton);
            buttons.add(jButton);
        }
    }

    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(225,225));
        this.pack();
        this.setVisible(true);
    }

    public void resetBoard() {
        buttons.forEach(e -> {
            e.setEnabled(true);
            e.setText("");
        });
        board.resetBoard();
        currentSign = "X";
    }
}

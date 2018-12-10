/*
 * Created by JFormDesigner on Mon Dec 10 15:54:06 CET 2018
 */

package org.brk.rspeer.userinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.brk.rspeer.data.settings.ScriptSettings;
import org.brk.rspeer.main.SeagullKiller;
import org.brk.rspeer.rendering.paint.PaintRenderer;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.StopWatch;


public class Gui extends JFrame {

    private SeagullKiller main;
    private JLabel label1;
    private JLabel label2;
    private JSpinner attackLvl;
    private JLabel label3;
    private JSpinner strengthLvl;
    private JLabel label4;
    private JSpinner defenceLvl;
    private JCheckBox buryBones;
    private JLabel label5;
    private JSpinner prayerLvl;
    private JButton button1;
    private ScriptSettings s = ScriptSettings.getInstance();

    public Gui(SeagullKiller main) {
        this.main = main;
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();
        attackLvl = new JSpinner();
        label3 = new JLabel();
        strengthLvl = new JSpinner();
        label4 = new JLabel();
        defenceLvl = new JSpinner();
        buryBones = new JCheckBox();
        label5 = new JLabel();
        prayerLvl = new JSpinner();
        button1 = new JButton();

        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout());

        label1.setText("Levels to train");
        contentPane.add(label1, "cell 0 0");

        label2.setText("Attack");
        contentPane.add(label2, "cell 0 1");

        attackLvl.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        contentPane.add(attackLvl, "cell 1 1");

        label3.setText("Strength");
        contentPane.add(label3, "cell 0 2");

        strengthLvl.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        contentPane.add(strengthLvl, "cell 1 2");

        label4.setText("Defence");
        contentPane.add(label4, "cell 0 3");

        defenceLvl.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        contentPane.add(defenceLvl, "cell 1 3");

        buryBones.setText("Bury bones?");
        contentPane.add(buryBones, "cell 0 5");

        label5.setText("Prayer");
        contentPane.add(label5, "cell 0 6");

        prayerLvl.setModel(new SpinnerNumberModel(2, 2, 99, 1));
        contentPane.add(prayerLvl, "cell 1 6");

        button1.setText("Start");
        contentPane.add(button1, "cell 8 8");
        pack();
        setLocationRelativeTo(getOwner());
        button1.addActionListener(e -> startBtnHandler(e));
    }

    private void startBtnHandler(ActionEvent e) {
        s.setDoBury(buryBones.isSelected());
        s.setTrainAttack((Integer) attackLvl.getModel().getValue());
        s.setTrainStrength((Integer) strengthLvl.getModel().getValue());
        s.setTrainDefence((Integer) defenceLvl.getModel().getValue());
        s.setTrainPrayer((Integer) prayerLvl.getModel().getValue());
        main.setPaused(false);
        setVisible(false);
        main.time = StopWatch.start();
        main.eventDispatcher = Game.getEventDispatcher();
        main.eventDispatcher.register(main.paintRenderer = new PaintRenderer(main));
    }
}

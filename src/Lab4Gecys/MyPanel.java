package Lab4Gecys;

import Gui.KsSwing;
import Gui.Panels;
import Gui.Resources;
import Lab2Gečys.Telefonas;
import Lab2Gečys.TelefonuGeneratorius;
import LaboraiDemo.GreitaveikosTyrimas;
import studijosKTU.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Jurgis on 2015-12-01.
 */
public class MyPanel extends JPanel implements ActionListener {

    private static final ResourceBundle rb = ResourceBundle.getBundle(Resources.class.getCanonicalName());
    private static final int TF_WIDTH = 8;
    private static final int NUMBER_OF_BUTTONS = 4;

    private final JTextArea taTree = new JTextArea();
    private final JScrollPane scrollTree = new JScrollPane(taTree);
    private final JTextArea taOutput = new JTextArea();
    private final JScrollPane scrollOutput = new JScrollPane(taOutput);
    private final JSplitPane splResults = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private final JComboBox cmbTreeSymbols = new JComboBox();
    private final JComboBox cmbTreeType = new JComboBox();
    private final JPanel panSouth = new JPanel();
    private final JScrollPane scrollSouth = new JScrollPane(panSouth);
    private final JPanel panParam2 = new JPanel();
    private Panels panParam1, panButtons;
    private TelefonuGeneratorius telGen = new TelefonuGeneratorius();
    private static SortedSetADTx<Telefonas> telSet;
    private int sizeOfGenSet;
    private String treeDrawType, delimiter = "";

    //                              MyPanel (BorderLayout)
//  |---------------------------------Center--------------------------------|
//  |  |~~~~~~~~~~~~~~~~~~~~~~~~~~~~splResults~~~~~~~~~~~~~~~~~~~~~~~~~~~|  |
//  |  |  |-----------------------------------|  |--------------------|  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |            scrollTree             |  |    scrollOutput    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |                                   |  |                    |  |  |
//  |  |  |-----------------------------------|  |--------------------|  |  |
//  |  |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|  |                                                  |
//  |---------------------------------South---------------------------------|
//  |  |~~~~~~~~~~~~~~~~~~~~~~~~~~~~scrollSouth~~~~~~~~~~~~~~~~~~~~~~~~~~|  |
//  |  |                                                                 |  |
//  |  |             |------------||-----------||-----------|            |  |
//  |  |             | panButtons || panParam1 || panParam2 |            |  |
//  |  |             |            ||           ||           |            |  |
//  |  |             |------------||-----------||-----------|            |  |
//  |  |                                                                 |  |
//  |  |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|  |
//  |-----------------------------------------------------------------------|
    public MyPanel() {
    }

    public void initComponents() {
        //======================================================================
        // Sudaromas rezultatų išvedimo JSplitPane klasės objektas, kuriame
        // talpinami du JTextArea klasės objektai
        //======================================================================
        splResults.setLeftComponent(scrollTree);
        splResults.setRightComponent(scrollOutput);
        splResults.setDividerLocation(570);
        splResults.setResizeWeight(0.7);
        splResults.setDividerSize(5);
        splResults.setPreferredSize(new Dimension(1000, 400));
        // Kad prijungiant tekstą prie JTextArea vaizdas visada nušoktų į apačią
        scrollTree.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            taTree.select(taTree.getCaretPosition()
                    * taTree.getFont().getSize(), 0);
        });
        scrollOutput.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            taOutput.select(taOutput.getCaretPosition()
                    * taOutput.getFont().getSize(), 0);
        });
        //======================================================================
        // Formuojamas mygtukų tinklelis (mėlynas). Naudojame klasę Panels.
        //======================================================================
        // 4 eilutes, stulpeliu - neribotai
        panButtons = new Panels(rb.getStringArray("btnLabels"), NUMBER_OF_BUTTONS, 0);
        for (JButton btn : panButtons.getButtons()) {
            btn.addActionListener(this);
        }
        enableButtons(false);
        //======================================================================
        // Formuojama pirmoji parametrų lentelė (žalia). Naudojame klasę Panels.
        //======================================================================
        panParam1 = new Panels(rb.getStringArray("lblParams2"),
                rb.getStringArray("tfParams2"), TF_WIDTH);
        //======================================================================
        // Formuojama antroji parametrų lentelė (gelsva).
        //======================================================================
        panParam2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 6, 3, 4);
        // Išlygiavimas į kairę
        c.anchor = GridBagConstraints.WEST;
        // Komponentų išplėtimas iki maksimalaus celės dydžio
        c.fill = GridBagConstraints.BOTH;
        // Pirmas stulpelis
        c.gridx = 0;
        for (String s : rb.getStringArray("lblParams1")) {
            panParam2.add(new JLabel(s), c);
        }
        // Antras stulpelis
        c.gridx = 1;
        for (String s : rb.getStringArray("cmbTreeTypes")) {
            cmbTreeType.addItem(s);
        }
        cmbTreeType.addActionListener(this);
        panParam2.add(cmbTreeType, c);
        for (String s : rb.getStringArray("cmbTreeSymbols")) {
            cmbTreeSymbols.addItem(s);
        }
        panParam2.add(cmbTreeSymbols, c);
        // Vėl pirmas stulpelis, tačiau plotis - 2 celės
        c.gridx = 0;
        c.gridwidth = 2;
        //======================================================================
        // Formuojamas bendras parametrų panelis (tamsiai pilkas).
        //======================================================================
        panSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panSouth.add(panButtons);
        panSouth.add(panParam1);
        panSouth.add(panParam2);
        //======================================================================
        // Formuojamas Lab4 panelis
        //======================================================================
        setLayout(new BorderLayout());
        // ..centre ir pietuose talpiname objektus..
        add(splResults, BorderLayout.CENTER);
        add(scrollSouth, BorderLayout.SOUTH);
        appearance();
    }

    private void appearance() {
        // Grafinių objektų rėmeliai
        int counter = 0;
        for (JComponent comp : new JComponent[]{scrollTree, scrollOutput,
                scrollSouth}) {
            TitledBorder tb = new TitledBorder(rb.getStringArray("lblBorders")[counter++]);
            tb.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
            comp.setBorder(tb);
        }
        panParam2.setBackground(new Color(255, 255, 153));// Gelsva
        panParam1.setBackground(new Color(204, 255, 204));// Šviesiai žalia
        panButtons.setBackground(new Color(112, 162, 255)); // Blyškiai mėlyna
        panSouth.setBackground(Color.GRAY);
        taTree.setFont(Font.decode("courier new-12"));
        // Swing'as Linux Ubuntu Su Gnome rodo teisingai su šiuo fontu:
        // taTree.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        taOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        taTree.setEditable(false);
        taOutput.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            System.gc();
            System.gc();
            System.gc();
            taOutput.setBackground(Color.white);
            taTree.setBackground(Color.white);
            Object source = ae.getSource();
            if (source.equals(panButtons.getButtons()[0])) {
                treeGeneration(null);
            }
            if (source.equals(panButtons.getButtons()[1])) {
                treeIteration();
            }
            if (source.equals(panButtons.getButtons()[2])) {
                treeAdd();
            }
            if (source.equals(panButtons.getButtons()[3])) {
                treeEfficiency();
            }
            if (source.equals(panButtons.getButtons()[4])) {
                treeRemove();
            }
            if (source.equals(panButtons.getButtons()[5])) { //mano funkcija 1
                treeHeight();
            }
            if(source.equals(panButtons.getButtons()[6])){ //mano funkcija 2
                treeHeadSet();
            }
            if(source.equals(panButtons.getButtons()[7])){
                treeTailSet();
            }
            if (source.equals(cmbTreeType)) {
                enableButtons(false);
            }
        } catch (KsSwing.MyException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                KsSwing.ounerr(taOutput, rb.getStringArray("errMsgs2")[e.getCode()] + ": " + e.getMessage());
                if (e.getCode() == 2) {
                    panParam1.getTfOfTable()[0].setBackground(Color.red);
                    panParam1.getTfOfTable()[1].setBackground(Color.red);
                }
            } else if (e.getCode() == 4) {
                KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[2]);
            } else {
                KsSwing.ounerr(taOutput, e.getMessage());
            }
        } catch (NullPointerException e) {
            KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[11]);
            e.printStackTrace(System.out);
        } catch (IllegalArgumentException e) {
            KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[11]);
            e.printStackTrace(System.out);
        } catch (UnsupportedOperationException e) {
            KsSwing.ounerr(taOutput, e.getMessage());
        } catch (Exception e) {
            KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[11]);
            e.printStackTrace(System.out);
        }
    }

    private void treeTailSet() {
        new HeadsetDialog(rb.getStringArray("manoParametrai")[2], telSet, item -> {
            if(item != null){
                BstSetKTU<Telefonas> tailSet = (BstSetKTU<Telefonas>) telSet.tailSet(item);
                KsSwing.setFormatStartOfLine(true);
                KsSwing.oun(taOutput, item.toString(), rb.getStringArray("manoParametrai")[2]);
                KsSwing.setFormatStartOfLine(false);
                KsSwing.oun(taTree, tailSet.toVisualizedString(treeDrawType, delimiter), rb.getStringArray("manoParametrai")[2]);
            }
        });

    }

    public void treeHeadSet() {
        new HeadsetDialog(rb.getStringArray("manoParametrai")[1], telSet, item -> {
            if(item != null){
                BstSetKTU<Telefonas> headSet = (BstSetKTU<Telefonas>) telSet.headSet(item);
                KsSwing.setFormatStartOfLine(true);
                KsSwing.oun(taOutput, item.toString(), rb.getStringArray("manoParametrai")[1]);
                KsSwing.setFormatStartOfLine(false);
                KsSwing.oun(taTree, headSet.toVisualizedString(treeDrawType, delimiter), rb.getStringArray("manoParametrai")[1]);
            }
        });

    }

    public void treeHeight() {
        int height = telSet.treeHeight();
        KsSwing.setFormatStartOfLine(true);
        KsSwing.oun(taOutput, height, rb.getStringArray("manoParametrai")[0]);
//        KsSwing.setFormatStartOfLine(true);
//        KsSwing.oun(taOutput, telSet, rb.getStringArray("msgs")[6]);
    }

    public void treeGeneration(String fName) throws KsSwing.MyException {
        // Nuskaitomi uždavinio parametrai
        getParameters();
        // Sukuriamas aibės objektas, priklausomai nuo medžio pasirinkimo
        // cmbTreeType objekte
        switch (cmbTreeType.getSelectedIndex()) {
            case 0:
                telSet = new BstSetKTUx(new Telefonas());
                break;
            case 1:
                telSet = new AvlSetKTUx(new Telefonas());
                break;
            default:
                enableButtons(false);
                throw new KsSwing.MyException(rb.getStringArray("msgs")[0]);
        }
        // Jei failas nenaudojamas
        if (fName == null) {
            telSet.clear();
            for (Telefonas a : telGen.generuotiSarasa(sizeOfGenSet)) {
                telSet.add(a);
            }
        } else { // Jei skaitoma is failo
            telSet.load(fName);
        }

        // Išvedami rezultatai
        // Nustatoma, kad eilutės pradžioje neskaičiuotų išvedamų eilučių skaičiaus
        KsSwing.setFormatStartOfLine(false);
        KsSwing.oun(taTree, telSet.toVisualizedString(treeDrawType, delimiter)  + (cmbTreeType.getSelectedIndex() == 0 ? "DP medis" : "AVL medis"),
                rb.getStringArray("msgs")[4]);
        // Nustatoma, kad eilutės pradžioje skaičiuotų išvedamų eilučių skaičių
        KsSwing.setFormatStartOfLine(true);
        KsSwing.oun(taOutput, telSet, rb.getStringArray("msgs")[5] + (cmbTreeType.getSelectedIndex() == 0 ? "DP medis" : "AVL medis"));
        enableButtons(true);
    }

    private void treeAdd() throws KsSwing.MyException {
        Telefonas auto = telGen.generuotiViena();
        telSet.add(auto);
        KsSwing.setFormatStartOfLine(false);
        KsSwing.oun(taTree, auto, rb.getStringArray("msgs")[6]);
        KsSwing.oun(taTree, telSet.toVisualizedString(treeDrawType, delimiter));
        KsSwing.setFormatStartOfLine(true);
        KsSwing.oun(taOutput, telSet, rb.getStringArray("msgs")[6]);
    }

    private void treeRemove() {
        if (telSet.isEmpty()) {
            KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[10]);
        } else {
            int nr = new Random().nextInt(telSet.size());
            Telefonas auto = (Telefonas) telSet.toArray()[nr];
            telSet.remove(auto);
            KsSwing.setFormatStartOfLine(false);
            KsSwing.oun(taTree, auto, rb.getStringArray("msgs")[8]);
            KsSwing.oun(taTree, telSet.toVisualizedString(treeDrawType, delimiter));
            KsSwing.setFormatStartOfLine(true);
            if (telSet.isEmpty()) {
                KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[10]);
            } else {
                KsSwing.oun(taOutput, telSet, rb.getStringArray("msgs")[8]);
            }
        }
    }

    private void treeIteration() {
        if (telSet.isEmpty()) {
            KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[10]);
        } else {
            KsSwing.oun(taOutput, telSet, rb.getStringArray("msgs")[9]);
        }
    }

    private void treeEfficiency() throws KsSwing.MyException {
        KsSwing.oun(taOutput, "", rb.getStringArray("msgs")[1]);
        SwingUtilities.invokeLater(() -> {
            GreitaveikosTyrimas gt = new GreitaveikosTyrimas(taOutput, panButtons.getButtons());
            gt.start();
        });
    }

    private void getParameters() throws KsSwing.MyException {
        // Nuskaitomos parametrų reiksmės. Jei konvertuojant is String
        // įvyksta klaida, sugeneruojama NumberFormatException situacija. Tam, kad
        // atskirti kuriame JTextfield'e ivyko klaida, panaudojama nuosava
        // situacija MyException
        int i = 0;
        try {
            // Pakeitimas (replace) tam, kad sukelti situaciją esant
            // neigiamam skaičiui
            sizeOfGenSet = Integer.valueOf(panParam1.getParametersOfTable()[i].replace("-", "x"));
            ++i;
        } catch (NumberFormatException e) {
            // Galima ir taip: pagauti exception'ą ir vėl mesti
            throw new KsSwing.MyException(panParam1.getParametersOfTable()[i], i);
        }
        // Nuskaitomas medžio elemento simbolis ir celės teksto kirtiklis
        treeDrawType = (String) cmbTreeSymbols.getSelectedItem();
    }

    private void enableButtons(boolean enable) {
        for (int i : new int[]{1, 2, 4, 5, 6, 7}) {
            panButtons.getButtons()[i].setEnabled(enable);
        }
    }

    public JTextArea getTaOutput() {
        return taOutput;
    }
}
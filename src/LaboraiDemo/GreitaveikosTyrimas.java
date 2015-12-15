package LaboraiDemo;

import Gui.KsSwing;
import Gui.KsSwing.MyException;
import Gui.Resources;

import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JTextArea;

import Lab2Gečys.Telefonas;
import Lab2Gečys.TelefonuGeneratorius;
import studijosKTU.*;

public class GreitaveikosTyrimas extends Thread {

    private static final ResourceBundle rb = ResourceBundle.getBundle(Resources.class.getCanonicalName());
    static JTextArea taOutput;
    static JButton[] btns;
    static String[] tyrimųVardai = {"addBstRec", "addBstIte", "addAvlRec", "removeBst"};
    static int[] tiriamiKiekiai = {10000, 20000, 40000, 80000};
    static SortedSetADTx<Telefonas> aSeries = new BstSetKTUx(new Telefonas(), new Telefonas.ComparatorPagalKaina());
    static SortedSetADTx<Telefonas> aSeries2 = new BstSetKTUx2(new Telefonas());
    static SortedSetADTx<Telefonas> aSeries3 = new AvlSetKTUx(new Telefonas());

    public GreitaveikosTyrimas(JTextArea taOutput, JButton[] btns) {
        GreitaveikosTyrimas.taOutput = taOutput;
        GreitaveikosTyrimas.btns = btns;
    }

    @Override
    public void run() {
        for (JButton btn : btns) {
            btn.setEnabled(false);
        }
        SisteminisTyrimas();
        for (JButton btn : btns) {
            btn.setEnabled(true);
        }
    }

    public void SisteminisTyrimas() {
        Timekeeper tk = new Timekeeper(tiriamiKiekiai, taOutput);
        for (int k : tiriamiKiekiai) {
            List<Telefonas> autoMas = new TelefonuGeneratorius().generuotiSarasa(k);
            aSeries.clear();
            aSeries2.clear();
            aSeries3.clear();
            tk.startAfterPause();
            tk.start();
            for (Telefonas a : autoMas) {
                aSeries.add(a);
            }
            tk.finish(tyrimųVardai[0]);
            for (Telefonas a : autoMas) {
                aSeries2.add(a);
            }
            tk.finish(tyrimųVardai[1]);
            for (Telefonas a : autoMas) {
                aSeries3.add(a);
            }
            tk.finish(tyrimųVardai[2]);
            for (Telefonas a : autoMas) {
                aSeries.remove(a);
            }
            tk.finish(tyrimųVardai[3]);
            tk.seriesFinish();
        }

    }
}

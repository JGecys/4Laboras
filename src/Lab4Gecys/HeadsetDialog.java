package Lab4Gecys;

import Lab2Gečys.Telefonas;
import studijosKTU.SortedSetADT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by Jurgis on 2015-12-03.
 */
public class HeadsetDialog extends JFrame {

    private SortedSetADT<Telefonas> set;

    private final int n = 5;
    private JComboBox<String>[] combo = new JComboBox[5];
    private JButton button = new JButton("Pasirinkti");
    private boolean isSelected = false;

    private OnItemSelected listener = null;

    public HeadsetDialog(String title, SortedSetADT<Telefonas> set, OnItemSelected listener){
        super(title);
        this.set = set;
        this.listener = listener;
        Container panel = getContentPane();
        panel.setLayout(new FlowLayout());
        for(int i = 0; i < n; i++){
            combo[i] = new JComboBox<>();
            combo[i].addActionListener(this::OnComboSelected);
            panel.add(combo[i]);
        }
        ArrayList<Object> list = new ArrayList<>();
        for(Telefonas t : set){
                if(!list.contains(t.getGamintojas()))
                    list.add(t.getGamintojas());
        }
        for(Object t : list)
            combo[0].addItem(t.toString());
        button.addActionListener(this::OnComplete);
        panel.add(button);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void OnComplete(ActionEvent actionEvent) {
        if(listener != null){
            listener.onItemSelected(build());
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    private void OnComboSelected(ActionEvent e) {
        if(e.getSource() instanceof JComboBox){
            JComboBox c = ((JComboBox) e.getSource());
            int ind = -1;
            for(int i = 0; i < n; i++)
                if(combo[i] == c)
                    ind = i;
            for(int i = ind + 1; i < n; i++){
                combo[i].removeAllItems();
            }
            ArrayList<Object> list = new ArrayList<>();
            switch (ind){
                case 0:
                    for(Telefonas t : set){
                        if(t.getGamintojas().equals(combo[0].getSelectedItem()))
                            if(!list.contains(t.getModelis()))
                                list.add(t.getModelis());
                    }
                    for(Object t : list)
                        combo[1].addItem(t.toString());
                    break;
                case 1:
                    if(combo[1].getItemCount() == 0)
                        break;
                    for(Telefonas t : set){
                        if(t.getGamintojas().equals(combo[0].getSelectedItem()) && t.getModelis().equals(combo[1].getSelectedItem()))
                            if(!list.contains(t.getKaina()))
                                list.add(t.getKaina());
                    }
                    for(Object t : list)
                        combo[2].addItem(t.toString());
                    break;
                case 2:
                    if(combo[2].getItemCount() == 0)
                        break;
                    for(Telefonas t : set){
                        if(t.getGamintojas().equals(combo[0].getSelectedItem())
                                && t.getModelis().equals(combo[1].getSelectedItem())
                                && t.getKaina() == Double.parseDouble(combo[2].getSelectedItem().toString()))
                            if(!list.contains(t.getNupirkimoMetai()))
                                list.add(t.getNupirkimoMetai());
                    }
                    for(Object t : list)
                        combo[3].addItem(t.toString());
                    break;
                case 3:
                    if(combo[3].getItemCount() == 0)
                        break;
                    for(Telefonas t : set){
                        if(t.getGamintojas().equals(combo[0].getSelectedItem())
                                && t.getModelis().equals(combo[1].getSelectedItem())
                                && t.getKaina() == Double.parseDouble(combo[2].getSelectedItem().toString())
                                && t.getNupirkimoMetai() == Integer.parseInt(combo[3].getSelectedItem().toString()))
                            if(!list.contains(t.getGarantija()))
                                list.add(t.getGarantija());
                    }
                    for(Object t : list)
                        combo[4].addItem(t.toString());
                    break;
                case 4:
                    if(combo[4].getItemCount() == 0)
                        break;
                    break;
            }
        }
    }

    private Telefonas build(){
        String data = "";
        for(JComboBox c : combo){
            data += c.getSelectedItem().toString() + " ";
        }
        data = data.substring(0, data.length() - 1);
        data = data.replace('.', ',');
        return Telefonas.FromString(data);
    }

    interface OnItemSelected{
        void onItemSelected(Telefonas item);
    }

}

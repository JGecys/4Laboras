package Lab4Gecys;

import Lab2Gečys.Telefonas;
import studijosKTU.BstSetKTU;
import studijosKTU.Ks;

import java.io.*;
import java.util.Comparator;

/**
 * Created by Jurgis on 2015-11-25.
 */
public class ElementuSaugojimas extends BstSetKTU<Telefonas> {

    public ElementuSaugojimas() {
        super();
    }

    public ElementuSaugojimas(Comparator<? super Telefonas> c) {
        super(c);
    }

    public void add(String dataString){
        try{
            add(Telefonas.FromString(dataString));
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    public void load(String fName) {
        clear();
        if (fName.length() == 0) {
            return;
        }
        String fN = "";
        try {
            new File(System.getProperty("user.dir")).mkdir();
            fN = System.getProperty("user.dir") + File.separatorChar + fName;
            try (BufferedReader fReader = new BufferedReader(new FileReader(new File(fN)))) {
                String dLine;
                while ((dLine = fReader.readLine()) != null && !dLine.trim().isEmpty()) {
                    add(dLine);
                }
            }
        } catch (FileNotFoundException e) {
            Ks.ern("Tinkamas duomenų failas " + fN + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + fN + " skaitymo klaida");
        }
    }

}

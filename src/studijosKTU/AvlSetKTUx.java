package studijosKTU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class AvlSetKTUx<E extends KTUable<E>> extends AvlSetKTU<E>
        implements SortedSetADTx<E> {

    private final E baseObj;       // bazinis objektas skirtas naujų kūrimui

    /**
     * Konstruktorius su bazinio objekto fiksacija dėl naujų elementų kūrimo
     *
     * @param baseObj
     */
    public AvlSetKTUx(E baseObj) {
        super();
        this.baseObj = baseObj;
    }

    /**
     * Konstruktorius su bazinio objekto fiksacija dėl naujų elementų kūrimo
     *
     * @param baseObj
     * @param c
     */
    public AvlSetKTUx(E baseObj, Comparator<? super E> c) {
        super(c);
        this.baseObj = baseObj;
    }

    /**
     * Sukuria elementą iš String ir įdeda jį į pabaigą
     *
     * @param dataString
     */
    @Override
    public void add(String dataString) {
        add((E) baseObj.create(dataString));
    }

    /**
     * Suformuoja sąrašą iš fName failo
     *
     * @param fName
     */
    @Override
    public void load(String fName) {
        clear();
        if (fName.length() == 0) {
            return;
        }
        if (baseObj == null) {          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "
                    + "reikia taikyti konstruktorių = new ListKTU(new E())");
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
            Ks.ern("Duomenų failas " + fN + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + fN + " skaitymo klaida");
        }
    }
}

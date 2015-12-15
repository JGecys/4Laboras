package Gui;

import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ListResourceBundle;

/**
 * Platesnėms studijoms:
 * http://download.oracle.com/javase/8/docs/api/java/util/ListResourceBundle.html
 * http://download.oracle.com/javase/tutorial/i18n/resbundle/concept.html
 * Naudojama nustatyta lokalė.
 *
 * @author darius
 */
public class Resources extends ListResourceBundle {

    /**
     * Grąžinamas programos resursų masyvas. Kiti metodai paveldimi iš
     * abstrakčios ListResourceBundle klasės.
     *
     * @return Grąžinamas programos resursų masyvas
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }

    /**
     * Objektų masyvų masyvas, kuriame saugomi programos resursai
     */
    private static final Object[][] contents = {
        {"lblTitle", "KTU IF " + LocalDate.now().getYear() + ". LD4. Medžio tipo duomenų struktūrų tiriamasis darbas"},
        {"lblAuthor", "<html><b>Autorius:Jurgis Gečys, IF-4/8</b><br>email: "
            + "<FONT COLOR=BLUE>jurgis.gecys@ktu.edu</FONT></html>"},
        {"lblMenus", new String[]{
            "Failas",
            "Pagalba"
        }},
        {"lblMenuItems", new String[][]{
            {"Atidaryti..", "Išsaugoti..", "Išeiti"},
            {"Apie.."}
        }},
        {"lblBorders", new String[]{
            "Duomenų aibė medžio formos duomenų struktūroje",
            "Rezultatai",
            "Parametrai",
            "Programos vykdymas"
        }},
        {"cmbTreeTypes", new String[]{
            "DP-medis",
            "AVL-Medis",
            "Kiti medžiai"
        }},
        {"cmbTreeSymbols", new String[]{
            "Apskritimas",
            "Kvadratas"
        }},
        {"btnLabels", new String[]{
            "Generuoti telefonų aibę",
            "Peržiūra su iteratoriumi",
            "Papildyti aibę telefonu",
            "Greitaveikos tyrimas",
            "Pašalinti telefoną iš aibės",
            "Medžio aukštis",
            "HeadSet",
            "TailSet"

        }},
        {"lblParams1", new String[]{
            "Paieškos medžio tipas",
            "Elemento simbolis medyje"
        }},
        {"manoParametrai", new String[]{
            "Medžio aukštis",
            "Headset",
            "Tailset"
        }},
        {"manoError", new String[]{
            "",
            "",
            "",
            "",}},
        {"lblParams2", new String[]{
            "Generuojamos automobilių aibės dydis"
        }},
        {"tfParams2", new String[]{
            "100",
            "10",
            "",
            "0.8",
            ""
        }},
        {"errMsgs2", new String[]{
            "Netinkamas generuojamos aibės dydis",
            "Netinkama pradinė aibės imtis arba netinkamai nuskaityti\n   duomenys",
            "Generuojama aibė turi būti 3 kartus\ndidesnė negu pradinė aibės imtis",
            "Netinkamas išbarstymo koeficientas"
        }},
        {"msgs", new String[]{
            "Dar neįdiegta",
            "Greitaveikos tyrimas",
            "Visa sugeneruota aibė jau išspausdinta",
            "Failas perskaitytas.",
            "Duomenų aibė medyje",
            "Duomenų aibė",
            "Aibės papildymas elementu",
            "Medžio papildymas elementu",
            "Elemento pašalinimas iš aibės",
            "Aibės peržiūra su iteratoriumi",
            "Aibė tuščia",
            "Sisteminė klaida. Žiūrėti konsolėje"
        }},
        {"keys", new int[][]{
            {KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X},
            {-1}
        }}
    };
}

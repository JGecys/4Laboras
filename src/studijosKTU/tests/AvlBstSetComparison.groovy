package studijosKTU.tests

import Lab2Gečys.Telefonas
import Lab2Gečys.TelefonuGeneratorius
import studijosKTU.AvlSetKTU
import studijosKTU.BstSetKTU

/**
 * Created by Jurgis on 2015-11-30.
 */
class AvlBstSetComparison extends GroovyTestCase {

    void testAvlBst(){
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        AvlSetKTU<Telefonas> avl = new AvlSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        for(int i = 0; i < 100; i++){
            Telefonas vienas = g.generuotiViena()
            bst.add(vienas);
            avl.add(vienas);
        }

        println bst.toVisualizedString("dgdf", "gdfg");
        println bst.treeHeight();
        println avl.toVisualizedString("dgdf", "gdfg");
        println avl.treeHeight();
    }

}

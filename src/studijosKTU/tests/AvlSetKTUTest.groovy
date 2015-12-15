package studijosKTU.tests

import Lab2Gečys.Telefonas
import Lab2Gečys.TelefonuGeneratorius
import studijosKTU.AvlSetKTU
import studijosKTU.BstSetKTU

/**
 * Created by Jurgis on 2015-11-30.
 */
class AvlSetKTUTest extends GroovyTestCase {
    void testRemove() {
        AvlSetKTU<Telefonas> avl = new AvlSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        List<Telefonas> toRemove = new LinkedList<>();
        for(int i = 0; i < 20; i++){
            Telefonas vienas = g.generuotiViena()
            avl.add(vienas);
            if(i > 5 && i < 20)
                toRemove.add(vienas);
        }

        println toRemove.toString();
        println avl.toVisualizedString("", "");
        println avl.size();
        for(Telefonas t : toRemove){
            avl.remove(t);
        }
        println avl.toVisualizedString("", "");
        println avl.size();
        println avl.treeHeight();
    }
}

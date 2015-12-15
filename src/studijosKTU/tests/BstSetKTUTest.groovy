package studijosKTU.tests

import Lab2Gečys.Telefonas
import Lab2Gečys.TelefonuGeneratorius
import studijosKTU.BstSetKTU

/**
 * Created by Jurgis on 2015-11-26.
 */
class BstSetKTUTest extends GroovyTestCase {
    void testHeadSet() {
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        Telefonas ieskomasis;
        for(int i = 0; i < 100; i++){
            Telefonas vienas = g.generuotiViena()
            bst.add(vienas);
            if(i == 68)
                ieskomasis = vienas;
        }

        BstSetKTU<Telefonas> headset = bst.headSet(ieskomasis);
        println ieskomasis.toString();
        println bst.toVisualizedString("", "");
        println headset.toVisualizedString("", "");
    }

    void testTailSet() {
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        Telefonas ieskomasis;
        for(int i = 0; i < 100; i++){
            Telefonas vienas = g.generuotiViena()
            bst.add(vienas);
            if(i == 20)
                ieskomasis = vienas;
        }

        BstSetKTU<Telefonas> headset = bst.tailSet(ieskomasis);
        println ieskomasis.toString();
        println bst.toVisualizedString("", "");
        println headset.toVisualizedString("", "");
    }

    void testSubset(){
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        Telefonas ieskomasis;
        Telefonas ieskomasis2;
        for(int i = 0; i < 100; i++){
            Telefonas vienas = g.generuotiViena()
            bst.add(vienas);
            if(i == 10)
                ieskomasis = vienas;
            if(i == 60)
                ieskomasis2 = vienas;
        }

        BstSetKTU<Telefonas> subset = bst.subSet(ieskomasis, ieskomasis2);
        println ieskomasis.toString();
        println ieskomasis2.toString();
        println bst.toVisualizedString("", "");
        if(subset != null){
            println subset.toVisualizedString("", "");
            assertEquals(true, subset.contains(ieskomasis));
            assertEquals(true, subset.contains(ieskomasis2));
        }else{
            println "couldnt find subset";
        }
    }

    void testIteratorRemove(){
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        for(int i = 0; i < 10; i++){
            Telefonas vienas = g.generuotiViena()
            bst.add(vienas);
        }


        println bst.toVisualizedString("", "");
        println bst.size();
        Iterator it = bst.iterator();
        it.next();
        it.next();
        it.next();
        println it.next().toString();
        it.remove();
        println bst.toVisualizedString("", "");
        println bst.size();

    }

    void testTreeHeight(){
        BstSetKTU<Telefonas> bst = new BstSetKTU<>();
        TelefonuGeneratorius g = new TelefonuGeneratorius();
        for(int i = 0; i < 100; i++){
            bst.add(g.generuotiViena());
        }
        println bst.toVisualizedString("", "");
        println "size: " + bst.size();
        println "height: " + bst.treeHeight();
    }

}

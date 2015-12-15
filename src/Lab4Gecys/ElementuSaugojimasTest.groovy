package Lab4Gecys

import Lab2Gečys.Telefonas
import Lab2Gečys.TelefonuGeneratorius

/**
 * Created by Jurgis on 2015-11-25.
 */
class ElementuSaugojimasTest extends GroovyTestCase {

    public void testGenerate(){
        TelefonuGeneratorius gen = new TelefonuGeneratorius();
        ElementuSaugojimas s = new ElementuSaugojimas();
        for(int i = 0; i < 2_000_000; i++){
            s.add(gen.generuotiViena());
        }
    }

    public void testLoad(){
        ElementuSaugojimas s = new ElementuSaugojimas();
        s.load("Duomenys\\t1.txt");
        for(Telefonas t : s){
            println(t);
        }
    }

}

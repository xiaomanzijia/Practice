package com.licheng.duotai;

/**
 * Created by licheng on 30/7/15.
 */
public class InstrumentTest {

    public void testPlay(Instrument instrument){
        instrument.play();
    }

    public static void main(String[] args) {
        InstrumentTest test = new InstrumentTest();
        Piano piano = new Piano();
        Violin violin = new Violin();
        Guitar guitar = new Guitar();

        test.testPlay(piano);
        test.testPlay(violin);
        test.testPlay(guitar);
    }
}

package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLaittaaLiikaa() {
        varasto.lisaaVarastoon(18);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(6);
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void eiVoiLaittaaNegatiivista() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivista() {
        double saatu = varasto.otaVarastosta(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, saatu, vertailuTarkkuus);
    }

    @Test
    public void negatiivisenTilavuudenVarasto() {
        Varasto fgsfds = new Varasto(-5);
        assertEquals(0, fgsfds.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void testToString() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

    @Test
    public void toinenKonstruktori() {
        Varasto testi = new Varasto(5, 5);
        assertEquals(5, testi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriRikki() {
        Varasto testi = new Varasto(-5, -5);
        assertEquals(0, testi.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriRikki2() {
        Varasto testi = new Varasto(5, 6);
        assertEquals(5, testi.getSaldo(), vertailuTarkkuus);
    }
}
package com.example.harmonicatulashop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.harmonicatulashop.database.account.room.AccountRoomDatabase;
import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRoomDatabase;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.account.activities.SignInActivity;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.Exchanger;

@RunWith(AndroidJUnit4.class)
public class CatalogRoomDatabaseTest {
    private HarmonicaDao harmonicaDao;
    private BayanDao bayanDao;
    private AccordionDao accordionDao;
    private CatalogRoomDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = CatalogRoomDatabase.getDatabase(context);
        harmonicaDao = db.harmonicaDao();
        bayanDao = db.bayanDao();
        accordionDao = db.accordionDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void addHarmonica() {
        byte[] icon = new byte[3];
        Harmonica harmonica = new Harmonica(icon, "Куликово поле", "До мажор", "27/25", 115, "");
        harmonica.setOptions("Регулировка ремня металлическим колёсиком");

        harmonicaDao.insert(harmonica);
        harmonicaDao.insert(harmonica);

        Harmonica h = harmonicaDao.getHarmonicaList().get(0);

        assertThat(h.getIcon(), equalTo(harmonica.getIcon()));
        assertThat(h.getType(), equalTo(harmonica.getType()));
        assertThat(h.getTone(), equalTo(harmonica.getTone()));
        assertThat(h.getRange(), equalTo(harmonica.getRange()));
        assertThat(h.getPrice(), equalTo(harmonica.getPrice()));
        assertThat(h.getOptions(), equalTo(harmonica.getOptions()));
    }

    @Test
    public void getHarmonica() {
        Harmonica harmonica = harmonicaDao.getHarmonicaList().get(0);
        Harmonica harmonica1 = harmonicaDao.getHarmonicaList().get(1);

        assertThat(harmonica, notNullValue());

        assertThat(harmonica.getOptions(), equalTo(harmonica1.getOptions()));
    }

    @Test
    public void deleteHarmonicas() {
        harmonicaDao.deleteAll();

        List<Harmonica> list = harmonicaDao.getHarmonicaList();

        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void addBayan() {
        byte[] icon = new byte[3];
        Bayan bayan = new Bayan(icon, "Этюд", "80/100", 112, "");
        bayanDao.insert(bayan);

        Bayan b = bayanDao.getBayanList().get(0);

        assertThat(b.getIcon(), equalTo(bayan.getIcon()));
        assertThat(b.getType(), equalTo(bayan.getType()));
        assertThat(b.getRange(), equalTo(bayan.getRange()));
        assertThat(b.getPrice(), equalTo(bayan.getPrice()));
        assertThat(b.getOptions(), equalTo(bayan.getOptions()));
    }

    @Test
    public void deleteBayan() {
        bayanDao.deleteAll();

        List<Bayan> list = bayanDao.getBayanList();

        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void addAccordion() {
        byte[] icon = new byte[3];
        Accordion accordion = new Accordion(icon, "27/25", 115, "");
        accordion.setOptions("Регулировка ремня металлическим колёсиком");

        accordionDao.insert(accordion);

        Accordion a = accordionDao.getAccordionList().get(0);

        assertThat(a.getIcon(), equalTo(accordion.getIcon()));
        assertThat(a.getRange(), equalTo(accordion.getRange()));
        assertThat(a.getPrice(), equalTo(accordion.getPrice()));
        assertThat(a.getOptions(), equalTo(accordion.getOptions()));
    }

    @Test
    public void deleteAccordion() {
        accordionDao.deleteAll();

        List<Accordion> list = accordionDao.getAccordionList();

        assertThat(list.size(), equalTo(0));
    }
}

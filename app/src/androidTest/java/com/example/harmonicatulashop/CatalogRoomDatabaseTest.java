package com.example.harmonicatulashop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRoomDatabase;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CatalogRoomDatabaseTest {
    private HarmonicaDao harmonicaDao;
    private CatalogRoomDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = CatalogRoomDatabase.getDatabase(context);
        harmonicaDao = db.harmonicaDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writeAddAndDeleteHarmonica() {
        byte[] icon = new byte[3];
        Harmonica harmonica = new Harmonica(icon, "Куликово поле", "До мажор", "27/25", 115, "");
        harmonica.setOptions("Регулировка ремня металлическим колёсиком");
        harmonicaDao.insert(harmonica);

        int id = harmonica.getId();

        Harmonica h = harmonicaDao.findById(id);

        assertThat(h, equalTo(harmonica));

        harmonicaDao.delete(harmonica);

        h = harmonicaDao.findById(id);

        assertThat(h, equalTo(null));
    }
}

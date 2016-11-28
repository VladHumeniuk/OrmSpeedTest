package orm.test.ormtest.core.cipher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import orm.test.ormtest.core.BaseOrmTester;

public class CipherTester extends BaseOrmTester {

    private File databaseFile;

    public CipherTester(Context context) {

        super(context);
    }

    @Override
    public void setup() {
        SQLiteDatabase.loadLibs(context);
        databaseFile = context.getDatabasePath("cipher.db");
        databaseFile.mkdirs();
        databaseFile.delete();
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "ciphertest", null);
        database.execSQL("create table t1(id, name, address, city, phone, state)");
    }

    @Override
    public void destroy() {
        databaseFile.delete();
    }

    @NonNull
    private List<ContentValues> setupData(int records) {
        List<ContentValues> items = new ArrayList<>(records);
        for (int i = 0; i < records; i++) {
            ContentValues record = new ContentValues();
            record.put("id", i);
            record.put("name", "name");
            record.put("address","Address");
            record.put("city","City");
            record.put("phone",123456789);
            record.put("state","State");

            items.add(record);
        }

        return items;
    }

    @Override
    public long timeSimpleCreate(int records) {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "ciphertest", null);
        database.delete("t1", null, null);
        List<ContentValues> data = setupData(records);
        long time = System.currentTimeMillis();
        for (ContentValues values : data) {
            database.insert("t1", null, values);
        }
        time = System.currentTimeMillis() - time;
        database.close();
        return time;
    }

    @Override
    public long timeSimpleCreateBulk(int records) {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "ciphertest", null);
        database.delete("t1", null, null);
        List<ContentValues> data = setupData(records);
        long time = System.currentTimeMillis();
        database.beginTransaction();
        for (ContentValues values : data) {
            database.insert("t1", null, values);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        time = System.currentTimeMillis() - time;
        database.close();
        return time;
    }

    @Override
    public long timeSimpleRead(int records) {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "ciphertest", null);
        timeSimpleCreateBulk(records);
        long time = System.currentTimeMillis();
        Cursor cursor = database.rawQuery("select * from t1", new String[]{});
        cursor.moveToFirst();
        time = System.currentTimeMillis() - time;
        cursor.close();
        database.close();
        return time;
    }

    @Override
    public String name() {

        return "Cipher";
    }
}

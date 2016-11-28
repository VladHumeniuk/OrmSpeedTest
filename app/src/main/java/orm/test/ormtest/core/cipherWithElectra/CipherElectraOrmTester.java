package orm.test.ormtest.core.electra;

import android.content.ContentValues;
import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;
import net.sqlcipher.database.SQLiteOpenHelper;

import android.database.Cursor;
import android.support.annotation.NonNull;

import org.bitbucket.txdrive.electra.Electra;
import org.bitbucket.txdrive.electra.core.manager.ElectraDatabase;
import org.bitbucket.txdrive.electra.core.manager.EntityManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import electra.ED;
import orm.test.ormtest.core.BaseOrmTester;

public class CipherElectraOrmTester extends BaseOrmTester {
	private EntityManager em;

	public CipherElectraOrmTester(Context context) {
		super(context);
	}

	@Override
	public void setup() {
		SQLiteDatabase.loadLibs(context);
		File databaseFile = context.getDatabasePath("cipherelectra.db");
		databaseFile.mkdirs();
		databaseFile.delete();
		CipherElectraDb db = new CipherElectraDb(databaseFile, "electra", null);
		Electra.configure(ED.TYPES);
		em = Electra.with(db);
		em.createTables();
	}

	@Override
	public void destroy() {
		em.close();
	}

	@Override
	public long timeSimpleCreate(int records) {
		em.where(ElectraBean.class).delete();

		List<ElectraBean> items = setupData(records);

		long time = System.currentTimeMillis();

		for (ElectraBean item: items) {
			em.create(item);
		}


		return System.currentTimeMillis() - time;
	}

	@NonNull
	private List<ElectraBean> setupData(int records) {
		List<ElectraBean> items = new ArrayList<>(records);
		for (int i = 0; i < records; i++) {
			ElectraBean record = new ElectraBean();
			record.setName("name");
			record.setAddress("Address");
			record.setCity("City");
			record.setPhone(123456789);
			record.setState("State");

			items.add(record);
		}

		return items;
	}

	@Override
	public long timeSimpleCreateBulk(int records) {
		em.where(ElectraBean.class).delete();

		List<ElectraBean> items = setupData(records);

		long time = System.currentTimeMillis();

		em.create(items);


		return System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleRead(int records) {
		timeSimpleCreateBulk(records);

		long time = System.currentTimeMillis();

		List<ElectraBean> list = em.select(ElectraBean.class).list();

		return System.currentTimeMillis() - time;
	}


	@Override
	public String name() {
		return "Electra";
	}

	private class CipherElectraDb implements ElectraDatabase {

		private SQLiteDatabase db;
		private SQLiteOpenHelper helper;

		public CipherElectraDb(String path,
							   String password,
							   CursorFactory factory) {
			db = SQLiteDatabase.openOrCreateDatabase(path, password, factory);
		}

		public CipherElectraDb(File file,
							   String password,
							   CursorFactory factory) {
			db = SQLiteDatabase.openOrCreateDatabase(file, password, factory);
		}

		@Override
		public void execSQL(String sql) {
			db.execSQL(sql);
		}

		@Override
		public Cursor rawQuery(String sql, String[] selectionArgs) {

			return db.rawQuery(sql, selectionArgs);
		}

		@Override
		public long insert(String table, ContentValues cv) {

			return db.insert(table, "", cv);
		}

		@Override
		public long update(String table, ContentValues cv, String where) {

			return db.update(table, cv, where, new String[]{});
		}

		@Override
		public long delete(String table, String where) {

			return db.delete(table, where, new String[]{});
		}

		@Override
		public void beginTransaction() {
			db.beginTransaction();
		}

		@Override
		public void setTransactionSuccessful() {
			db.setTransactionSuccessful();
		}

		@Override
		public void endTransaction() {
			db.endTransaction();
		}

		@Override
		public void close() {
			db.close();
		}

		@Override
		public boolean isOpen() {

			return db.isOpen();
		}

		@Override
		public int getVersion() {

			return db.getVersion();
		}

		@Override
		public Object getWrappedDatabase() {

			return db;
		}
	}
}

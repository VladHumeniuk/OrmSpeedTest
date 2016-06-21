package orm.test.ormtest.core.cupboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.DatabaseCompartment;
import orm.test.ormtest.core.BaseOrmTester;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CupboardTester extends BaseOrmTester {
	public CupboardTester(Context context) {
		super(context);
	}
	private Helper helper;

	@Override
	public void setup() {
		helper = new Helper(context, "Cupboard", 1);
	}

	@Override
	public void destroy() {
		helper.close();
	}

	@Override
	public long timeSimpleCreate(int records) {
		DatabaseCompartment databaseCompartment = cupboard().withDatabase(helper.getWritableDatabase());
		databaseCompartment.delete(CupboardBean.class, null);

		List<CupboardBean> items = setupData(records);

		long time = System.currentTimeMillis();

		for (CupboardBean item: items) {
			databaseCompartment.put(item);
		}


		return System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleCreateBulk(int records) {
		DatabaseCompartment databaseCompartment = cupboard().withDatabase(helper.getWritableDatabase());
		databaseCompartment.delete(CupboardBean.class, null);

		List<CupboardBean> items = setupData(records);

		long time = System.currentTimeMillis();

		databaseCompartment.put(items);

		return System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleRead(int records) {

		DatabaseCompartment databaseCompartment = cupboard().withDatabase(helper.getWritableDatabase());

		timeSimpleCreateBulk(records);

		long time = System.currentTimeMillis();


		List<CupboardBean> list = databaseCompartment.query(CupboardBean.class).list();

		return System.currentTimeMillis() - time;
	}


	@NonNull
	private List<CupboardBean> setupData(int records) {
		List<CupboardBean> items = new ArrayList<>(records);
		for (int i = 0; i < records; i++) {
			CupboardBean record = new CupboardBean();
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
	public String name() {
		return "Cupboard";
	}

	private class Helper extends SQLiteOpenHelper {

		public Helper(Context context, String name, int version) {
			super(context, name, null, version);
			cupboard().register(CupboardBean.class);
		}


		@Override
		public void onCreate(SQLiteDatabase sqLiteDatabase) {
			cupboard().withDatabase(sqLiteDatabase).createTables();
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
			cupboard().withDatabase(sqLiteDatabase).upgradeTables();
		}
	}

}

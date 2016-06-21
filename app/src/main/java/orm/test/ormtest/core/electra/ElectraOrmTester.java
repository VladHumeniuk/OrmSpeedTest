package orm.test.ormtest.core.electra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import org.bitbucket.txdrive.electra.Electra;
import org.bitbucket.txdrive.electra.core.manager.EntityManager;

import java.util.ArrayList;
import java.util.List;

import electra.ED;
import orm.test.ormtest.core.BaseOrmTester;

public class ElectraOrmTester extends BaseOrmTester {
	private EntityManager em;

	public ElectraOrmTester(Context context) {
		super(context);
	}


	@Override
	public void setup() {
		Helper helper = new Helper(context, "electra", 1);
		Electra.configure(ED.TYPES);
		em = Electra.with(helper);
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

	private class Helper extends SQLiteOpenHelper {

		public Helper(Context context, String name, int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase sqLiteDatabase) {
			Electra.with(sqLiteDatabase).createTables();
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
			Electra.with(sqLiteDatabase).updateTables();
		}
	}


	@Override
	public String name() {
		return "Electra";
	}
}

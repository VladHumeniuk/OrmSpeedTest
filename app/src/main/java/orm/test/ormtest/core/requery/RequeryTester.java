package orm.test.ormtest.core.requery;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import orm.test.ormtest.core.BaseOrmTester;

public class RequeryTester extends BaseOrmTester {
	private EntityDataStore<Persistable> ds;

	public RequeryTester(Context context) {
		super(context);
	}

	@Override
	public void setup() {
		StrictMode.enableDefaults();

		DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);
			source.setTableCreationMode(TableCreationMode.DROP_CREATE);

		Configuration configuration = source.getConfiguration();
		ds = new EntityDataStore<>(configuration);

	}

	@Override
	public void destroy() {
		ds.close();
	}

	@NonNull
	private List<RequeryBeanEntity> setupData(int records) {
		List<RequeryBeanEntity> items = new ArrayList<>(records);
		for (int i = 0; i < records; i++) {
			RequeryBeanEntity record = new RequeryBeanEntity();
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
	public long timeSimpleCreate(int records) {
		ds.delete(RequeryBeanEntity.class).get().value();

		List<RequeryBeanEntity> items = setupData(records);

		long time = System.currentTimeMillis();

		for (RequeryBeanEntity item: items) {
			ds.insert(item);
		}


		return System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleCreateBulk(int records) {
		ds.delete(RequeryBeanEntity.class).get().value();

		List<RequeryBeanEntity> items = setupData(records);

		long time = System.currentTimeMillis();

		ds.insert(items);

		return System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleRead(int records) {
		timeSimpleCreateBulk(records);
		long time = System.currentTimeMillis();

		List<RequeryBeanEntity> requeryBeanEntities = ds.select(RequeryBeanEntity.class).get().toList();

		return System.currentTimeMillis() - time;
	}

	@Override
	public String name() {
		return "Requery";
	}
}


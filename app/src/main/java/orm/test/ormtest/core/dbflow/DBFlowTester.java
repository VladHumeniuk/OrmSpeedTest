package orm.test.ormtest.core.dbflow;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;

import java.util.ArrayList;
import java.util.List;

import orm.test.ormtest.core.BaseOrmTester;

public class DBFlowTester extends BaseOrmTester {

	public DBFlowTester(Context context) {
		super(context);
	}

	@Override
	public void setup() {
		FlowManager.init(new FlowConfig.Builder(context)
				.openDatabasesOnInit(true).build());
	}

	@Override
	public void destroy() {
		FlowManager.destroy();
	}

	@Override
	public long timeSimpleCreate(int records) {
		SQLite.delete(DBFlowBean.class).execute();

		List<DBFlowBean> DBFlowBeen = setupData(records);

		long time = System.currentTimeMillis();

		for (DBFlowBean item : DBFlowBeen) {
			item.insert();
		}

		return  System.currentTimeMillis() - time;
	}


	@NonNull
	private List<DBFlowBean> setupData(int records) {
		List<DBFlowBean> items = new ArrayList<>(records);
		for (int i = 0; i < records; i++) {
			DBFlowBean record = new DBFlowBean();
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
		SQLite.delete(DBFlowBean.class).execute();


		List<DBFlowBean> DBFlowBeen = setupData(records);

		long time = System.currentTimeMillis();

		DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);

		ProcessModelTransaction<DBFlowBean> processModelTransaction =
				new ProcessModelTransaction.Builder<>(new ProcessModelTransaction.ProcessModel<DBFlowBean>() {
					@Override
					public void processModel(DBFlowBean model) {
						model.insert();
					}
				}).processListener(new ProcessModelTransaction.OnModelProcessListener<DBFlowBean>() {
					@Override
					public void onModelProcessed(long current, long total, DBFlowBean modifiedModel) {

					}
				}).addAll(DBFlowBeen).build();



		database.executeTransaction(processModelTransaction);

		return  System.currentTimeMillis() - time;
	}

	@Override
	public long timeSimpleRead(int records) {

		timeSimpleCreateBulk(records);

		long time = System.currentTimeMillis();

		List<DBFlowBean> DBFlowBeen = SQLite.select().from(DBFlowBean.class).queryList();

		return System.currentTimeMillis() - time;
	}

	@Override
	public String name() {
		return "DB Flow";
	}
}

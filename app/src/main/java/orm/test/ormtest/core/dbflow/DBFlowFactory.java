package orm.test.ormtest.core.dbflow;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class DBFlowFactory extends AbstractOrmTestFactory {


	public DBFlowFactory(Context context) {
		super(context);
	}

	@Override
	public OrmTester buildOrmTester() {
		return new DBFlowTester(context);
	}
}

package orm.test.ormtest.core.electra;


import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class ElectraFactory extends AbstractOrmTestFactory {


	public ElectraFactory(Context context) {
		super(context);
	}

	@Override
	public OrmTester buildOrmTester() {
		return new ElectraOrmTester(context);
	}
}

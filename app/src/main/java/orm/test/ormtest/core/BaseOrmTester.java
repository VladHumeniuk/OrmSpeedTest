package orm.test.ormtest.core;

import android.content.Context;

abstract public class BaseOrmTester implements OrmTester {
	protected Context context;

	public BaseOrmTester(Context context) {
		this.context = context;
	}


}

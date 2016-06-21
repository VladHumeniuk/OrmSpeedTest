package orm.test.ormtest.core.cupboard;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class CupboardFactory extends AbstractOrmTestFactory {


	public CupboardFactory(Context context) {
		super(context);
	}

	@Override
	public OrmTester buildOrmTester() {
		return new CupboardTester(context);
	}
}

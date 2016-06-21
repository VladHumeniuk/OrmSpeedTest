package orm.test.ormtest.core.requery;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class RequeryFactory extends AbstractOrmTestFactory {
	public RequeryFactory(Context context) {
		super(context);
	}

	@Override
	public OrmTester buildOrmTester() {
		return new RequeryTester(context);
	}
}

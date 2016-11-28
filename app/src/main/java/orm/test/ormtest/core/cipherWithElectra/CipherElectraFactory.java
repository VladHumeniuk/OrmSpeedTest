package orm.test.ormtest.core.cipherWithElectra;


import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;
import orm.test.ormtest.core.electra.CipherElectraOrmTester;

public class CipherElectraFactory extends AbstractOrmTestFactory {


	public CipherElectraFactory(Context context) {
		super(context);
	}

	@Override
	public OrmTester buildOrmTester() {
		return new CipherElectraOrmTester(context);
	}
}

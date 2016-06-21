package orm.test.ormtest.core;


import android.content.Context;

import orm.test.ormtest.core.cupboard.CupboardFactory;
import orm.test.ormtest.core.dbflow.DBFlowFactory;
import orm.test.ormtest.core.electra.ElectraFactory;
import orm.test.ormtest.core.requery.RequeryFactory;

abstract public class AbstractOrmTestFactory {
	public static final int ELECTRA = 0;
	public static final int DBFLOW = 1;
	public static final int CUPBOARD = 2;
	public static final int REQUERY = 3;

	protected Context context;

	public AbstractOrmTestFactory(Context context) {
		this.context = context;
	}

	abstract public OrmTester buildOrmTester();

	public static AbstractOrmTestFactory buildFactory(int orm, Context context) {
		AbstractOrmTestFactory factory = null;

		switch (orm) {
			case ELECTRA: {
				factory = new ElectraFactory(context);
				break;
			}
			case DBFLOW: {
				factory = new DBFlowFactory(context);
				break;
			}
			case CUPBOARD: {
				factory = new CupboardFactory(context);
				break;
			}
			case REQUERY: {
				factory = new RequeryFactory(context);
				break;
			}
		}

		return factory;
	}
}

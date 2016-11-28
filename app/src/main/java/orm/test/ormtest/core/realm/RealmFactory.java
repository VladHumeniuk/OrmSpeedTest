package orm.test.ormtest.core.realm;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class RealmFactory extends AbstractOrmTestFactory {

    public RealmFactory(Context context) {

        super(context);
    }

    @Override
    public OrmTester buildOrmTester() {

        return new RealmTester(context);
    }
}

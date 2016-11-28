package orm.test.ormtest.core.cipher;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class CipherFactory extends AbstractOrmTestFactory {

    public CipherFactory(Context context) {

        super(context);
    }

    @Override
    public OrmTester buildOrmTester() {

        return new CipherTester(context);
    }
}

package orm.test.ormtest.core.realmEncrypted;

import android.content.Context;

import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;

public class RealmEncryptedFactory extends AbstractOrmTestFactory {

    public RealmEncryptedFactory(Context context) {

        super(context);
    }

    @Override
    public OrmTester buildOrmTester() {

        return new RealmEncryptedTester(context);
    }
}

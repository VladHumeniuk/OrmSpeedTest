package orm.test.ormtest.core.realm;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import orm.test.ormtest.core.BaseOrmTester;

public class RealmTester extends BaseOrmTester {

    private RealmConfiguration configuration;

    public RealmTester(Context context) {

        super(context);
    }

    @Override
    public void setup() {
        Realm.init(context);
        configuration = new RealmConfiguration.Builder().name("electra.realm")
                                                                           .build();
    }

    @NonNull
    private List<RealmBean> setupData(int records) {
        List<RealmBean> items = new ArrayList<>(records);
        for (int i = 0; i < records; i++) {
            RealmBean record = new RealmBean();
            record.setId(i);
            record.setName("name");
            record.setAddress("Address");
            record.setCity("City");
            record.setPhone(123456789);
            record.setState("State");

            items.add(record);
        }

        return items;
    }

    @Override
    public void destroy() {
        Realm.getInstance(configuration).close();
    }

    @Override
    public long timeSimpleCreate(int records) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.where(RealmBean.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        List<RealmBean> data = setupData(records);

        long time = System.currentTimeMillis();
        for (RealmBean bean : data) {
            realm.beginTransaction();
            realm.copyToRealm(bean);
            realm.commitTransaction();
        }

        return System.currentTimeMillis() - time;
    }

    @Override
    public long timeSimpleCreateBulk(int records) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.where(RealmBean.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        List<RealmBean> data = setupData(records);

        long time = System.currentTimeMillis();
        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();

        return System.currentTimeMillis() - time;
    }

    @Override
    public long timeSimpleRead(int records) {
        Realm realm = Realm.getInstance(configuration);

        timeSimpleCreateBulk(records);

        long time = System.currentTimeMillis();

        List<RealmBean> list = realm.where(RealmBean.class).findAll();

        return System.currentTimeMillis() - time;
    }

    @Override
    public String name() {

        return "Realm";
    }
}

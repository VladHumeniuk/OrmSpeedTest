package orm.test.ormtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import orm.test.ormtest.core.AbstractOrmTestFactory;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);
	}

	@OnClick(R.id.electra)
	public void electra(View view) {
		startActivityForTest(AbstractOrmTestFactory.ELECTRA);
	}

	@OnClick(R.id.dbflow)
	public void dbflow(View view) {
		startActivityForTest(AbstractOrmTestFactory.DBFLOW);
	}

	@OnClick(R.id.cupboard)
	public void cupboard(View view) {
		startActivityForTest(AbstractOrmTestFactory.CUPBOARD);
	}

	@OnClick(R.id.requery)
	public void requery(View view) {
		startActivityForTest(AbstractOrmTestFactory.REQUERY);
	}

	@OnClick(R.id.cipher_electra)
	public void cipherElectra(View view){
        startActivityForTest(AbstractOrmTestFactory.CIPHER_ELECTRA);
	}

    @OnClick(R.id.realm)
    public void realm(View view){
        startActivityForTest(AbstractOrmTestFactory.REALM);
    }

    @OnClick(R.id.realm_encrypted)
    public void realmEncrypted(View view){
        startActivityForTest(AbstractOrmTestFactory.REALM_ENCRYPTED);
    }

    @OnClick(R.id.cipher)
    public void cipher(View view){
        startActivityForTest(AbstractOrmTestFactory.CIPHER);
    }

	private void startActivityForTest(int type) {
		Intent intent = new Intent(this, TestActivity.class);
		intent.putExtra(TestActivity.KEY_ORM, type);
		startActivity(intent);
	}
}

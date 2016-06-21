package orm.test.ormtest;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import orm.test.ormtest.core.AbstractOrmTestFactory;
import orm.test.ormtest.core.OrmTester;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {
	public static final String KEY_ORM = "ORM";
	public static final int S_COUNT_1000 = 1000;
	public static final int M_COUNT_10000 = 10000;
	public static final int L_COUNT_50000 = 50000;
	private OrmTester ormTester;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		ormTester = AbstractOrmTestFactory.buildFactory(getIntent().getIntExtra(KEY_ORM, -1), this).buildOrmTester();
		ormTester.setup();

		setTitle(ormTester.name());

		ButterKnife.bind(this);
	}

	@OnClick(R.id.simpleCreate1000)
	public void simpleCreate1000(View view) {
		startInsert(S_COUNT_1000);
	}

	@OnClick(R.id.simpleCreate10000)
	public void simpleCreate10000(View view) {
		startInsert(M_COUNT_10000);
	}

	@OnClick(R.id.simpleCreate50000)
	public void simpleCreate50000(View view) {
		startInsert(L_COUNT_50000);
	}

	@OnClick(R.id.simpleCreateBulk1000)
	public void simpleCreateBulk1000(View view) {
		startInsertBulk(S_COUNT_1000);
	}

	@OnClick(R.id.simpleCreateBulk10000)
	public void simpleCreateBulk10000(View view) {
		startInsertBulk(M_COUNT_10000);
	}

	@OnClick(R.id.simpleCreateBulk50000)
	public void simpleCreateBulk50000(View view) {
		startInsertBulk(L_COUNT_50000);
	}

	@OnClick(R.id.read1000)
	public void read1000(View view) {
		read(S_COUNT_1000);
	}

	@OnClick(R.id.read10000)
	public void read10000(View view) {
		read(M_COUNT_10000);
	}

	@OnClick(R.id.read50000)
	public void read50000(View view) {
		read(L_COUNT_50000);
	}


	private void startInsert(final int items) {
		showDialog();

		Observable.defer(new Func0<Observable<Long>>() {
			@Override
			public Observable<Long> call() {
				return Observable.just(ormTester.timeSimpleCreate(items));
			}
		})
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long result) {
						hideDialog();
						setResult(result);
					}
				});

	}

	private void startInsertBulk(final int items) {
		showDialog();

		Observable.defer(new Func0<Observable<Long>>() {
			@Override
			public Observable<Long> call() {
				return Observable.just(ormTester.timeSimpleCreateBulk(items));
			}
		})
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long result) {
						hideDialog();
						setResult(result);
					}
				});

	}

	private void read(final int items) {
		showDialog();

		Observable.defer(new Func0<Observable<Long>>() {
			@Override
			public Observable<Long> call() {
				return Observable.just(ormTester.timeSimpleRead(items));
			}
		})
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Long>() {
					@Override
					public void call(Long result) {
						hideDialog();
						setResult(result);
					}
				});

	}

	private void showDialog() {
		progressDialog = ProgressDialog.show(this, "In progress...", "In progress...");
	}

	private void hideDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	private void setResult(Long mills) {
		((TextView) findViewById(R.id.result)).setText("Result: " + String.valueOf(mills));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ormTester.destroy();

	}

}
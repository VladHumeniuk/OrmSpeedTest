package orm.test.ormtest.core;

public interface OrmTester {
	void setup();
	void destroy();

	long timeSimpleCreate(int records);
	long timeSimpleCreateBulk(int records);
	long timeSimpleRead(int records);

	String name();
}

package orm.test.ormtest.core.requery;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;

@Entity
public interface RequeryBean {
	@Key @Generated
	 long getId();

	 String getName();
	 String getAddress();
	 String getCity();
	 String getState();
	 long getPhone();

}

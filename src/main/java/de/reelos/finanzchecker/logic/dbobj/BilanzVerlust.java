package de.reelos.finanzchecker.logic.dbobj;

import java.util.Date;

public class BilanzVerlust extends BilanzBase {

	public BilanzVerlust(int id, Date createDate, Date executeDate, Date endDate, Type type, double value, int uid) {
		super(id, createDate, executeDate, endDate, type, value, uid);
	}
	
	@Override
	public KardinalType getKardinalType() {
		return KardinalType.MALIFIT;
	}

}

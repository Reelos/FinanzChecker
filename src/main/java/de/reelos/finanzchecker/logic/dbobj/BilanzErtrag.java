package de.reelos.finanzchecker.logic.dbobj;

import java.util.Date;

public class BilanzErtrag extends BilanzBase {

	public BilanzErtrag(int id, Date createDate, Date executeDate, Date endDate, Type type, double value, int uid) {
		super(id, createDate, executeDate, endDate, type, value, uid);
	}

}
package com.pandero.dao;



public abstract class FabricaDao {

	public static final int MYSQL = 1;
	
	public abstract SocioDao getSocioDao();
	public abstract UbigeoDAO getUbigeoDao();
	public abstract UsuarioDao getUsuarioDao();
	public abstract PagoDao getPagoDao();
	public abstract ReporteDAO getReporteDao();
	public static FabricaDao getFactorty(int bd) {
		switch (bd) {
			case MYSQL:
				return new FabricaMysql();
		}
		return null;
	}

	

}

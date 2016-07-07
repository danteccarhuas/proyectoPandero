package com.pandero.dao;


public class FabricaMysql extends  FabricaDao {
	
	@Override
	public SocioDao getSocioDao() {
		// TODO Auto-generated method stub
		return new MySqlSocioDao();
	}

	@Override
	public UbigeoDAO getUbigeoDao() {
		// TODO Auto-generated method stub
		return new MySqlUbigeo();
	}

	@Override
	public UsuarioDao getUsuarioDao() {
		// TODO Auto-generated method stub
		return new MySqlUsuario();
	}

	@Override
	public PagoDao getPagoDao() {
		// TODO Auto-generated method stub
		return new MySqlPagoDao();
	}

	@Override
	public ReporteDAO getReporteDao() {
		// TODO Auto-generated method stub
		return new MySqlReporteDAO();
	}
	
	
	
}

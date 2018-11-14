package main;
import java.sql.SQLException;

import metier.Requetes;
import model.Apprenant;

public class Application {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		for (Apprenant apprenant : Requetes.getAllApprenantsAvecMapping()) {
			
			System.out.println(apprenant);
		}
		
		
		
	}
	
	
}

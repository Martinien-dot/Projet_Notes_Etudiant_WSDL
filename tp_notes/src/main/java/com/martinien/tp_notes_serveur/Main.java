package com.martinien.tp_notes_serveur;

import javax.swing.JOptionPane;


import jakarta.xml.ws.Endpoint;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Endpoint endpoint = Endpoint.
				publish("http://localhost:8080/servicesNotes",
				new ServiceGestionNotes());
		JOptionPane.showMessageDialog(null, "Stop serveur");
		endpoint.stop();
	}

}

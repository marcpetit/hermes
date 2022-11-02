package fr.marcpetit.architecture.hermes.controllers;

import fr.marcpetit.architecture.hermes.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Product {
	@FXML private TextField productName;
	@FXML private TextField productReference;
	
	private Model model;
	
	public void initialize() {
		productName.setEditable(false);
		productName.textProperty().addListener((observable, oldValue, newValue) -> {
			this.model.getProduct().get().setName(newValue);
		});
		productReference.setEditable(false);
		productReference.textProperty().addListener((observable, oldValue, newValue) -> {
			this.model.getProduct().get().setReference(newValue);
		});
	}

	public void setModel( Model model ) {
		this.model = model;
		
		if (model != null) {
			boolean productInitialized = model.getProduct().get() != null;

			model.getProduct().addListener((observable, oldValue, newValue) -> {
				productName.setEditable(newValue != null);
				productName.setText(newValue.getName());
				productReference.setEditable(newValue != null);	
				productReference.setText(newValue.getReference());
			});
			productName.setEditable(productInitialized);			
			productReference.setEditable(productInitialized);			
		} else {
			productName.setEditable(false);
			productReference.setEditable(false);
		}
	}
	
	public void update() {
		fr.marcpetit.architecture.hermes.model.Product product = model.getProduct().get();
		productName.setText(product.getName());
		productReference.setText(product.getReference());
	}
}

package fr.marcpetit.architecture.hermes.controllers;

import fr.marcpetit.architecture.hermes.model.Milestone;
import fr.marcpetit.architecture.hermes.model.Model;
import fr.marcpetit.architecture.hermes.model.Product;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductProperties {
	@FXML private TextField productName;
	@FXML private TextField productReference;
	@FXML private ListView<Milestone> milestones;
	@FXML private TextField milestone;
	
	private Product product;
	private Stage stage;
	
	public void initialize() {
		milestone.setEditable(false);
		milestone.setText("---");
		
		milestones.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Milestone>)(x -> {
			Milestone m = milestones.getSelectionModel().getSelectedItem();
			if (m != null) {
				milestone.setText(m.toString());
				milestone.setEditable(true);
			} else {
				milestone.setText("");
				milestone.setEditable(false);				
			}
		}));
		
		milestone.textProperty().addListener((observable,oldValue,newValue) -> {
			milestones.getSelectionModel().getSelectedItem().setId(newValue);
			milestones.refresh();
		});
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setModel( Model model ) {
		this.product = model.getProduct().get();;
		
		productName.setText(product.getName());
		productReference.setText(product.getReference());
		milestones.setItems(product.getMilestonesProperty());
	}
	
	@FXML
	public void validate() {
		product.setName(productName.getText());
		product.setReference(productReference.getText());
		stage.close();
	}
	
	@FXML
	public void cancel() {
		stage.close();
	}
	
	@FXML
	public void appendMilestone() {
		Milestone m = new Milestone();
		m.setId("milestone");
		product.getMilestonesProperty().add(m);
	}
}

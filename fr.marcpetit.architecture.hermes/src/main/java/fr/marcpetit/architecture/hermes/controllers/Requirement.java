package fr.marcpetit.architecture.hermes.controllers;

import fr.marcpetit.architecture.hermes.model.Category;
import fr.marcpetit.architecture.hermes.model.Milestone;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Requirement {
	@FXML private TextField id;
	@FXML private TextField text;
	@FXML private ComboBox<Milestone> milestone;
	@FXML private ComboBox<Category> category;
	
	private fr.marcpetit.architecture.hermes.model.Requirement requirement;
	
	public void initialize() {
		id.setText("");
		id.setEditable(false);
		text.setText("");
		text.setEditable(false);
		milestone.setValue(null);
		milestone.setDisable(true);
		category.setValue(null);
		category.setDisable(true);
	}
	
	public interface ModificationListener { public void modified(); };
	public void addModificationListener(ModificationListener listener) {
		id.textProperty().addListener((observable,oldValue,newValue) -> {
			if (requirement != null) requirement.setId(newValue);
			listener.modified();
			});
		text.textProperty().addListener((observable,oldValue,newValue) -> {
			if (requirement != null) requirement.setText(newValue);
			listener.modified();
			});
		milestone.valueProperty().addListener((observable,oldValue,newValue) -> {
			if (requirement != null) requirement.setMilestone(newValue);
			listener.modified();
		});
		category.valueProperty().addListener((observable,oldValue,newValue) -> {
			if (requirement != null) requirement.setCategory(newValue);
			listener.modified();
		});
	}
	
	public void setMilestones( ObservableList<Milestone> milestones ) {
		milestone.setItems(milestones);
	}

	public void setCategories( ObservableList<Category> categories ) {
		category.setItems(categories);
	}

	public void setModel( fr.marcpetit.architecture.hermes.model.Requirement requirement ) {
		this.requirement = requirement;
		
		boolean ok = (requirement != null);
		id.setEditable(ok);
		text.setEditable(ok);
		milestone.setDisable(!ok);
		category.setDisable(!ok);
		
		if (ok) {
			id.setText(requirement.getId());
			text.setText(requirement.getText());
			milestone.setValue(requirement.getMilestone());
			category.setValue(requirement.getCategory());
		} else {
			initialize();
		}
	}

	public void update() {
		setModel(requirement);
	}
}

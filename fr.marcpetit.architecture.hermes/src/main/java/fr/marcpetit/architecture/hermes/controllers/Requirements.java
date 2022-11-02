package fr.marcpetit.architecture.hermes.controllers;


import java.util.ArrayList;
import java.util.List;

import fr.marcpetit.architecture.hermes.model.Requirement;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTableCell;

public class Requirements {
	@FXML
	private TableView<Requirement> requirements;

	@FXML
	private TableColumn<Requirement, String> colId;

	@FXML
	private TableColumn<Requirement, String> colText;

	public interface RequirementChangeListener { public void requirementChanged(Requirement req); };
	public void addChangeListener(RequirementChangeListener listener) { listeners.add(listener); };
	private List<RequirementChangeListener> listeners = new ArrayList<RequirementChangeListener>();
	
	public void initialize() {
		requirements.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableViewSelectionModel<Requirement> selectionModel = requirements.getSelectionModel();
		selectionModel.setCellSelectionEnabled(true);
		
		requirements.setEditable(true);
		colId.setCellFactory(TextFieldTableCell.<Requirement>forTableColumn());
		colId.setOnEditCommit(event -> {
			Requirement req = (Requirement) event.getTableView().getItems().get(
                    event.getTablePosition().getRow()
                    );
			req.setId(event.getNewValue());
			listeners.forEach((listener) -> {
				listener.requirementChanged(req);
			});
		});
		colText.setCellFactory(TextFieldTableCell.<Requirement>forTableColumn());
		colText.setOnEditCommit(event -> {
			Requirement req = (Requirement) event.getTableView().getItems().get(
                    event.getTablePosition().getRow()
                    );
			req.setText(event.getNewValue());
			listeners.forEach((listener) -> {
				listener.requirementChanged(req);
			});
		});
	}

	public void setModel(ObservableList<Requirement> model) {
		requirements.setItems(model);
		requirements.refresh();
	}
	
	public void addSelectionListener( ListChangeListener<Requirement> listener ) {
		requirements.getSelectionModel().getSelectedItems().addListener(listener);
	}
	
	public void update() {
		requirements.refresh();
	}
}

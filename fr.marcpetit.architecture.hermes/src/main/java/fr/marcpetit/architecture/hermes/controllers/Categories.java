package fr.marcpetit.architecture.hermes.controllers;

import java.util.ArrayList;

import fr.marcpetit.architecture.hermes.model.Category;
import fr.marcpetit.architecture.hermes.model.Model;
import fr.marcpetit.architecture.hermes.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;

public class Categories {
	
	private Product product;
	private TreeItem<Category> rootCategory;
	private Stage stage;
	
	@FXML
	private TreeTableView<Category> categories;
	
	@FXML
	private TreeTableColumn<Category, String> nameCol;
	
	@FXML
	private TreeTableColumn<Category, String> descriptionCol;
	
	@FXML
	private TreeTableColumn<Category, String> uriCol;
	
	public void initialize() {
		categories.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
		TreeTableViewSelectionModel<Category> selectionModel = categories.getSelectionModel();
		selectionModel.setCellSelectionEnabled(true);
		
		categories.setEditable(true);
		
		nameCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		nameCol.setOnEditCommit(event -> {
			Category cat = (Category) event.getRowValue().getValue();
			cat.setName(event.getNewValue());
		});
		
		descriptionCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		descriptionCol.setOnEditCommit(event -> {
			Category cat = (Category) event.getRowValue().getValue();
			cat.setDescription(event.getNewValue());
		});
		
		uriCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		uriCol.setOnEditCommit(event -> {
			Category cat = (Category) event.getRowValue().getValue();
			cat.setUri(event.getNewValue());
		});
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private TreeItem<Category> getCategoryItem(Category category) {
		var cat = new TreeItem<Category>(category);
		
		category.getSubSubCategories().forEach(child -> {
			cat.getChildren().add(getCategoryItem(child));
		});
		
		return cat;
	}

	public void setModel( Model model ) {
		this.product = model.getProduct().get();
		if (this.product == null) return;
		
		rootCategory = getCategoryItem(this.product.getCategory());
		categories.setRoot(rootCategory);
	}
	
	private Category getCategory(TreeItem<Category> item) {
		var category = item.getValue();
		
		var children = new ArrayList<Category>();
		item.getChildren().forEach(i -> children.add(getCategory(i)));
		
		category.setCategories(children);
		
		return category;
	}
	
	@FXML
	public void addChild() {
		var current = categories.getSelectionModel().getSelectedItem();
		var cat = new Category();
		cat.setName("---");
		current.getChildren().add(new TreeItem<Category>(cat));
	}

	@FXML
	public void delete() {
		var current = categories.getSelectionModel().getSelectedItem();
		var parent = current.getParent();
		
		if (parent != null) parent.getChildren().remove(current);
	}

	@FXML
	public void validate() {
		product.setCategory(getCategory(rootCategory));
		
		stage.close();
	}
	
	@FXML
	public void cancel() {
		stage.close();
	}
}

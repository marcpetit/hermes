package fr.marcpetit.architecture.hermes.controllers;

import java.io.File;
import java.io.IOException;

import fr.marcpetit.architecture.hermes.model.Category;
import fr.marcpetit.architecture.hermes.model.Model;
import fr.marcpetit.architecture.hermes.model.Product;
import fr.marcpetit.architecture.hermes.model.Requirement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main {
	@FXML
	private fr.marcpetit.architecture.hermes.controllers.Product productController;

	@FXML
	private BorderPane mainPane;

	@FXML
	private MenuItem saveAs;
	
	@FXML
	private MenuItem editProduct;
	
	@FXML
	private MenuItem addRequirement;
	
	@FXML
	private MenuItem manageCategories;
	
	@FXML
	private ScrollPane scroll;

	@FXML
	private fr.marcpetit.architecture.hermes.controllers.Requirements requirementsController;

	@FXML
	private fr.marcpetit.architecture.hermes.controllers.Requirement requirementController;
	
	Property<Requirement> selectedRequirement;

	public void initialize() {
		saveAs.setDisable(true);
		editProduct.setDisable(true);
		addRequirement.setDisable(true);
		manageCategories.setDisable(true);
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		requirementsController.addSelectionListener((selection) -> {
			var selected = selection.getList();
			Requirement req = selected.isEmpty() ? null : selected.get(selected.size()-1);
			requirementController.setModel(req);
		});

		requirementsController.addChangeListener((requirement)->{
			requirementController.update();
		});

		requirementController.addModificationListener(()->{
			requirementsController.update();
		});
	}

	public void setStage(Stage stage) {
		stage.setTitle("Hermes - ");
		model.getFilePathProperty().addListener((observable, oldValue, newValue) -> {
			String path = model.getFilePath() != null ? model.getFilePath() : "";
			stage.setTitle("Hermes - " + path);
		});
	}

	private Model model;

	public void setModel(Model model) {
		this.model = model;
		productController.setModel(model);

		model.getProduct().addListener((observable, oldValue, newValue) -> {
			boolean productNotOK = model.getProduct().isNull().get();
			saveAs.setDisable(productNotOK);
			editProduct.setDisable(productNotOK);
			addRequirement.setDisable(productNotOK);
			manageCategories.setDisable(productNotOK);

			ObservableList<Requirement> reqs = newValue.getRequirementsProperty();
			requirementsController.setModel(reqs);
		});
	}

	@FXML
	private void newProduct() {
		Product product = new Product();
		Category rootCategory = new Category();
		rootCategory.setName("Requirements");
		rootCategory.setDescription("Base requirements category");
		product.setCategory(rootCategory);
		model.setProduct(product);
		model.setFilePath(null);
		requirementController.setMilestones(null);
	}

	@FXML
	private void saveProductAs() {
		Product product = model.getProduct().get();
		Stage stage = (Stage) mainPane.getScene().getWindow();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save product as ...");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
				new FileChooser.ExtensionFilter("Hermes Specification", "*.hms"),
				new FileChooser.ExtensionFilter("XML", "*.xml"));
		fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(1));
		fileChooser.setInitialFileName(product.getName() + ".hms");
		File outFile = fileChooser.showSaveDialog(stage);
		if (outFile == null) {
			System.out.println("\"Save as\" canceled");
			return;
		}
		model.setFilePath(outFile.getAbsolutePath());

		try {
			JAXBContext context = JAXBContext.newInstance(Product.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(product, outFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void openProduct() {
		Stage stage = (Stage) mainPane.getScene().getWindow();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open product");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
				new FileChooser.ExtensionFilter("Hermes Specification", "*.hms"),
				new FileChooser.ExtensionFilter("XML", "*.xml"));
		fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(1));
		// fileChooser.setInitialFileName(product.getName()+".hms");
		File file = fileChooser.showOpenDialog(stage);
		if (file == null) {
			System.out.println("\"Open\" canceled");
			return;
		}
		model.setFilePath(file.getAbsolutePath());

		try {
			JAXBContext context = JAXBContext.newInstance(Product.class);
			Unmarshaller u = context.createUnmarshaller();
			Product p = (Product) u.unmarshal(file);
			System.out.println("Reqs : " + p.getRequirements().size());
			model.setProduct(p);
			requirementsController.setModel(p.getRequirementsProperty());
			requirementController.setMilestones(p.getMilestonesProperty());
			requirementController.setCategories(p.getCategories());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void editProduct() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/marcpetit/architecture/hermes/views/ProductProperties.fxml"));
		try {
			Parent parent = loader.load();
			ProductProperties controller = loader.getController();
			controller.setModel(model);
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			controller.setStage(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productController.update();
	}

	@FXML
	private void exitApplication() {
		Platform.exit();
	}
	
	@FXML
	private void addRequirement() {
		Requirement r = new Requirement();
		r.setId( "new" );
		r.setText( "..." );
		model.getProduct().get().getRequirementsProperty().add(r);
	}
	
	@FXML
	private void manageCategories() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/marcpetit/architecture/hermes/views/Categories.fxml"));
		try {
			//Stage stage = (Stage) mainPane.getScene().getWindow();

			Parent parent = loader.load();
			Categories controller = loader.getController();
			controller.setModel(model);
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			controller.setStage(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productController.update();
	}
}

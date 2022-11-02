package fr.marcpetit.architecture.hermes.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
	private SimpleObjectProperty<Product> product = new SimpleObjectProperty<>();
	public void setProduct( Product product ) {
		this.product.set(product);
	}
	public SimpleObjectProperty<Product> getProduct() {
		return product;
	}
	
	private SimpleStringProperty filePath = new SimpleStringProperty("");
	public void setFilePath( String filePath ) {
		this.filePath.set( filePath );
	}
	public String getFilePath() {
		return filePath.get();
	}
	public SimpleStringProperty getFilePathProperty() {
		return filePath;
	}
}

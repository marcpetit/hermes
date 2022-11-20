module fr.marcpetit.architecture.hermes {
	exports fr.marcpetit.architecture.hermes.views;
	
	opens fr.marcpetit.architecture.hermes.controllers to javafx.fxml;
	opens fr.marcpetit.architecture.hermes.model to jakarta.xml.bind, javafx.base;
	
	requires transitive javafx.graphics;
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires jakarta.xml.bind;
	requires java.base;
	requires jdk.javadoc;
}
package ui;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import fr.marcpetit.architecture.hermes.views.Hermes;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class TestFXBase extends ApplicationTest {

	private static boolean isHeadless = true;
	
	protected Stage stage;

	static {
	    if (isHeadless) {
	        System.setProperty("testfx.robot", "glass");
	        System.setProperty("testfx.headless", "true");
	    }

	    try {
	        ApplicationTest.launch(Hermes.class);
	    } catch (Exception e) {
	        // oh no
	    }
	}
	
	@After
	public void afterEachTest() throws TimeoutException {

		FxToolkit.hideStage();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}
	
	public Parent getRootNode() {
		return FxToolkit.toolkitContext().getRegisteredStage().getScene().getRoot();
	}
}
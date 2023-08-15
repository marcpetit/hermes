package ui;

import java.util.stream.Stream;

import org.junit.Assert;
import org.testfx.api.FxToolkit;
import org.testfx.api.FxToolkitContext;
import org.testfx.util.WaitForAsyncUtils;

import fr.marcpetit.architecture.hermes.model.Milestone;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StepDefinitions extends TestFXBase {
	@Given("the application is started")
	public void the_application_is_started() {
	}

	@When("the product manager clicks on New Product")
	public void the_product_manager_clicks_on_new_product() {
		clickOn("#File");
		Menu file = getFileMenu();
		WaitForAsyncUtils.waitForAsyncFx(2000, () -> file.isVisible());
		clickOn("#NewProduct");
	}

	@When("the product manager clicks on File - Properties")
	public void the_product_manager_clicks_on_file_properties() {
		clickOn("#File");
		Menu file = getFileMenu();
		WaitForAsyncUtils.waitForAsyncFx(2000, () -> file.isVisible());
		clickOn("#editProduct");
		WaitForAsyncUtils.waitForFxEvents();
	}

	@When("the product manager clicks on append Milestone")
	public void the_product_manager_clicks_on_append_milestone() {
		clickOn("#appendMilestone");
	}

	@When("the product manager selects the last Milestone")
	public void the_product_manager_selects_the_last_milestone() {
		ListView<Milestone> milestones = getMilestones();
		milestones.getSelectionModel().clearAndSelect(milestones.getItems().size()-1);
	}

	@Then("a product is being edited")
	public void a_product_is_being_edited() {
		MenuItem editProduct = getEditProduct();
		Assert.assertEquals(false, editProduct.isDisable());
	}
	
	@Given("the product manager sets the product name to {string}")
	public void the_product_manager_sets_the_product_name_to(String name) {
		getProductName().setText(name);
	}
	
	@Given("the product manager sets the current milestone name to {string}")
	public void the_product_manager_sets_the_current_milestone_name_name_to(String name) {
		TextField field = getMilestoneName();
		field.setText(name);
	}
	
	@Then("the product's name is not {string}")
	public void the_product_s_name_is_not(String name) {
		Assert.assertNotEquals(getProductName().getText(), name);
	}
	
	@Then("a {string} milestone exists")
	public void a_xxx_milestone_exists(String name) {
		boolean found = false;
		ObservableList<Milestone> milestones = getMilestones().getItems();
		for(Milestone m : milestones) {
			if (m.getId().equals(name) ) found = true;
		}
		Assert.assertTrue(found);
	}
	
	private Menu getFileMenu() {
		MenuBar bar = (MenuBar) getRootNode().lookup("#MenuBar");
		Menu file = bar.getMenus().filtered(m -> m.getId() == null ? false : m.getId().equals("File")).get(0);
		return file;
	}

	private MenuItem getEditProduct() {
		Menu file = getFileMenu();
		MenuItem editProduct = file.getItems().filtered(i -> i.getId() == null ? false : i.getId().equals("editProduct")).get(0);
		return editProduct;
	}

	private TextField getProductName() {
		TextField productName = (TextField) getRootNode().lookup("#productName");
		return productName;
	}

	private TextField getMilestoneName() {
		TextField name = null;

		for(Window w: Stage.getWindows()) {
			if (w instanceof Stage) {
				Node node = w.getScene().getRoot().lookup("#milestone");
				if (node instanceof TextField) {
					TextField n = (TextField) node;
					name = n;
				}
			}			
		}

		return name;
	}

	private ListView<Milestone> getMilestones() {
		ListView<Milestone> milestones = null;
		
		for(Window w: Stage.getWindows()) {
			if (w instanceof Stage) {
				Node node = w.getScene().getRoot().lookup("#milestones");
				if (node instanceof ListView<?>) {
					@SuppressWarnings("unchecked")
					ListView<Milestone> ms = (ListView<Milestone>) node;
					milestones = ms;
				}
			}			
		}
		
		return milestones;
	}
}

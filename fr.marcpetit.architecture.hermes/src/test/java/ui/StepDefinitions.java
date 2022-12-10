package ui;

import org.junit.Assert;
import org.testfx.util.WaitForAsyncUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

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

	@Then("a product is being edited")
	public void a_product_is_being_edited() {
		MenuItem editProduct = getEditProduct();
		Assert.assertEquals(false, editProduct.isDisable());
	}
	
	@Given("the product manager sets the product name to {string}")
	public void the_product_manager_sets_the_product_name_to(String name) {
		getProductName().setText(name);
	}
	
	@Then("the product's name is not {string}")
	public void the_product_s_name_is_not(String name) {
		Assert.assertNotEquals(getProductName().getText(), name);
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
}

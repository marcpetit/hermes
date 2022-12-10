@ui
@FReq-01
Feature: A product manager can create a product

	Scenario: Create a product from scratch
		Given the application is started
		When the product manager clicks on New Product
		Then a product is being edited

	Scenario: Create a product while another one is being edited
		Given the application is started
		And the product manager clicks on New Product
		And the product manager sets the product name to "This definitely shouldn't be a product's base name !"
		When the product manager clicks on New Product
		Then a product is being edited
		And the product's name is not "This definitely shouldn't be a product's base name !"
	
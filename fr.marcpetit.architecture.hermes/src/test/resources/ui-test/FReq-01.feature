@ui
@FReq-01
Feature: A product manager can create a product

	Scenario: Create a product from scratch
		Given the application is started
		When the product manager clicks on New Product
		Then a product is being edited

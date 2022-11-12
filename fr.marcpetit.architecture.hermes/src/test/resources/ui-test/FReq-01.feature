@ui
Feature: A product manager can create a product
	# FReq-01

	Scenario: Create a product from scratch
		Given the application is started
		When the product manager clicks on New Product
		Then a product is being edited

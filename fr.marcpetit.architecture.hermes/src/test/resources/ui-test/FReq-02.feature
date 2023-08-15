@ui
@FReq-02
Feature: Create a new product milestone

	Scenario: Create a new milestone
		Given the application is started
		And the product manager clicks on New Product
		When the product manager clicks on File - Properties
		And the product manager clicks on append Milestone
		And the product manager selects the last Milestone
		And the product manager sets the current milestone name to "test-milestone"
		Then a "test-milestone" milestone exists

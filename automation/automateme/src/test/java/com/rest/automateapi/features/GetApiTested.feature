Feature: Verify various GET operations using rest-assured
  Scenario: Request the task resource
	When I make "GET" request to "/fake-api-call/php"
	Then I get a "200" response

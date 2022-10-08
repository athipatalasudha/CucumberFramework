

Feature: Login to SFDC
  
    Scenario: Error Message to enter password in password field
    
    Given User on "LoginPage"
    When User enters 'august22-qa@abc.com' in username field
    And User enters no password in password field
    And clicks on login button
    Then error message "Please enter your password." is displayed

    Scenario: Login to Salesforce with valid credentials
    
    Given User on "LoginPage"
    When User enters 'august22-qa@abc.com' in username field
    And User enters 'Selenium1234' in password field
    And clicks on login button
	  Given User on "HomePage"
    Then HomePage is displayed
    
    Scenario: Check RememberMe checkbox
    
    Given User on "LoginPage"
    When User enters 'august22-qa@abc.com' in username field
    And User enters 'Selenium1234' in password field
    And User clicks on RememberMe checkbox
    And clicks on login button
    Given User on "HomePage"
    Then HomePage is displayed
    And User logsout of the Salesforce
    Given User on "LogoutPage"
    And Username is displayed in the username field
    
    Scenario: Forgot Password link
    
    Given User on "LoginPage"
    When User clicks ForgotPassword link
    Then User on "Forgotpasswordpage"
    When User enters 'sathipatala@gmail.com' in email field
    And User clicks Continue button
    Then Password reset message is displayed 
 
    Scenario: Validate login error message
 		
 		Given User on "LoginPage"
    When User enters '123' in username field
    And User enters '22131' in password field
    And clicks on login button
	  Then error message "Please check your username and password. If you still can't log in, contact your Salesforce administrator." is displayed
    
    
    
    
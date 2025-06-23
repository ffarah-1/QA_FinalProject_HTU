"# QA_FinalProject_HTU" 
 Automation and Manual Testing Project – AutomationTestStore

This project is part of a QA Upskilling Program at Al-Hussein Technical University (HTU), where both **manual** and **automated** testing techniques were applied to test critical functionalities of the [AutomationTestStore](https://automationteststore.com/) e-commerce website.

##  Project Description

The objective of this project is to automate major user journeys of an e-commerce platform using Selenium WebDriver and TestNG in Java, and to validate key UI flows through manual testing.

The project includes:
- Account registration & login
- Product search and category navigation
- Cart and checkout flow
- UI validations and error handling
- Social media link verification

---

## 🛠 Tools and Technologies Used

| Tool/Technology     | Purpose                             |
|---------------------|-------------------------------------|
| Selenium WebDriver  | Web automation                      |
| Java                | Test scripting                      |
| TestNG              | Test execution and assertions       |
| ChromeDriver        | Browser driver                      |
| Eclipse IDE         | Development environment             |

---

##  Automation Test Scenarios (TestNG)

| Test Case                          | Description                                               |
|-----------------------------------|-----------------------------------------------------------|
| `SignUp()`                        | Randomly generates and registers a new user              |
| `invalidLogin()`                  | Tests login with incorrect credentials                   |
| `LoginTest()`                     | Logs in with valid credentials                           |
| `ValidProductSearch()`            | Searches for a product and verifies results              |
| `BrandNavigation()`              | Scrolls and opens the Gucci brand page                   |
| `AddRandomItemFromEyes()`        | Opens "Eyes" category under Skin Care and adds to cart   |
| `changeQuantity()`               | Edits item quantity in cart                              |
| `checkout()`                      | Completes checkout steps                                 |
| `InvalidSearch()`                | Searches for a non-existent product                      |
| `verifySocialLink()`             | Verifies Facebook link opens correctly                   |

---

##  Manual Testing

A separate Excel file includes 5 manual test cases:
- Currency switch validation
- Product detail view
- Special offer redirection
- Newsletter subscription
- Product image zoom (visual check)

# 🤖 AutomationExercise – Test Automation Framework

[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Playwright](https://img.shields.io/badge/Framework-Playwright-45ba4b.svg)](https://playwright.dev/)
[![JUnit5](https://img.shields.io/badge/Testing-JUnit%205-25a162.svg)](https://junit.org/junit5/)
[![Allure](https://img.shields.io/badge/Reports-Allure-orange.svg)](https://allurereport.org/)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF.svg)](https://github.com/features/actions)

> **End-to-end UI and REST API test automation framework built with Playwright and Java, covering the key modules of the [AutomationExercise](https://www.automationexercise.com/) web application.**

---

## 📋 Overview

This project is a portfolio test automation framework designed to demonstrate practical skills in:

- **UI Test Automation** using Playwright with the Page Object Model pattern
- **REST API Testing** using Playwright's HTTP client and Jackson
- **CI/CD Integration** with GitHub Actions
- **Test Reporting** with Allure Reports including screenshots, logs, and trace data

**Test subject:** [automationexercise.com](https://www.automationexercise.com/) — a web platform built specifically for practicing test automation skills.

---

## ✨ Framework Features

- 🏗️ **Page Object Model (POM)** architecture — clean separation of UI logic from test logic
- 🔁 **Reusable base setup** via `BaseTest` with `@BeforeEach` / `@AfterEach` lifecycle management
- 📸 **Automatic screenshots** on test failure via custom `TestResultWatcher` extension
- 🌐 **REST API testing** with Playwright's built-in HTTP client (no extra libraries needed)
- 📊 **Allure Reports** with step-level annotations, screenshots, and current URL capture
- ⚙️ **CI/CD pipeline** via GitHub Actions — triggered on push and pull request
- 🎲 **Dynamic test data** generated with Java Faker

---

## 🏗️ Architecture
src/

├── main/java/

│   ├── base/          # BaseTest – browser/page init, teardown

│   ├── pages/         # Page Object classes (POM)

│   ├── config/        # ConfigManager – base URL, env settings

│   └── utils/         # Helpers: ScreenshotExtension, PopupUtils, TestResultWatcher

└── test/java/

├── ui/            # UI test classes

└── api/           # API test classes

.github/

└── workflows/

└── tests.yml      # GitHub Actions CI/CD pipeline

### Design Patterns & Principles
- **Page Object Model (POM)** — each page/screen is a separate class with its own locators and methods
- **Single Responsibility** — page classes contain only elements and actions for that specific screen
- **BaseTest inheritance** — shared browser setup, page initialization, and teardown logic
- **ConfigManager** — centralized configuration (base URL, credentials) for easy maintenance

---

## 🧪 Test Documentation

### 📋 Test Plan
- Defined testing scope across **4 core modules** + REST API
- Testing strategy: Smoke Tests, Functional Tests, E2E Tests, API Tests
- **Out of scope:** Payment gateways, performance, security testing

### ✅ Test Cases — 22 total

| Module | Test Cases | Types |
|---|---|---|
| Authentication (Login / Register) | 5 | Functional, Negative |
| Shopping Cart | 5 | Functional, E2E |
| Product Search | 2 | Functional, Negative |
| Checkout | 3 | E2E |
| REST API — Smoke | 5 | Smoke |
| REST API — E2E | 2 | E2E |

### 🔍 Test Types Applied
- Smoke Testing
- Functional Testing
- Negative Testing
- End-to-End (E2E) Testing
- REST API Testing

---

## 🛠️ Tech Stack

| Tool / Library | Purpose |
|---|---|
| Java | Programming language |
| Playwright | UI automation + HTTP client for API tests |
| JUnit 5 | Test runner and lifecycle management |
| Maven | Build tool and dependency management |
| Jackson | JSON parsing for API responses |
| Java Faker | Dynamic test data generation |
| Allure Reports | Test reporting with screenshots and traces |
| GitHub Actions | CI/CD pipeline |
| IntelliJ IDEA | IDE |
| Postman | API exploration and manual verification |
| Git & GitHub | Version control |

---

## 📊 Allure Reports

Tests are annotated with Allure metadata for structured, readable reports:

- **Step-level annotations** for clear test flow visibility
- **Automatic screenshots** attached on test failure
- **Current URL** captured at the moment of failure
- **Suite and module grouping** for organized test result navigation

To generate the report locally:
```bash
mvn clean test
allure serve target/allure-results
```

---

## ⚙️ CI/CD — GitHub Actions

The pipeline triggers automatically on **push** and **pull request** to the main branch.

**Pipeline stages:**
1. `Checkout repository` — pulls latest code
2. `Setup Java` — configures the JDK runtime
3. `Cache Maven packages` — speeds up dependency resolution on subsequent runs
4. `Install Playwright browsers` — installs Chromium and required system dependencies
5. `Run Tests` — executes all tests via `mvn clean test`
6. `Upload Allure Results` — archives test results as a pipeline artifact

---

## 🌐 API Test Coverage

REST API tests use **Playwright's built-in HTTP client**, eliminating the need for RestAssured or Retrofit.

**Endpoints covered:**

| Endpoint | Method | Test |
|---|---|---|
| `/api/productsList` | GET | Fetch all products |
| `/api/searchProduct` | POST | Search existing / missing params |
| `/api/brandsList` | GET | Fetch all brands |
| `/api/verifyLogin` | POST | Verify account / missing params |
| `/api/createAccount` | POST | Register new user |
| `/api/updateAccount` | PUT | Update user / missing params |
| `/api/deleteAccount` | DELETE | Delete user (E2E flow) |

**E2E API scenarios:**
- Create account → verify login → delete account
- Create account → update data → verify updated values via GET

---

## 🚀 Running Tests Locally

**Prerequisites:** Java 17+, Maven, Playwright browsers

```bash
# Clone the repository
git clone https://github.com/TaloHomes404/Automation_Exercise_Playwright.git
cd automation-exercise

# Install dependencies and Playwright browsers
mvn install
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps"

# Run all tests
mvn clean test

# Generate Allure report
allure serve target/allure-results
```
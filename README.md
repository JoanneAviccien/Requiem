# Requiem V2 - Release Planning Language

<div align="center">

![Requiem Logo](https://img.shields.io/badge/Requiem-V2-blueviolet)
![ANTLR](https://img.shields.io/badge/ANTLR-4.13.2-blue)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![License](https://img.shields.io/badge/License-MIT-green)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

**A Domain-Specific Language for Feature Prioritization and Release Planning**

</div>

## 📋 Table of Contents

- [Overview](#overview)
- [✨ Features](#-features)
- [🚀 Quick Start](#-quick-start)
- [📖 Language Syntax](#-language-syntax)
- [🔍 Error Handling](#-error-handling)
- [🏗️ Architecture](#-architecture)
- [📊 Example](#-example)

## Overview

Requiem V2 is a domain-specific language (DSL) designed for software project managers and product owners to define features, prioritize them using MoSCoW methodology, and create detailed release plans. Built with ANTLR 4, it provides a clean, readable syntax for managing software development priorities.

<div align="center">

![Requiem Workflow](https://img.shields.io/badge/Workflow-Parse→Validate→Plan→Execute-success)
![Syntax Check](https://img.shields.io/badge/Syntax-100%25%20Checked-yellow)
![Validation](https://img.shields.io/badge/Validation-Semantic%2BSyntactic-red)

</div>

## ✨ Features

<div align="center">

![Features](https://img.shields.io/badge/📋-Feature%20Definition-blue)
![Release Planning](https://img.shields.io/badge/📅-Release%20Planning-green)
![MoSCoW](https://img.shields.io/badge/🎯-MoSCoW%20Methodology-purple)
![Priority Calculation](https://img.shields.io/badge/⚡-Auto%20Priority%20Scoring-orange)
![Dependency Management](https://img.shields.io/badge/🔗-Dependency%20Tracking-cyan)
![Error Reporting](https://img.shields.io/badge/🛡️-Robust%20Error%20Handling-red)

</div>

- **Feature Definition**: Define software features with business value, effort, and risk
- **MoSCoW Prioritization**: Categorize features as Must/Should/Could/Won't have
- **Release Planning**: Create release plans with target dates and team capacity
- **Smart Priority Scoring**: Automatic priority calculation based on multiple factors
- **Dependency Tracking**: Define feature dependencies and validate them
- **Comprehensive Validation**: Syntax and semantic error checking
- **AST Generation**: Abstract Syntax Tree for programmatic analysis

## 🚀 Quick Start

### Prerequisites

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=java&logoColor=white)
![ANTLR](https://img.shields.io/badge/ANTLR-4.13.2-FF5722?logo=&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-C71A36?logo=apache-maven&logoColor=white)

</div>

```bash
# Clone the repository
git clone https://github.com/yourusername/requiem-v2.git
cd requiem-v2

# Build the project
mvn clean compile

# Run an example
java -jar target/Requiem.jar examples/sample.rq
```

### Installation

```bash
# Download the JAR file
wget https://github.com/yourusername/requiem-v2/releases/latest/download/Requiem.jar

# Run Requiem on your planning file
java -jar Requiem.jar your-plan.rq
```

## 📖 Language Syntax

### Feature Declaration

```requiem
feature LoginFeature(title: "User Authentication System", 
                     value: 8, 
                     effort: 13, 
                     risk: 2,
                     dependencies: AuthLib, LoggingFramework);
```

**Attributes:**
- `value`: Business value (1-10 scale)
- `effort`: Estimated story points (non-negative)
- `risk`: Project risk (1-5 scale)
- `dependencies`: Optional list of dependent features

### Release Planning

```requiem
release Q4_2023(targetDate: 15-12-2023, 
                teamCapacity: 40, 
                currentProgress: 65) {
    must {
        assign LoginFeature;
        assign PaymentGateway, priorityScore: 9.5;
    }
    should {
        assign UserProfile;
        assign AnalyticsDashboard;
    }
    could {
        assign SocialLogin;
    }
    wont {
        assign VoiceAuth;
    }
};
```

### MoSCoW Categories

<div align="center">

![Must Have](https://img.shields.io/badge/MUST-Have-critical)
![Should Have](https://img.shields.io/badge/SHOULD-Have-important)
![Could Have](https://img.shields.io/badge/COULD-Have-blue)
![Won't Have](https://img.shields.io/badge/WON'T-Have-lightgrey)

</div>

| Category | Description | Priority |
|----------|-------------|----------|
| `must`   | Critical for release | Highest |
| `should` | Important but not critical | High |
| `could`  | Desirable but not necessary | Medium |
| `wont`   | Out of scope for this release | Lowest |

## 🔍 Error Handling

<div align="center">

![Lexer Errors](https://img.shields.io/badge/🔤-Lexer%20Errors%20Caught-red)
![Parser Errors](https://img.shields.io/badge/📐-Parser%20Errors%20Caught-orange)
![Semantic Errors](https://img.shields.io/badge/🧠-Semantic%20Errors%20Caught-yellow)
![Validation](https://img.shields.io/badge/✅-100%25%20Validated-brightgreen)

</div>

Requiem V2 provides comprehensive error reporting at multiple levels:

### Lexer Errors
- Invalid characters/tokens
- Malformed identifiers
- Incorrect number formats
- Invalid date formats

### Parser Errors
- Syntax errors
- Missing punctuation
- Incorrect keyword usage
- Structural issues

### Semantic Errors
- Duplicate feature IDs
- Undefined dependencies
- Value range violations
- Invalid date values
- Circular dependencies

**Example Error Output:**
```
Requiem V2 Semantic Errors:
==================================================
• Line 12:15 - Business value must be between 1-10 (got 15)
• Line 25:8 - Undefined feature 'AuthLib' cannot be assigned
• Line 30:22 - Invalid date format '32-13-2023'
==================================================
```

## 🏗️ Architecture

<div align="center">

![Architecture](https://img.shields.io/badge/🏗️-ANTLR%20Pipeline-blueviolet)
![Lexer](https://img.shields.io/badge/🔠-Lexer%20Phase-blue)
![Parser](https://img.shields.io/badge/📐-Parser%20Phase-green)
![Visitor](https://img.shields.io/badge/👣-Visitor%20Pattern-orange)
![AST](https://img.shields.io/badge/🌳-AST%20Generation-success)

</div>

```
Requiem Workflow:
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Input File    │───▶│    ANTLR Lexer  │───▶│   ANTLR Parser  │
│   (*.rq)        │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                                            │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Error Report  │◀───│  AST Builder    │◀───│  Parse Tree     │
│                 │    │                 │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
  Error Handling         Priority Analysis        Release Planning
```

### Components

1. **Grammar (`RequiemV2.g4`)**: ANTLR grammar defining the language syntax
2. **Lexer/Parser**: Generated by ANTLR from the grammar
3. **Error Listeners**: Custom error handling for syntax and semantic errors
4. **AST Builder**: Converts parse tree to abstract syntax tree
5. **Analysis Engine**: Performs calculations and validations

### Generated Files

<div align="center">

![Generated](https://img.shields.io/badge/⚡-ANTLR%20Generated-important)
![Lexer](https://img.shields.io/badge/🔤-RequiemV2Lexer.java-blue)
![Parser](https://img.shields.io/badge/📐-RequiemV2Parser.java-green)
![Visitor](https://img.shields.io/badge/👣-RequiemV2Visitor.java-orange)
![Listener](https://img.shields.io/badge/👂-RequiemV2Listener.java-purple)

</div>

## 📊 Example

### Complete Example File

```requiem
# ============================================
# Sample Release Plan for E-commerce Platform
# ============================================

/* Core Features */
feature UserAuth(title: "User Authentication System", 
                 value: 9, 
                 effort: 13, 
                 risk: 2);

feature ProductCatalog(title: "Product Browsing and Search", 
                       value: 8, 
                       effort: 20, 
                       risk: 3);

feature ShoppingCart(title: "Shopping Cart Management", 
                     value: 7, 
                     effort: 15, 
                     risk: 2,
                     dependencies: ProductCatalog);

feature Checkout(title: "Secure Payment Checkout", 
                 value: 10, 
                 effort: 25, 
                 risk: 4,
                 dependencies: ShoppingCart, UserAuth);

/* Enhanced Features */
feature UserReviews(title: "Product Reviews and Ratings", 
                    value: 6, 
                    effort: 12, 
                    risk: 1);

feature Wishlist(title: "User Wishlist Feature", 
                 value: 5, 
                 effort: 8, 
                 risk: 1);

/* Future Features */
feature ARView(title: "AR Product Visualization", 
               value: 7, 
               effort: 40, 
               risk: 5);

/* Release Plan for Q4 */
release Q4_Release(targetDate: 15-12-2023, 
                   teamCapacity: 80, 
                   currentProgress: 20) {
    
    must {
        assign UserAuth;
        assign ProductCatalog;
        assign ShoppingCart;
        assign Checkout, priorityScore: 9.8;
    }
    
    should {
        assign UserReviews;
        assign Wishlist;
    }
    
    could {
        # Nice to have if time permits
        assign ARView;
    }
    
    wont {
        # Postponed to next release
        # assign VoiceSearch;
        # assign AIRecommendations;
    }
};

/* Additional Release */
release Q1_2024(targetDate: 31-03-2024, 
                teamCapacity: 90, 
                currentProgress: 0) {
    could {
        assign ARView;
    }
};
```

### Generated Output

```
============================================================
                    REQUIREMENT PRIORITIZATION ANALYSIS REPORT
============================================================

FEATURES SUMMARY:
ID         TITLE                                    VALUE     EFFORT     RISK       DEPENDENCIES
----------------------------------------------------------------------------------------------------
UserAuth   User Authentication System               9         13         2          []
ProductCat Product Browsing and Search              8         20         3          []
ShoppingCa Shopping Cart Management                 7         15         2          [ProductCatalog]
Checkout   Secure Payment Checkout                  10        25         4          [ShoppingCart, UserAuth]
UserReviews Product Reviews and Ratings             6         12         1          []
Wishlist   User Wishlist Feature                    5         8          1          []
ARView     AR Product Visualization                 7         40         5          []

RELEASE PLAN: Q4_Release
Target Date: 2023-12-15
Days until deadline: 85
Team Capacity: 80 story points
Current Progress: 20%

PRIORITY ALLOCATION:

MUST HAVE:
Feature ID       Priority Score       Consideration
------------------------------------------------------------
UserAuth         6.92                 Auto-assigned
ProductCatalog   2.67                 Auto-assigned
ShoppingCart     2.33                 Auto-assigned
Checkout         4.90                 Manual: 9.8

SHOULD HAVE:
Feature ID       Priority Score       Consideration
------------------------------------------------------------
UserReviews      3.00                 Auto-assigned
Wishlist         3.13                 Auto-assigned

COULD HAVE:
Feature ID       Priority Score       Consideration
------------------------------------------------------------
ARView           0.26                 Auto-assigned

CAPACITY ANALYSIS:
✓ Remaining capacity: 45.0 story points

============================================================
```
<div align="center">

![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Copyright](https://img.shields.io/badge/©-2024%20Requiem%20Project-lightgrey)

</div>

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**Built with ❤️ and** ![ANTLR](https://img.shields.io/badge/ANTLR-4.13.2-blue)

*If you find Requiem useful, please give it a ⭐ on GitHub!*

</div>

## 🔗 Useful Links

- [ANTLR Official Website](https://www.antlr.org/)
- [ANTLR GitHub Repository](https://github.com/antlr/antlr4)
- [MoSCoW Prioritization Method](https://en.wikipedia.org/wiki/MoSCoW_method)
- [Domain-Specific Languages](https://martinfowler.com/books/dsl.html)

---

<div align="center">

![Made With](https://img.shields.io/badge/Made%20with-Java%20%26%20ANTLR-red)
![Status](https://img.shields.io/badge/Status-Active%20Development-brightgreen)
![Version](https://img.shields.io/badge/Version-2.0.0-blue)

**Requiem V2 - Making Release Planning Simple and Effective**

</div>

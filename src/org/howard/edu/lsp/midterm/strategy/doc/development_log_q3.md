
can you provide an outline to answer the question bellow?
 Question 3 - Refactoring to Use a Design Pattern (30 points) The following class calculates final prices for customer purchases. Although the code works, the design makes the system difficult to extend and maintain. Study the code carefully and answer the questions that follow. Provided Class package org.howard.edu.lsp.midterm.strategy; public class PriceCalculator { public double calculatePrice(String customerType, double price) { double finalPrice = price; if (customerType.equals("REGULAR")) { finalPrice = price; } if (customerType.equals("MEMBER")) { finalPrice = price * 0.90; } if (customerType.equals("VIP")) { finalPrice = price * 0.80; } if (customerType.equals("HOLIDAY")) { finalPrice = price * 0.85; } return finalPrice; } } Part 1 - Design Evaluation (8 points) Evaluate the design of the PriceCalculator class. Explain why the current implementation may lead to maintenance or extensibility problems as the system evolves. Your answer must be written in a Markdown file named: design_evaluation.md Place the file in the package: org.howard.edu.lsp.midterm.strategy.doc Part 2 - Refactoring Using a Design Pattern (10 points) Refactor the provided design to use the Strategy Pattern discussed during lecture. Your refactored implementation must be placed in the package: org.howard.edu.lsp.midterm.strategy Each class must be placed in its own .java file. Your implementation should demonstrate the use of the Strategy design pattern to support different discount behaviors. All classes and public methods must include Javadoc comments. Part 3 - Driver Program (5 points) Create a Driver class that demonstrates that your Strategy Pattern implementation works correctly. Your Driver class must be placed in the package: org.howard.edu.lsp.midterm.strategy Your driver must demonstrate the following cases using a purchase price of 100.0: • REGULAR customer • MEMBER customer • VIP customer • HOLIDAY customer Your driver must produce output in the following format: REGULAR: 100.0 MEMBER: 90.0 VIP: 80.0 HOLIDAY: 85.0 The output must be produced by executing your Strategy-based implementation, not by hardcoding the results. Part 4 - Development Log (5 points) Create a Markdown file named development_log_q3.md Place the file in the package: org.howard.edu.lsp.midterm.strategy.doc This file must document any external resources used while completing this question, including AI tools, internet searches, documentation, lecture notes, or textbooks. For each resource used, include what you searched or asked and how the information helped you complete the problem. If no external assistance was used, clearly state that in the file. Grading Rubric (30 points) Component Points Design evaluation (design_evaluation.md) 8 Correct refactoring using Strategy Pattern 10 Driver execution and required output 5 Javadoc documentation in classes and public methods 2 Development log documenting AI/internet use 5

Yes — here’s a strong outline you can use to answer Question 3 clearly and match the required deliverables.

Outline for Question 3 — Refactoring to Use the Strategy Pattern
1. Understand what the question is asking
Your response has 4 required parts:

Design evaluation in design_evaluation.md

Refactored Java implementation using the Strategy Pattern

Driver class showing correct output

Development log in development_log_q3.md

Part 1 — design_evaluation.md
File location:
org.howard.edu.lsp.midterm.strategy.doc

Suggested structure
Title
# Design Evaluation of PriceCalculator

Paragraph 1 — What the current class does
Briefly explain that the current PriceCalculator class calculates a final price based on a customerType string and applies discounts using a series of if statements.

Paragraph 2 — Main design problems
Discuss why this design is weak:

Violates Open/Closed Principle because adding a new customer type requires modifying existing code.

Uses string comparisons, which are error-prone and can break if misspelled.

All discount logic is in one class, so the class has too many responsibilities.

Harder to test and maintain because every pricing rule is bundled together.

Poor extensibility because future discount rules may become more complex.

Paragraph 3 — Why Strategy Pattern is better
Explain that the Strategy Pattern improves the design because:

each discount rule becomes its own class,

behavior can be changed without modifying the calculator,

the system is easier to extend,

logic is cleaner and more reusable.

Easy paragraph flow you can follow
Intro: describe current design

Problems: maintenance, scalability, code smell

Improvement: Strategy Pattern solves these issues

Part 2 — Refactoring Using the Strategy Pattern
Package:
org.howard.edu.lsp.midterm.strategy

Classes you should create
Each class must be in its own .java file.

1. DiscountStrategy.java
Interface for all discount strategies.

Purpose:

define a common method like:
double applyDiscount(double price);

What to say in your answer:

This is the strategy interface.

It allows all discount types to be used interchangeably.

2. RegularDiscountStrategy.java
Implements no discount.

Logic:

return price

3. MemberDiscountStrategy.java
Implements 10% discount.

Logic:

return price * 0.90

4. VipDiscountStrategy.java
Implements 20% discount.

Logic:

return price * 0.80

5. HolidayDiscountStrategy.java
Implements 15% discount.

Logic:

return price * 0.85

6. PriceCalculator.java
Refactored context class.

Purpose:

store a DiscountStrategy

use that strategy to calculate final price

Suggested design:

field: private DiscountStrategy discountStrategy;

constructor to set strategy

method to calculate final price

Suggested method flow
constructor accepts a strategy

calculatePrice(double price) returns discountStrategy.applyDiscount(price)

This is the class that demonstrates the actual Strategy Pattern.

Part 3 — Driver Program
Package:
org.howard.edu.lsp.midterm.strategy

Class name
Driver.java

What the driver should do
Use a purchase price of 100.0 and demonstrate all four cases:

REGULAR

MEMBER

VIP

HOLIDAY

Suggested structure
In main:
create a PriceCalculator with RegularDiscountStrategy

print result

repeat for member, vip, holiday

Output must be exactly:
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
Important note to mention
The driver should not hardcode the results. It should call the strategy-based calculator each time.

Part 4 — development_log_q3.md
File location:
org.howard.edu.lsp.midterm.strategy.doc

Suggested structure
Title
# Development Log for Question 3

If you used help/resources
For each resource, include:

Resource used

What you searched or asked

How it helped

Possible categories
AI tool

lecture notes

textbook

Java documentation

internet search

Example structure
Resource 1
Resource: ChatGPT

What I asked: How to refactor an if-based discount calculator using the Strategy Pattern

How it helped: Helped me organize the design into an interface, concrete strategy classes, and a context class.

Resource 2
Resource: Lecture notes

What I reviewed: Strategy Pattern examples

How it helped: Confirmed how the pattern separates algorithms into interchangeable classes.

If no help was used
Write clearly:

No external assistance was used in completing this question.

Suggested file/package checklist
Java files in:
org.howard.edu.lsp.midterm.strategy

DiscountStrategy.java

RegularDiscountStrategy.java

MemberDiscountStrategy.java

VipDiscountStrategy.java

HolidayDiscountStrategy.java

PriceCalculator.java

Driver.java

Markdown files in:
org.howard.edu.lsp.midterm.strategy.doc

design_evaluation.md

development_log_q3.md

Javadoc outline
Since the instructions require Javadoc for all classes and public methods, make sure each file has:

For each class
what the class represents

what role it plays in the Strategy Pattern

For each public method
what the method does

parameters

return value

Example format
/**
 * Applies a pricing strategy to a purchase amount.
 */
public class PriceCalculator {
/**
 * Calculates the final price after applying the selected discount strategy.
 *
 * @param price the original purchase price
 * @return the final discounted price
 */
public double calculatePrice(double price) {
Main ideas your instructor likely wants to see
To earn full credit, your answer should show that you understand:

the original design is too rigid

Strategy Pattern separates discount behaviors

PriceCalculator should depend on an abstraction, not conditional logic

each discount rule should be its own class

the driver proves the refactor works

Simple writing plan for the whole question
A. First write design_evaluation.md
Use 3 paragraphs:

what the original class does

why it is hard to maintain/extend

why Strategy Pattern is a better design

B. Then write the Java code
Order:

interface

concrete strategies

calculator/context

driver

C. Then write development_log_q3.md
List every outside resource and how it helped

One-sentence summary you can keep in mind
The whole answer is basically showing that instead of using if statements to choose discount behavior, you should move each discount rule into its own strategy class and let PriceCalculator use whichever strategy it is given.

If you want, I can also turn this outline into the actual design_evaluation.md text and the full Java class structure.
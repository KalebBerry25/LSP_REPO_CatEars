Here’s a strong way to approach Question 2, plus draft content you can adapt into the three required markdown files.

Best strategy

Break the question into exactly the three deliverables the grader wants:

evaluation.md
Explain what is wrong with the current class design and why.

design.md
Propose a better object-oriented design using CRC cards.

development_log_q2.md
Document any outside help used.

The safest exam strategy is to keep your answer tightly tied to the code they gave you. Point to concrete issues in OrderProcessor, then show how to split those responsibilities into separate classes.

1. What the professor is likely looking for in evaluation.md

The key issue is that OrderProcessor is doing too many unrelated jobs.

Right now it:

stores order/customer data

calculates tax

applies discounts

prints a receipt

writes to a file

sends a confirmation email

logs activity

That is a classic bad OO design because one class has many responsibilities. In class terms, this usually connects to ideas like:

weak encapsulation

low cohesion

high coupling

violation of single responsibility

poor information hiding

a “god class” or manager-style class doing too much

Main design issues to mention
A. Poor encapsulation

The fields are public:

public String customerName;
public String email;
public String item;
public double price;

That means any other class can change them directly. This breaks information hiding and makes the object harder to control and validate.

B. Too many responsibilities

processOrder() mixes business logic, I/O, logging, and presentation. That makes the class harder to understand, test, and change.

C. Low cohesion

The methods and fields do not all support one focused purpose. A well-designed class should have a small, related set of responsibilities.

D. High coupling to external details

The class is directly tied to:

file storage through FileWriter

console output through System.out.println

time logging through Date

That means changing storage or output behavior forces changes inside this class.

E. Business rules mixed with infrastructure

Tax and discount rules are business logic. Writing to files and sending email are infrastructure concerns. Mixing them together makes the design rigid.

F. Order of logic is questionable

The code prints and saves the total before applying the discount:

double total = price + tax;
...
writer.write(...)
...
if(price > 500) {
    total = total * 0.9;
}

That means the saved or displayed total may not match the discounted total. Even if the question is about design, this is a good example of how poor structure can create logic bugs.

G. Hard to extend

If later you want:

different tax policies

email through a real mail service

database storage instead of file storage

different discount strategies

formatted receipts

you would need to keep editing the same class.

H. Hard to test

Unit testing is difficult because one method does everything at once. You cannot easily test tax calculation separately from file writing or console printing.

2. Suggested evaluation.md draft

You can use something like this:

# Evaluation of OrderProcessor Design

The `OrderProcessor` class has several object-oriented design problems that make it difficult to maintain, test, and extend. The biggest issue is that the class has too many responsibilities. It stores order data, calculates tax, applies discounts, prints a receipt, writes order data to a file, sends a confirmation email, and logs activity. In a good object-oriented design, these responsibilities should be divided across multiple focused classes rather than placed inside one class.

Another major issue is poor encapsulation. The fields `customerName`, `email`, `item`, and `price` are all public, which means other classes can directly read or modify them without any control. This weakens information hiding and makes it harder to enforce valid object state. A better design would keep fields private and provide controlled access through methods.

The class also has low cohesion because its behavior is not centered around one clear purpose. The `processOrder()` method combines business rules such as tax and discount calculations with infrastructure concerns such as file writing, console output, and logging. This creates high coupling to implementation details like `FileWriter`, `System.out.println`, and `Date`. If the system later changes to use a database, a different receipt format, or a real email service, the class would need to be modified in several places.

The design also increases the risk of logic errors. For example, the total is printed and written to the file before the discount is applied, which can lead to inconsistent results. This shows how combining many responsibilities into one method can make the flow harder to reason about.

Overall, the design leads to weak encapsulation, low cohesion, high coupling, and poor extensibility. A better object-oriented design would separate the domain data, pricing rules, persistence, notification, and logging into different collaborating classes so that each class has a clear and focused responsibility.
3. What to include in design.md

For the CRC redesign, the easiest strong answer is to separate the system into domain classes and service classes.

A clean redesign could include:

Order

Customer

PricingService

ReceiptPrinter

OrderRepository

NotificationService

OrderLogger

OrderProcessor

You do not need to overdo it, but you should show clear separation.

Good class breakdown
Customer

Stores customer name and email.

Order

Stores item, price, and maybe the customer. Represents order data.

PricingService

Calculates tax, total, and discount.

ReceiptPrinter

Prints or formats the receipt.

OrderRepository

Saves order data.

NotificationService

Sends confirmation messages.

OrderLogger

Logs processing activity.

OrderProcessor

Coordinates the workflow by calling the other classes, instead of doing everything itself.

This is a strong redesign because OrderProcessor becomes an orchestrator rather than a class that directly performs every job.

4. Suggested design.md draft
# Proposed CRC Redesign

Class: Customer  
Collaborators:
- Order

Class: Order  
Collaborators:
- Customer
- PricingService

Class: PricingService  
Collaborators:
- Order

Class: ReceiptPrinter  
Collaborators:
- Order
- PricingService

Class: OrderRepository  
Collaborators:
- Order
- PricingService

Class: NotificationService  
Collaborators:
- Customer
- Order

Class: OrderLogger  
Collaborators:
- Order

Class: OrderProcessor  
Collaborators:
- Order
- PricingService
- ReceiptPrinter
- OrderRepository
- NotificationService
- OrderLogger
5. Why this redesign works

If you want to explain it briefly in your own words while writing or discussing it:

Order and Customer are domain objects

PricingService handles pricing rules

OrderRepository handles storage

NotificationService handles emails

ReceiptPrinter handles receipt output

OrderLogger handles logs

OrderProcessor only coordinates

That is much closer to strong OO design because responsibilities are distributed instead of bundled.

6. Suggested development_log_q2.md

Because you used ChatGPT here, that should be documented honestly.

A simple version:

# Development Log for Question 2

Resource: ChatGPT  
What I asked:
I asked for help outlining how to evaluate the object-oriented design of the provided `OrderProcessor` class, identify key design problems, and propose an improved CRC-based redesign.

How it helped:
This helped me organize my response for `evaluation.md` by identifying issues such as poor encapsulation, low cohesion, high coupling, and too many responsibilities in one class. It also helped me structure `design.md` by separating the system into focused classes such as `Order`, `Customer`, `PricingService`, `OrderRepository`, and `NotificationService`.

If your instructor wants exact transcript-style detail, include more complete wording from your interaction.

7. Best exam-writing tips for this question

To score well, make sure your answer does these three things:

In evaluation.md

Do not just say “bad design.” Name the actual problems:

public fields

too many responsibilities

low cohesion

high coupling

hard to test

hard to extend

In design.md

Do not only rename classes. Make the responsibilities clearly different.

In development_log_q2.md

Be honest and specific about help used.

8. A shorter version if you want to write it fast

If you are short on time, the most important idea is:

OrderProcessor should not store data, calculate totals, save files, print receipts, send emails, and log activity all in one place.

That sentence alone points to the heart of the problem.

If you want, I can also format all three markdown files exactly as ready-to-submit text blocks.
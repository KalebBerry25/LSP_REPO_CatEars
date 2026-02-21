# AI_Prompts.md

## Prompt 1
I want to take the code bellow and refactor it to be more in line with object oriented principles. How could I do so according to the instructions in the attached document, while maintaining the exact functionality and I/O structure.

## Output 1
Here’s a clean way to redesign your Assignment 2 ETL code into a more object-oriented Assignment 3 solution without changing any behavior (same input/output paths, same transformations in the same order, same skipping rules, same summary printing). This matches the A3 instructions about splitting into multiple classes, using OO ideas, one public class per file, correct package, and adding Javadocs. 


What “more object-oriented” should look like here

Right now, main() is doing everything:

file existence checks

reading CSV

parsing/validation

transformations

writing

counting rows + printing summary

OO redesign = separate responsibilities into classes.

A solid decomposition:

1) A domain model: Product

Encapsulate the data for one row:

productId (int)

name (String)

price (BigDecimal)

category (String)

This is an “object” in the OO sense: it represents a real “thing” your program works with.

2) Parsing + validation: ProductCsvParser

A class that converts a CSV line → Product (or returns “invalid” so you can skip).
This keeps line-splitting, length checks, integer/decimal parsing out of your ETL orchestration.

3) Transformations via polymorphism: ProductTransformer (interface)

Each transformation becomes its own class that implements a shared interface:

public interface ProductTransformer {
    Product transform(Product input);
}

Then you create a pipeline list of transformers and apply them in order. This gives you polymorphism: the pipeline works with “a ProductTransformer” without caring which specific transformer it is.

Suggested transformer classes (mirrors your required order):

UppercaseNameTransformer

ElectronicsDiscountTransformer

PremiumElectronicsCategoryTransformer

PriceRangeTransformer (or “enricher” that computes PriceRange)

4) Output model: TransformedProductRecord

Because your output includes PriceRange (which isn’t in the input), you can either:

add priceRange to Product, or

create a separate output record class.

I recommend a separate output class to keep input vs output clean:

Product = raw product

TransformedProductRecord = productId, name, price, category, priceRange

5) IO classes: CsvProductReader and CsvProductWriter

Reader yields rows/lines (or Products)

Writer writes header + output rows

6) Orchestration: ETLPipelineRunner

This is your “controller”:

checks file exists

opens reader/writer

loops over lines

counts rowsRead / rowsSkipped / rowsTransformed

prints summary

Your main() becomes tiny.

Recommended class layout (matches A3 packaging + one public class per file)

Under:
src/org/howard/edu/lsp/assignment3/

Example files:

ETLPipelineApp.java (only has main)

ETLPipelineRunner.java (runs everything)

Product.java

TransformedProductRecord.java

ProductCsvParser.java

CsvProductWriter.java

ProductTransformer.java

UppercaseNameTransformer.java

ElectronicsDiscountTransformer.java

PremiumElectronicsCategoryTransformer.java

PriceRangeTransformer.java

PriceRange.java (enum)

This matches the deliverable structure expectations (assignment3 package, multiple classes, Javadocs, etc.). 

HW_3_ETL_Pipeline_with_AI

How to preserve your exact transformation logic (but OO)
Key detail: “originalCategory” dependency

Your current logic needs the “original category” to decide premium electronics:

If final rounded price > 500 AND original category was Electronics → category becomes Premium Electronics

To preserve this exactly, the cleanest OO approach is:

store originalCategory in Product as a separate field, set once when parsed

then transformers can safely reference it later

Example Product fields:

category (current category, can change)

originalCategory (never changes)

That avoids accidental behavior changes.

Suggested OO design that uses OO concepts explicitly
Encapsulation

Product hides data behind getters (or is immutable).
Transformers return a new Product instead of mutating (recommended).

Inheritance (optional but easy to justify)

Create an abstract base class that provides common helpers:

public abstract class BaseProductTransformer implements ProductTransformer {
    protected BigDecimal roundMoney(BigDecimal v) { ... }
}

Then your transformers extend it. This checks the “inheritance” box in a reasonable way.

Polymorphism

Pipeline stores List<ProductTransformer> and applies them uniformly.

Concrete “skeleton” of how the pipeline would look
ETLPipelineApp.java
package org.howard.edu.lsp.assignment3;

public class ETLPipelineApp {
    public static void main(String[] args) {
        new ETLPipelineRunner().run();
    }
}
ETLPipelineRunner.java (the orchestrator)

holds input/output paths

builds transformer list

loops through file lines

High-level flow:

validate input exists (same as now)

write fixed header

for each line:

increment rowsRead

skip empty line

parse into Product (skip invalid)

apply transformers in order

build output record with PriceRange

write it

increment rowsTransformed

print summary (same fields as now)

Transformers (examples)

UppercaseNameTransformer

name = name.toUpperCase(Locale.ROOT)

ElectronicsDiscountTransformer

if category == Electronics → price *= 0.90

DO NOT round here if you want rounding exactly once (same as your current code does after discount)

PremiumElectronicsCategoryTransformer

after rounding, check:

if originalCategory == Electronics AND price > 500.00 → category = Premium Electronics

PriceRangeTransformer

uses final rounded price to set PriceRange

How you can explain this in your Reflection (A3 requirement)

In REFLECTION_A3.md, you can write (in your own voice) points like:

What changed: A2 had one large main() with mixed responsibilities; A3 splits into domain (Product), parsing, transformations, IO, and orchestration classes.

Why more OO: responsibilities are isolated; transformations are interchangeable objects; data is encapsulated in Product.

OO ideas used:

Objects/classes: Product, transformers, reader/writer

Encapsulation: Product fields + controlled construction

Inheritance: Base transformer helper (optional)

Polymorphism: transformer interface applied in a list

Testing: same input file; compare output CSV exactly; also test missing input and empty input behavior (the assignment explicitly calls those out)

Final directory structure you should end up with
OOP Spring 2025
│
├─ data
│   ├─ products.csv
│   └─ transformed_products.csv
│
├─ src
│   └─ org
│       └─ howard
│           └─ edu
│               └─ lsp
│                   ├─ assignment1
│                   ├─ assignment2
│                   │   └─ ETLPipeline.java        (your original A2 code)
│                   │
│                   └─ assignment3
│                       ├─ ETLPipeline.java
│                       ├─ ETLPipelineRunner.java
│                       ├─ ETLRunReport.java
│                       ├─ RunSummaryPrinter.java
│                       ├─ Product.java
│                       ├─ TransformedProductRecord.java
│                       ├─ ProductCsvParser.java
│                       ├─ CsvProductWriter.java
│                       ├─ ProductTransformer.java
│                       ├─ TransformResult.java
│                       ├─ UppercaseNameTransformer.java
│                       ├─ ElectronicsDiscountTransformer.java
│                       ├─ MoneyRoundingTransformer.java
│                       ├─ PremiumElectronicsCategoryTransformer.java
│                       ├─ PriceRangeEnricher.java
│                       └─ PriceRange.java
│
├─ .classpath
└─ .gitignore

## Prompt 2
Could you trim this design down significantly. Currently things are too many classes in the design and it feels like an over abstraction. 

## Output 2
Yep — we can trim this down a lot and still be clearly OO while matching every rule in the spec. 

HW_2_Introduction_to_Java_assig…

Below is a lean 4-class design (plus one enum if you want). This keeps the “OO refactor” vibe without looking over-engineered.

Trimmed design (4 files + optional enum)
1) ETLPipeline.java (main only)

Just sets paths and calls the runner.

2) ETLPipelineRunner.java (does ETL orchestration)

Owns:

file checks + folder creation

reading + writing

counters + printing summary

calling parser + transformer

3) Product.java (domain object)

Encapsulates one input row and stores:

productId

name

price

category

originalCategory (needed for rule #3) 

HW_2_Introduction_to_Java_assig…

4) ProductTransformer.java (all transformation logic)

Single class with methods:

transform(Product p) applies steps 1–4 in order, including rounding and price range.

determinePriceRange(...)

✅ This still counts as object-oriented:

You have an object model (Product)

Behavior moved into methods on a “service” class (ProductTransformer)

Runner coordinates instead of doing everything

Why this is a good “trim” (and still defendable)

Only 4 Java files.

Still OO: Product is an object, ProductTransformer encapsulates behavior, Runner coordinates.

Still easy to explain in reflection: separation of concerns + encapsulation + cleaner main.
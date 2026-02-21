# Reflection.md

## Overview

Assignment 3 is supposed to build on Assignment 2 by refactoring the original CSV ETL pipeline into a more object-oriented design while preserving **all functional behavior**. The input/output paths, transformation order, row skipping rules, rounding behavior, error handling, and run summary outputs are all identical to Assignment 2. The primary difference lies in **how the logic is structured and organized**, not in what the program does.

The goal of this refactor was to improve readability, maintainability, and separation of concerns, while avoiding unnecessary complexity.

---

## Differences Between Assignment 2 and Assignment 3 Implementations

### Assignment 2 (Procedural Design)

In the Assignment 2 implementation, the program largely used a procedural approach. Most of the ETL logic was implemented inside the single main method, combining file input and output, CSV parsing, validation, transformation logic, and summary printing in one place. While this design was fine and satisfied the assignment requirements, it resulted in a long and tightly coupled method that is harder to read, make sense of, and modify. Data was handled primarily through local variables rather than structured objects, which makes it less clear how information flows through the pipeline.

---

### Assignment 3 (Object-Oriented Refactor)

The Assignment 3 implementation improves on this design by introducing a small number of focused classes that better reflect object-oriented principles. The ETLPipeline class now serves only as the entry point, delegating execution to the ETLPipelineRunner, which coordinates the Extract–>Transform–>Load process. The Product class represents each single input row as a domain object, encapsulating product-related data and storing the original category value needed to correctly apply the Premium Electronics check at the end. Transformation logic is now handled in the ProductTransformer class, which applies the required transformations in the exact order specified. This separation of responsibilities makes the code easier to understand, maintain, and extend without changing its behavior.

---

## Object-Oriented Design Decisions

Encapsulation plays a key role in the refactored design. By representing each product as a Product object, the program avoids passing multiple loosely related variables through multiple methods methods. Storing the originalCategory inside the Product object also ensures that the Premium Electronics condition is evaluated correctly based on the original input value, even after other transformations have modified the category field. This design choice helps to fullfill the specification requirement that the Premium Electronics decision be based on the final rounded price and the original category.

---


## Testing

To ensure that the Assignment 3 implementation produces the exact same output as Assignment 2, both versions were tested using the same input files provided in Assignment 2. Each implementation was run independently, and the resulting transformed_products.csv files were compared line by line to confirm they were the same. Additional tests were performed for edge cases, including an empty input file  and a missing input file, to verify that both implementations handled these scenarios in the same way. Matching output files and identical run summaries confirmed that the refactor did not change program behavior, only code structure.

---

## Conclusion

Overall, the changes made for the Assignment 3 implementation demonstrates how a correct procedural solution can be refactored into a cleaner and more maintainable object-oriented design without introducing unnecessary complexity. By separating concerns, encapsulating data, and organizing transformation logic more clearly, the refactored implementation improves readability and maintainability while remaining fully compliant with all Assignment 2 requirements

---
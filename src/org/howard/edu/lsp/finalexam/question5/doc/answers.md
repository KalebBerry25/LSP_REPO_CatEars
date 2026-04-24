Heuristic 1:

Name:

Data privatization and encapsulation

Explanation:

This improves maintainability by preventing external classes from directly modifying internal state.
In lecture, this was shown as avoiding public fields and instead using controlled access through methods.
It reduces bugs caused by unintended side effects.


Heuristic 2:

Name:

Well-defined class responsibility

Explanation:

This improves readability by making classes easier to understand and maintain.
In lecture, we saw examples of “god classes” causing complexity.
Breaking responsibilities into focused classes simplifies debugging and extension.


Heuristic 3:

Name:

Information Hiding

Explanation:

This allows internal logic to change without affecting other parts of the system.
In lecture, this was illustrated by keeping helper methods private (like getNextId).
It improves flexibility and reduces coupling between classes.

Explanation:

The Template Method pattern is implemented in the Report class through the generateReport() method, which defines a fixed workflow for generating reports. The steps loadData(), formatHeader(), formatBody(), and formatFooter() are declared as abstract methods and are implemented by subclasses. The StudentReport and CourseReport classes provide their own specific formatting while reusing the same overall structure defined in the base class. This approach promotes code reuse and ensures consistent report generation while still allowing for customization in each report type.
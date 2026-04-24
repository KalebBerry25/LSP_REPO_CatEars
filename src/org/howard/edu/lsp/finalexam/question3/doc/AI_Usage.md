AI Tools Used:
ChatGPT

Prompts Used (2–5 max):
1. Outline the steps I should take to answer this question Question 3 (20 points) Instructions - Write JUnit 5 test cases only - Do NOT write a Driver - Do NOT include a written explanation - Use clear test method names - Focus on correctness and boundary cases Given Code package org.howard.edu.lsp.finalexam.question3; public class GradeCalculator { public double average(int score1, int score2, int score3) { validateScore(score1); validateScore(score2); validateScore(score3); return (score1 + score2 + score3) / 3.0; } public String letterGrade(double average) { if (average >= 90) return "A"; else if (average >= 80) return "B"; else if (average >= 70) return "C"; else if (average >= 60) return "D"; else return "F"; } public boolean isPassing(double average) { return average >= 60; } private void validateScore(int score) { if (score < 0 || score > 100) { throw new IllegalArgumentException("Score must be between 0 and 100"); } } } Requirements Your test class must include: 1. One test for average() 2. One test for letterGrade() 3. One test for isPassing() 4. Two boundary-value tests 5. Two exception tests using assertThrows()

How AI Helped (2–3 sentences):
AI helped with this problem by essentially providing a checklist of test that I would need to implement in order to fulfill the requirements of the quesrion. This takes the hardest part of thinking of the necessary test out.

Reflection (1–2 sentences):
I learned what some good example test would be needed to implement for a similar problem in the future or just any class that would need testing.

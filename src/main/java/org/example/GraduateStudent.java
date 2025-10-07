package org.example;

// Graduate student with 5.0 GPA scale
public class GraduateStudent extends Student {
    private double totalScore; // total out of 100

    public GraduateStudent(int id, String name, String email, double totalScore) {
        super(id, name, email);
        this.totalScore = totalScore;
    }

    @Override
    public double calculateGPA() {
        // Different formula: scale 0–100 to 0–5
        return (totalScore / 100) * 5.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", GPA=" + String.format("%.2f", calculateGPA());
    }
}

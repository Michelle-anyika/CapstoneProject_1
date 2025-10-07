package org.example;

// Undergraduate student with 4.0 GPA scale
public class UndergraduateStudent extends Student {
    private double totalScore; // total out of 100

    public UndergraduateStudent(int id, String name, double totalScore) {
        super(id, name);
        this.totalScore = totalScore;
    }

    @Override
    public double calculateGPA() {
        // Simple example: scale 0–100 to 0–4
        return (totalScore / 100) * 4.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", GPA=" + calculateGPA();
    }
}

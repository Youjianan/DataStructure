package com.hziee.bean;

public class Student {
    private int id;
    private String name;
    private int chinese;
    private int math;
    private int english;
    private int physics;
    private int totalScore;
    private int rank;

    public Student() {}

    public Student(int id, String name, int chinese, int math, int english, int physics, int totalScore) {
        this.id = id;
        this.name = name;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
        this.physics = physics;
        this.totalScore = totalScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        if (rank != 0) {
            return id + "\t" + name + "\t" + chinese + "\t" + math + "\t" + english + "\t" + physics + "\t" + totalScore + "\t\t" + rank;
        }
        return id + "\t" + name + "\t" + chinese + "\t" + math + "\t" + english + "\t" + physics + "\t" + totalScore;
    }

    /* 默认权值1:1:1:1 */
    public void calcTotal() {
        setTotalScore(getChinese() + getMath() + getEnglish() + getPhysics());
    }

    /* 设置权值 */
    public void calcTotal(int a, int b, int c, int d) {
        int sum = a + b + c + d;
        setTotalScore(4 * (getChinese() * a / sum + getMath() * b / sum + getEnglish() * c / sum + getPhysics() * d / sum));
    }
}

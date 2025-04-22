package Modules;

public class Note {
    private int id;
    private Student student;
    private Module module;
    private double grade;
    private int absencesModule;
    private static final double MAX_PENALTY = 2.0;
    private static final double PENALTY_PER_ABSENCE = 0.5;
    public Note() {}
    
    public Note(int id, Student student, Module module, double grade, int absencesModule) {
        this.id = id;
        this.student = student;
        this.module = module;
        this.grade = grade;
        this.absencesModule = absencesModule;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    public int getAbsencesModule() { return absencesModule; }
    public void setAbsencesModule(int absencesModule) { 
        this.absencesModule = Math.min(absencesModule, 1); // Max 1 absence pénalisante
    }

    public void incrementAbsencesModule() {
        this.absencesModule++; // Plus de limite d'absences
    }
    public double getGradeWithPenalty() {
        double penalty = Math.min(absencesModule * PENALTY_PER_ABSENCE, MAX_PENALTY);
        return Math.max(this.grade - penalty, 0); 
    }
}
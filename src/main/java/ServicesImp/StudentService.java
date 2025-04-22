package ServicesImp;

import Modules.Note;
import Modules.Student;
import java.util.List;

public class StudentService {
    public double calculerMoyenne(Student student, List<Note> notes) {
        double totalPoints = 0;
        int totalCoefficients = 0;
        
        for (Note note : notes) {
            double notePenalisee = note.getGradeWithPenalty();
            totalPoints += notePenalisee * note.getModule().getCoefficient();
            totalCoefficients += note.getModule().getCoefficient();
        }
        
        if(totalCoefficients == 0) return 0.0;
        
        double moyenne = totalPoints / totalCoefficients;
        return Math.round(moyenne * 100.0) / 100.0; // Arrondi à 2 décimales
    }
}
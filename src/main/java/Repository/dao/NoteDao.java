package Repository.dao;

import Modules.Note;
import java.util.List;

public interface NoteDao {
    void addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int id);
    Note getNoteById(int id);
    List<Note> getAllNotes();
    List<Note> getNotesByStudentId(int studentId);
}

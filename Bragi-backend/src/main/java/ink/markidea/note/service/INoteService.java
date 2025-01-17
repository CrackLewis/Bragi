package ink.markidea.note.service;


import ink.markidea.note.entity.NoteTagDo;
import ink.markidea.note.entity.dto.NotePreviewInfo;
import ink.markidea.note.entity.dto.UserNoteKey;
import ink.markidea.note.entity.resp.ServerResponse;
import ink.markidea.note.entity.vo.*;
import org.eclipse.jgit.api.Git;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 *  
 */
public interface INoteService {

    /**
     * list user's all notebooks and their notes
     */
    ServerResponse<List<String>> listNotebooks();

    /**
     * list all notes of the notebook
     */
    ServerResponse<List<NoteVo>> listNotes(String notebookName);


    ServerResponse<List<NoteVo>> search(String keyWord, List<String> searchNotebooks);

    /** create a notebook */
    ServerResponse createNotebook(String notebookName);

    ServerResponse createNote(String noteTitle, String notebookName, String content, List<String> noteTags);
    /**
     * save changes of note.
     * If the note don't exist, it will be created.
     */
    ServerResponse saveNote(String noteTitle, String notebookName, String content);

    ServerResponse uploadnote(MultipartFile file, String directory);

    /**
     * save note temporarily
     */
    void tmpSaveNote(String noteTitle, String notebookName, String content);

    void delTmpSavedNote(String noteTitle, String notebookName);

    /**
     * delete note
     */
    ServerResponse deleteNote(String notebookName, String noteTitle);
    /**
     * get the content of note
     */
    ServerResponse<String> getNote(String notebookName, String noteTitle);

    ServerResponse<String> getNotePath(Integer noteId);

    /**
     * get the content of note
     */
    ServerResponse<String> getNote(String notebookName, String noteTitle, String username);

    /**
     * get the Id of note
     */
    ServerResponse<Integer> getNoteId(String notebookName, String noteTitle);
    /**
     * get version history of note
     */
    ServerResponse<List<NoteVersionVo>> getNoteHistory(String notebookName, String noteTitle);

    /**
     * get note content at a history version
     */
    ServerResponse<String> getNoteHistoryContent(String notebookName, String noteTitle, String versionRef);

    /**
     * recover note to a certain version
     */
    ServerResponse<String> resetAndGet(String notebookName, String noteTitle, String versionRef);

    /**
     * delete notebook and its all notes
     */
    ServerResponse deleteNotebook(String notebookName);

    /**
     * rename a notebook
     */
    void renameNotebook(String srcNotebookName, String targetNotebookName);

    /**
     * list all delnotes
     */
    ServerResponse<List<DeletedNoteVo>> listDelNotes();

    /** recover deleted note.
     * if the corresponding notebook is deleted, it will be created
     */
    ServerResponse recoverNote(Integer id);

    /**
     * clear delnote record
     */
    ServerResponse clearDelNote(Integer id);


    /**
     * move a note
     */
    ServerResponse moveNote(String srcNotebook, String srcTitle, String targetNotebook, String targetTitle);

    /**
     * clear all delnotes by username
     */
    ServerResponse clearAllDelNotes();

    /**
     * copy a note
     * @param srcNotebook
     * @param targetNotebook
     * @param title
     * @return
     */
    ServerResponse copyNote(String srcNotebook, String targetNotebook, String title);

    /**
     * 获取用户文件夹目录
     */
    File getOrInitUserNotebookDir();

//    void updateNotesNotebookName(String srcNotebook, String targetNotebook);
    String getUsername();

    File getOrCreateUserNotebookDir();

    NotePreviewInfo getContentPreview(String notebookName, String noteName);

    public Git getOrCreateUserGit();

    public UserNoteKey buildUserNoteKey(String notebookName, String noteTitle);

    public UserNoteKey buildUserNoteKey(String notebookName, String noteTitle, String username);

    public void invalidateCache(UserNoteKey key);
}

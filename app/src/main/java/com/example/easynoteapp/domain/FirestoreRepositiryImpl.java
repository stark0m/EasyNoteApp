package com.example.easynoteapp.domain;

import com.example.easynoteapp.ui.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreRepositiryImpl implements NotesRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String NOTES = "notes";
    private final String CONTENT = "content";
    private final String DATE = "createdAt";
    private final String ID = "id";

    public static final FirestoreRepositiryImpl INSTANCE = new FirestoreRepositiryImpl();


    @Override
    public void getNotes(CallBack<ArrayList<Note>> callback) {
        db.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        ArrayList<Note> result = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
//                            String id = queryDocumentSnapshot.getId();
                            String id = queryDocumentSnapshot.getString(ID);
                            String content = queryDocumentSnapshot.getString(CONTENT);
                            Date date = queryDocumentSnapshot.getDate(DATE);


                            result.add(new Note(id, date, content));
                        }
                        callback.onSuccess(result);
                    }
                });
    }

    @Override
    public void updateNote(Note note, int index, CallBack<Void> callback) {

        String docId = note.getId();
        final String[] toDeleteId = new String[1];
        db.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();

                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                            String id = queryDocumentSnapshot.getString(ID);
                            if (docId.equals(id)) {
                                toDeleteId[0] = queryDocumentSnapshot.getId();
                                break;
                            }





                        }

                        Map<String, Object> data = new HashMap<>();
                        data.put(CONTENT, note.getText());
                        data.put(DATE, note.getDate());
                        data.put(ID, note.getId());

                        db.collection((NOTES))
                                .document(toDeleteId[0])
                                .update(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        callback.onSuccess(null);
                                    }
                                });

                    }
                });
    }

    @Override
    public void deleteNote(int index, CallBack<Void> callback) {

    }

    @Override
    public void deleteNote(Note note, CallBack<Void> callback) {


        String docId = note.getId();
        final String[] toDeleteId = new String[1];
        db.collection(NOTES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();

                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                            String id = queryDocumentSnapshot.getString(ID);
                            if (docId.equals(id)) {
                                toDeleteId[0] = queryDocumentSnapshot.getId();
                                break;
                            }





                        }


                        db.collection((NOTES))
                                .document(toDeleteId[0])
                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                callback.onSuccess(null);
                            }
                        });

                    }
                });

    }

    @Override
    public void addNote(Note note, int index, CallBack<Void> callback) {
        Map<String, Object> data = new HashMap<>();
        data.put(CONTENT, note.getText());
        data.put(DATE, note.getDate());
        data.put(ID, note.getId());

        db.collection(NOTES)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        callback.onSuccess(null);
                    }
                });
    }
}

package pgu.client;

import java.util.ArrayList;

import pgu.shared.Note;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
    void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

    void createNote(String text, AsyncCallback<Void> asyncCallback);

    void listNotes(AsyncCallback<ArrayList<Note>> asyncCallback);
}

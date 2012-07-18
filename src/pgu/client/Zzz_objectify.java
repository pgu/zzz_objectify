package pgu.client;

import java.util.ArrayList;

import pgu.shared.Note;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Zzz_objectify implements EntryPoint {

    private final GreetingServiceAsync service = GWT.create(GreetingService.class);

    final VerticalPanel                notes   = new VerticalPanel();
    final TextBox                      newNote = new TextBox();
    final Button                       send    = new Button("+");

    @Override
    public void onModuleLoad() {

        final HorizontalPanel edition = new HorizontalPanel();
        edition.add(newNote);
        edition.add(send);

        final VerticalPanel page = new VerticalPanel();
        page.add(edition);
        page.add(notes);
        RootPanel.get().add(page);

        listNotes();

        send.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                service.createNote(newNote.getText(), new AsyncCallback<Void>() {

                    @Override
                    public void onFailure(final Throwable caught) {
                        GWT.log("failure");
                    }

                    @Override
                    public void onSuccess(final Void result) {
                        listNotes();
                    }

                });
            }
        });
    }

    private void listNotes() {
        service.listNotes(new AsyncCallback<ArrayList<Note>>() {

            @Override
            public void onFailure(final Throwable caught) {
                GWT.log("failure");
            }

            @Override
            public void onSuccess(final ArrayList<Note> result) {
                notes.clear();
                for (final Note r : result) {
                    notes.add(new Label(r.getMessage()));
                }
            }

        });
    }

}

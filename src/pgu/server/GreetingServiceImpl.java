package pgu.server;

import java.util.ArrayList;

import pgu.client.GreetingService;
import pgu.shared.FieldVerifier;
import pgu.shared.Note;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private final AppDAO dao = new AppDAO();

    @Override
    public String greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid. 
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }

        final String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
                + userAgent;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to prevent cross-site script vulnerabilities.
     * 
     * @param html
     *            the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(final String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    @Override
    public void createNote(final String text) {
        final Note note = new Note();
        note.setMessage(text);
        dao.ofy().put(note);
    }

    @Override
    public ArrayList<Note> listNotes() {
        return new ArrayList<Note>(dao.ofy().query(Note.class).list());
    }
}

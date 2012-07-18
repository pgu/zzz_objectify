package pgu.server;

import pgu.shared.Note;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

public class AppDAO extends DAOBase {

    static {
        ObjectifyService.register(Note.class);
    }

}

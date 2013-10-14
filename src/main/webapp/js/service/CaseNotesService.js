app.factory('CaseNotesService', function ($resource, NOTES_RESOURCE_URL) {
    var caseNotesFactory = {};

    var caseNotes = $resource(NOTES_RESOURCE_URL + "/notes", {}, {
        query:{method:'GET', isArray:true}
    });

    var createNotes = $resource(NOTES_RESOURCE_URL + "/add", {}, {
        save:{method:'POST'}
    });

    var deleteNote = $resource(NOTES_RESOURCE_URL + "/delete", {}, {
        del:{method:'DELETE'}
    });

    var updateNote = $resource(NOTES_RESOURCE_URL + "/update", {}, {
        update:{method:'POST'}
    });

    var clientDetails = $resource(NOTES_RESOURCE_URL + "/clientDetails", {}, {
        query:{method:'GET'}
    });

    var caseNote = $resource(NOTES_RESOURCE_URL + "/noteDetails", {}, {
        query:{method:'GET'}
    });

    caseNotesFactory.caseNotes = caseNotes.query;
    caseNotesFactory.createNotes = createNotes.save;
    caseNotesFactory.updateNote = updateNote.update;
    caseNotesFactory.deleteNote = deleteNote.del;
    caseNotesFactory.clientDetails = clientDetails.query;
    caseNotesFactory.note = caseNote.query;

    return caseNotesFactory;
});



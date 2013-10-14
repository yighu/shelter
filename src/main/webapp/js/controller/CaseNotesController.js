function CaseNotesCtrl($scope,  $rootScope,  CaseNotesService, $timeout, NOTES_LIST_DELAY)
{
    $timeout(function() {
        init();
    }, NOTES_LIST_DELAY);

    function init() {
       // alert($rootScope.clientId);
        $scope.caseDataReceived = false;
        $rootScope.client = null;
        var clientId = $rootScope.clientId;
        $scope.clientId = clientId;
        $scope.title = "Case Notes" ;

            $scope.caseNotes = null;

            CaseNotesService.caseNotes({'clientId': clientId}, function (data) {
                    if(data == null) {
                        $scope.caseStatus = "There are no notes";
                    }
                    else if (data.length ==1) {
                        $scope.caseStatus = "There is 1 note";
                    }
                    else {
                        $scope.caseStatus = "There are " + data.length + " notes";
                    }
                    $scope.caseDataReceived = true;
                    $scope.caseNotes = data;
                },
                function (err) {
                    $scope.caseDataReceived = true;
                    $scope.error = 'Error getting data';
                    console.log(err);
                });
    }

    $scope.goToAddNotes = function() {
        $rootScope.clientId = $scope.clientId;
        //$location.path('/addNotes');
    };

    $scope.lookback = function() {
        $rootScope.clientId =  $scope.clientId
    };

    $scope.removeNote = function (noteId) {
        var confirmRemoval = confirm('Are you sure you want to remove this note?');
        if (confirmRemoval) {
            CaseNotesService.deleteNote({caseNotesId: noteId},
                function (data) {
                    init();
                },
                function (err) {
                    deferred.resolve('/notes');
                    alert('Error removing note');
                });
        }
    }

}

function CaseNotesCreateCtrl($scope, $q, $rootScope, $location, CaseNotesService) {
        init();

    function init() {
        $scope.clientId = $rootScope.clientId;
        $scope.client = null;
        initData();
        populateClient();
    }

    function populateClient()
    {
        CaseNotesService.clientDetails({'clientId': $scope.clientId}, function (data) {
                console.log(data);
                $scope.client = data;
            },
            function (err) {
                console.log(err);
            });
    }

    function initData()
    {
        $scope.housingStages =
            ['Development','Application','Waiting','Pool','Approved','Housed'];
        $scope.replycharCount = 300;
        $scope.title = 'Create Case Note';
        $scope.caseNotes = null;
    }

    function fillCaseNotes()
    {
        $scope.caseNotes.clientId = $scope.clientId;
        $scope.caseNotes.caseMngName = $scope.client.caseMgr;

        $scope.caseNotes.clientName =  $scope.client.firstName;
    }

    $scope.submitNotes = function() {

      try
      {

        var deferred = $q.defer();
        if($scope.caseNotes == null)
        {
            $scope.error = "Error: fill out form to save case note.";
        }
        else
        {
            fillCaseNotes();
            var fn =  CaseNotesService.createNotes;
            if($scope.caseNotes.note != null && $scope.caseNotes.note != '')
            {
                $scope.caseNotes.note = $scope.caseNotes.note.substr(0,255);
                if($scope.caseNotes.housingStage != null && $scope.caseNotes.housing != '')
                {
                    fn($scope.caseNotes, function(data) {
                        console.log('received data', data);
                        $scope.caseNotes = null;
                        $rootScope.clientId = $scope.clientId;
                        deferred.resolve('/notes');

                    },
                    function (err) {
                        console.log('error', err);
                        $scope.error = "Error: case note could not be saved.";
                    });
                }
                else
                    $scope.error = "Error: housing stage is empty";
            }
            else
                $scope.error = "Error: note is empty";
         }

        deferred.promise.then(function (value) {
            $location.path(value);
        });
      }
        catch(e)
        {
            console.log("error");
        }
    };


    $scope.cancel = function() {
        $rootScope.clientId = $scope.clientId;

    };
}
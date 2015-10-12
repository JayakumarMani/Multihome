var app = angular.module('tradenetapp', ['ngRoute', 'ui.bootstrap']);

app.config(["$routeProvider", "$locationProvider", function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/riskall", {
            templateUrl: "riskall.html"

        })
        .when("/riskpermit", {
            templateUrl: "riskPermitPage.web",

        })
        // .otherwise({ redirectTo: '/'})
    ;
}]);

app.controller('jobHeaderRecentCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $http({method: 'GET', url: 'processedRecentJob.web'}).
        success(function (data, status, headers, config) {
            $scope.recentJob = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
});


app.controller('riskallstatsctrl', function ($scope, $http) {
    $http({method: 'GET', url: 'getRiskAllStats.web'}).
        success(function (data, status, headers, config) {
            $scope.riskAllStatsData = data;
            $scope.greenLaneStat = data[0].LANECOUNT;
            $scope.redLaneStat = data[1].LANECOUNT;
            $scope.yellowLaneStat = data[2].LANECOUNT;
            $scope.totalLaneStat = $scope.greenLaneStat + $scope.redLaneStat + $scope.yellowLaneStat;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
});

app.controller('riskpermitstatsctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitStats.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.riskPermitStatsData = data;
        $scope.riskScoreStat = data.RISK_SCORE;
        $scope.paScoreStat = data.PA_SCORE;
        $scope.adScoreStat = data.AD_SCORE;
        $scope.rbStat = data.RB_SCORE;
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});

app.controller('riskpermitdetailsctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitDetails.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.permitData = data;
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});

app.controller('riskpermitdetailsIEctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitIEDetails.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.ieData = data;
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
    $scope.getAllParty = function () {
        var response = $http.post('getPermitAllPartyList.web', searchParam);
        response.success(function (data, status, headers, config) {
            $scope.allPartyData = data;
        });
        response.error(function (data, status, headers, config) {
        });
    };
});

app.controller('permitItemsListctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitItemsList.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.itemsListData = data;
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});

app.controller('permitallpartyctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitAllPartyList.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.allPartyData = data;
    });
    response.error(function (data, status, headers, config) {
    });
});

app.controller('permitcontainerctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitContainersList.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.containerListData = data;
    });
    response.error(function (data, status, headers, config) {
    });
});

app.controller('permitinvoicectrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitInvoicesList.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.invoiceListData = data;
    });
    response.error(function (data, status, headers, config) {
    });
});

app.controller('permitsupdocctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitSupDocList.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.supdocListData = data;
    });
    response.error(function (data, status, headers, config) {
    });
});


app.controller('riskListCtrl', function ($scope, $http, srvShareData, $location) {
    $http({method: 'GET', url: 'getRiskList.web'}).
        success(function (data, status, headers, config) {
            $scope.riskListData = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    $scope.setLinkParam = function (riskData) {
        $scope.riskDataToShare = {
            userId: riskData.USER_ID,
            jobNo: riskData.JOB_NO
        };
        srvShareData.addData($scope.riskDataToShare);
        window.location.href = "riskPermitPage.web";
    };
});

app.service('srvShareData', function ($window) {
    var RISK_PERMIT_KEY = 'Risk.Permit.SelectedValue';

    var addData = function (newObj) {
        /*var mydata = $window.sessionStorage.getItem(KEY);
         if (mydata) {
         mydata = JSON.parse(mydata);
         } else {
         mydata = [];
         }*/
        var mydata = [];
        mydata.push(newObj);
        $window.sessionStorage.setItem(RISK_PERMIT_KEY, JSON.stringify(mydata));
    };

    var getData = function () {
        var mydata = $window.sessionStorage.getItem(RISK_PERMIT_KEY);
        if (mydata) {
            mydata = JSON.parse(mydata);
        }
        return mydata || [];
    };

    return {
        addData: addData,
        getData: getData
    };
});

app.service('sharedRiskProperties', function () {
    var jobNo = '';
    var userId = '';
    return {
        getJobNo: function () {
            return jobNo;
        },
        setJobNo: function (value) {
            jobNo = value;
        },
        getUserId: function () {
            return userId;
        },
        setUserId: function (value) {
            userId = value;
        }
    };
});


app.controller('riskpermitchartstatsctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getRiskPermitStats.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.riskPermitStatsData = data;
        $scope.riskScoreStat = data.RISK_SCORE;
        $scope.paScoreStat = data.PA_SCORE;
        $scope.adScoreStat = data.AD_SCORE;
        $scope.rbStat = data.RB_SCORE;

        var convertedData = [];
        convertedData.push(['RISK SCORE', data.RISK_SCORE]);
        convertedData.push(['PREDICTIVE ANALYSIS SCORE', data.PA_SCORE]);
        convertedData.push(['ANAMOLY DETECTION SCORE', data.AD_SCORE]);
        convertedData.push(['RULE BASE SCORE', data.RB_SCORE]);

        var chart = c3.generate({
            size: {
                height: 310,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'pie',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#riskPermitStatPie',
        });
    });
    response.error(function (data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
});


app.controller('getPermitListCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $http({method: 'GET', url: 'getPermitList.web'}).
        success(function (data, status, headers, config) {
            $scope.permitList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
});


app.controller('getPermitStatCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $http({method: 'GET', url: 'getPermitStatsList.web'}).
        success(function (data, status, headers, config) {
            $scope.permitStat = data;
            $scope.totalProcessedStat = data.TOTAL_PROCESSED;
            $scope.gstStat = data.GST_AMT;
            $scope.customsDutyStat = data.CUSTOMS_DUTY_AMT;
            $scope.exciseDutyStat = data.EXCISE_AMT;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
});

app.controller('getTopHScodeCtrl', function ($scope, $http, srvShareData) {
    var response = $http.get('getTopHScode.web');
    response.success(function (data, status, headers, config) {
        $scope.topHSCodeList = data;

        var convertedData = [];
        convertedData.push([data[0].CODE, data[0].COUNT]);
        convertedData.push([data[1].CODE, data[1].COUNT]);
        convertedData.push([data[2].CODE, data[2].COUNT]);
        convertedData.push([data[3].CODE, data[3].COUNT]);
        convertedData.push([data[4].CODE, data[4].COUNT]);
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'donut',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#topHSCodeDonut',
        });
    });
    response.error(function (data, status, headers, config) {
    });
});


app.controller('getTopTraderRedCtrl', function ($scope, $http, srvShareData) {
    var response = $http.get('getTopTraderRed.web');
    response.success(function (data, status, headers, config) {
        $scope.topTraderRedList = data;

        var convertedData = [];
        data.forEach(function (item) {
            convertedData.push([item.NAME, item.COUNT]);
        });
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'donut',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#topRedLaneDonut',
        });
    });
    response.error(function (data, status, headers, config) {
    });

    $scope.getTopTraderRedPermit = function (topTrader) {

        var traderName = {
            name: topTrader.NAME
        };
        var response = $http.post('getTopTraderRedPermit.web', traderName);
        response.success(function (data, status, headers, config) {
            $scope.traderRedPermitListData = data;
        });
        response.error(function (data, status, headers, config) {
        });
    };
});

app.controller('permitauditctrl', function ($scope, $http, srvShareData) {
    var searchParam = {
        userId: srvShareData.getData()[0].userId,
        jobNo: srvShareData.getData()[0].jobNo
    };
    var response = $http.post('getPermitAuditDetails.web', searchParam);
    response.success(function (data, status, headers, config) {
        $scope.permitAuditData = data;
    });
    response.error(function (data, status, headers, config) {
    });
});


app.controller('permitApprovalTaskListCtrl', function ($scope, $http, $modal) {

    var response = $http.get('getPermitApprovalTaskList.web');
    response.success(function (data, status, headers, config) {
        $scope.permitApprovalTaskList = data;
    });
    response.error(function (data, status, headers, config) {
    });

    $scope.getSelectedPermitTaskData = function (selectedPermitTaskData) {
        $scope.approvalData = selectedPermitTaskData;
    };
    $scope.animationsEnabled = true;
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'permitApprovalTaskPopup.html',
            controller: 'permitApprovalTaskPopupCtrl',
            size: size,
            resolve: {
                approvalData: function () {
                    return $scope.approvalData;
                }
            }
        });
    };
});

app.controller('permitApprovalTaskPopupCtrl', function ($scope, $http, $modalInstance, approvalData) {

    $scope.approvalData = approvalData;
    $scope.submitPermitApproval = function (modifiedapprovalData) {

        var response = $http.post('submitPermitApprovalDetails.web', modifiedapprovalData);
        response.success(function (data, status, headers, config) {
            $modalInstance.dismiss('cancel');
            window.location.href = "permitapproval.web";
        });
        response.error(function (data, status, headers, config) {
        });
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

app.controller('inspectionapprovalTaskListCtrl', function ($scope, $http, $modal) {

    var response = $http.get('getInspectionApprovalTaskList.web');
    response.success(function (data, status, headers, config) {
        $scope.inspectionApprovalTaskList = data;
    });
    response.error(function (data, status, headers, config) {
    });

    $scope.getSelectedInspectionTaskData = function (selectedInspectionTaskData) {

        $scope.inspectionApprovalData = selectedInspectionTaskData;
    };
    $scope.animationsEnabled = true;
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'inspectionApprovalTaskPopup.html',
            controller: 'inspectionApprovalTaskPopupCtrl',
            size: size,
            resolve: {
                inspectionApprovalData: function () {
                    return $scope.inspectionApprovalData;
                }
            }
        });
    };
});

app.controller('inspectionApprovalTaskPopupCtrl', function ($scope, $http, $modalInstance, inspectionApprovalData) {

    $scope.inspectionApprovalData = inspectionApprovalData;
    $scope.submitInspectionApproval = function (modifiedInspectionApprovalData) {

        var response = $http.post('submitInspectionApprovalDetails.web', modifiedInspectionApprovalData);
        response.success(function (data, status, headers, config) {
            $modalInstance.dismiss('cancel');
            window.location.href = "inspectionapproval.web";
        });
        response.error(function (data, status, headers, config) {
        });
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});


app.controller('createLoggingCtrl', function ($scope, $http) {

    $http({method: 'GET', url: 'getAllApplicationDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.applicationList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllModuleDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.moduleList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllLogLevelDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.logLevelList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllFunctionDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.functionList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $scope.submitLogging = function (logData) {
        var response = $http.post('createLog.web', logData);
        response.success(function (data, status, headers, config) {
            window.location.href = "createlogging.web";
        });
        response.error(function (data, status, headers, config) {
        });
    };
});


app.controller('searchLoggingCtrl', function ($scope, $http, $modal) {
    $http({method: 'GET', url: 'getAllApplicationDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.applicationList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllModuleDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.moduleList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllLogLevelDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.logLevelList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $http({method: 'GET', url: 'getAllFunctionDetails.web'}).
        success(function (data, status, headers, config) {
            $scope.functionList = data;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    $scope.searchLogging = function (logData) {
        var response = $http.post('searchLog.web', logData);
        response.success(function (data, status, headers, config) {
            $scope.logDataList = data;
        });
        response.error(function (data, status, headers, config) {
        });
    };

    $scope.purgeLogging = function (logData) {
        var response = $http.post('purgeLog.web', logData);
        response.success(function (data, status, headers, config) {
            window.location.href = "purgelogging.web";
        });
        response.error(function (data, status, headers, config) {
        });
    };

    $scope.pdfLogging = function (logData) {
       var response = $http.post('getpdf.web', logData,{responseType: 'arraybuffer'});
        response.success(function (data, status, headers, config) {
            var file = new Blob([data], {type: 'application/pdf'});
            var fileURL = URL.createObjectURL(file);
            var downloadLinkElement = document.createElement("a");
            downloadLinkElement.href = fileURL;
            downloadLinkElement.download = "Logging.pdf";
            document.body.appendChild(downloadLinkElement);
            downloadLinkElement.click();
            //window.open(fileURL);
        });
        response.error(function (data, status, headers, config) {
        });
    };

    $scope.excelLogging = function (logData) {
        var response = $http.post('getexcel.web', logData,{responseType: 'arraybuffer'});
        response.success(function (data, status, headers, config) {
            var file = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
            var fileURL = URL.createObjectURL(file);
            var downloadLinkElement = document.createElement("a");
            downloadLinkElement.href = fileURL;
            downloadLinkElement.download = "Logging.xls";
            document.body.appendChild(downloadLinkElement);
            downloadLinkElement.click();
            //window.open(fileURL);
        });
        response.error(function (data, status, headers, config) {
        });
    };

    $scope.printLogging = function (logData) {
        var response = $http.post('getPrint.web', logData,{responseType: 'arraybuffer'});
        response.success(function (data, status, headers, config) {
            var file = new Blob([data], {type: 'text/html'});
            var fileURL = URL.createObjectURL(file);
            window.open(fileURL);
        });
        response.error(function (data, status, headers, config) {
        });
    };

    $scope.getSelectedLogData = function (selectedLogData) {
        $scope.logDetailsData = selectedLogData;
    };
    $scope.animationsEnabled = true;
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'logDetailsDataPopup.html',
            controller: 'logDetailsDataPopupCtrl',
            size: size,
            resolve: {
                logDetailsData: function () {
                    return $scope.logDetailsData;
                }
            }
        });
    };
});

app.controller('logDetailsDataPopupCtrl', function ($scope, $http, $modalInstance, logDetailsData) {

    $scope.logDetailsData = logDetailsData;
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});


app.controller('getLogStatCtrl', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $http({method: 'GET', url: 'getLogStatsList.web'}).
        success(function (data, status, headers, config) {
            $scope.logStat = data;

            if (angular.isUndefined(data[0])) {
                $scope.highStat = 0;
            }
            else {
                $scope.highStat = data[0].log_level_processed;
            }
            if (angular.isUndefined(data[1])) {
                $scope.mediumStat = 0;
            }
            else {
                $scope.mediumStat = data[1].log_level_processed;
            }
            if (angular.isUndefined(data[2])) {
                $scope.lowStat = 0;
            }
            else {
                $scope.lowStat = data[2].log_level_processed;
            }
            $scope.total_processed = $scope.highStat + $scope.mediumStat + $scope.lowStat;
        }).
        error(function (data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
});


app.controller('getLogDateTimeListCtrl', function ($scope, $http) {
    var response = $http.get('getLogDateTimeList.web');
    response.success(function (data, status, headers, config) {
        $scope.recentLogsList = data;

        var convertedData = [];
        if (!angular.isUndefined(data[0])) {
            convertedData.push([data[0].log_datetime, data[0].logcount]);
        }
        if (!angular.isUndefined(data[1])) {
            convertedData.push([data[1].log_datetime, data[1].logcount]);
        }
        if (!angular.isUndefined(data[2])) {
            convertedData.push([data[2].log_datetime, data[2].logcount]);
        }
        if (!angular.isUndefined(data[3])) {
            convertedData.push([data[3].log_datetime, data[3].logcount]);
        }
        if (!angular.isUndefined(data[4])) {
            convertedData.push([data[4].log_datetime, data[4].logcount]);
        }
        if (!angular.isUndefined(data[5])) {
            convertedData.push([data[5].log_datetime, data[5].logcount]);
        }
        if (!angular.isUndefined(data[6])) {
            convertedData.push([data[6].log_datetime, data[6].logcount]);
        }
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'bar',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bar: {
                /*width: {
                 ratio: 0.5 // this makes bar width 50% of length between ticks
                 }*/
                // or
                width: 50 // this makes bar width 100px
            },
            bindto: '#recentLogsBar',
        });
    });
    response.error(function (data, status, headers, config) {
    });
});

app.controller('getAppListCtrl', function ($scope, $http) {
    var response = $http.get('getAppCountList.web');
    response.success(function (data, status, headers, config) {
        $scope.appLogsList = data;

        var convertedData = [];
        if (!angular.isUndefined(data[0])) {
            convertedData.push([data[0].app_id, data[0].appcount]);
        }
        if (!angular.isUndefined(data[1])) {
            convertedData.push([data[1].app_id, data[1].appcount]);
        }
        if (!angular.isUndefined(data[2])) {
            convertedData.push([data[2].app_id, data[2].appcount]);
        }
        if (!angular.isUndefined(data[3])) {
            convertedData.push([data[3].app_id, data[3].appcount]);
        }
        if (!angular.isUndefined(data[4])) {
            convertedData.push([data[4].app_id, data[4].appcount]);
        }
        if (!angular.isUndefined(data[5])) {
            convertedData.push([data[5].app_id, data[5].appcount]);
        }
        if (!angular.isUndefined(data[6])) {
            convertedData.push([data[6].app_id, data[6].appcount]);
        }
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'pie',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#topAppPie',
        });
    });
    response.error(function (data, status, headers, config) {
    });
});


app.controller('getModuleListCtrl', function ($scope, $http) {
    var response = $http.get('getModuleCountList.web');
    response.success(function (data, status, headers, config) {
        $scope.moduleLogsList = data;

        var convertedData = [];
        if (!angular.isUndefined(data[0])) {
            convertedData.push([data[0].module_name, data[0].modulecount]);
        }
        if (!angular.isUndefined(data[1])) {
            convertedData.push([data[1].module_name, data[1].modulecount]);
        }
        if (!angular.isUndefined(data[2])) {
            convertedData.push([data[2].module_name, data[2].modulecount]);
        }
        if (!angular.isUndefined(data[3])) {
            convertedData.push([data[3].module_name, data[3].modulecount]);
        }
        if (!angular.isUndefined(data[4])) {
            convertedData.push([data[4].module_name, data[4].modulecount]);
        }
        if (!angular.isUndefined(data[5])) {
            convertedData.push([data[5].module_name, data[5].modulecount]);
        }
        if (!angular.isUndefined(data[6])) {
            convertedData.push([data[6].module_name, data[6].modulecount]);
        }
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'donut',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#topModuleDonut',
        });
    });
    response.error(function (data, status, headers, config) {
    });
});


app.controller('getFunctionListCtrl', function ($scope, $http) {
    var response = $http.get('getFunctionCountList.web');
    response.success(function (data, status, headers, config) {
        $scope.functionLogsList = data;

        var convertedData = [];
        if (!angular.isUndefined(data[0])) {
            convertedData.push([data[0].function_name, data[0].functioncount]);
        }
        if (!angular.isUndefined(data[1])) {
            convertedData.push([data[1].function_name, data[1].functioncount]);
        }
        if (!angular.isUndefined(data[2])) {
            convertedData.push([data[2].function_name, data[2].functioncount]);
        }
        if (!angular.isUndefined(data[3])) {
            convertedData.push([data[3].function_name, data[3].functioncount]);
        }
        if (!angular.isUndefined(data[4])) {
            convertedData.push([data[4].function_name, data[4].functioncount]);
        }
        if (!angular.isUndefined(data[5])) {
            convertedData.push([data[5].function_name, data[5].functioncount]);
        }
        if (!angular.isUndefined(data[6])) {
            convertedData.push([data[6].function_name, data[6].functioncount]);
        }
        var chart = c3.generate({
            size: {
                height: 300,
                width: 450
            },
            data: {
                columns: convertedData,
                type: 'bar',
                /*colors: {
                 Green : '#3DE20C',
                 Red : '#FF3E01',
                 Yellow : '#FFF148'
                 },*/
            },
            bindto: '#topFunctionBar',
        });
    });
    response.error(function (data, status, headers, config) {
    });
});

app.controller('loginCtrl', function ($scope, $http) {

    $scope.login = function (loginData) {
        var response = $http.post('login.web', loginData);
        response.success(function (data, status, headers, config) {
            window.location.href = "loghome.web";
        });
        response.error(function (data, status, headers, config) {
            alert(data);
            window.location.href = "login.web";
        });
    };
});


app.controller('getTransactionBasedCtrl', function ($scope, $http) {
    var response = $http.get('getTransactionBased.web');
    response.success(function (data, status, headers, config) {

        /*var convertedData = [];
        if (!angular.isUndefined(data[0])) {
            convertedData.push([data[0].app_id, data[0].appcount]);
        }
        if (!angular.isUndefined(data[1])) {
            convertedData.push([data[1].app_id, data[1].appcount]);
        }
        if (!angular.isUndefined(data[2])) {
            convertedData.push([data[2].app_id, data[2].appcount]);
        }
        if (!angular.isUndefined(data[3])) {
            convertedData.push([data[3].app_id, data[3].appcount]);
        }
        if (!angular.isUndefined(data[4])) {
            convertedData.push([data[4].app_id, data[4].appcount]);
        }
        if (!angular.isUndefined(data[5])) {
            convertedData.push([data[5].app_id, data[5].appcount]);
        }
        if (!angular.isUndefined(data[6])) {
            convertedData.push([data[6].app_id, data[6].appcount]);
        }*/

        var chart = c3.generate({
            bindto: "#chart",
            data: {
                columns: [
                    ['data', 15.0]
                ],
                type: 'gauge',
            },
            gauge: {
                label:{
                    format: function(value, ratio){
                        return value; //returning here the value and not the ratio
                    },
                },
                min: 50,
                max: 100,
                units: '%' //this is only the text for the label
            },
            bindto: '#tableSizeGauge'
        });

    });
    response.error(function (data, status, headers, config) {
    });
});


















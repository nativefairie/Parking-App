$(document).ready(function () {

    stringMonth = "January";
    var history;

    var days = {};

    

    var options = {
        title: {
            text: "Daily usage for " + stringMonth
        },
        animationEnabled: true,
        exportEnabled: true,
        data: [
            {
                type: "spline", //change it to line, area, column, pie, etc
                dataPoints: [
                    { x: 1, y: 80 },
                    { x: 2, y: 70 },
                    { x: 3, y: 80 },
                    { x: 4, y: 83 },
                    { x: 5, y: 67 },
                    { x: 6, y: 44 },
                    { x: 7, y: 21 },
                    { x: 8, y: 82 },
                    { x: 9, y: 79 },
                    { x: 10, y: 82 },
                    { x: 11, y: 81 },
                    { x: 12, y: 87 },
                    { x: 13, y: 13 },
                    { x: 14, y: 55 },
                    { x: 15, y: 67 },
                    { x: 16, y: 69 },
                    { x: 17, y: 81 },
                    { x: 18, y: 25 },
                    { x: 19, y: 84 },
                    { x: 20, y: 77 },
                    { x: 21, y: 56 },
                    { x: 23, y: 87 },
                    { x: 24, y: 86 },
                    { x: 25, y: 15 },
                    { x: 26, y: 21 },
                    { x: 27, y: 71 },
                    { x: 28, y: 87 },
                    { x: 29, y: 45 },
                    { x: 30, y: 69 },
                    { x: 31, y: 84 }
                ]
            }
        ]
    };

    resetOptions();
    function statisticDay($) {

        "use strict";

        $.fn.daysOfWeekInput = function () {
            return this.each(function () {
                var $field = $(this);

                var days = [
                    {
                        Name: 'Su',
                        Value: 'N',
                        Checked: false
                    },
                    {
                        Name: 'Mo',
                        Value: 'M',
                        Checked: false
                    },
                    {
                        Name: 'Tu',
                        Value: 'T',
                        Checked: false
                    },
                    {
                        Name: 'We',
                        Value: 'W',
                        Checked: false
                    },
                    {
                        Name: 'Th',
                        Value: 'R',
                        Checked: false
                    },
                    {
                        Name: 'Fr',
                        Value: 'F',
                        Checked: false
                    },
                    {
                        Name: 'Sa',
                        Value: 'S',
                        Checked: false
                    }
                ];

                var currentDays = $field.val().split('');
                for (var i = 0; i < currentDays.length; i++) {
                    var dayA = currentDays[i];
                    for (var n = 0; n < days.length; n++) {
                        var dayB = days[n];
                        if (dayA === dayB.Value) {
                            dayB.Checked = true;
                        }
                    }
                }

                // Make the field hidden when in production.
                //$field.attr('type','hidden');

                var options = '';
                var n = 0;
                while ($('.days' + n).length) {
                    n = n + 1;
                }

                var optionsContainer = 'days' + n;
                $field.before('<div class="days ' + optionsContainer + '"></div>');

                for (var i = 0; i < days.length; i++) {
                    var day = days[i];
                    var id = 'day' + day.Name + n;
                    var checked = day.Checked ? 'checked="checked"' : '';
                    options = options + '<div><input type="checkbox" value="' + day.Value + '" id="' + id + '" ' + checked + ' /><label for="' + id + '">' + day.Name + '</label>&nbsp;&nbsp;</div>';
                }

                $('.' + optionsContainer).html(options);

                $('body').on('change', '.' + optionsContainer + ' input[type=checkbox]', function () {
                    var value = $(this).val();
                    var index = getIndex(value);
                    if (this.checked) {
                        updateField(value, index);
                    } else {
                        updateField(' ', index);
                    }
                });

                function getIndex(value) {
                    for (i = 0; i < days.length; i++) {
                        if (value === days[i].Value) {
                            return i;
                        }
                    }
                }

                function updateField(value, index) {
                    $field.val($field.val().substr(0, index) + value + $field.val().substr(index + 1)).change();
                }
            });
        }


    } (jQuery);



    $('#selected--month').click(function () {

        var selValue = $('option[name=month]:selected').val();
        changeMonth(selValue);

        var myReq = new Object();
        myReq.month = selValue;
        var myJson = JSON.stringify(myReq);
        console.log(myJson);
        resetOptions();
        sendJSON(myJson);

        //updateVec();

        

        options.title.text = "Daily usage for " + stringMonth;

        $("#chartContainer").CanvasJSChart(options);
    });






    function sendJSON(myJson) {
        $.ajax({
            url: "http://localhost:8080/statistic",
            type: "POST",
            contentType: "application/json",
            data: myJson,
            dataType: "json"
        });
        getAllHistory();
    }



    //functie pentru parsarea jsonului de istoric:
    function getAllHistory() {

        // var JSONs = $.getJSON('http://localhost:8080/statistics');
        //
        // console.log(JSONs);
        console.log("getHistory");

        fetch('http://localhost:8080/statistics')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                renderHistory(myJson);
            });
    }

    function updateVec() {
        for (i = 1; i <= 31; i++)
        options.dataPoints[i].y = days[i];
    }

    function resetOptions() {
        for (i = 0; i < 31; i++) {
            console.log(options.data[0].dataPoints);
            //options.data[0].dataPoints[i].y = 0;
        }
    }

    function getHistoryForMonth(month) {
        var monthHistory = [];
        each(history, function () {
            if (this.date.getMonth() == month) {
                monthHistory.push(this);
            }
        });
        return monthHistory;
    }

    function renderHistory(myJson) {
        var i;
        var spotCount = Object.keys(myJson).length;
       

        for (i = 0; i < spotCount; i++) {
           var day = myJson[i].date;
           var intDay = parseInt(day.substr(8,10));
           console.log(day , intDay);

           options.data[0].dataPoints[intDay].y++;
        }
        
    }

    function changeMonth(monthId) {

        if(monthId == 1) {
            stringMonth = "January";
        }
        if(monthId == 2) {
            stringMonth = "February"
        }
        if(monthId == 3) {
            stringMonth = "March";
        }
        if(monthId == 4) {
            stringMonth = "April";
        }
        if(monthId == 5) {
            stringMonth = "May";
        }
        if(monthId == 6) {
            stringMonth = "June";
        }
        if(monthId == 7) {
            stringMonth = "July";
        }
        if(monthId == 8) {
            stringMonth = "August";
        }
        if(monthId == 9) {
            stringMonth = "September";
        }
        if(monthId == 10) {
            stringMonth = "October";
        }
        if(monthId == 11) {
            stringMonth = "November";
        }
        if(monthId == 12) {
            stringMonth = "December";
        }
    }

});

// functie de creat jsonul cu ID-ul lunii pentru care se cere statistica


// 
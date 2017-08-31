function getData() {
    var data = [];

    //parse date format dd.mm.yyyy
    function toDate(dateStr) {
        var parts = dateStr.split(".");
        return new Date(parts[2], parts[1] - 1, parts[0]);
    }

    $("#data_table tbody tr").each(function () {
        var date = toDate($(this).find(':nth-child(2)').text());
        var value = $(this).find(':nth-child(3)').text();
        data.push([date, value]);
    });

    return data;
}


$(function () {


    $.plot($("#flotcontainer"),
        [
            {data: getData()}
        ],
        {
            grid: {
                backgroundColor: { colors: ["#75A7E0", "#1F77DB"]  }
            },
            xaxis: {
                mode: "time",
                timeformat: "%m/%d/%y"
            },
            yaxis: { min: 0, max:210,tickSize: 20  },
            points: {
                show: true,
                radius: 1.5,
                fillColor: '#75A7E0'
            },
            lines: {
                show: true,
                fillColor: { colors: [{ opacity: 0.7 }, { opacity: 0.1}] }
            }

        }
    );

});

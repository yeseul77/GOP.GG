    function createchart(data) {
        const ctx = document.getElementById('myChartdounat');

        var labelList = [];
        var labelWinCountList = [];


        for (let i = recentGames.length - 1; i >= 0; i--) {
            var gameInfo = recentGames[i];
            var winCount = 0;

            for (var j = 0; j < gameInfo.info.length; j++) {
                var playerInfo = gameInfo.info[j];
                if (playerInfo.win && playerInfo.riotIdGameName === gameName && playerInfo.riotIdTagline === tagLine) {
                    winCount++;
                }
            }

            labelList.push("게임 " + (i + 1));
            labelWinCountList.push(winCount);
        }

        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labelList,
                datasets: [{
                    label: '이긴 횟수',
                    data: labelWinCountList,
                    backgroundColor: '#4BC0C0',
                    borderColor: '#4BC0C0',
                    borderWidth: 1,
                    fill: false
                }]
            },
            options: {
                responsive: true,
                title: {
                    display: true,
                    text: "최근 10게임의 승 패"
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    }
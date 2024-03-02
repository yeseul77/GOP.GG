/*$(document).ready(function() {
    var myDoughnutChart = null; // 초기에 차트 변수를 null로 설정합니다.

    // 도넛 차트 생성 함수
    function createDoughnutChart() {
        var data = {
            labels: ['승리', '패배'], // 각 값의 라벨
            datasets: [{
                data: [30, 70], // 각 값의 비율 (전체 합이 100이 되도록 설정)
                backgroundColor: [
                    'rgba(54, 162, 235, 0.5)',// 첫 번째 값의 색상
                    'rgba(255, 99, 132, 0.5)'// 두 번째 값의 색상
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        };

        // 차트 설정
        var options = {
            responsive: true,
            maintainAspectRatio: false
        };

        // 차트가 이미 생성되었다면 제거합니다.
        if (myDoughnutChart) {
            myDoughnutChart.destroy();
        }

        // 도넛 차트 생성
        var ctx = document.getElementById('myChartdounat').getContext('2d');
        myDoughnutChart = new Chart(ctx, {
            type: 'doughnut',
            data: data,
            options: options
        });
    }

    // 도넛 차트 생성 함수 호출
    createDoughnutChart();
});*/

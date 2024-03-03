//        var data = {
//            labels: ['Value 1', 'Value 2', 'Value 3', 'Value 4', 'Value 5'],
//            datasets: [{
//                label: 'Value',
//                data: [12, 19, 3, 5, 2], // 다섯 개의 값
//                backgroundColor: [
//                    'rgba(255, 99, 132, 0.5)',
//                    'rgba(54, 162, 235, 0.5)',
//                    'rgba(255, 206, 86, 0.5)',
//                    'rgba(75, 192, 192, 0.5)',
//                    'rgba(153, 102, 255, 0.5)'
//                ],
//                borderColor: [
//                    'rgba(255, 99, 132, 1)',
//                    'rgba(54, 162, 235, 1)',
//                    'rgba(255, 206, 86, 1)',
//                    'rgba(75, 192, 192, 1)',
//                    'rgba(153, 102, 255, 1)'
//                ],
//                borderWidth: 1
//            }]
//        };
//
//        // 차트 생성
//        var ctx = document.getElementById('myChartbar').getContext('2d');
//        var myBarChart = new Chart(ctx, {
//            type: 'bar',
//            data: data,
//            options: {
//                scales: {
//                    y: {
//                        beginAtZero: true
//                    }
//                }
//            }
//        });
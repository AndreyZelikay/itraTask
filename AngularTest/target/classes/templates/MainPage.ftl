<head>
    <spring:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js" var="angularjs" /></head>
<div class="col-lg-12">
    <script>
        var app = angular.module('application', []);
        app.controller('myController', function($scope) {
            $scope.framework = {name:'Spring MVC Framework'};

            $scope.links = [
                {link:'http://spring.io/'},
                {link:'http://javastudy.ru'},
                {link:'https://yandex.ru'},
                {someFunction: function() {
                        return 'someFunctionTest'
                    }
                }
            ];

            $scope.someFunction = function(value) {
                return 'you put this value: ' + value;
            };
        });
    </script>

    <div ng-app="application">

        <h1>AngularJS expressions</h1>
        <div  ng-controller="myController">
            <div>
                <table class="table table-striped">
                    <tr>
                        <td><b>Сложение чисел:</b></td>
                        <td>{{ 1 + 1 }} </td>
                    </tr>
                    <tr>
                        <td><b>Конкатенация строк:</b></td>
                        <td>{{ "AngularJS " + " " + "Spring MVC"}} </td>
                    </tr>
                    <tr>
                        <td><b>$scope:</b></td>
                        <td>{{ framework }}</td>
                    </tr>
                    <tr>
                        <td><b>Вызов объекта $scope.framework.name:</b></td>
                        <td>{{ framework.name }}</td>
                    </tr>
                    <tr>
                        <td><b>Массивы $scope.links[1].link:</b></td>
                        <td><a href="{{ links[1].link }}" target="_blank">{{ links[1].link }}</a></td>
                    </tr>
                    <tr>
                        <td><b>Вызов функции (из $scope.someFunction(123)):</b></td>
                        <td>{{ someFunction(123) }}</td>
                    </tr>
                    <tr>
                        <td><b>Вызов функции (из $scope.links):</b></td>
                        <td>{{ links[3].someFunction()}}</td>
                    </tr>
                </table>

                1. $scope - Область видимости, созданная при объявлении директивы ngController;
            </div>
        </div>
    </div>

</div>
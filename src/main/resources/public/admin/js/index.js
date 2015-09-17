(function(){
    'use strict';
    angular
        .module('train', ['ui.router'])
        .config(RouterConfig)
        .controller('TrainController', TrainController)
        .controller('TicketController', TicketController)
    ;

    function RouterConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/");

        $stateProvider.state('home', {
                url: '/',
                templateUrl: 'templates/home.html'
            }).state('train', {
                url: '/train',
                templateUrl: 'templates/train.html',
                controller: 'TrainController',
                controllerAs: 'tc'
            }).state('train.ticket', {
                url: '/ticket/:number',
                templateUrl: 'templates/ticket.html',
                controller: 'TicketController',
                controllerAs: 'tc'
            });

    }

    function TrainController($http, $timeout) {
        var vm = this;

        vm.addTrain = addTrain;
        vm.getTrain = getTrain;
        vm.removeTrain = removeTrain;
        vm.trains = [];

        activate();

        function activate() {
            vm.loading = true;
            getTrain();
        }

        function getTrain() {
            $http
                .get('/api/trains')
                .success(function(trains) {
                    vm.trains = trains;
                    vm.loading = false;
                });
        }

        function addTrain() {
            $http
                .post("/api/trains", {
                    number: vm.number,
                    start: vm.start,
                    end: vm.end,
                    departTime: vm.departTime,
                    arrivalTime: vm.arrivalTime
                })
                .success(function() {
                    vm.loading = true;
                    getTrain();
                });
        }

        function removeTrain(number) {
            $http
                .delete('/api/trains/' + number)
                .success(function() {
                    vm.loading = true;
                    getTrain();
                });
        }
    }

    function TicketController($state, $http) {
        var vm = this;
        vm.number = $state.params.number;
        vm.addTicket = addTicket;
        vm.getTicket = getTicket;
        vm.tickets = [];

        activate();

        function activate() {
            getTicket();
        }

        function addTicket() {
            $http
                .post('/api/trains/' + vm.number + '/tickets', {
                    business: vm.business,
                    state: vm.state,
                    firstClass: vm.firstClass,
                    secondClass: vm.secondClass,
                    standing: vm.standing,
                    departDate: vm.departDate
                })
                .success(function() {
                    getTicket();
                });
        }

        function getTicket() {
            $http
                .get('/api/trains/' + vm.number + '/tickets')
                .success(function(tickets) {
                    vm.tickets = tickets;
                });
        }
    }
})();

/**
 * Created by seal on 9/18/15.
 */


(function(){
    'use strict';

    angular
        .module('train.client', ['ui.router', 'ui.bootstrap'])
        .config(routerConfig)
        .factory('user', user)
        .controller('HomeController', HomeController)
        .controller('LoginController', LoginController)
        .controller('RegisterController', RegisterController)
        .controller('ReserveController', ReserveController);


    function user() {
        return {
            logged: false,
            username: '',
            token: '',
            phone: '',
            id: ''
        }
    }


    function routerConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/reserve');


        $stateProvider
            .state('reserve', {
                url: '/reserve',
                templateUrl: 'templates/reserve.html',
                controller: 'ReserveController',
                controllerAs: 'rc'
            });

    }

    function ReserveController($http, $filter) {
        var vm = this;

        vm.start = '上海';
        vm.end = '济南';
        vm.departDate = new Date();
        vm.query  = query;
        vm.tickets = [];

        function query() {
            $http
                .get('http://127.0.0.1:8080/api/trains/tickets/search', {
                    params: {
                        start: vm.start,
                        end: vm.end,
                        departDate: $filter('date')(vm.departDate, 'yyyy-MM-dd')
                    }
                })
                .then(function(response) {
                    vm.tickets =  response.data;
                }, function(){

                });
        }

    }

    function HomeController($modal, user) {
        var vm = this;

        var loginModal, registerModal;

        vm.openLogin = openLogin;
        vm.openRegister = openRegister;
        vm.user = user;
        vm.logout = logout;

        function openLogin() {
            loginModal = $modal.open({
                animation: true,
                templateUrl: 'login.html',
                controller: 'LoginController',
                controllerAs: 'lc',
                resolve: {
                }
            });
        }

        function openRegister() {
            registerModal = $modal.open({
                animation: true,
                templateUrl: 'register.html',
                controller: 'RegisterController',
                controllerAs: 'rc',
                resolve: {
                }
            });
        }

        function logout() {
            user.logged = false;
        }

    }

    function LoginController($modalInstance, $http, user) {
        var vm = this;
        vm.username = '';
        vm.password = '';
        vm.close = close;
        vm.login = login;

        function close() {
            $modalInstance.close();
        }

        function login() {
            $http
                .post('/api/login', {
                    username: vm.username,
                    password: vm.password
                })
                .then(function(response) {
                    alert('登录成功');
                    vm.close();
                    var u = response.data;
                    user.logged = true;
                    user.token = u.token;
                    user.phone = u.phone;
                    user.username = u.username;
                    user.id = u.id;

                }, function() {
                    alert('登录失败');
                });
        }

    }

    function RegisterController($modalInstance, $http, user) {
        var vm = this;
        vm.username = '';
        vm.password = '';
        vm.phone = '';
        vm.id = '';
        vm.close = close;
        vm.register = register;

        function close() {
            $modalInstance.close();
        }

        function register() {
            $http
                .post('/api/register', {
                    username: vm.username,
                    password: vm.password,
                    id: vm.id,
                    phone: vm.phone
                })
                .then(function() {
                    alert('注册成功');
                    vm.close();
                }, function() {
                    alert('注册失败');
                });
        }
    }

})();
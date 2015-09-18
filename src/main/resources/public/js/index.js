/**
 * Created by seal on 9/18/15.
 */


(function(){
    'use strict';

    angular
        .module('train.client', ['ui.router', 'ui.bootstrap'])
        .config(routerConfig)
        .factory('user', user)
        .filter('seatType', seatType)
        .filter('tesseraStatus', tesseraStatus)
        .controller('HomeController', HomeController)
        .controller('UserController', UserController)
        .controller('LoginController', LoginController)
        .controller('RegisterController', RegisterController)
        .controller('ReserveController', ReserveController);

    function tesseraStatus() {
        var map = {
            'UNPAID': '未支付',
            'RETURNED': '已退票'
        };
        return function(tesseraStatus){
            return map[tesseraStatus] ? map[tesseraStatus] : '出错啦';
        }
    }

    function seatType() {
        return function(seatType) {
            switch (seatType) {
                case 'STANDING':
                    return '站票';
                case 'FIRST_CLASS':
                    return '一等座';
                case 'STATE':
                    return '特等座';
                case 'SECOND_CLASS':
                    return '二等座';
                case 'BUSINESS':
                    return '商务座';
                default:
                    return '出错啦';
            }
        }
    }


    function user($rootScope, $window) {

        var u = $window.localStorage.getItem('user'),
            user;
        if (u != null) {
            user = JSON.parse(u);
        } else {
            user = {
                logged: false,
                username: '',
                token: '',
                phone: '',
                id: ''
            };
        }

        $rootScope.$watch(function() {
            return user.logged;
        }, function(){
            if (user.logged) {
                $window.localStorage.setItem('user', JSON.stringify(user));
            } else {
                $window.localStorage.removeItem('user');
            }
        });

        return user;


    }


    function routerConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/reserve');


        $stateProvider.state('reserve', {
            url: '/reserve',
            templateUrl: 'templates/reserve.html',
            controller: 'ReserveController',
            controllerAs: 'rc'
        }).state('user', {
            url: '/user',
            templateUrl: 'templates/user.html',
            controller: 'UserController',
            controllerAs: 'uc'
        });

    }

    function ReserveController($http, $filter, user) {
        var vm = this;

        vm.start = '上海';
        vm.end = '济南';
        vm.departDate = new Date();
        vm.query  = query;
        vm.buy = buy;
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

        function buy(id) {
            $http({
                url: 'http://127.0.0.1:8080/api/trains/buy' ,
                method: 'POST',
                data: {
                    ticket: id,
                    seatType: 'STANDING'
                },
                headers: {
                    token: user.token
                }
            }).then(function(){
                alert('购买成功');
            }, function(){
                alert('购买失败');
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

    function UserController($http, user) {
        var vm = this;
        vm.tesseras = [];
        $http
            .get('/api/trains/tesseras', {
                headers: {
                    token: user.token
                }
            })
            .then(function(response){
                vm.tesseras.splice(0);
                response.data.forEach(function(tessera) {
                    vm.tesseras.push(tessera);
                })
                console.log(response.data);
            }, function(){

            });
        vm.returnTessera = returnTessera;

        function returnTessera(tessera) {
            $http
                .delete('/api/trains/tesseras/' + tessera.id)
                .then(function() {
                    alert('退票成功');
                    tessera.tesseraStatus = 'RETURNED';
                }, function(){
                    alert('退票失败');
                })
        }
    }

})();
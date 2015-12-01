/**
 * calendarDemoApp - 0.9.0
 */
var calendarDemoApp = angular.module('calendarDemoApp', [ 'ui.calendar',
		'ui.bootstrap' ]);

calendarDemoApp
		.controller(
				'CalendarCtrl',
				function($scope, $compile, $timeout, $http, uiCalendarConfig) {
					$scope.defaultDate = new Date();

					$scope.addedDays = [];

					$scope.view = 'month';

					$scope.daysOff = [];

					$scope.test = false;

					jQuery(".draggable").each(function() {
						jQuery(this).draggable({
							zIndex : 999,
							revert : true, // will cause the event to go
							// back to its
							revertDuration : 0
						});
					});

					/* rest call to get days off */
					$http.get('/daysoff').success(function(data) {
						angular.forEach(data, function(value) {
							$scope.daysOff.push(value);
						});
					});

					$scope.events = [];

					/* alert on eventClick */
					$scope.alertOnEventClick = function(date, jsEvent, view) {
						$scope.alertMessage = (date.title + ' was clicked ');
					};
					/* alert on Drop */
					$scope.alertOnDrop = function(event, delta, revertFunc,
							jsEvent, ui, view) {
						$scope.alertMessage = ('Event Dropped to make dayDelta ' + delta);
					};
					/* alert on Resize */
					$scope.alertOnResize = function(event, delta, revertFunc,
							jsEvent, ui, view) {
						$scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
					};
					/* add custom event */
					$scope.addEvent = function() {
						// $scope.events.push();
					};
					/* remove event */
					$scope.remove = function(index) {
						$scope.events.splice(index, 1);
					};
					/* Change View */
					$scope.changeView = function(view, calendar) {
						uiCalendarConfig.calendars[calendar].fullCalendar(
								'changeView', view);
					};
					/* Render Tooltip */
					$scope.eventRender = function(event, element, view) {
						// element.attr({
						// 'tooltip' : event.title,
						// 'tooltip-append-to-body' : true
						// });
						element.qtip({
							content : {
								text : event.title
							},
							position : {
								my : 'top center',
								at : 'bottom center'
							},
							style : {
								classes : 'qtip-admb'
							}
						});

						// $compile(element)($scope);
					};

					$scope.eventAfterAllRender = function(view) {
						$scope.view = view.name;
						var holidays = $scope.daysOff;
						var holidayMoment;
						for (var i = 0; i < holidays.length; i++) {
							holidayMoment = moment(holidays[i]);
							if (view.name == 'month') {
								jQuery(
										"td[data-date="
												+ holidayMoment
														.format('YYYY-MM-DD')
												+ "]").addClass('holiday');
							} else if (view.name == 'agendaWeek') {
								var classNames = $("th")
										.filter(
												function() {
													return $(this).text()
															.split(" ")[1] === holidayMoment
															.format('M/D');
												}).attr("class");
								if (classNames != null) {
									var classNamesArray = classNames.split(" ");
									jQuery(
											"td."
													+ classNamesArray[classNamesArray.length - 1])
											.addClass('holiday');
								}
							} else if (view.name == 'agendaDay') {
								if (holidayMoment.format('YYYY-MM-DD') == this.calendar
										.getDate().format('YYYY-MM-DD')) {
									jQuery("td.fc-day").addClass('holiday');
								}
								;
							}
						}
					}

					/* lazy loading of events */
					$scope.lazeEvents = function(start, end, callback) {
						if ($scope.events.length > 0) {
							$scope.events.length = 0;
						}
						$http.get('/greeting?start=' + start + '&end=' + end)
								.success(function(data) {
									if ($scope.events.length === 0) {
										angular.forEach(data, function(value) {
											$scope.events.push(value);
										});
										angular.forEach($scope.addedDays, function(value) {
											$scope.events.push(value);
										});
									}
								});
						// $scope.defaultDate.setMonth(start.format('M'));
						// $scope.defaultDate.setFullYear(start.format('YYYY'));
						// $scope.duplicate.setMonth(start.format('M'))
						// $scope.duplicate.setFullYear(start.format('YYYY'));
						// jQuery("#littleCalendar").data(
						// "$uibDatepickerController").refreshView();
					}
					/* can be used to remove the dropped event from the list */
					$scope.dropEvent = function(date, jsEvent, ui) {

					}

					$scope.eventReceive = function(event) {
						$scope.addedDays.push(event);
					}

					/* config object */
					$scope.uiConfig = {
						calendar : {
							firstDay : 1,
							businessHours : {
								start : '8:00',
								end : '16:45'
							},
							defaultDate : $scope.defaultDate,
							height : 450,
							editable : true,
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							droppable : true,
							drop : $scope.dropEvent,
							defaultView : $scope.view,
							events : $scope.lazeEvents,
							eventClick : $scope.alertOnEventClick,
							eventDrop : $scope.alertOnDrop,
							eventResize : $scope.alertOnResize,
							eventRender : $scope.eventRender,
							eventAfterAllRender : $scope.eventAfterAllRender,
							eventColor : '#0f554c',
							eventReceive : $scope.eventReceive,
							slotDuration : '00:15:00'
						}
					};

					/* event sources array */
					$scope.eventSources = [ $scope.events, $scope.addedDay ];

					$scope.$watch('defaultDate',function() {
										var calendar = uiCalendarConfig.calendars['myCalendar1'];
										if (calendar !== undefined) {
											calendar.fullCalendar('gotoDate',
													$scope.defaultDate);
										}
									});
				});
calendarDemoApp.controller('SidebarCtrl', function($scope, $http) {
	$scope.locale = 'nl_BE';
	$scope.menuItems = [];
	$http.get('/sidemenu').success(function(data) {
		angular.forEach(data, function(value) {
			$scope.menuItems.push(value);
		});
	});
});

calendarDemoApp.controller('HeaderCtrl', function($scope, $http) {
	$http.get('/currentUser').success(function(data) {
		$scope.currentUser = data;
	});
});

calendarDemoApp.directive('translated', function($http) {
	return {
		restrict : "AEC",
		scope : {
			"twoway" : "=" // two way binding
		},
		link : function(scope, el, attr) {

			$http.get('/translation?lang=nl_BE&key=' + attr.translated)
					.success(function(data) {
						jQuery(el).text(data.value);
					});
		}
	}
});
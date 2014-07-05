var SERVER = "http://"+location.hostname+":8080/reservations";
	
$(function () {
	wb.reservations.callService();
});

var wb = wb || {};

wb.reservations = {
		callService : function() {
			$.ajax({
				url : SERVER,
				success : function(data) {
					var $reservations = $('#reservations');
					data.forEach(function(entry) {
						$reservations.append("<tr><td>"+entry.nomChalet+"</td><td>"+entry.dateDebut+"</td><td>"+entry.dateFin+"</td></tr>");
					});
				}
			});
		}
}
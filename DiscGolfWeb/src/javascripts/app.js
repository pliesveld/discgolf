import './modules'

console.log(`app.js has loaded!`)

$(document).ready(() => {
	$("#btn-new-game").click((event) => {
		$(event.currentTarget).hide();
	});
});

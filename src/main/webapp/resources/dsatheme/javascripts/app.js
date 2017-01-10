$(function () {
    $('.js-toggle').bind('click', function (event) {
        $('.js-sidebar, .js-content').toggleClass('is-toggled');
        event.preventDefault();
    });
    $('.js-notify').bind('click', function (event) {
        $('.dsa-notify').toggleClass('dsa-notify-toogle');
        event.preventDefault();
    });
    $('.inner-dsa-notify').slimScroll({
        height: '250px'
    });
});
function removeNotifyItem(id) {
    $('#' + id).hide('slide');
    var contagem = $('#notifySize').html();
    if (contagem > 1) {
        contagem = contagem - 1;
        $('#notifySize').html(contagem);
    } else {
        $('#notifySize').hide();
    }
}
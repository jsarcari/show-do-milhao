document.addEventListener('DOMContentLoaded', function() {
    const premiums = document.querySelectorAll('.valuePremium');
    for (var i=0; i<premiums.length; i++) {
        var premium = premiums[i];
        var valuePremium = premium.textContent.split(' ');
        let formatted = valuePremium[1].replace(/\.00$/, ',00');
        formatted = formatted.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
        premium.textContent = valuePremium[0] + ' ' + formatted;
    }
})
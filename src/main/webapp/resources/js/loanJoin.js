function reloadPage() {
    location.reload();
}

function formatCurrency(input) {
    let value = input.value.replace(/[^0-9]/g, '');
    let formattedValue = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    input.value = formattedValue + '원';
}

const searchProduct = document.querySelector("#searchProduct");
searchProduct.addEventListener("input", function () {
    console.log(searchProduct.value);
    if (searchProduct.value.length >= 2) {
        searchProduct.setAttribute("autocomplete", "on");
        searchProduct.setAttribute("list", "product");
    } else {
        searchProduct.setAttribute("list", "");
    }
});

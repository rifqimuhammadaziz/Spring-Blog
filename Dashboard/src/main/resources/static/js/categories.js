const myModal = document.getElementById('editCategoryModal')
const myInput = document.getElementById('categoryName')
const categoryId = document.getElementById('categoryId')
const categoryName = document.getElementById('categoryName')

$(document).on("click", "#editButton", function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    $.get(href, function (category, status) {
        $('#categoryId').val(category.id);
        $('#categoryName').val(category.name);
    });
    $('#editCategoryModal').modal('show');
});
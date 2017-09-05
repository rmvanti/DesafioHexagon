$(document).ready(function (event) {
    var baseUrl = 'http://localhost:8084/MedicalRecord/rs/patient';
    var btnAdd = $('.btnAdd');

    var fieldPatientId = $('.fieldPatientId');
    var fieldPatientName = $('.fieldPatientName');
    var fieldPatientPhone = $('.fieldPatientPhone');
    var fieldPatientCpf = $('.fieldPatientCpf');

    var tablePatientList = $('.tablePatientList');

    function refreshList() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", baseUrl, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                json = typeof json === 'string' ? JSON.parse(json) : json;
                var line = '';
                tablePatientList.find('tbody').find('tr').remove();
                for (var i = 0; i < json.length; i++) {
                    line = '<tr>';
                    line += '<td>' + json[i].name + '</td>';
                    line += '<td>' + json[i].phone + '</td>';
                    line += '<td>' + json[i].cpf + '</td>';
                    line += '<td>';
                    line += '<button class="btn btn-sm btn-warning btnEdit" title="Editar" style="margin-right:5px;" data-id="' + json[i].id + '" data-name="' + json[i].name + '" data-phone="' + json[i].phone + '" data-cpf="' + json[i].cpf + '">';
                    line += '<i class="fa fa-edit"/>';
                    line += '</button>';
                    line += '<button class="btn btn-sm btn-danger btnDelete" title="Remover" style="margin-right:5px;" data-id="' + json[i].id + '">';
                    line += '<i class="fa fa-edit"/>';
                    line += '</button>';
                    line += '</td>';
                    line += "</tr>";
                    tablePatientList.find('tbody').append(line);
                }
            }
        };
        xhr.send();
    }

    btnAdd.click(function (ev) {
        ev.preventDefault();

        var id = fieldPatientId.val();
        var url = baseUrl;
        if (id !== '') {
            url += '/' + id;
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                //json = typeof json === 'string' ? JSON.parse(json) : json;
                refreshList();
                btnAdd.html('<i class="fa fa-plus"/> Adicionar');
                fieldPatientId.val('');
                fieldPatientName.val('');
                fieldPatientPhone.val('');
                fieldPatientCpf.val('');
            }
        };
        var dataToSend = JSON.stringify({
            'id': fieldPatientId.val() === '' ? 0 : fieldPatientId.val(),
            'name': fieldPatientName.val(),
            'phone': fieldPatientPhone.val(),
            'cpf': fieldPatientCpf.val()
        });
        xhr.send(dataToSend);
    });

    tablePatientList.on('click', '.btnEdit', function (ev) {
        ev.preventDefault();
        btnAdd.html('<i class="fa fa-plus"></i> Salvar');
        fieldPatientCpf.val($(this).attr('data-cpf'));
        fieldPatientPhone.val($(this).attr('data-phone'));
        fieldPatientName.val($(this).attr('data-name'));
        fieldPatientId.val($(this).attr('data-id'));
    });

    tablePatientList.on('click', '.btnDelete', function (ev) {
        ev.preventDefault();
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", baseUrl + '/' + $(this).attr('data-id'), true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                //json = typeof json === 'string' ? JSON.parse(json) : json;
                refreshList();
            }
        };
        xhr.send();
    });
    refreshList();
});


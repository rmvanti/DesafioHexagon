$(document).ready(function (event) {

    var baseUrl = 'http://localhost:8084/MedicalRecord/rs/speciality';

    var fieldSpecialityId = $('.fieldSpecialityId');
    var fieldSpecialityName = $('.fieldSpecialityName');

    var btnAddSpeciality = $('.btnAddSpeciality');

    var tableSpecialityList = $('.tableSpecialityList');
    var tableDoctorList = $('.tableDoctorList');

    function refreshSpecilityList() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", baseUrl, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                json = typeof json === 'string' ? JSON.parse(json) : json;
                tableSpecialityList.find('tbody').find('tr').remove();
                var line;
                for (var i = 0; i < json.length; i++) {
                    line = '<tr>';
                    line += '<td>' + json[i].name + "</td>";
                    line += '<td>';
                    line += '<button class="btn btn-sm btn-primary btnView" style="margin-left:5px;" title="Visualizar médicos" data-id="' + json[i].id + '">';
                    line += '<i class="fa fa-eye"></i></button>';
                    line += '<button class="btn btn-sm btn-warning btnEdit" style="margin-left:5px;" title="Editar especialidade" data-id="' + json[i].id + '" data-name="' + json[i].name + '">';
                    line += '<i class="fa fa-edit"></i></button>';
                    line += '<button class="btn btn-sm btn-danger btnDelete" style="margin-left:5px;" title="Remover especialidade" data-id="' + json[i].id + '">';
                    line += '<i class="fa fa-remove"></i></button>';
                    line += '</td>';
                    line += '</tr>';
                    tableSpecialityList.find('tbody').append(line);
                }
            }
        };
        xhr.send();
    }

    function refreshDoctorList(pid) {
        var id = pid;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", baseUrl + '/doctors/' + id, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                json = typeof json === 'string' ? JSON.parse(json) : json;
                tableDoctorList.find('tbody').find('tr').remove();
                var line;
                for (var i = 0; i < json.length; i++) {
                    line = '<tr>';
                    line += '<td>' + json[i][5] + '</td>';
                    line += '<td>' + json[i][4] + '</td>';
                    line += '<td>' + json[i][6] + '</td>';
                    line += '</tr>';
                    tableDoctorList.find('tbody').append(line);
                }
            }
        };
        xhr.send();
    }

    btnAddSpeciality.click(function (ev) {
        ev.preventDefault();
        if (fieldSpecialityName.val() === '' ||
                fieldSpecialityName === null ||
                fieldSpecialityName === undefined) {
            alert('Preencha os campos corretamente!');
        } else {
            var id = fieldSpecialityId.val();
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
                    fieldSpecialityName.val('');
                    fieldSpecialityId.val('');
                    btnAddSpeciality.html('<i class="fa fa-plus"/> Adicionar');
                    refreshSpecilityList();
                }
            };
            var dataToSend = JSON.stringify({
                "id": fieldSpecialityId.val() === '' || fieldSpecialityId.val() === undefined || fieldSpecialityId.val() === null ? 0 : fieldSpecialityId.val(),
                "name": fieldSpecialityName.val()
            });
            xhr.send(dataToSend);
        }
    });

    tableSpecialityList.on('click', '.btnView', function (ev) {
        ev.preventDefault();
        refreshDoctorList($(this).attr('data-id'));
    });

    tableSpecialityList.on('click', '.btnEdit', function (ev) {
        ev.preventDefault();
        btnAddSpeciality.html('<i class="fa fa-plus"/> Salvar');
        fieldSpecialityId.val($(this).attr('data-id'));
        fieldSpecialityName.val($(this).attr('data-name'));
    });

    tableSpecialityList.on('click', '.btnDelete', function (ev) {
        ev.preventDefault();
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", baseUrl + '/' + $(this).attr('data-id'), true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                //json = typeof json === 'string' ? JSON.parse(json) : json;                            
                refreshSpecilityList();
            }
        };
        xhr.send();
    });

    refreshSpecilityList();
});

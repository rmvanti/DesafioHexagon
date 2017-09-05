$(document).ready(function (event) {

    var baseUrl = 'http://localhost:8084/MedicalRecord/rs/doctor';

    var fieldDoctorId = $('.fieldDoctorId');
    var fieldDoctorName = $('.fieldDoctorName');
    var fieldDoctorPhone = $('.fieldDoctorPhone');
    var fieldDoctorCpf = $('.fieldDoctorCpf');
    var fieldDoctorCrm = $('.fieldDoctorCrm');

    var btnAdd = $('.btnAdd');

    var tableDoctorList = $('.tableDoctorList');
    var tableSpecialityList = $('.tableSpecialityList');

    function getSpecialitiesList() {
        var checkList = tableSpecialityList.find('tbody').find('.checkSpeciality');
        var s = '';
        for (var i = 0; i < checkList.length; i++) {
            if (checkList[i].prop('checked')) {
                s += checkList[i].attr('data-id') + ';';
            }
        }
        return s;
    }

    function refreshDoctorList() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", baseUrl, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = xhr.response;
                json = typeof json === 'string' ? JSON.parse(json) : json;
                var line = '';
                tableDoctorList.find('tbody').find('tr').remove();
                for (var i = 0; i < json.length; i++) {
                    line = '<tr>';
                    line += '<td> <span>' + json[i].name + '</span></td>';
                    line += '<td> <span>' + json[i].phone + '</span></td>';
                    line += '<td> <span>' + json[i].cpf + '</span></td>';
                    line += '<td> <span>' + json[i].crm + '</span></td>';
                    line += '<td>';
                    line += '<button class="btn btn-warning btn-sm btnEdit" style="margin-right:5px;" title="Editar"';
                    line += 'data-id="' + json[i].id + '"';
                    line += 'data-name="' + json[i].name + '"';
                    line += 'data-phone="' + json[i].phone + '"';
                    line += 'data-cpf="' + json[i].cpf + '"';
                    line += 'data-crm="' + json[i].crm + '"';
                    line += '><i class="fa fa-edit"/>';
                    line += '</button>';
                    line += '<button class="btn btn-danger btn-sm btnDelete" style="margin-right:5px;" title="Remover" data-id="' + json[i].id + '">';
                    line += '<i class="fa fa-remove"/>';
                    line += '</button>';
                    line += '</td>';
                    line += '</tr>';
                    tableDoctorList.find('tbody').append(line);
                }
            }
        };
        xhr.send();
    }

    btnAdd.click(function (ev) {
        ev.preventDefault();
        var id = $('.fieldDoctorId').val();
        var url = baseUrl;
        if (id !== undefined) {
            url += '/' + id;
        }
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {            
            fieldDoctorId.val('');
            fieldDoctorName.val('');
            fieldDoctorPhone.val('');
            fieldDoctorCpf.val('');
            fieldDoctorCrm.val('');
            btnAdd.html('<i class="fa fa-plus"/> Adicionar');
            refreshDoctorList();
        };
        //var specialities = getSpecialitiesList();
        var dataToSend = JSON.stringify({
            'id': fieldDoctorId.val() === '' ? 0 : fieldDoctorId.val(),
            'name': fieldDoctorName.val(),
            'phone': fieldDoctorPhone.val(),
            'cpf': fieldDoctorCpf.val(),
            'crm': fieldDoctorCrm.val()
                    //'especialities': specialities
        });
        xhr.send(dataToSend);
    });

    tableDoctorList.on('click', '.btnEdit', function (ev) {
        ev.preventDefault();
        var $this = $(this);
        fieldDoctorId.val($this.attr('data-id'));
        fieldDoctorName.val($this.attr('data-name'));
        fieldDoctorPhone.val($this.attr('data-phone'));
        fieldDoctorCpf.val($this.attr('data-cpf'));
        fieldDoctorCrm.val($this.attr('data-crm'));
        btnAdd.html('<i clas="fa fa-plus"/> Salvar');
    });

    tableDoctorList.on('click', '.btnDelete', function (ev) {
        ev.preventDefault();        
        var id = $(this).attr('data-id');
        var url = baseUrl + "/" + id;
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", url, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.withCredentials = false;
        xhr.onreadystatechange = function () {
            refreshDoctorList();
        };
        xhr.send();
    });

    refreshDoctorList();
});

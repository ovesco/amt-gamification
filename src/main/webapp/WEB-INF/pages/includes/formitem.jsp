<div class="form-group">
    <div class="input-group input-group-alternative">
        <input class="form-control ${errors.containsKey(param.field) ? 'is-invalid' : ''}" name="firstName" value="${param.value}" placeholder="First name" type="${param.type}">
    </div>
    <c:if test="${errors.containsKey('firstName')}"><p class="text-danger">${errors.get('firstName')}</p></c:if>
</div>
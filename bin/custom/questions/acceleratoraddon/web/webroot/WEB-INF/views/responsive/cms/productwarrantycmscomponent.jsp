<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="warranty" value="${product.warrantyYears}"/>
<c:if test="${not empty warranty}">
    <p>Warranty: ${warranty} years.</p>
</c:if>
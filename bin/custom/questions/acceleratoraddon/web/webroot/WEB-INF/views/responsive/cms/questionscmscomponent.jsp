<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="questions-section" style="font-size: ${fontSize}px;">
    <h3>Questions</h3>
    <c:forEach items="${questions}" var="question" end="${numberOfQuestionsToShow}">
        <p>${question.questionCustomer.firstName} ${question.questionCustomer.lastName}: ${question.question}</p>
        <c:if test="${not empty question.answer}">
            <p>${question.answerCustomer.firstName} ${question.answerCustomer.lastName}: ${question.answer}</p>
        </c:if>
    </c:forEach>
</div>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POST Request Test for Feedback Endpoint</title>
</head>
<body>
    <h1>POST Request Test for Feedback Rest API</h1>
    <hr>
    <form:form modelAttribute="feedback">
        <form:label path="product">Product</form:label>
        <form:input path="product" type="text" />
        <form:label path="description">Review</form:label>
        <form:input path="description" type="text" />
        <form:label path="rating">Rating</form:label>
        <form:input path="rating" type="text" />
        <button type="submit">Submit</button>

    </form:form>
    
</body>
</html>
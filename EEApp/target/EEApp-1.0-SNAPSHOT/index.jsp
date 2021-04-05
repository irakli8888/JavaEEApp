<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container" >
    <div class="row">
        <div class="col"></div>
        <div class="col align-self-center">
            <form action="EEApp_war/" method="post">
                <label for="exampleFormControlTextarea1" class="form-label">введите текст сообщения</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" name="area" rows="3"></textarea>
                <p></p>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Confirm identity</button>
                </div>
            </form>
        </div>
        <div class="col"></div>
        <p></p>
        ${message}

    </div>
</div>
<br/>
</body>
</html>
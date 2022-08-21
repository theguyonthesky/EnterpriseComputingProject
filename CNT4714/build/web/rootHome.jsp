<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Root Home Page</title>
    </head>
    <body>
        <div class="container query-form">
            <form action="RootUserApp" method="post">
                <div>
                    <input type="text" name="query" class="query-input" />
                </div>
                <div class="d-flex">
                    <div>
                        <button type="submit" class="btn btn-success mt-2 mx-2">Execute Command</button>
                    </div>
                    <div>
                        <button type="reset" class="btn btn-success mt-2 mx-2">Reset Form</button>
                    </div>
                    <div>
                        <input onclick="clearResults()" value="Clear Results" class="btn btn-success mt-2 mx-2"/>
                    </div>
                </div>
                <div>
                    <b>Database Results:</b>
                    <div class="output" id="output">
                        <%
                            ResultSet set = (ResultSet) session.getAttribute("result");
                            if (set != null) {
                                ResultSetMetaData metaData = set.getMetaData();
                        %>
                        <table class="table">
                            <thead>
                                <tr>
                                    <%
                                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                                    %>
                                    <th scope="col"><%= metaData.getColumnName(i)%></th>
                                        <%
                                            }
                                        %>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while (set.next()) {
                                %>
                                <tr>
                                    <%
                                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                                    %>
                                    <td><%= set.getString(i)%></td>
                                    <%
                                        }
                                    %>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                        <%
                                session.removeAttribute("result");
                            }
                        %>

                        <!--for error message-->
                        <%
                            String error = (String) session.getAttribute("error");
                            if (error != null) {
                        %>
                        <p style="background: tomato; text-align: center; color: white;"><%= error%></p>
                        <%
                                session.removeAttribute("error");
                            }
                        %>
                        <!--end error message-->

                        <!--for success message-->
                        <%
                            String success = (String) session.getAttribute("success");
                            if (success != null) {
                        %>
                        <p style="background: seagreen; text-align: center;"><%= success%></p>
                        <%
                                session.removeAttribute("success");
                            }
                        %>
                        <!--end error message-->
                    </div>
                </div>
            </form>
        </div>

        <script>
            function clearResults() {
                document.getElementById('output').innerHTML = "";
            }
        </script>
    </body>
</html>

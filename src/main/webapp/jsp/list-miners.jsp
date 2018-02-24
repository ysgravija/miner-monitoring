<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/color.css">
        <script src="../js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
            <h2>Miners</h2>

            <!--Miners List-->
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if>
            <form action="/monitoring" method="post" id="minersForm" role="form" >
                <input type="hidden" id="idMiner" name="idMiner">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty minerList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Worker</th>
                                    <th>Model</th>
                                    <th>Hash Rate</th>
                                    <th>Ideal Hash Rate</th>
                                    <th>Temp 1</th>
                                    <th>Temp 2</th>
                                    <th>Temp 3</th>
                                    <th>Stats</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:forEach var="miner" items="${minerList}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${idMiner == miner.id}">
                                    <c:set var="classSuccess" value="info"/>
                                </c:if>
                                <tr class="${classSuccess}">
                                    <td>
                                        <a href="http://${miner.hostIp}/" target="_blank">${miner.workerName}</a>
                                    </td>
                                    <td>${miner.model}</td>
                                    <td>${miner.hashRate}</td>
                                    <td>${miner.idealHashRate}</td>
                                    <td align="center" class="${miner.chipTemperature.temp1 < 70 ? 'low_temp' : miner.chipTemperature.temp1 < 80 ? 'medium_temp' : 'high_temp'}">${miner.chipTemperature.temp1} </td>
                                    <td align="center" class="${miner.chipTemperature.temp2 < 70 ? 'low_temp' : miner.chipTemperature.temp2 < 80 ? 'medium_temp' : 'high_temp'}">${miner.chipTemperature.temp2} </td>
                                    <td align="center" class="${miner.chipTemperature.temp3 < 70 ? 'low_temp' : miner.chipTemperature.temp3 < 80 ? 'medium_temp' : 'high_temp'}">${miner.chipTemperature.temp3} </td>
                                    <td>A: ${miner.miningAccepted} &nbsp; R: ${miner.miningRejected} &nbsp; E: ${miner.hardwareErrors}</td>
                                    <td><a href="#" id="remove"
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('idMiner').value = '${miner.id}';
                                                    document.getElementById('minerForm').submit();">
                                            <span class="glyphicon glyphicon-trash"/>
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <div class="alert alert-info">
                            No mining machine found.
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </body>
</html>
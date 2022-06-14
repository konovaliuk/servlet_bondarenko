<%@ page import ="kpi.iasa.mmsa.taxiservice.DTO.DriverDTO"%>
<%@ page import = "java.util.List"%>
<html>
    <style type="text/css">
            table {
                background: grey;
                border-collapse: separate; /* Способ отображения границы */
                width: 75%; /* Ширина таблицы */
                border-spacing: 0; /* Расстояние между ячейками */
                border-radius: 20px;
               }
               td, th {
                padding: 20px; /* Поля вокруг текста */
                border: 1px solid black; /* Граница вокруг ячеек */
                border-left: 0;
                border-right: 0;
                text-align: center; /* Выравнивание по центру */
               }
               td {
                    background: silver;
                    border-bottom: 0;
               }
               th{
                    border-top: 0;
               }
        </style>
        <style type="text/css">
                        html {
                          font-family: sans-serif;
                        }

                        label {
                          margin-right: 15px;
                          line-height: 32px;
                        }

                        input {
                          -webkit-appearance: none;
                          -moz-appearance: none;
                          appearance: none;

                          border-radius: 50%;
                          width: 16px;
                          height: 16px;

                          border: 2px solid #999;
                          transition: 0.2s all linear;
                          outline: none;
                          margin-right: 5px;

                          position: relative;
                          top: 4px;
                        }

                        input:checked {
                          border: 6px solid black;
                        }

                        button,
                        legend {
                          color: white;
                          background-color: black;
                          padding: 5px 10px;
                          border-radius: 0;
                          border: 0;
                          font-size: 14px;
                        }

                        button:hover,
                        button:focus {
                          color: #999;
                        }

                        button:active {
                          background-color: white;
                          color: black;
                          outline: 1px solid black;
                        }
                    </style>
<body>
    <center>
    <h3>Choose your driver</h3>
    <hr/>
    <div>
    <form name="chooseDriverForm" method="POST" action="Controller">
    <input type="hidden" name="command" value="chooseDriver"/>
    <% List<DriverDTO> listOfDrivers = (List<DriverDTO>)request.getAttribute("listOfDrivers");
            out.print("<table> <tbody>"+
                        "<tr>"+
                            "<th>Name</th>"+
                            "<th>Telephone Number</th>"+
                            "<th>Telegram Username</th>"+
                            "<th>Arrival Time</th>"+
                            "<th>Type of Car</th>"+
                            "<th>Car capacity</th>"+
                            "<th>Rating</th>"+
                            "<th>Cost</th>"+
                            "<th></th>"+
                        "</tr>");
            for(DriverDTO driverDTO : listOfDrivers){
                out.print( "<tr>" +
                                "<td>"+driverDTO.getName() +"</td>"+
                                "<td>"+driverDTO.getTelephoneNumber() +"</td>"+
                                "<td>"+driverDTO.getTelegramUsername() +"</td>"+
                                "<td>"+driverDTO.getArrivalTime() +"</td>"+
                                "<td>"+driverDTO.getType() +"</td>"+
                                "<td>"+driverDTO.getCapacity() +"</td>"+
                                "<td>"+driverDTO.getRating() +"</td>"+
                                "<td>"+driverDTO.getCost() +" UAN </td>"+
                                "<td><input name=\"chosenDriver\" type=\"radio\" value = "+driverDTO.getTelegramUsername()+"></td>"+
                          "</tr>");
            }
            out.print("</tbody></table><p><button type=\"submit\">Enter</button></p>");
    %>
    </form>
    </center>
    </div>
</body>
</html>
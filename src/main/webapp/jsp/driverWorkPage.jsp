<%@ page import ="kpi.iasa.mmsa.taxiservice.DTO.RideDTO"%>
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
    <h3>Choose your ride</h3>
    <hr/>
    <div>
    <form name="chooseRideForm" method="POST" action="Controller">
    <input type="hidden" name="command" value="chooseRide"/>
    <% List<RideDTO> listOfRides = (List<RideDTO>)request.getAttribute("available_rides");
            out.print("<table> <tbody>"+
                        "<tr>"+
                            "<th>Name of User</th>"+
                            "<th>Departure point</th>"+
                            "<th>Destination</th>"+
                            "<th>Cost</th>"+
                            "<th></th>"+
                        "</tr>");
            for(RideDTO rideDTO : listOfRides){
                out.print( "<tr>" +
                                "<td>"+rideDTO.getName() +"</td>"+
                                "<td>"+rideDTO.getDeparturePoint() +"</td>"+
                                "<td>"+rideDTO.getDestination() +"</td>"+
                                "<td>"+rideDTO.getCost() +" UAN </td>"+
                                "<td><input name=\"chosenRide\" type=\"radio\" value = "+rideDTO.getRideId()+"></td>"+
                          "</tr>");
            }
            out.print("</tbody></table><p><button type=\"submit\">Enter</button></p>");
    %>
    </form>
    </center>
    </div>
</body>
</html>
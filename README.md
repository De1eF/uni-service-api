# Uni Service API
A simple API for controlling university sites with students, teachers, and grades
<h2>Sturcture</h2>
 <ul>
  <li>Student ( Name, Surname, Grade list, Teacher list )</li>
  <li>Teacher ( Name, Surname, Subject, Student list )</li>
  <li>Grade ( Student, Score, Subject )</li>
  <li>Subject ( Name )</li>
 </ul>
<h2>Technologies</h2>
 <ul>
  <li>JDK (v17)</li>
  <li>Intelij Idea (v2023.2)</li>
  <li>Maven (v3.1.1)</li>
  <li>Spring Boot (v3.1.2)</li>
  <li>Spring Web (v6.0)</li>
  <li>Spring data JPA (v3.1.0)</li>
  <li>Lombok (v3)</li>
  <li>Spring Validation (v3.0.6)</li>
  <li>H2 database (v1.4)</li>
  <li>Swagger (v2.1)</li>
  <li>Doceker</li>
 </ul>
<h2>How to run</h2>
 <ul>
  <li>Make sure you have JDK 17, Maven 3, and Docker installed</li>
  <li>Pull project from this repository</li>
  <li>Run `mvn package`</li>
  <li>Run `docker -t build delef/uni-service-api .`</li>
  <li>Run `docker compose up`</li>
  <li>Now you can use <a href="http://127.0.0.1:8080/swagger-ui/index.html#/">Swagger</a> to see the endpoints</li>
  <li>Or use <a href="https://www.postman.com/winter-flare-675251/workspace/uni-service/collection/27138445-7e9675f7-fe2a-4cc2-a9ce-0df7f1ef1129?action=share&creator=27138445"> Postman collection</a></li>
 </ul>

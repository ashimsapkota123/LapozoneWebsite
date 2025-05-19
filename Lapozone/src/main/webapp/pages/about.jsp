<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Lapozone About Us</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/about.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/all.min.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<div class="our-team">
  <h2>Our Team</h2>
</div>

<section class="about-us">
  <div class="container">

    <!-- Frontend Developer -->
    <div class="person">
      <div class="image-placeholder"></div>
      <div class="text">
        <h2>Bishesh Lamichanne</h2>
        <h5>Front-end Developer</h5>
        <p>I am Bishesh Lamichanne, a Front-end Developer skilled in creating responsive and interactive web interfaces. I focus on HTML, CSS, JavaScript, and modern frameworks to deliver seamless user experiences. I believe in clean code, design harmony, and continuous learning to enhance the frontend development process.
.</p>
        <div class="data"><a href="#"><button class="but">Connect</button></a></div>
      </div>
      <div class="image-placeholder"><img src="../images/bishesh2.jpg" class="image-placeholder"></div>
    </div>

    <!-- Backend Developer -->
    <div class="person">
      <div class="image-placeholder"></div>
      <div class="text">
        <h2>Asim Sapkota</h2>
        <h5>Back-end Developer</h5>
        <p>I am Asim Sapkota, a Backend Developer focused on creating robust server-side solutions. I work with databases, APIs, and security protocols to ensure smooth backend operations. I'm passionate about scalable systems and love solving complex problems through backend logic and optimization.
</p>
        <div class="data"><a href="#"><button class="but">Connect</button></a></div>
      </div>
      <div class="image-placeholder"><img src="../images/Ashim.jpg" class="image-placeholder"></div>
    </div>

    <!-- Database Specialist -->
    <div class="person">
      <div class="image-placeholder"></div>
      <div class="text">
        <h2>Sayal Adhikari</h2>
        <h5>Database Specialist</h5>
        <p>I am Sayal Adhikari, a Database Specialist with a passion for managing and optimizing relational databases. I ensure data integrity, design efficient schemas, and write performant queries. My goal is to maintain a secure and well-structured data layer for the application.
</p>
        <div class="data"><a href="#"><button class="but">Connect</button></a></div>
      </div>
      <div class="image-placeholder"><img src="../images/sayal.jpg" class="image-placeholder"></div>
    </div>

    <!-- UI/UX Designer -->
    <div class="person">
      <div class="image-placeholder"></div>
      <div class="text">
        <h2>Kushal Subedi</h2>
        <h5>UI/UX Designer</h5>
        <p>I am Kushal Subedi, a UI/UX Designer who focuses on crafting user-centric interfaces. I specialize in wireframing, prototyping, and user research. My mission is to create seamless and intuitive user experiences that align with user behavior and brand identity.
</p>
        <div class="data"><a href="#"><button class="but">Connect</button></a></div>
      </div>
      <div class="image-placeholder"><img src="../images/kusal.jpg" class="image-placeholder"></div>
    </div>

    <!-- Graphic Designer -->
    <div class="person">
      <div class="image-placeholder"><img src="../images/sumit.jpg" class="image-placeholder"></div>
      <div class="text">
        <h2>Sumit Chettri</h2>
        <h5>Graphic Designer</h5>
        <p>I am Sumit Chettri, a Graphic Designer skilled in visual storytelling and brand representation. I work with colors, typography, and layouts to design impactful graphics that elevate the brand image and engage the audience.
</p>
        <div class="data"><a href="#"><button class="but">Connect</button></a></div>
      </div>
      <div class="image-placeholder"></div>
    </div>


  </div>
</section>

<jsp:include page="footer.jsp" />
<script src="${pageContext.request.contextPath}/js/home.js"></script>
</body>
</html>

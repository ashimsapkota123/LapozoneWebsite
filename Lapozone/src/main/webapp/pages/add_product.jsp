<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/admin.css" />
    <title>Add Product</title>
</head>
<body>

<section class="arb-mid-mir">
  <div class="arb-conmir-container">
    <a href="${pageContext.request.contextPath}/FetchProductForAdmin"><button class="arb-back-p">Back</button></a>
    <div class="arb-conmir-logo-container">
        <div class="arb-conmir-company-name">Add Product &#128722;</div>
    </div>

    <div class="arb-conmir-form-container">
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div style="color: red; font-weight: bold; margin-bottom: 15px;"><%= error %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="arb-conmir-form-grid">
                <div>
                    <label for="pID" class="arb-conmir-label">Product ID:</label>
                    <input type="text" id="pID" class="arb-conmir-input" name="pID" required>
                </div>
                <div>
                    <label for="pname" class="arb-conmir-label">Product Name:</label>
                    <input type="text" id="pname" class="arb-conmir-input" name="pname" required>
                </div>
                <div>
                    <label for="pprice" class="arb-conmir-label">Price:</label>
                    <input type="text" id="pprice" class="arb-conmir-input" name="pprice" required>
                </div>
                <div>
                    <label for="psize" class="arb-conmir-label">Screen Size:</label>
                    <input type="text" id="psize" class="arb-conmir-input" name="psize" required>
                </div>
                <div>
                    <label for="pram" class="arb-conmir-label">Ram:</label>
                    <input type="text" id="pram" class="arb-conmir-input" name="pram" required>
                </div>
                <div>
                    <label for="pproc" class="arb-conmir-label">Processor:</label>
                    <input type="text" id="pproc" class="arb-conmir-input" name="pproc" required>
                </div>
                <div>
                    <label for="pimage" class="arb-conmir-label">Product Image:</label>
                    <input type="file" id="pimage" class="arb-conmir-upload" name="pimage" accept="image/*" required>
                </div>
                <div>
                    <label for="pbat" class="arb-conmir-label">Battery:</label>
                    <input type="text" id="pbat" class="arb-conmir-input" name="pbat" required>
                </div>
                <div>
                    <label for="pstor" class="arb-conmir-label">Storage:</label>
                    <input type="text" id="pstor" class="arb-conmir-input" name="pstor" required>
                </div>
                <div>
                    <label for="pwar" class="arb-conmir-label">Warranty:</label>
                    <input type="text" id="pwar" class="arb-conmir-input" name="pwar" required>
                </div>
                <div>
                    <label for="pdis" class="arb-conmir-label">Discount %:</label>
                    <input type="text" id="pdis" class="arb-conmir-input" name="pdis" required>
                </div>
                <div>
                    <label for="pfeat" class="arb-conmir-label">Features:</label>
                    <textarea id="pfeat" class="arb-conmir-textarea" name="pfeat" rows="10" required></textarea>
                </div>
            </div>
            <button type="submit" class="arb-conmir-button">ADD</button>
        </form>
    </div>
  </div>
</section>

</body>
</html>

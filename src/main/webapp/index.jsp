<!DOCTYPE html>
<html>
<head>
  <title>Epic Shelter CyberSecurity</title>
  <link rel="stylesheet" type="text/css" href="styles.css">
  <script src="script.js"></script>
  <style>
    .search-container {
      display: flex;
      align-items: center;
    }

    .dropdown {
      margin-right: 10px;
    }

    .search-wrapper {
      display: flex;
      align-items: center;
    }
  </style>
</head>
<body>

  <div id="loading-screen" class="loading-screen">
    <div class="loading-wrapper">
      <div class="loading-message">Loading...</div>
      <div id="loading-timer" class="loading-timer"></div>
    </div>
  </div>
  <header class="header">
    <nav>
      <a href="#">Contact Us</a>
      <a href="#">Report URL</a>
      <a href="#">Link Not Detected Correctly</a>
      <a href="#">About</a>
    </nav>
  </header>
  <main>
    <div class="logo-container">
      <img src="logo.png" alt="Epic Shelter Logo" class="logo">
    </div>
    <div id="intro-text" class="intro-text"></div>
    <div class="search-container">
      <div class="search-wrapper">
        <div class="dropdown">
          <button class="dropdown-button">Options</button>
          <div class="dropdown-content">
            <a href="#" onclick="selectOption('Quick Check')">Quick Check</a>
            <a href="#" onclick="selectOption('In Depth Check')">In Depth Check</a>
          </div>
        </div>
        <input type="text" id="search-bar" placeholder="Paste your URL here" class="search-bar">
        <button id="search-button" class="search-button">Search</button>
      </div>
    </div>
    <div id="search-results">
      <% String result = (String) request.getAttribute("result");
      System.out.println(result);
      
      if (result != null) {
        %>
        
       <p style="color: red;">Result: <%= result %></p>
       
        <%  
      }
      %>
    </div>
    <div id="error-message" class="error-message"></div>
  </main>
</body>
</html>

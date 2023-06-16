window.addEventListener("load", function() {
  const loadingScreen = document.getElementById("loading-screen");
  const heading = document.querySelector(".intro-text");

  // Start loading timer
  let seconds = 6;
  const timerInterval = setInterval(() => {
    seconds--;
    if (seconds === 0) {
      clearInterval(timerInterval);
      loadingScreen.style.display = "none"; // Hide loading screen
      typeWriter(); // Start the typewriter effect
    }
  }, 100);

  // Typewriter effect for the heading
  const text = "Welcome to Epic Shelter";
  let index = 0;
  const speed = 30; // Speed of typing animation in milliseconds

  function typeWriter() {
    if (index < text.length) {
      heading.textContent += text.charAt(index);
      index++;
      setTimeout(typeWriter, speed);
    }
  }

  const searchBar = document.getElementById('search-bar');
  const searchButton = document.getElementById('search-button');
  const errorMessage = document.getElementById('error-message');
  const searchResults = document.getElementById('search-results');

  function handleSearch() {
    const url = searchBar.value.trim();
    if (!isValidUrl(url)) {
      errorMessage.textContent = 'Please enter a valid URL';
      return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "servlet-url", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // Set the content type to url-encoded
   xhr.onreadystatechange = function () {
  if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
    // Handle the response from the server
    const result = xhr.responseText;

    // Extract the desired part of the result
    const startIndex = result.indexOf('<div id="search-results">');
    const endIndex = result.indexOf('</div>', startIndex) + 6;
    const desiredResult = result.substring(startIndex, endIndex);

    // Update the search results on the webpage
    searchResults.innerHTML = desiredResult;

    searchResults.scrollIntoView({ behavior: 'smooth' });

  }
};


    const params = "url=" + encodeURIComponent(url); // Encode the URL parameter
    xhr.send(params);
  }

  function isValidUrl(url) {
    var urlPattern = /^(ftp|http|https):\/\/[^ "]+$/;
    return urlPattern.test(url);
  }

  searchButton.addEventListener('click', handleSearch);

  searchBar.addEventListener('keyup', function(event) {
    if (event.key === 'Enter') {
      event.preventDefault();
      handleSearch();
    }
  });
});

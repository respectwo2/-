<!DOCTYPE html>
<html>
<head>
  <script src="https://code.jquery.com/jquery-3.7.0.js"
          integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
          crossorigin="anonymous"></script>
</head>
<body>
  <p>Exchange Rate: <span id="exchangeRate"></span></p>

  <script>
    $(document).ready(function() {
      // Fetch the exchange rate data
      $.ajax({
        url: "https://openexchangerates.org/api/latest.json",
        dataType: "jsonp",
        data: {
          app_id: "YOUR_APP_ID", // Replace with your Open Exchange Rates app ID
          base: "USD",
          symbols: "KRW"
        },
        success: function(data) {
          // Retrieve the exchange rate for KRW
          const exchangeRate = data.rates.KRW;

          // Display the exchange rate on the webpage
          $("#exchangeRate").text(exchangeRate);
        }
      });
    });
  </script>
</body>
</html>

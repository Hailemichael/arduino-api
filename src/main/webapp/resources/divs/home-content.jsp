<!-- Example row of columns -->
<div class="container">
  <div class="row main-content-text">
    <div class="col-md-4">
      <h2>Water Plants</h2>
      <p>Water plants in one click.</p>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/water" />'">Water</button>
      <div class="alert alert-success collapse">
        <strong>Success!</strong> You have successfully watered your plants :).
      </div>
      <div class="alert alert-danger collapse">
        <strong>Failed!</strong> Problem occurred while watering plants.
      </div>
    </div>
    <div class="col-md-4">
      <h2>Control Garage Door</h2>
      <p>Open and close your garage door</p>
      <p>
        <button class="btn btn-danger"
          onclick="location.href='<c:url value="/opengarage" />'">Open</button>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/closegarage" />'">Close</button>
          <div id = "alert_placeholder"></div>
      </p>
    </div>
    <div class="col-md-4">
      <h2>Control Yourself</h2>
      <p>That is a little bit scary´\_(/)_/´.</p>
      <p>
        <button class="btn btn-info"
          onclick="location.href='<c:url value="/" />'">Think?</button>
        <button class="btn btn-primary"
          onclick="location.href='<c:url value="/" />'">Ready</button>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/" />'">Work</button>
        <button class="btn btn-danger"
          onclick="location.href='<c:url value="/" />'">Sleep</button>
      </p>
    </div>
  </div>
</div>

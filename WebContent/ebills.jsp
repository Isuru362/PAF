<%@page import="model.Ebill"%> <%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Bill Details</title>
    <link rel="stylesheet" href="Views/bootstrap.min.css" />
    <script src="Components/jquery-3.6.0.min.js"></script>
    <script src="Components/ebills.js"></script>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-6">
          <h1>Bill Details</h1>
          <form id="formEbill" name="formEbill">
            Bill ID:
            <input
              id="ebillid"
              name="ebillid"
              type="text"
              class="form-control form-control-sm"
            />
            <br />
            Bill ACC no.:
            <input
              id="accno"
              name="accno"
              type="text"
              class="form-control form-control-sm"
            />
            <br />
            Bill Amount:
            <input
              id="amount"
              name="amount"
              type="text"
              class="form-control form-control-sm"
            />
            <br />
            Start Date:
            <input
              id="startdate"
              name="startdate"
              type="text"
              class="form-control form-control-sm"
            />
            <br />
            End Date:
            <input
              id="enddate"
              name="enddate"
              type="text"
              class="form-control form-control-sm"
            />
            <br />
            Reference No:
            <input
              id="refno"
              name="refno"
              type="text"
              class="form-control form-control-sm"
            />
            <br />

            <input
              id="btnSave"
              name="btnSave"
              type="button"
              value="Save"
              class="btn btn-primary"
            />
            <input
              type="hidden"
              id="hidEbillIDSave"
              name="hidEbillIDSave"
              value=""
            />
          </form>
          <div id="alertSuccess" class="alert alert-success"></div>
          <div id="alertError" class="alert alert-danger"></div>
          <br />
          <div id="divEbillsGrid">
            <% Ebill ebillObj = new Ebill(); out.print(ebillObj.readEbills());
            %>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

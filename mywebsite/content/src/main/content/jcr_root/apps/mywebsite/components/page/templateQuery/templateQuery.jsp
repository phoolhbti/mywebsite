<%@include file="/apps/mywebsite/global.jsp"%>
<cq:include script="/libs/wcm/core/components/init/init.jsp" />
<cq:includeClientLib categories="jcrtemplate" />
<html>
<head>
<meta charset="UTF-8">
<title>Adobe CQ Persist Page</title>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<p class="logo">Adobe CQ JCR Customer Persist/Query Application</p>
		</div>
		<div class="content">
			<div class="main">
				<h1>CQ Data Persist Example</h1>

				<form name="signup" id="signup">
					<table>
						<tr>
							<td><label for="first">First Name:</label></td>
							<td><input type="first" id="first" name="first" value="" />
							</td>
						</tr>
						<tr>
							<td><label for="last">Last Name:</label></td>
							<td><input type="last" id="last" name="last" value="" /></td>
						</tr>
						<tr>
							<td><label for="address">Address:</label></td>
							<td><input type="address" id="address" name="address"
								value="" /></td>
						</tr>
						<tr>
							<td><label for="description">Description:</label></td>
							<td><select id="description">
									<option>Active Customer</option>
									<option>Past Customer</option>
							</select></td>
						</tr>
						<tr>
							<td><label for="custId">Customer Id:</label></td>
							<td><input type="custId" id="custId" name="custId" value=""
								readonly="readonly" /></td>
						</tr>

					</table>
					<div>
						<input type="button" value="Add Customer!" name="submit"
							id="submit" value="Submit">
					</div>
				</form>
			</div>
		</div>

		<div id="container">
			<form name="custdata" id="custdata">

				<h1>Query Customer Data from the AEM JCR</h1>
				<div>
					<select id="custQuery">
						<option>All Customers</option>
						<option>Active Customer</option>
						<option>Past Customer</option>
					</select>
				</div>
				<div id="dynamic"></div>
				<div class="spacer"></div>

				<div>
					<input type="button" value="Get Customers!" name="submitget"
						id="submitget" value="Submit">
				</div>
			</form>

		</div>
	</div>
	<h2>This page displays twitter feeds from @AdobeMktgCloud</h2>
	<cq:include path="par" resourceType="foundation/components/parsys" />
</body>
</html>
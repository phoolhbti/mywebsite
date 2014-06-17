jQuery(document)
		.ready(
				function() {

					var aDataSet = [ [ '', '', '', '', '' ],
							[ '', '', '', '', '' ] ];

					jQuery('#dynamic')
							.html(
									'<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>');
					jQuery('#example').dataTable({
						"aaData" : aDataSet,
						"aoColumns" : [ {
							"sTitle" : "First Name"
						}, {
							"sTitle" : "Last Name"
						}, {
							"sTitle" : "Address",
							"sClass" : "center"
						}, {
							"sTitle" : "Description",
							"sClass" : "center"
						} ]
					});

					jQuery('body').hide().fadeIn(5000);

					jQuery('#submit').click(
							function() {
								var failure = function(err) {
									// $(".main").unmask();
									alert("Unable to retrive data " + err);

								};

								// Get the user-defined values to persist in the
								// database
								var myFirst = jQuery('#first').val();
								var myLast = jQuery('#last').val();
								var myDescription = jQuery('#description')
										.val();
								var myAddress = jQuery('#address').val();

								var url = location.pathname.replace(".html",
										"/_jcr_content.persist.json")
										+ "?first="
										+ myFirst
										+ "&last="
										+ myLast
										+ "&desc="
										+ myDescription
										+ "&phone=" + myAddress;

								// $(".main").mask("Saving Data...");

								jQuery.ajax(url, {
									dataType : "text",
									success : function(rawData, status, xhr) {
										var data;
										try {
											data = jQuery.parseJSON(rawData);

											// Set the fields in the forum
											jQuery('#custId').val(data.pk);

										} catch (err) {
											failure(err);
										}
									},
									error : function(xhr, status, err) {
										failure(err);
									}
								});
							});

					// Get customer data -- called when the submitget button is
					// clicked
					// this method populates the data grid with data retrieved
					// from the //Adobe CQ JCR
					jQuery('#submitget')
							.click(
									function() {
										var failure = function(err) {
											alert("Unable to retrive data "
													+ err);
										};

										// Get the query filter value from drop
										// down control
										var filter = jQuery('#custQuery').val();
										alert("filter---" + filter);

										var url = location.pathname.replace(
												".html",
												"/_jcr_content.query.json")
												+ "?filter=" + filter;

										jQuery
												.ajax(
														url,
														{
															dataType : "text",
															success : function(
																	rawData,
																	status, xhr) {
																var data;
																try {
																	data = jQuery
																			.parseJSON(rawData);
																	alert("data--"
																			+ data);

																	// Set the
																	// fields in
																	// the forum
																	var myXML = data.xml;
																	alert("xml is"
																			+ myXML);
																	var loopIndex = 0;

																	// Reference
																	// the data
																	// grid,
																	// clear it,
																	// and add
																	// new
																	// records
																	// queried
																	// from the
																	// Adobe CQ
																	// JCR
																	var oTable = jQuery(
																			'#example')
																			.dataTable();
																	oTable
																			.fnClearTable(true);

																	// Loop
																	// through
																	// this
																	// function
																	// for each
																	// Customer
																	// element
																	// in the
																	// returned
																	// XML
																	jQuery(
																			myXML)
																			.find(
																					'Customer')
																			.each(
																					function() {

																						var $field = jQuery(this);
																						var firstName = $field
																								.find(
																										'First')
																								.text();

																						var lastName = $field
																								.find(
																										'Last')
																								.text();
																						var Description = $field
																								.find(
																										'Description')
																								.text();
																						var Address = $field
																								.find(
																										'Address')
																								.text();

																						// Set
																						// the
																						// new
																						// data
																						oTable
																								.fnAddData([
																										firstName,
																										lastName,
																										Address,
																										Description, ]);

																					});

																} catch (err) {
																	failure(err);
																}
															},
															error : function(
																	xhr,
																	status, err) {
																failure(err);
															}
														});
									});

				}); // end ready


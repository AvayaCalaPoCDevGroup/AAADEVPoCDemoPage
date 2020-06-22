<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<mt:masterpage title="Demos">
	<jsp:attribute name="content">
		<div id="container">
		    <div id="demosContainer">
		
		        <div class="ant-row vertical-spacing">
		            <div
								class="ant-col ant-col-xs-24 ant-col-sm-12 ant-col-xl-6">
								<span
									class="ant-input-search ant-input-search-enter-button ant-input-group-wrapper"><span
									class="ant-input-wrapper ant-input-group">
									<span
										class="ant-input-search ant-input-search-enter-button ant-input-affix-wrapper">
										<input placeholder="Search Demo" class="ant-input" type="text" id="text_searchDemo"
											value="">
											<span class="ant-input-suffix"></span>
											</span>
											<span class="ant-input-group-addon" >
												<button
													type="button" onclick="filterDemos();"
													class="ant-btn ant-input-search-button ant-btn-primary" >
												<i aria-label="icon: search" class="anticon anticon-search">
												<svg
														viewBox="64 64 896 896" focusable="false" class=""
														data-icon="search" width="1em" height="1em"
														fill="currentColor" aria-hidden="true">
		                                        <path
															d="M909.6 854.5L649.9 594.8C690.2 542.7 712 479 712 412c0-80.2-31.3-155.4-87.9-212.1-56.6-56.7-132-87.9-212.1-87.9s-155.5 31.3-212.1 87.9C143.2 256.5 112 331.8 112 412c0 80.1 31.3 155.5 87.9 212.1C256.5 680.8 331.8 712 412 712c67 0 130.6-21.8 182.7-62l259.7 259.6a8.2 8.2 0 0 0 11.6 0l43.6-43.5a8.2 8.2 0 0 0 0-11.6zM570.4 570.4C528 612.7 471.8 636 412 636s-116-23.3-158.4-65.6C211.3 528 188 471.8 188 412s23.3-116.1 65.6-158.4C296 211.3 352.2 188 412 188s116.1 23.2 158.4 65.6S636 352.2 636 412s-23.3 116.1-65.6 158.4z">
		                                        </path>
		                                    </svg></i>
											</button></span>
								</span></span>
							</div>
		        </div>
		
		        <!-- Aqui empieza la lista de demos -->
		
		        <!-- Aqui Termina empieza la lista de demos -->
		
		    </div>
		</div>
		<script src="Demos/js/mainSolutions.js"></script>
		<script src="Demos/js/renderdemosUtils.js"></script>
	</jsp:attribute>
</mt:masterpage>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<meta charset="utf-8" />
	<!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<title></title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link href="style.css" rel="stylesheet">
</head>

<body>

<div class="wrapper">

	<header class="header">
		<div>???????? ???i????? ?????? ????'????</div> 
	</header><!-- .header-->

	<div class="middle">

		<div class="container">
			<main class="content">
                            <h:outputLabel style="font-weight: bolder" for="fn" value ="Name:"/>
                            <h:outputText value="${contact.firstName}" id="fn" />
                            <br/>
                            <h:outputLabel style="font-weight: bolder" for="ln" value ="Surname:"/>
                            <h:outputText value="${contact.lastName}" id="ln" />
                            <br/>
                            <h:outputLabel style="font-weight: bolder" for="pn" value ="Phone:"/>
                            <h:outputText value="${contact.phone}" id="pn" />
                            <br/>
                            <h:outputLabel style="font-weight: bolder" for="em" value ="email:"/>
                            <h:outputText value="${contact.phone}" id="em" />
                            <br/>
			</main><!-- .content -->
		</div><!-- .container-->

		<aside class="left-sidebar">
                    <h:form>
                        <h:commandLink value="Hobbies" action="hobbies"/>
                        <br/>
                        <h:commandLink value="Friends" action="friends"/>
                    </h:form>
		</aside><!-- .left-sidebar -->

	</div><!-- .middle-->

</div><!-- .wrapper -->

<footer class="footer">
	<div>author Oksana Siniaieva</div> 
</footer><!-- .footer -->

</body>
</html>